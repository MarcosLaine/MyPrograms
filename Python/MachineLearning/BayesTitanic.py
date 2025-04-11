# Importações necessárias
import pandas as pd
from sklearn.model_selection import train_test_split
from sklearn.preprocessing import LabelEncoder
from sklearn.metrics import accuracy_score, classification_report
from sklearn.ensemble import RandomForestClassifier
from sklearn.tree import DecisionTreeClassifier
from skopt import BayesSearchCV

# Carregar os dados
treino = pd.read_csv('C:/Users/kino1/Desktop/Projects/MyPrograms/Python/MachineLearning/train.csv')
teste = pd.read_csv('C:/Users/kino1/Desktop/Projects/MyPrograms/Python/MachineLearning/test.csv')

# Pré-processamento dos dados
def preprocessamento(df):
    df = df.drop(['PassengerId', 'Name', 'Ticket', 'Cabin'], axis=1)
    df['Age'].fillna(df['Age'].median(), inplace=True)
    df['Fare'].fillna(df['Fare'].median(), inplace=True)
    df['Embarked'].fillna(df['Embarked'].mode()[0], inplace=True)
    df['Sex'] = LabelEncoder().fit_transform(df['Sex'])
    df['Embarked'] = LabelEncoder().fit_transform(df['Embarked'])
    return df

# Split training data
train_processed = preprocessamento(treino)
X_train = train_processed.drop('Survived', axis=1)
y_train = train_processed['Survived']

# Process test data (without trying to drop 'Survived' column)
test_processed = preprocessamento(teste)
X_test = test_processed  # Don't try to drop 'Survived' as it doesn't exist
y_test = None  # We don't have test labels in this case

# Definir e otimizar modelos usando BayesSearchCV
param_rf = {
    'n_estimators': (50, 200),
    'max_depth': (3, 15),
    'min_samples_split': (2, 10)
}

param_dt = {
    'max_depth': (3, 15),
    'min_samples_split': (2, 10)
}

# Otimizando Random Forest
bayes_rf = BayesSearchCV(RandomForestClassifier(), param_rf, n_iter=30, cv=5, scoring='accuracy')
bayes_rf.fit(X_train, y_train)

# Otimizando Árvore de Decisão
bayes_dt = BayesSearchCV(DecisionTreeClassifier(), param_dt, n_iter=30, cv=5, scoring='accuracy')
bayes_dt.fit(X_train, y_train)

# Modify evaluation section to only make predictions (since we don't have test labels)
y_pred_rf = bayes_rf.predict(X_test)
y_pred_dt = bayes_dt.predict(X_test)

# Print model performance on training data instead
print("Desempenho Random Forest (dados de treino):")
print(classification_report(y_train, bayes_rf.predict(X_train)))

print("\nDesempenho Árvore de Decisão (dados de treino):")
print(classification_report(y_train, bayes_dt.predict(X_train)))

# Importância dos atributos
importance_rf = bayes_rf.best_estimator_.feature_importances_
importance_dt = bayes_dt.best_estimator_.feature_importances_

atributos = X_train.columns

print("Atributos mais importantes (Random Forest):")
print(sorted(zip(atributos, importance_rf), key=lambda x: x[1], reverse=True))

print("Atributos mais importantes (Árvore de Decisão):")
print(sorted(zip(atributos, importance_dt), key=lambda x: x[1], reverse=True))
