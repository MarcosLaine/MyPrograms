import pandas as pd
import numpy as np
from pathlib import Path

# ---------- funções utilitárias ----------
def detect_outliers_iqr(df: pd.DataFrame, cols):
    """
    Retorna um boolean mask indicando se cada linha contém
    ao menos um outlier segundo a regra IQR (1,5×IQR).
    """
    mask = np.zeros(len(df), dtype=bool)
    for col in cols:
        q1, q3 = df[col].quantile([0.25, 0.75])
        iqr = q3 - q1
        low, high = q1 - 1.5 * iqr, q3 + 1.5 * iqr
        mask |= (df[col] < low) | (df[col] > high)
    return mask


def min_max_normalize(df: pd.DataFrame, cols):
    """Aplica Min‑Max 0‑1 in place e devolve o DataFrame."""
    mins, maxs = df[cols].min(), df[cols].max()
    df[cols] = (df[cols] - mins) / (maxs - mins)
    return df

# ---------- pipeline principal ----------
def main(csv_in: str, csv_out: str):
    df = pd.read_csv(csv_in)
    num_cols = df.select_dtypes(include=[np.number]).columns.tolist()

    # 1) outliers
    outlier_mask = detect_outliers_iqr(df, num_cols)
    print(f"Linhas identificadas como outlier: {outlier_mask.sum()}")
    df_clean = df.loc[~outlier_mask].reset_index(drop=True)

    # 2) normalização
    df_norm = min_max_normalize(df_clean.copy(), num_cols)

    # 3) salvar
    Path(csv_out).parent.mkdir(parents=True, exist_ok=True)
    df_norm.to_csv(csv_out, index=False)
    print(f"Arquivo salvo em: {csv_out}")

if __name__ == "__main__":
    main("C:/Users/kino1/Desktop/Projects/MyPrograms/Python/MachineLearning/Iris.csv", "Iris_preprocessed.csv")

### Após utilizar o códi go acima com o arquivo Iris.csv, o arquivo Iris_preprocessed.csv será criado e pronto para ser utilizado no KMeans.
