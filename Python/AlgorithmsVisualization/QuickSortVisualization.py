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

# Função para trocar elementos
def swap(data, i, j):
    data[i], data[j] = data[j], data[i]

# Algoritmo QuickSort baseado na sua implementação com cores para esq, dir e pivô
def quicksort(data, esq, dir, canvas, speed):
    i, j = esq, dir
    pivo = data[(esq + dir) // 2]

    bar_color = ["blue" for _ in range(len(data))]
    
    while i <= j:
        # Marcar o pivô com verde
        bar_color[(esq + dir) // 2] = "green"

        # Movendo i para direita até encontrar um elemento maior que o pivô
        while data[i] < pivo:
            # Marcar a posição do i (esquerda) com amarelo
            bar_color[i] = "yellow"
            draw_bars(data, canvas, bar_color)
            time.sleep(speed)
            i += 1
            # Restaurar a cor depois de passar pela posição
            bar_color[i - 1] = "blue"

        # Movendo j para esquerda até encontrar um elemento menor que o pivô
        while data[j] > pivo:
            # Marcar a posição do j (direita) com vermelho
            bar_color[j] = "red"
            draw_bars(data, canvas, bar_color)
            time.sleep(speed)
            j -= 1
            # Restaurar a cor depois de passar pela posição
            bar_color[j + 1] = "blue"

        if i <= j:
            swap(data, i, j)
            # Colorir as posições trocadas
            bar_color[i] = "yellow"
            bar_color[j] = "red"
            draw_bars(data, canvas, bar_color)
            time.sleep(speed)
            i += 1
            j -= 1

        # Restaurando a cor do pivô e as outras após o loop
        bar_color = ["blue" for _ in range(len(data))]
        bar_color[(esq + dir) // 2] = "green"  # Mantém a cor do pivô
        draw_bars(data, canvas, bar_color)

    # Chamadas recursivas para a esquerda e direita do pivô
    if esq < j:
        quicksort(data, esq, j, canvas, speed)
    if i < dir:
        quicksort(data, i, dir, canvas, speed)

# Função para iniciar o QuickSort
def start_quick_sort():
    global data
    quicksort(data, 0, len(data) - 1, canvas, speed_slider.get())

# Configuração da interface Tkinter
root = tk.Tk()
root.title("Visualização do Algoritmo QuickSort")

canvas = tk.Canvas(root, width=600, height=400)
canvas.grid(row=0, column=0, padx=10, pady=10, columnspan=3)

speed_slider = tk.Scale(root, from_=0.01, to=1.0, resolution=0.01, length=200, orient=tk.HORIZONTAL, label="Velocidade")
speed_slider.grid(row=1, column=0, padx=5, pady=5)

start_button = tk.Button(root, text="Iniciar QuickSort", command=start_quick_sort)
start_button.grid(row=1, column=1, padx=5, pady=5)

# Gerando dados aleatórios
data = [random.randint(10, 390) for _ in range(20)]

# Desenhando as barras iniciais
draw_bars(data, canvas, ["blue" for _ in range(len(data))])

root.mainloop()
