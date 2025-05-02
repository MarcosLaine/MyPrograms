"""
calinski_harabasz.py
--------------------
Cálculo do índice Calinski–Harabasz (CH) para o clusterizador K‑means
no conjunto Iris (já normalizado e sem outliers).
"""

import pandas as pd
import numpy as np
from sklearn.cluster import KMeans
from sklearn.metrics import calinski_harabasz_score

# --------------------------------------------------------------------
# 1. Dados
# --------------------------------------------------------------------
DATA_PATH = "C:/Users/kino1/Desktop/Projects/MyPrograms/Python/MachineLearning/Iris_preprocessed.csv"      # ajuste se necessário
df = pd.read_csv(DATA_PATH)

# Seleciona apenas colunas numéricas (já estão normalizadas 0‑1)
X = df.select_dtypes(include=[np.number]).values
n_samples, n_features = X.shape

# --------------------------------------------------------------------
# 2. K‑means
# --------------------------------------------------------------------
k = 3                     # número de clusters escolhido antes
kmeans = KMeans(
    n_clusters=k,
    init="k-means++",
    n_init=10,
    random_state=42
).fit(X)
labels = kmeans.labels_
centroids = kmeans.cluster_centers_

# --------------------------------------------------------------------
# 3. CH via scikit‑learn (função pronta)
# --------------------------------------------------------------------
ch_sklearn = calinski_harabasz_score(X, labels)

# --------------------------------------------------------------------
# 4. CH implementado manualmente
# --------------------------------------------------------------------
# média global dos dados
global_mean = np.mean(X, axis=0)

# Within‑cluster scatter (tr(W)) e Between‑cluster scatter (tr(B))
tr_W = 0.0
tr_B = 0.0

for c in range(k):
    # índices das amostras do cluster c
    idx = np.where(labels == c)[0]
    X_c = X[idx]
    n_c = len(idx)

    # centroide do cluster c
    mu_c = centroids[c]

    # W: soma dos quadrados das distâncias dentro do cluster
    tr_W += np.sum((X_c - mu_c) ** 2)

    # B: contribuição do cluster para a variância entre clusters
    tr_B += n_c * np.sum((mu_c - global_mean) ** 2)

# fórmula de Calinski–Harabasz
ch_manual = (tr_B / (k - 1)) / (tr_W / (n_samples - k))

# --------------------------------------------------------------------
# 5. Resultados
# --------------------------------------------------------------------
print(f"Calinski–Harabasz (scikit‑learn) : {ch_sklearn:.3f}")
print(f"Calinski–Harabasz (manual)       : {ch_manual:.3f}")

# Se quiser avaliar vários k em um laço:
# for k in range(2, 11):
#     ...

