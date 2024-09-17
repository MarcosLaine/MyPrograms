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

# Algoritmo de ordenação por bolha
def bubble_sort(data, canvas, speed):
    for i in range(len(data) - 1):
        for j in range(len(data) - 1 - i):
            # Colorindo as barras em comparação
            bar_color = ["blue" for _ in range(len(data))]
            bar_color[j] = "red"
            bar_color[j + 1] = "red"
            draw_bars(data, canvas, bar_color)
            time.sleep(speed)
            if data[j] > data[j + 1]:
                # Troca
                data[j], data[j + 1] = data[j + 1], data[j]
                # Colorindo a barra trocada
                bar_color[j] = "green"
                bar_color[j + 1] = "green"
                draw_bars(data, canvas, bar_color)
                time.sleep(speed)
        # Colorindo as barras que já estão na posição correta
        bar_color[len(data) - 1 - i] = "green"
        draw_bars(data, canvas, bar_color)

# Função para iniciar o processo de ordenação
def start_sort():
    global data
    bubble_sort(data, canvas, speed_slider.get())

# Configuração da interface Tkinter
root = tk.Tk()
root.title("Visualização do Algoritmo de Bolha")

canvas = tk.Canvas(root, width=600, height=400)
canvas.grid(row=0, column=0, padx=10, pady=10, columnspan=3)

speed_slider = tk.Scale(root, from_=0.01, to=1.0, resolution=0.01, length=200, orient=tk.HORIZONTAL, label="Velocidade")
speed_slider.grid(row=1, column=0, padx=5, pady=5)

start_button = tk.Button(root, text="Iniciar", command=start_sort)
start_button.grid(row=1, column=1, padx=5, pady=5)

# Gerando dados aleatórios
data = [random.randint(10, 390) for _ in range(20)]

# Desenhando as barras iniciais
draw_bars(data, canvas, ["blue" for _ in range(len(data))])

root.mainloop()
