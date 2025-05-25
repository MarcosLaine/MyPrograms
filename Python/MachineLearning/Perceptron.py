import matplotlib.pyplot as plt
import random

class Perceptron:
    def __init__(self, n_inputs, learning_rate=1.0, epochs=10):
        # Peso w[0] é o bias, w[1:] são pesos das entradas
        self.n_inputs = n_inputs
        self.lr = learning_rate
        self.epochs = epochs
        self.w = [0.0] * (n_inputs + 1)
        self.history = []  # histórico de pesos (incluindo bias) por época

    def _activation(self, xi):
        # soma ponderada: bias + Σ(w_i * x_i)
        total = self.w[0]  # bias
        for i in range(self.n_inputs):
            total += self.w[i+1] * xi[i]
        return total

    def fit(self, X, y):
        # treinamento online por épocas
        for epoch in range(self.epochs + 1):
            # salvar cópia do vetor de pesos atual
            self.history.append(self.w.copy())
            if epoch == self.epochs:
                break
            for xi, target in zip(X, y):
                activation = self._activation(xi)
                pred = 1 if activation >= 0 else 0
                error = target - pred
                # atualizar bias
                self.w[0] += self.lr * error
                # atualizar pesos das entradas
                for i in range(self.n_inputs):
                    self.w[i+1] += self.lr * error * xi[i]

    def predict(self, X):
        # aceita X como lista de vetores
        results = []
        for xi in X:
            activation = self._activation(xi)
            results.append(1 if activation >= 0 else 0)
        return results

    def plot_boundaries(self, X, y):
        # Apenas para 2D: desenha todas as linhas de decisão por época
        if self.n_inputs != 2:
            raise ValueError("Plot disponível apenas para 2 entradas.")
        xs = [0.0, 1.0]
        plt.figure(figsize=(6,6))
        for w in self.history:
            # w = [bias, w1, w2] -> linha w1*x + w2*y + bias = 0
            b = w[0]
            w1, w2 = w[1], w[2]
            if abs(w2) < 1e-10:  # Se w2 é muito próximo de zero
                # Linha vertical: x = -b/w1
                if abs(w1) < 1e-10:  # Se w1 também é zero
                    continue  # Pula esta linha de decisão
                x = -b/w1
                plt.axvline(x=x, linestyle='--', alpha=0.6)
            else:
                ys = [-(b + w1*x)/w2 for x in xs]
                plt.plot(xs, ys, linestyle='--', alpha=0.6)
        # plot dos pontos
        for xi, label in zip(X, y):
            marker = 'o' if label == 1 else 'x'
            plt.scatter(xi[0], xi[1], marker=marker, s=80)
        plt.xlim(-0.1, 1.1)
        plt.ylim(-0.1, 1.1)
        plt.xlabel('x1')
        plt.ylabel('x2')
        plt.title('Fronteiras de decisão por época para o problema ' + op)
        plt.show()


def generate_data(n, op):
    # gera todas as combinações de 0/1 em n dimensões recursivamente
    X, y = [], []
    def dfs(prefix):
        if len(prefix) == n:
            X.append(prefix.copy())
            if op == 'AND':
                y.append(1 if all(prefix) else 0)
            elif op == 'OR':
                y.append(1 if any(prefix) else 0)
            elif op == 'XOR':
                if n != 2:
                    raise ValueError("XOR só suportado para n=2")
                y.append(1 if prefix[0] != prefix[1] else 0)
            else:
                raise ValueError(f"Operação '{op}' inválida")
            return
        for bit in [0, 1]:
            prefix.append(bit)
            dfs(prefix)
            prefix.pop()
    dfs([])
    return X, y


def plot_xor_plane(X, y, w):
    xs = [0.0, 1.0]
    b, w1, w2 = w[0], w[1], w[2]
    plt.figure(figsize=(6,6))
    # Plotar a última linha de decisão
    if abs(w2) < 1e-10:
        if abs(w1) >= 1e-10:
            x = -b/w1
            plt.axvline(x=x, linestyle='--', color='red', label='Fronteira de decisão')
    else:
        ys = [-(b + w1*x)/w2 for x in xs]
        plt.plot(xs, ys, linestyle='--', color='red', label='Fronteira de decisão')
    # Plotar os pontos
    for xi, label in zip(X, y):
        marker = 'o' if label == 1 else 'x'
        color = 'blue' if label == 1 else 'orange'
        plt.scatter(xi[0], xi[1], marker=marker, s=100, color=color)
    plt.xlim(-0.1, 1.1)
    plt.ylim(-0.1, 1.1)
    plt.xlabel('x1')
    plt.ylabel('x2')
    plt.title('Plano do XOR e última fronteira do Perceptron')
    plt.legend()
    plt.show()


if __name__ == '__main__':
    random.seed(0)

    # Testes para n=2
    for op in ['AND', 'OR', 'XOR']:
        print(f"--- {op} (2 entradas) ---")
        X2, y2 = generate_data(2, op)
        model = Perceptron(2, learning_rate=1.0, epochs=10)
        model.fit(X2, y2)
        preds = model.predict(X2)
        for xi, tgt, pr in zip(X2, y2, preds):
            print(f"entrada={xi} target={tgt} pred={pr}")
        if op in ['AND', 'OR']:
            model.plot_boundaries(X2, y2)
        else:
            accuracy = sum(1 for i in range(len(y2)) if preds[i] == y2[i]) / len(y2)
            print(f"Acurácia XOR: {accuracy:.2f}")
            if accuracy < 1.0:
                print("O Perceptron de camada única NÃO consegue resolver o problema XOR, pois ele não é linearmente separável.\n")
                plot_xor_plane(X2, y2, model.w)

    # Testes para n=10
    print("--- Testes 10 entradas ---")
    for op in ['AND', 'OR']:
        X10, y10 = generate_data(10, op)
        model10 = Perceptron(10, learning_rate=1.0, epochs=10)
        model10.fit(X10, y10)
        print(f"{op} (10 entradas):")
        samples = {
            'all_zeros': [0]*10,
            'all_ones': [1]*10,
            'one_hot': [1] + [0]*9,
            'random': [random.randint(0,1) for _ in range(10)]
        }
        for name, sample in samples.items():
            pred = model10.predict([sample])[0]
            print(f"{name}: pred={pred}")
        print()
