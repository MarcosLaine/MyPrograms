import tkinter as tk
import random
import time

RECT_WIDTH = 50
RECT_HEIGHT = 30
ARRAY_SIZE = 10
DELAY = 500  # Tempo de espera entre passos em milissegundos (0.5s)

class MergeSortVisualization(tk.Tk):
    def __init__(self):
        super().__init__()
        self.title("Merge Sort Visualization")
        self.canvas = tk.Canvas(self, width=800, height=400)
        self.canvas.pack(fill=tk.BOTH, expand=True)

        # Gera um array aleatório
        self.array = random.sample(range(1, 100), ARRAY_SIZE)

        # Desenha o array inicial
        self.draw_array(self.array)

        # Inicia a animação de Merge Sort
        self.after(1000, self.animate_merge_sort)

    def draw_array(self, array, colors=None):
        """Desenha o array no canvas"""
        self.canvas.delete("all")
        if colors is None:
            colors = ["lightgray"] * len(array)

        for i, val in enumerate(array):
            x = i * (RECT_WIDTH + 5) + 20
            self.canvas.create_rectangle(x, 200 - val * 2, x + RECT_WIDTH, 200, fill=colors[i])
            self.canvas.create_text(x + RECT_WIDTH / 2, 210, text=str(val), anchor=tk.N)

        self.update_idletasks()

    def animate_merge_sort(self):
        self.merge_sort(self.array, 0, len(self.array) - 1)

    def merge_sort(self, array, start, end):
        if start < end:
            mid = (start + end) // 2
            self.merge_sort(array, start, mid)
            self.merge_sort(array, mid + 1, end)
            self.merge(array, start, mid, end)

    def merge(self, array, start, mid, end):
        left = array[start:mid + 1]
        right = array[mid + 1:end + 1]

        i = j = 0
        k = start

        while i < len(left) and j < len(right):
            if left[i] <= right[j]:
                array[k] = left[i]
                i += 1
            else:
                array[k] = right[j]
                j += 1
            k += 1

            self.draw_array(array, ["lightgreen" if x == k-1 else "lightgray" for x in range(len(array))])
            self.update()
            time.sleep(DELAY / 1000.0)

        while i < len(left):
            array[k] = left[i]
            i += 1
            k += 1

            self.draw_array(array, ["lightgreen" if x == k-1 else "lightgray" for x in range(len(array))])
            self.update()
            time.sleep(DELAY / 1000.0)

        while j < len(right):
            array[k] = right[j]
            j += 1
            k += 1

            self.draw_array(array, ["lightgreen" if x == k-1 else "lightgray" for x in range(len(array))])
            self.update()
            time.sleep(DELAY / 1000.0)

if __name__ == "__main__":
    app = MergeSortVisualization()
    app.mainloop()
