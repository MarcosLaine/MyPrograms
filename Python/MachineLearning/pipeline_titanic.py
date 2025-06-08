# 1. IMPORTAÇÕES E CARREGAMENTO
# Bibliotecas para manipulação de dados e visualização
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
from apyori import apriori
from mlxtend.preprocessing import TransactionEncoder

# 1.1 Carregamento dos dados
# Carrega os datasets de treino e teste
train = pd.read_csv(r"train.csv")
test = pd.read_csv(r"test.csv")

# =============================================================================
# 2. ANÁLISE E PRÉ-PROCESSAMENTO
# =============================================================================

# 2.1 Engenharia de Features
# Extrai o título do nome do passageiro (Mr., Mrs., etc)
train['Title'] = train['Name'].str.extract(r' ([A-Za-z]+)\.', expand=False)
# Calcula o tamanho da família (incluindo o próprio passageiro)
train['FamilySize'] = train['SibSp'] + train['Parch'] + 1
# Cria uma feature binária indicando se o passageiro está sozinho
train['IsAlone'] = (train['FamilySize'] == 1).astype(int)

# 2.2 Tratamento de Valores Nulos
# Preenche valores nulos com estatísticas apropriadas
train['Age'].fillna(train['Age'].median(), inplace=True)  # Idade com mediana
train['Embarked'].fillna(train['Embarked'].mode()[0], inplace=True)  # Porto com moda
train['Fare'].fillna(train['Fare'].median(), inplace=True)  # Tarifa com mediana

# 2.3 Codificação de Variáveis Categóricas
# Converte variáveis categóricas em numéricas usando Label Encoding
label = LabelEncoder()
train['Sex'] = label.fit_transform(train['Sex'])
train['Embarked'] = label.fit_transform(train['Embarked'])
train['Title'] = label.fit_transform(train['Title'])

# 2.4 Seleção e Preparação das Features
# Define as features que serão utilizadas no modelo
features = ['Pclass', 'Sex', 'Age', 'Fare', 'Embarked', 'FamilySize', 'IsAlone', 'Title']
X = train[features]
y = train['Survived']

# 2.5 Padronização dos Dados
# Normaliza as features para terem média 0 e desvio padrão 1
scaler = StandardScaler()
X_scaled = scaler.fit_transform(X)

# =============================================================================
# 3. CLASSIFICAÇÃO SUPERVISIONADA
# =============================================================================

# 3.1 Divisão dos Dados
# Separa os dados em conjuntos de treino e validação
X_train, X_val, y_train, y_val = train_test_split(X_scaled, y, test_size=0.2, random_state=42)

# 3.2 Treinamento e Avaliação dos Modelos
# Random Forest Classifier
# Random Forest Classifier with hyperparameters
rf = RandomForestClassifier(n_estimators=100, max_depth=None, min_samples_split=2, random_state=42)
rf.fit(X_train, y_train)
y_pred_rf = rf.predict(X_val)

# Decision Tree Classifier with hyperparameters
dt = DecisionTreeClassifier(max_depth=None, min_samples_split=2, random_state=42)
dt.fit(X_train, y_train)
y_pred_dt = dt.predict(X_val)

# 3.3 Avaliação dos Resultados
print("Random Forest:\n", classification_report(y_val, y_pred_rf))
print("Decision Tree:\n", classification_report(y_val, y_pred_dt))

# =============================================================================
# 4. CLUSTERIZAÇÃO
# =============================================================================

# 4.1 Aplicação do KMeans
# Agrupa os passageiros em 3 clusters
kmeans = KMeans(n_clusters=3, random_state=0)
kmeans.fit(X_scaled)
clusters = kmeans.labels_

# 4.2 Redução de Dimensionalidade com PCA
# Reduz as features para 2 dimensões para visualização
pca = PCA(n_components=2)
pca_data = pca.fit_transform(X_scaled)

# 4.3 Visualização dos Clusters
plt.figure(figsize=(8,6))
plt.scatter(pca_data[:,0], pca_data[:,1], c=clusters, cmap='viridis')
plt.title('Clusters de Passageiros (KMeans + PCA)')
plt.xlabel('PC1')
plt.ylabel('PC2')
plt.show()

# =============================================================================
# 5. REGRAS DE ASSOCIAÇÃO
# =============================================================================

# 5.1 Preparação dos Dados
# Seleciona e prepara as features para análise de associação
assoc_data = train[['Sex', 'Pclass', 'Embarked', 'Survived']].copy()
assoc_data['Sex'] = assoc_data['Sex'].map({1: 'male', 0: 'female'})
assoc_data['Survived'] = assoc_data['Survived'].map({1: 'survived', 0: 'died'})

# 5.2 Aplicação do Algoritmo Apriori
# Converte os dados para o formato necessário
records = assoc_data.astype(str).values.tolist()

# 5.3 Geração e Visualização das Regras
# Encontra padrões frequentes e gera regras de associação
association_rules = list(apriori(records, 
                               min_support=0.1,    # Suporte mínimo
                               min_confidence=0.5, # Confiança mínima
                               min_lift=1.0,       # Lift mínimo
                               min_length=2))      # Tamanho mínimo do conjunto

# Exibe as regras encontradas
print("\nRegras de Associação Encontradas:")
for item in association_rules:
    # Extrai os itens da regra
    pair = item[0]
    items = [x for x in pair]
    
    # Extrai as métricas
    support = item[1]
    confidence = item[2][0][2]
    lift = item[2][0][3]
    
    print(f"\nRegra: {items}")
    print(f"Suporte: {support:.3f}")
    print(f"Confiança: {confidence:.3f}")
    print(f"Lift: {lift:.3f}")
