import tkinter as tk
import random
import time

# Função para desenhar as barras
def draw_bars(data, canvas, bar_color):
    canvas.delete("all")
    canvas_height = 400
    canvas_width = 600
    bar_width = canvas_width / (len(data) + 1)
    spacing = 5
    for i, height in enumerate(data):
        x0 = i * bar_width + spacing
        y0 = canvas_height - height
        x1 = (i + 1) * bar_width
        y1 = canvas_height
        canvas.create_rectangle(x0, y0, x1, y1, fill=bar_color[i])
    canvas.update()

# Algoritmo Insertion Sort
def insertion_sort(data, canvas, speed):
    for i in range(1, len(data)):
        key = data[i]
        j = i - 1

        # Inicia a cor com todas barras azuis
        bar_color = ["blue" for _ in range(len(data))]

        # Comparação com elementos anteriores
        while j >= 0 and key < data[j]:
            # Marcar os elementos sendo comparados
            bar_color[i] = "yellow"  # Elemento a ser inserido
            bar_color[j] = "red"  # Elemento comparado
            draw_bars(data, canvas, bar_color)
            time.sleep(speed)

            data[j + 1] = data[j]
            j -= 1

            # Atualiza a cor após a troca
            bar_color = ["blue" for _ in range(len(data))]
            bar_color[i] = "yellow"  # Elemento atual
            bar_color[j + 1] = "green"  # Novo local do elemento inserido

        data[j + 1] = key

        # Marca a barra atual como ordenada
        bar_color[i] = "green"
        draw_bars(data, canvas, bar_color)
        time.sleep(speed)

# Função para iniciar o Insertion Sort
def start_insertion_sort():
    global data
    insertion_sort(data, canvas, speed_slider.get())

# Configuração da interface Tkinter para Insertion Sort
root = tk.Tk()
root.title("Visualização do Algoritmo Insertion Sort")

canvas = tk.Canvas(root, width=600, height=400)
canvas.grid(row=0, column=0, padx=10, pady=10, columnspan=3)

speed_slider = tk.Scale(root, from_=0.01, to=1.0, resolution=0.01, length=200, orient=tk.HORIZONTAL, label="Velocidade")
speed_slider.grid(row=1, column=0, padx=5, pady=5)

start_button = tk.Button(root, text="Iniciar Insertion Sort", command=start_insertion_sort)
start_button.grid(row=1, column=1, padx=5, pady=5)

# Gerando dados aleatórios
data = [random.randint(10, 390) for _ in range(20)]

# Desenhando as barras iniciais
draw_bars(data, canvas, ["blue" for _ in range(len(data))])

root.mainloop()
