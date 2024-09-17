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

# Função para construir o heap
def heapify(data, n, i, canvas, speed):
    largest = i
    left = 2 * i + 1
    right = 2 * i + 2

    if left < n and data[left] > data[largest]:
        largest = left
    if right < n and data[right] > data[largest]:
        largest = right

    if largest != i:
        data[i], data[largest] = data[largest], data[i]
        # Colorindo o heap
        bar_color = ["blue" for _ in range(len(data))]
        bar_color[i] = "yellow"  # Nó trocado
        bar_color[largest] = "red"  # Nó maior
        draw_bars(data, canvas, bar_color)
        time.sleep(speed)
        heapify(data, n, largest, canvas, speed)

# Algoritmo HeapSort
def heap_sort(data, canvas, speed):
    n = len(data)
    # Construir heap
    for i in range(n // 2 - 1, -1, -1):
        heapify(data, n, i, canvas, speed)

    # Extraindo elementos do heap
    for i in range(n - 1, 0, -1):
        data[i], data[0] = data[0], data[i]
        # Colorindo as trocas
        bar_color = ["blue" for _ in range(len(data))]
        bar_color[i] = "green"  # Elemento ordenado
        draw_bars(data, canvas, bar_color)
        time.sleep(speed)
        heapify(data, i, 0, canvas, speed)

# Função para iniciar o HeapSort
def start_heap_sort():
    global data
    heap_sort(data, canvas, speed_slider.get())

# Configuração da interface Tkinter para HeapSort
root = tk.Tk()
root.title("Visualização do Algoritmo HeapSort")

canvas = tk.Canvas(root, width=600, height=400)
canvas.grid(row=0, column=0, padx=10, pady=10, columnspan=3)

speed_slider = tk.Scale(root, from_=0.01, to=1.0, resolution=0.01, length=200, orient=tk.HORIZONTAL, label="Velocidade")
speed_slider.grid(row=1, column=0, padx=5, pady=5)

start_button = tk.Button(root, text="Iniciar HeapSort", command=start_heap_sort)
start_button.grid(row=1, column=1, padx=5, pady=5)

# Gerando dados aleatórios
data = [random.randint(10, 390) for _ in range(20)]

# Desenhando as barras iniciais
draw_bars(data, canvas, ["blue" for _ in range(len(data))])

root.mainloop()
