import numpy as np
import random

# Funções de ativação e suas derivadas
ACTIVATIONS = {
    'sigmoid': (
        lambda x: 1 / (1 + np.exp(-x)),
        lambda x: (1 / (1 + np.exp(-x))) * (1 - (1 / (1 + np.exp(-x))))
    ),
    'tanh': (
        lambda x: np.tanh(x),
        lambda x: 1 - np.tanh(x) ** 2
    ),
    'relu': (
        lambda x: np.maximum(0, x),
        lambda x: (x > 0).astype(float)
    )
}

def generate_data(n, op):
    X, y = [], []
    def dfs(prefix):
        if len(prefix) == n:
            X.append(prefix.copy())
            if op == 'AND':
                y.append([1 if all(prefix) else 0])
            elif op == 'OR':
                y.append([1 if any(prefix) else 0])
            elif op == 'XOR':
                if n != 2:
                    raise ValueError("XOR só suportado para n=2")
                y.append([1 if prefix[0] != prefix[1] else 0])
            else:
                raise ValueError(f"Operação '{op}' inválida")
            return
        for bit in [0, 1]:
            prefix.append(bit)
            dfs(prefix)
            prefix.pop()
    dfs([])
    return np.array(X), np.array(y)

class MLP:
    def __init__(self, n_inputs, n_hidden, activation='sigmoid', learning_rate=0.1, use_bias=True):
        self.n_inputs = n_inputs
        self.n_hidden = n_hidden
        self.lr = learning_rate
        self.use_bias = use_bias
        self.act_name = activation
        self.act, self.act_deriv = ACTIVATIONS[activation]
        # Inicialização dos pesos
        self.W1 = np.random.randn(n_inputs, n_hidden) * 0.1
        self.W2 = np.random.randn(n_hidden, 1) * 0.1
        if use_bias:
            self.b1 = np.zeros((1, n_hidden))
            self.b2 = np.zeros((1, 1))
        else:
            self.b1 = None
            self.b2 = None

    def forward(self, X):
        self.z1 = X @ self.W1
        if self.use_bias:
            self.z1 += self.b1
        self.a1 = self.act(self.z1)
        self.z2 = self.a1 @ self.W2
        if self.use_bias:
            self.z2 += self.b2
        self.a2 = self.act(self.z2)
        return self.a2

    def backward(self, X, y):
        m = X.shape[0]
        # Saída
        dz2 = (self.a2 - y) * self.act_deriv(self.z2)
        dW2 = self.a1.T @ dz2 / m
        db2 = np.sum(dz2, axis=0, keepdims=True) / m if self.use_bias else 0
        # Oculta
        dz1 = (dz2 @ self.W2.T) * self.act_deriv(self.z1)
        dW1 = X.T @ dz1 / m
        db1 = np.sum(dz1, axis=0, keepdims=True) / m if self.use_bias else 0
        # Atualização
        self.W2 -= self.lr * dW2
        self.W1 -= self.lr * dW1
        if self.use_bias:
            self.b2 -= self.lr * db2
            self.b1 -= self.lr * db1

    def fit(self, X, y, epochs=1000, verbose=False):
        for epoch in range(epochs):
            self.forward(X)
            self.backward(X, y)
            if verbose and (epoch % (epochs // 10) == 0 or epoch == epochs-1):
                loss = np.mean((self.a2 - y) ** 2)
                print(f"Epoch {epoch+1}/{epochs} - Loss: {loss:.4f}")

    def predict(self, X):
        out = self.forward(X)
        return (out >= 0.5).astype(int)

if __name__ == '__main__':
    random.seed(0)
    np.random.seed(0)
    print("Escolha a operação (AND, OR, XOR): ")
    op = input().strip().upper()
    n = int(input("Número de entradas (2 ou 10): "))
    X, y = generate_data(n, op)
    print("Função de ativação (sigmoid, tanh, relu): ")
    act = input().strip().lower()
    lr = float(input("Taxa de aprendizado (ex: 0.1): "))
    n_hidden = 4 if n == 2 else 8
    model = MLP(n_inputs=n, n_hidden=n_hidden, activation=act, learning_rate=lr, use_bias=True)
    model.fit(X, y, epochs=2000, verbose=True)
    preds = model.predict(X)
    print("Resultados:")
    for xi, tgt, pr in zip(X, y, preds):
        print(f"entrada={xi.tolist()} target={tgt[0]} pred={pr[0]}")
    acc = np.mean(preds == y)
    print(f"Acurácia: {acc:.2f}") 