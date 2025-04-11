import pandas as pd
from apyori import apriori

# Load the data
base = pd.read_csv('C:/Users/kino1/Desktop/Projects/MyPrograms/Python/MachineLearning/MercadoSim.csv', sep=';', encoding='cp1252', header=None)

# Prepare transactions
transacoes = []
for i in range(len(base)):
    transacoes.append([str(item) for item in base.iloc[i] if pd.notna(item)])

# Run Apriori algorithm
regras = apriori(transacoes, min_support=0.3, min_confidence=0.8)
saida = list(regras)

# Initialize counters and storage for itemsets
itemsets_1 = []
itemsets_2 = []
itemsets_3 = []
num_regras = 0

# Count ItemSets and Rules
for resultado in saida:
    items = list(resultado.items)
    tamanho_itemset = len(items)
    suporte = resultado.support
    
    if tamanho_itemset == 1:
        itemsets_1.append((items, suporte))
    elif tamanho_itemset == 2:
        itemsets_2.append((items, suporte))
    elif tamanho_itemset == 3:
        itemsets_3.append((items, suporte))
    
    if len(resultado.ordered_statistics) > 0:
        num_regras += len(resultado.ordered_statistics)

# Print results with support
print("ItemSets de tamanho 1:")
for items, sup in itemsets_1:
    print(f"Items: {items}, Suporte: {sup:.3f}")

print("\nItemSets de tamanho 2:")
for items, sup in itemsets_2:
    print(f"Items: {items}, Suporte: {sup:.3f}")

print("\nItemSets de tamanho 3:")
for items, sup in itemsets_3:
    print(f"Items: {items}, Suporte: {sup:.3f}")

print(f"\nNúmero total de regras: {num_regras}")

# Extract and format rules
Antecedente = []
Consequente = []
suporte = []
confianca = []
lift = []

for resultado in saida:
    s = resultado[1]
    result_rules = resultado[2]
    for result_rule in result_rules:
        a = list(result_rule[0])
        b = list(result_rule[1])
        c = result_rule[2]  # confidence
        l = result_rule[3]
        if len(a) == 0 or len(b) == 0: continue
        Antecedente.append(a)
        Consequente.append(b)
        suporte.append(s)
        confianca.append(c)
        lift.append(l)

RegrasFinais = pd.DataFrame({
    'Antecedente': Antecedente,
    'Consequente': Consequente,
    'Suporte': suporte,
    'Confiança': confianca,
    'Lift': lift
})

# Print formatted rules with confidence
print("\nRegras de Associação:")
for i, row in RegrasFinais.iterrows():
    # Format antecedente with 'e' for multiple items
    if len(row['Antecedente']) > 1:
        antecedente = ' e '.join(row['Antecedente'])
    else:
        antecedente = row['Antecedente'][0]
    
    # Format consequente with 'e' for multiple items
    if len(row['Consequente']) > 1:
        consequente = ' e '.join(row['Consequente'])
    else:
        consequente = row['Consequente'][0]
    
    print(f"Quem leva {antecedente}, leva {consequente}")
    print(f"Confiança: {row['Confiança']:.3f}")
    print(f"Suporte: {row['Suporte']:.3f}")
    print(f"Lift: {row['Lift']:.3f}")
    print("-" * 50)

# Extraído de:
# 
# https://www.section.io/engineering-education/apriori-algorithm-in-python/
# 
# https://splunktool.com/understanding-apyoris-output


