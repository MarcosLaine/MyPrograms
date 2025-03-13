# Importação das bibliotecas necessárias
import pandas as pd
import numpy as np
import os
from sklearn.tree import DecisionTreeClassifier, plot_tree
from sklearn.model_selection import train_test_split
from sklearn.metrics import accuracy_score, confusion_matrix, precision_score, recall_score, f1_score, classification_report
import matplotlib.pyplot as plt

# Muda para o diretório onde o script está localizado
os.chdir(os.path.dirname(os.path.abspath(__file__)))

# =============================================================================
# 1. Leitura dos Dados
# =============================================================================
# Carrega os dados de treino, teste e submissão (arquivo com modelo de submissão, opcional)
train_df = pd.read_csv("train.csv")
test_df = pd.read_csv("test.csv")
gender_submission_df = pd.read_csv("gender_submission.csv")  # arquivo opcional

# Exibe as primeiras linhas do dataset de treino para conferir os dados
print("Dados de Treino:")
print(train_df.head())

# =============================================================================
# 2. Pré-processamento dos Dados
# =============================================================================
# 2.1. Tratamento de valores faltantes
# Preenche valores faltantes na coluna 'Age' com a mediana (tanto no treino quanto no teste)
train_df["Age"].fillna(train_df["Age"].median(), inplace=True)
test_df["Age"].fillna(test_df["Age"].median(), inplace=True)

# No conjunto de teste, a coluna 'Fare' pode ter valores faltantes – preenche com a mediana
test_df["Fare"].fillna(test_df["Fare"].median(), inplace=True)

# Para a coluna 'Embarked' no conjunto de treino, preenche com o valor mais frequente (moda)
train_df["Embarked"].fillna(train_df["Embarked"].mode()[0], inplace=True)

# 2.2. Conversão de variáveis categóricas em numéricas
# Converte a coluna 'Sex': mapeia "male" para 0 e "female" para 1
train_df["Sex"] = train_df["Sex"].map({"male": 0, "female": 1})
test_df["Sex"] = test_df["Sex"].map({"male": 0, "female": 1})

# Converte a coluna 'Embarked' mapeando os valores: S -> 0, C -> 1 e Q -> 2
embarked_mapping = {"S": 0, "C": 1, "Q": 2}
train_df["Embarked"] = train_df["Embarked"].map(embarked_mapping)
test_df["Embarked"] = test_df["Embarked"].map(embarked_mapping)

# =============================================================================
# 3. Seleção de Variáveis (Features) para o Modelo
# =============================================================================
# Seleciona as features consideradas importantes para o modelo
features = ["Pclass", "Sex", "Age", "SibSp", "Parch", "Fare", "Embarked"]

# Define X (variáveis independentes) e y (variável alvo) do conjunto de treino
X = train_df[features]
y = train_df["Survived"]

# =============================================================================
# 4. Divisão do Conjunto de Treino para Validação Interna
# =============================================================================
# Divide os dados de treino em conjunto de treino e validação (80% treino e 20% validação)
X_train, X_val, y_train, y_val = train_test_split(X, y, test_size=0.2, random_state=42)

# =============================================================================
# 5. Treinamento da Árvore de Decisão
# =============================================================================
# Inicializa e treina o classificador de árvore de decisão com profundidade máxima de 3
clf = DecisionTreeClassifier(max_depth=3, random_state=42)
clf.fit(X_train, y_train)

# =============================================================================
# 6. Avaliação do Modelo no Conjunto de Validação
# =============================================================================
# Realiza predições no conjunto de validação
y_val_pred = clf.predict(X_val)

# Calcula a acurácia e exibe a matriz de confusão
acc = accuracy_score(y_val, y_val_pred)
print("\nAcurácia na validação:", acc)
print("Matriz de confusão:")
print(confusion_matrix(y_val, y_val_pred))

# Calcula e exibe métricas adicionais: precisão, recall e f1-score
precision = precision_score(y_val, y_val_pred)
recall = recall_score(y_val, y_val_pred)
f1 = f1_score(y_val, y_val_pred)

print(f"\nPrecisão: {precision:.4f}")
print(f"Recall: {recall:.4f}")
print(f"F1-Score: {f1:.4f}")

# Exibe um relatório de classificação completo
print("\nRelatório de classificação completo:")
print(classification_report(y_val, y_val_pred, target_names=["Não Sobreviveu", "Sobreviveu"]))

# =============================================================================
# 7. Visualização da Árvore de Decisão
# =============================================================================
# Plota a árvore de decisão para visualização dos critérios de decisão
plt.figure(figsize=(20, 10))
plot_tree(clf, feature_names=features, class_names=["Não Sobreviveu", "Sobreviveu"], filled=True)
plt.title("Árvore de Decisão - Titanic")
plt.show()

# =============================================================================
# 8. Predição no Conjunto de Teste e Geração do Arquivo de Submissão
# =============================================================================
# Faz a predição dos rótulos para o conjunto de teste usando as mesmas features
test_pred = clf.predict(test_df[features])

# Cria o DataFrame para submissão conforme o formato exigido (PassengerId e Survived)
submission = pd.DataFrame({
    "PassengerId": test_df["PassengerId"],
    "Survived": test_pred
})

# Salva o arquivo de submissão no formato CSV
submission.to_csv("submission.csv", index=False)
print("\nArquivo de submissão gerado: submission.csv")
