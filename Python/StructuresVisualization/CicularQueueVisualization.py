import tkinter as tk
from tkinter import messagebox

class FilaCircular:
    def __init__(self, tamanho, canvas, labels):
        self.tamanho = tamanho
        self.fila = [None] * tamanho
        self.frente = -1
        self.tras = -1
        self.canvas = canvas
        self.labels = labels
        self.atualizar_fila()

    def esta_cheia(self):
        return (self.tras + 1) % self.tamanho == self.frente

    def esta_vazia(self):
        return self.frente == -1

    def insere(self, valor):
        if self.esta_cheia():
            messagebox.showinfo("Aviso", "A fila está cheia.")
            return
        if self.esta_vazia():
            self.frente = 0
        self.tras = (self.tras + 1) % self.tamanho
        self.fila[self.tras] = valor
        self.atualizar_fila()

    def remove(self):
        if self.esta_vazia():
            messagebox.showinfo("Aviso", "A fila está vazia.")
            return None
        valor = self.fila[self.frente]
        if self.frente == self.tras:
            self.frente = -1
            self.tras = -1
        else:
            self.frente = (self.frente + 1) % self.tamanho
        self.atualizar_fila()
        return valor

    def atualizar_fila(self):
        for i in range(self.tamanho):
            if self.fila[i] is None:
                self.labels[i].config(text='_')
            else:
                self.labels[i].config(text=str(self.fila[i]))

        self.canvas.delete("all")
        self.canvas.create_text(10, 10, text=f'Frente: {self.frente}', anchor='nw')
        self.canvas.create_text(10, 30, text=f'Trás: {self.tras}', anchor='nw')


class AplicacaoFilaCircular:
    def __init__(self, root, tamanho):
        self.root = root
        self.root.title("Fila Circular")
        self.tamanho = tamanho

        # Criação do layout
        self.canvas = tk.Canvas(root, width=200, height=50)
        self.canvas.grid(row=0, column=0, columnspan=tamanho)

        self.labels = []
        for i in range(tamanho):
            label = tk.Label(root, text="_", width=4, height=2, font=('Arial', 14), borderwidth=2, relief="solid")
            label.grid(row=1, column=i)
            self.labels.append(label)

        self.fila = FilaCircular(tamanho, self.canvas, self.labels)

        # Botões para as operações
        self.entrada = tk.Entry(root)
        self.entrada.grid(row=2, column=0, columnspan=tamanho//2)

        self.btn_insere = tk.Button(root, text="Inserir", command=self.inserir)
        self.btn_insere.grid(row=2, column=tamanho//2, columnspan=tamanho//4)

        self.btn_remove = tk.Button(root, text="Remover", command=self.remover)
        self.btn_remove.grid(row=2, column=tamanho//2 + tamanho//4, columnspan=tamanho//4)

    def inserir(self):
        valor = self.entrada.get()
        if valor:
            self.fila.insere(int(valor))
        self.entrada.delete(0, 'end')

    def remover(self):
        self.fila.remove()


if __name__ == "__main__":
    root = tk.Tk()
    app = AplicacaoFilaCircular(root, 5)

    # Inserir elementos para completar
    app.fila.insere(10)
    app.fila.insere(20)
    app.fila.insere(30)
    app.fila.insere(40)
    app.fila.insere(50)

    # Remover dois elementos
    app.fila.remove()
    app.fila.remove()

    # Inserir mais um elemento
    app.fila.insere(60)

    # Remover mais dois elementos
    app.fila.remove()
    app.fila.remove()

    # Inserir até completar
    app.fila.insere(70)
    app.fila.insere(80)

    root.mainloop()
