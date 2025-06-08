# =============================================================================
# ANÃLISE PREDITIVA DO TITANIC - PIPELINE COMPLETO
# Este script implementa uma anÃ¡lise completa dos dados do Titanic, incluindo:
# - PrÃ©-processamento e engenharia de features
# - ClassificaÃ§Ã£o supervisionada (Random Forest e Decision Tree)
# - ClusterizaÃ§Ã£o (KMeans)
# - AnÃ¡lise de regras de associaÃ§Ã£o
# =============================================================================

# 1. IMPORTAÃ‡Ã•ES E CARREGAMENTO
# Bibliotecas para manipulaÃ§Ã£o de dados e visualizaÃ§Ã£o
import pandas as pd
import numpy as np
import matplotlib.pyplot as plt
import seaborn as sns

# Bibliotecas para machine learning
from sklearn.model_selection import train_test_split
from sklearn.preprocessing import StandardScaler, LabelEncoder, OneHotEncoder
from sklearn.ensemble import RandomForestClassifier
from sklearn.tree import DecisionTreeClassifier
from sklearn.metrics import classification_report
from sklearn.cluster import KMeans
from sklearn.decomposition import PCA
from mlxtend.frequent_patterns import apriori, association_rules
from mlxtend.preprocessing import TransactionEncoder

# 1.1 Carregamento dos dados
# Carrega os datasets de treino e teste
train = pd.read_csv("train.csv")
test = pd.read_csv("test.csv")

# =============================================================================
# 2. ANÃLISE E PRÃ‰-PROCESSAMENTO
# =============================================================================

# 2.1 Engenharia de Features
# Extrai o tÃ­tulo do nome do passageiro (Mr., Mrs., etc)
train['Title'] = train['Name'].str.extract(' ([A-Za-z]+)\.', expand=False)
# Calcula o tamanho da famÃ­lia (incluindo o prÃ³prio passageiro)
train['FamilySize'] = train['SibSp'] + train['Parch'] + 1
# Cria uma feature binÃ¡ria indicando se o passageiro estÃ¡ sozinho
train['IsAlone'] = (train['FamilySize'] == 1).astype(int)

# 2.2 Tratamento de Valores Nulos
# Preenche valores nulos com estatÃ­sticas apropriadas
train['Age'].fillna(train['Age'].median(), inplace=True)  # Idade com mediana
train['Embarked'].fillna(train['Embarked'].mode()[0], inplace=True)  # Porto com moda
train['Fare'].fillna(train['Fare'].median(), inplace=True)  # Tarifa com mediana

# 2.3 CodificaÃ§Ã£o de VariÃ¡veis CategÃ³ricas
# Converte variÃ¡veis categÃ³ricas em numÃ©ricas usando Label Encoding
label = LabelEncoder()
train['Sex'] = label.fit_transform(train['Sex'])
train['Embarked'] = label.fit_transform(train['Embarked'])
train['Title'] = label.fit_transform(train['Title'])

# 2.4 SeleÃ§Ã£o e PreparaÃ§Ã£o das Features
# Define as features que serÃ£o utilizadas no modelo
features = ['Pclass', 'Sex', 'Age', 'Fare', 'Embarked', 'FamilySize', 'IsAlone', 'Title']
X = train[features]
y = train['Survived']

# 2.5 PadronizaÃ§Ã£o dos Dados
# Normaliza as features para terem mÃ©dia 0 e desvio padrÃ£o 1
scaler = StandardScaler()
X_scaled = scaler.fit_transform(X)

# =============================================================================
# 3. CLASSIFICAÃ‡ÃƒO SUPERVISIONADA
# =============================================================================

# 3.1 DivisÃ£o dos Dados
# Separa os dados em conjuntos de treino e validaÃ§Ã£o
X_train, X_val, y_train, y_val = train_test_split(X_scaled, y, test_size=0.2, random_state=42)

# 3.2 Treinamento e AvaliaÃ§Ã£o dos Modelos
# Random Forest Classifier
rf = RandomForestClassifier()
rf.fit(X_train, y_train)
y_pred_rf = rf.predict(X_val)

# Decision Tree Classifier
dt = DecisionTreeClassifier()
dt.fit(X_train, y_train)
y_pred_dt = dt.predict(X_val)

# 3.3 AvaliaÃ§Ã£o dos Resultados
print("Random Forest:\n", classification_report(y_val, y_pred_rf))
print("Decision Tree:\n", classification_report(y_val, y_pred_dt))

# =============================================================================
# 4. CLUSTERIZAÃ‡ÃƒO
# =============================================================================

# 4.1 AplicaÃ§Ã£o do KMeans
# Agrupa os passageiros em 3 clusters
kmeans = KMeans(n_clusters=3, random_state=0)
kmeans.fit(X_scaled)
clusters = kmeans.labels_

# 4.2 ReduÃ§Ã£o de Dimensionalidade com PCA
# Reduz as features para 2 dimensÃµes para visualizaÃ§Ã£o
pca = PCA(n_components=2)
pca_data = pca.fit_transform(X_scaled)

# 4.3 VisualizaÃ§Ã£o dos Clusters
plt.figure(figsize=(8,6))
plt.scatter(pca_data[:,0], pca_data[:,1], c=clusters, cmap='viridis')
plt.title('Clusters de Passageiros (KMeans + PCA)')
plt.xlabel('PC1')
plt.ylabel('PC2')
plt.show()

# =============================================================================
# 5. REGRAS DE ASSOCIAÃ‡ÃƒO
# =============================================================================

# 5.1 PreparaÃ§Ã£o dos Dados
# Seleciona e prepara as features para anÃ¡lise de associaÃ§Ã£o
assoc_data = train[['Sex', 'Pclass', 'Embarked', 'Survived']].copy()
assoc_data['Sex'] = assoc_data['Sex'].map({1: 'male', 0: 'female'})
assoc_data['Survived'] = assoc_data['Survived'].map({1: 'survived', 0: 'died'})

# 5.2 AplicaÃ§Ã£o do Algoritmo Apriori
# Converte os dados para o formato necessÃ¡rio
records = assoc_data.astype(str).values.tolist()
te = TransactionEncoder()
te_ary = te.fit(records).transform(records)
df_assoc = pd.DataFrame(te_ary, columns=te.columns_)

# 5.3 GeraÃ§Ã£o e VisualizaÃ§Ã£o das Regras
# Encontra padrÃµes frequentes e gera regras de associaÃ§Ã£o
frequent = apriori(df_assoc, min_support=0.1, use_colnames=True)
rules = association_rules(frequent, metric="lift", min_threshold=1.0)

# Exibe as 3 regras mais relevantes
print(rules[['antecedents','consequents','support','confidence','lift']].head(3))
