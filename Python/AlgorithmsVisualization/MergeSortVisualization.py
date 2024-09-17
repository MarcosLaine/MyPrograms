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

# Algoritmo MergeSort
def merge_sort(data, left, right, canvas, speed):
    if left < right:
        mid = (left + right) // 2
        merge_sort(data, left, mid, canvas, speed)
        merge_sort(data, mid + 1, right, canvas, speed)
        merge(data, left, mid, right, canvas, speed)

def merge(data, left, mid, right, canvas, speed):
    left_part = data[left:mid + 1]
    right_part = data[mid + 1:right + 1]
    left_idx, right_idx = 0, 0

    bar_color = ["blue" for _ in range(len(data))]

    for i in range(left, right + 1):
        bar_color[i] = "yellow"
    draw_bars(data, canvas, bar_color)
    time.sleep(speed)

    for i in range(left, right + 1):
        if left_idx < len(left_part) and (right_idx >= len(right_part) or left_part[left_idx] <= right_part[right_idx]):
            data[i] = left_part[left_idx]
            left_idx += 1
        else:
            data[i] = right_part[right_idx]
            right_idx += 1
        
        bar_color[i] = "red"
        draw_bars(data, canvas, bar_color)
        time.sleep(speed)

    for i in range(left, right + 1):
        bar_color[i] = "green"
    draw_bars(data, canvas, bar_color)
    time.sleep(speed)

# Função para iniciar o MergeSort
def start_merge_sort():
    global data
    merge_sort(data, 0, len(data) - 1, canvas, speed_slider.get())

# Configuração da interface Tkinter
root = tk.Tk()
root.title("Visualização do Algoritmo MergeSort")

canvas = tk.Canvas(root, width=600, height=400)
canvas.grid(row=0, column=0, padx=10, pady=10, columnspan=3)

speed_slider = tk.Scale(root, from_=0.01, to=1.0, resolution=0.01, length=200, orient=tk.HORIZONTAL, label="Velocidade")
speed_slider.grid(row=1, column=0, padx=5, pady=5)

start_button = tk.Button(root, text="Iniciar MergeSort", command=start_merge_sort)
start_button.grid(row=1, column=1, padx=5, pady=5)

# Gerando dados aleatórios
data = [random.randint(10, 390) for _ in range(20)]

# Desenhando as barras iniciais
draw_bars(data, canvas, ["blue" for _ in range(len(data))])

root.mainloop()
