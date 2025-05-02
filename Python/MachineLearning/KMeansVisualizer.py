import pandas as pd
import numpy as np
import matplotlib.pyplot as plt
from sklearn.cluster import KMeans
from sklearn.preprocessing import MinMaxScaler
from ace_tools import display_dataframe_to_user

# 1. Carregar dados com rótulo original
df = pd.read_csv("C:/Users/kino1/Desktop/Projects/MyPrograms/Python/MachineLearning/Iris_preprocessed.csv")

# 2. Executar KMeans exatamente como em kmeans.py
X = df.select_dtypes(include=[np.number]).values
X = MinMaxScaler().fit_transform(X)    # reescala (já está 0‑1, mas mantemos)

kmeans = KMeans(n_clusters=3, init="k-means++", n_init=10, random_state=42)
clusters = kmeans.fit_predict(X)
df["cluster"] = clusters

# 3. Mapear cada cluster para a espécie dominante
dominant_species = (
    df.groupby("cluster")["class"]
      .agg(lambda x: x.value_counts().idxmax())
      .to_dict()
)

# 4. Identificar instâncias mal agrupadas
df["pred_species"] = df["cluster"].map(dominant_species)
df["misclassified"] = df["class"] != df["pred_species"]

mis_df = df[df["misclassified"]].copy()
display_dataframe_to_user("Instâncias_mal_agrupadas", mis_df.head(10))

print(f"Total de instâncias: {len(df)}")
print(f"Instâncias fora do cluster correto: {mis_df.shape[0]}")

# 5. Plot: duas primeiras features normalizadas
plt.figure()
for c in sorted(df["cluster"].unique()):
    idx = df["cluster"] == c
    plt.scatter(X[idx, 0], X[idx, 1], label=f"Cluster {c}", s=40)

# sobrepor mal classificados
plt.scatter(
    X[df["misclassified"], 0],
    X[df["misclassified"], 1],
    marker="x",
    s=80,
    linewidths=1.5,
    label="Mal agrupado"
)

plt.xlabel("Feature 1 (esc. 0‑1)")
plt.ylabel("Feature 2 (esc. 0‑1)")
plt.title("KMeans (k=3) – pontos mal agrupados marcados com X")
plt.legend()
plt.tight_layout()
plt.show()
