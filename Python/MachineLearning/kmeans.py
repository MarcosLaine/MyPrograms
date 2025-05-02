import pandas as pd
import numpy as np
from sklearn.cluster import KMeans
from sklearn.preprocessing import StandardScaler
import matplotlib.pyplot as plt
from sklearn.metrics import silhouette_score

# Carregar os dados
dados = pd.read_csv('C:/Users/kino1/Desktop/Projects/MyPrograms/Python/MachineLearning/Iris_preprocessed.csv')

# Separar features e target
X = dados.iloc[:, :-1].values
y = dados.iloc[:, -1].values

# Normalizar os dados
scaler = StandardScaler()
X = scaler.fit_transform(X)

# Método do cotovelo para determinar o número ideal de clusters
wcss = []
for i in range(1, 11):
    kmeans = KMeans(n_clusters=i, random_state=42)
    kmeans.fit(X)
    wcss.append(kmeans.inertia_)

# Plotar o gráfico do método do cotovelo
plt.plot(range(1, 11), wcss)
plt.title('Método do Cotovelo')
plt.xlabel('Número de Clusters')
plt.ylabel('WCSS')
plt.show()

# Aplicar K-means com o número ideal de clusters (3 neste caso)
kmeans = KMeans(n_clusters=3, random_state=42)
y_kmeans = kmeans.fit_predict(X)

# Adicionar os clusters ao DataFrame
dados['Cluster'] = y_kmeans

# Exibir o DataFrame com os clusters
print("\nDataFrame com Clusters:")
print(dados)

# Calcular e exibir o score de silhueta
silhouette_avg = silhouette_score(X, y_kmeans)
print(f"\nScore de Silhueta: {silhouette_avg:.3f}")

# Visualizar os clusters
plt.scatter(X[y_kmeans == 0, 0], X[y_kmeans == 0, 1], s=100, c='red', label='Cluster 1')
plt.scatter(X[y_kmeans == 1, 0], X[y_kmeans == 1, 1], s=100, c='blue', label='Cluster 2')
plt.scatter(X[y_kmeans == 2, 0], X[y_kmeans == 2, 1], s=100, c='green', label='Cluster 3')
plt.scatter(kmeans.cluster_centers_[:, 0], kmeans.cluster_centers_[:, 1], s=300, c='yellow', label='Centroids')
plt.title('Clusters de Clientes')
plt.xlabel('Feature 1')
plt.ylabel('Feature 2')
plt.legend()
plt.show()
