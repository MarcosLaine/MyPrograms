import math
import csv

def calcular_entropia(arquivo, atributo):
    with open(arquivo, 'r') as file:
        leitor = csv.DictReader(file, delimiter=';')
        contagem = {}
        
        # Contar a frequência de cada valor no atributo especificado
        for linha in leitor:
            chave = linha[atributo]
            if chave not in contagem:
                contagem[chave] = 0
            contagem[chave] += 1

    total = sum(contagem.values())
    entropia = 0.0

    # Calcular a entropia usando a fórmula fornecida
    for chave in contagem:
        if contagem[chave] > 0:
            probabilidade = contagem[chave] / total
            entropia -= probabilidade * math.log(probabilidade, 2)

    return entropia, contagem, total

# Exemplo de uso
atributo_escolhido = 'SexSab'  # Altere para o atributo desejado
entropia, contagem, total = calcular_entropia('Python/data/restaurante.csv', atributo_escolhido)

# Impressão das fórmulas e resultados
print("Fórmula da Entropia: Entropia(S) = -∑(pi * log2(pi))")
print("Frequências dos valores na coluna '{}':".format(atributo_escolhido), contagem)
print(f'Total de instâncias: {total}')
print(f'Entropia: {entropia}')
    