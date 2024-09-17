import tkinter as tk
import random

class EstruturaVisual:
    def __init__(self, root, titulo, canvas, posicoes, tamanho):
        self.root = root
        self.titulo = titulo
        self.canvas = canvas
        self.posicoes = posicoes
        self.tamanho = tamanho
        self.elementos = []
        self.rectangles = []
        self.text_ids = []
        self.criar_visualizacao()

    def criar_visualizacao(self):
        self.canvas.delete("all")
        self.rectangles = []
        self.text_ids = []
        for i in range(self.tamanho):
            x0, y0, x1, y1 = self.posicoes[i]
            rect = self.canvas.create_rectangle(x0, y0, x1, y1, fill='white')
            self.rectangles.append(rect)
            text_id = self.canvas.create_text((x0 + x1) // 2, (y0 + y1) // 2, text='', font=("Arial", 12))
            self.text_ids.append(text_id)

    def atualizar_visualizacao(self):
        for i in range(self.tamanho):
            if i < len(self.elementos):
                self.canvas.itemconfig(self.rectangles[i], fill='green')
                self.canvas.itemconfig(self.text_ids[i], text=str(self.elementos[i]))
            else:
                self.canvas.itemconfig(self.rectangles[i], fill='white')
                self.canvas.itemconfig(self.text_ids[i], text='')


class PilhaVisual(EstruturaVisual):
    def push(self, valor):
        if len(self.elementos) < self.tamanho:
            self.elementos.append(valor)
            self.atualizar_visualizacao()

    def pop(self):
        if self.elementos:
            self.elementos.pop()
            self.atualizar_visualizacao()


class FilaVisual(EstruturaVisual):
    def enqueue(self, valor):
        if len(self.elementos) < self.tamanho:
            self.elementos.append(valor)
            self.atualizar_visualizacao()

    def dequeue(self):
        if self.elementos:
            self.elementos.pop(0)
            self.atualizar_visualizacao()


class ListaVisual(EstruturaVisual):
    def inserir(self, valor):
        if len(self.elementos) < self.tamanho:
            self.elementos.append(valor)
            self.atualizar_visualizacao()

    def remover(self):
        if self.elementos:
            self.elementos.pop(0)  # Remove sempre o primeiro da lista, como uma fila
            self.atualizar_visualizacao()


class Aplicacao:
    def __init__(self, root):
        self.root = root
        self.root.title("Visualização de Estruturas de Dados")

        # Criação das áreas de desenho para as estruturas
        self.canvas_pilha = tk.Canvas(root, width=200, height=150)
        self.canvas_pilha.grid(row=0, column=0, padx=10, pady=10)

        self.canvas_fila = tk.Canvas(root, width=200, height=150)
        self.canvas_fila.grid(row=0, column=1, padx=10, pady=10)

        self.canvas_lista = tk.Canvas(root, width=200, height=150)
        self.canvas_lista.grid(row=0, column=2, padx=10, pady=10)

        # Posições dos elementos (retângulos) nas estruturas
        self.posicoes_pilha = [(10, i * 25 + 10, 180, i * 25 + 40) for i in range(5)]
        self.posicoes_fila = [(i * 40 + 10, 10, i * 40 + 40, 40) for i in range(5)]
        self.posicoes_lista = [(i * 40 + 10, 10, i * 40 + 40, 40) for i in range(5)]

        # Inicializando as estruturas
        self.pilha = PilhaVisual(root, "Pilha", self.canvas_pilha, self.posicoes_pilha, 5)
        self.fila = FilaVisual(root, "Fila", self.canvas_fila, self.posicoes_fila, 5)
        self.lista = ListaVisual(root, "Lista", self.canvas_lista, self.posicoes_lista, 5)

        self.passos = 0
        self.numeros = [random.randint(1, 100) for _ in range(10)]

        # Começando as inserções automáticas
        self.executar_passos()

    def executar_passos(self):
        if self.passos == 0:
            # Inserir até completar
            for i in range(5):
                self.pilha.push(self.numeros[i])
                self.fila.enqueue(self.numeros[i])
                self.lista.inserir(self.numeros[i])
        elif self.passos == 1:
            # Remover 2 elementos
            for i in range(2):
                self.pilha.pop()
                self.fila.dequeue()
                self.lista.remover()
        elif self.passos == 2:
            # Inserir 1 elemento
            self.pilha.push(self.numeros[5])
            self.fila.enqueue(self.numeros[5])
            self.lista.inserir(self.numeros[5])
        elif self.passos == 3:
            # Remover 1 elemento
            self.pilha.pop()
            self.fila.dequeue()
            self.lista.remover()
        elif self.passos == 4:
            # Remover 1 elemento
            self.pilha.pop()
            self.fila.dequeue()
            self.lista.remover()
        elif self.passos == 5:
            # Inserir até completar
            for i in range(6, 8):
                self.pilha.push(self.numeros[i])
                self.fila.enqueue(self.numeros[i])
                self.lista.inserir(self.numeros[i])

        # Avançar para o próximo passo
        self.passos += 1
        if self.passos <= 5:
            self.root.after(1000, self.executar_passos)


if __name__ == "__main__":
    root = tk.Tk()
    app = Aplicacao(root)
    root.mainloop()
