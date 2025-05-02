import pandas as pd
from apyori import apriori

# Load the data
base = pd.read_csv('C:/Users/kino1/Desktop/Projects/MyPrograms/Python/MachineLearning/MercadoSim.csv', sep=';', encoding='cp1252', header=None)

# Get all possible items (column names)
all_items = set()
for i in range(len(base)):
    items = [str(item) for item in base.iloc[i] if pd.notna(item)]
    all_items.update(items)

# Prepare transactions with negations
transacoes = []
for i in range(len(base)):
    # Get items present in this transaction
    items_presentes = set([str(item) for item in base.iloc[i] if pd.notna(item)])
    
    # Add both present items and "Nao_" prefix for absent items
    transaction = []
    for item in all_items:
        if item in items_presentes:
            transaction.append(item)
        else:
            transaction.append(f"Nao_{item}")
    
    transacoes.append(transaction)

# Run Apriori algorithm
regras = apriori(transacoes, min_support=0.3, min_confidence=0.8)
saida = list(regras)

# Initialize counters and storage for itemsets
itemsets_1 = []
itemsets_2 = []
itemsets_3 = []
num_regras = 0

# Count ItemSets and Rules with negations
for resultado in saida:
    items = list(resultado.items)
    # Only count if at least one item is negative
    if any(str(item).startswith('Nao_') for item in items):
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

# Print results with support (only for negative items)
print("ItemSets de tamanho 1 (com negações):")
for items, sup in itemsets_1:
    print(f"Items: {items}, Suporte: {sup:.3f}")

print("\nItemSets de tamanho 2 (com negações):")
for items, sup in itemsets_2:
    print(f"Items: {items}, Suporte: {sup:.3f}")

print("\nItemSets de tamanho 3 (com negações):")
for items, sup in itemsets_3:
    print(f"Items: {items}, Suporte: {sup:.3f}")

print(f"\nNúmero total de regras com negações: {num_regras}")

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
        c = result_rule[2]
        l = result_rule[3]
        # Only include rules that have at least one negative item
        if len(a) == 0 or len(b) == 0: continue
        if not (any(str(item).startswith('Nao_') for item in a) or 
                any(str(item).startswith('Nao_') for item in b)): continue
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

# Print formatted rules with confidence (only negative rules)
print("\nRegras de Associação com Negações:")
for i, row in RegrasFinais.iterrows():
    # Format antecedente with 'e' for multiple items
    antecedentes = []
    for item in row['Antecedente']:
        if item.startswith('Nao_'):
            antecedentes.append(f"não leva {item[4:]}")
        else:
            antecedentes.append(f"leva {item}")
    
    # Format consequente with 'e' for multiple items
    consequentes = []
    for item in row['Consequente']:
        if item.startswith('Nao_'):
            consequentes.append(f"não leva {item[4:]}")
        else:
            consequentes.append(f"leva {item}")
    
    antecedente_str = ' e '.join(antecedentes)
    consequente_str = ' e '.join(consequentes)
    
    print(f"Quem {antecedente_str}, {consequente_str}")
    print(f"Confiança: {row['Confiança']:.3f}")
    print(f"Suporte: {row['Suporte']:.3f}")
    print(f"Lift: {row['Lift']:.3f}")
    print("-" * 50) 