import tkinter as tk
import random

class Node:
    def __init__(self, valor):
        self.valor = valor
        self.esquerda = None
        self.direita = None

class ArvoreBinariaVisual:
    def __init__(self, root):
        self.root = root
        self.root.title("Árvore Binária de Pesquisa")
        self.canvas = tk.Canvas(root, width=600, height=400)
        self.canvas.pack(pady=20)

        self.raiz = None
        self.passos = 0
        self.numeros_inseridos = []  # Armazenará os números inseridos para remoção posterior
        self.numeros_para_remover = []  # Lista de números que serão removidos

        # 9 números que serão inseridos
        self.numeros_inserir = random.sample(range(1, 100), 9)  
        self.numeros_para_remover = [self.numeros_inserir[i] for i in [0, 2, 4, 6]]  # Escolhe alguns números já inseridos para remoção

        self.operacoes = [
            "inserir", "inserir", "inserir", "inserir", "inserir",
            "inserir", "inserir", "inserir", "inserir", "remover", "remover", "remover",
            "inserir", "inserir", "remover", "remover"
        ]
        
        self.executar_passos()

    def desenhar_arvore(self, node, x, y, espacamento_x):
        if node is not None:
            self.canvas.create_text(x, y, text=str(node.valor), font=("Arial", 12))
            if node.esquerda:
                self.canvas.create_line(x, y, x - espacamento_x, y + 50)
                self.desenhar_arvore(node.esquerda, x - espacamento_x, y + 50, espacamento_x // 2)
            if node.direita:
                self.canvas.create_line(x, y, x + espacamento_x, y + 50)
                self.desenhar_arvore(node.direita, x + espacamento_x, y + 50, espacamento_x // 2)

    def atualizar_visualizacao(self):
        self.canvas.delete("all")
        if self.raiz:
            self.desenhar_arvore(self.raiz, 300, 50, 100)  # Começa no meio da tela

    def inserir(self, valor):
        if not self.raiz:
            self.raiz = Node(valor)
        else:
            self._inserir(self.raiz, valor)
        self.numeros_inseridos.append(valor)  # Armazena os números inseridos
        self.atualizar_visualizacao()

    def _inserir(self, node, valor):
        if valor < node.valor:
            if node.esquerda:
                self._inserir(node.esquerda, valor)
            else:
                node.esquerda = Node(valor)
        elif valor > node.valor:
            if node.direita:
                self._inserir(node.direita, valor)
            else:
                node.direita = Node(valor)

    def remover(self, valor):
        self.raiz = self._remover(self.raiz, valor)
        if valor in self.numeros_inseridos:
            self.numeros_inseridos.remove(valor)  # Remove o número da lista de inseridos
        self.atualizar_visualizacao()

    def _remover(self, node, valor):
        if node is None:
            return node

        if valor < node.valor:
            node.esquerda = self._remover(node.esquerda, valor)
        elif valor > node.valor:
            node.direita = self._remover(node.direita, valor)
        else:
            if not node.esquerda:
                return node.direita
            elif not node.direita:
                return node.esquerda
            temp = self._min_valor_node(node.direita)
            node.valor = temp.valor
            node.direita = self._remover(node.direita, temp.valor)
        return node

    def _min_valor_node(self, node):
        atual = node
        while atual.esquerda is not None:
            atual = atual.esquerda
        return atual

    def executar_passos(self):
        if self.passos < len(self.operacoes):
            operacao = self.operacoes[self.passos]
            if operacao == "inserir":
                valor = self.numeros_inserir.pop(0)
                print(f"Inserindo {valor}")
                self.inserir(valor)
            elif operacao == "remover":
                if self.numeros_para_remover:
                    valor = self.numeros_para_remover.pop(0)
                    print(f"Removendo {valor}")
                    self.remover(valor)

            self.passos += 1
            self.root.after(1000, self.executar_passos)  # Pausa de 1 segundo entre as operações

if __name__ == "__main__":
    root = tk.Tk()
    app = ArvoreBinariaVisual(root)
    root.mainloop()
