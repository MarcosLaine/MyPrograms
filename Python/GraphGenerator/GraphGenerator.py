import random

# Parâmetros do grafo
n = 100
p = 0.05  # probabilidade de existência de cada aresta
edges = []

# Gerar arestas aleatórias direcionadas com peso [1,100]
for u in range(1, n+1):
    for v in range(1, n+1):
        if u != v and random.random() < p:
            w = random.randint(1, 100)
            edges.append((u, v, w))

m = len(edges)

# Escrever em arquivo
filename = 'graph_100.txt'
with open(filename, 'w') as f:
    f.write(f"{n} {m}\n")
    for u, v, w in edges:
        f.write(f"{u} {v} {w}\n")
    # Exemplo de par de origem e destino
    f.write("1 100\n")

# # Mostrar as primeiras linhas geradas
# print("Primeiras 10 linhas de graph_100.txt:")
# with open(filename) as f:
#     for _ in range(10):
#         print(f.readline().strip())
# print("...")
# print(f"Total de vértices: {n}")
# print(f"Total de arestas: {m}")
