# agrupa_Iris_DBSCAN_SOM.py
import pandas as pd, numpy as np
from sklearn.preprocessing import MinMaxScaler
from sklearn.cluster import KMeans, DBSCAN
from minisom import MiniSom          # pip install minisom
from collections import Counter

df = pd.read_csv("C:/Users/kino1/Desktop/Projects/MyPrograms/Python/MachineLearning/Iris_preprocessed.csv")
X  = MinMaxScaler().fit_transform(df.select_dtypes(np.number))

# K‑means (já usado)
km_lbl = KMeans(3, init='k-means++', n_init=10, random_state=42).fit_predict(X)

# DBSCAN
db = DBSCAN(eps=0.25, min_samples=4).fit(X)
db_lbl = db.labels_                       # −1 = ruído
db_clusters = {c for c in db_lbl if c!=-1}

# SOM 1×3
som = MiniSom(1, 3, X.shape[1], sigma=0.5, learning_rate=0.5,
              random_seed=7)
som.train_random(X, 500)
som_lbl = [np.argmin(np.linalg.norm(w-som.get_weights()[0], axis=1))
           for w in X]

def resume(lbl):                     # quantidade e espécie dominante
    res = {}
    for c in sorted(set(lbl)-{-1}):
        idx = np.where(lbl==c)[0]
        sp  = df['class'].iloc[idx].value_counts().idxmax()
        res[c] = (len(idx), sp)
    return res

print("K‑means :", resume(km_lbl))
print("DBSCAN  :", resume(db_lbl))
print("SOM     :", resume(np.array(som_lbl)))
