import tkinter as tk
from tkinter import ttk, messagebox
import time
import heapq
from collections import deque
import random

GOAL_STATE = (1, 2, 3, 4, 5, 6, 7, 8, 0)
GOAL_POS = {v: (i // 3, i % 3) for i, v in enumerate(GOAL_STATE)}

def manhattan(puzzle):
    return sum(
        abs(i // 3 - GOAL_POS[val][0]) + abs(i % 3 - GOAL_POS[val][1])
        for i, val in enumerate(puzzle) if val != 0
    )

def misplaced(puzzle):
    return sum(
        1 for i, val in enumerate(puzzle)
        if val != 0 and val != GOAL_STATE[i]
    )

def get_neighbors(state):
    neighbors = []
    zero = state.index(0)
    x, y = divmod(zero, 3)
    for dx, dy in [(-1,0),(1,0),(0,-1),(0,1)]:
        nx, ny = x + dx, y + dy
        if 0 <= nx < 3 and 0 <= ny < 3:
            new_idx = nx * 3 + ny
            lst = list(state)
            lst[zero], lst[new_idx] = lst[new_idx], lst[zero]
            neighbors.append(tuple(lst))
    return neighbors

def astar(start, heuristic):
    frontier = []
    heapq.heappush(frontier, (heuristic(start), 0, start, []))
    visited = set()
    nodes_expanded = 0

    while frontier:
        est_total, cost, current, path = heapq.heappop(frontier)
        if current in visited:
            continue
        visited.add(current)
        nodes_expanded += 1
        if current == GOAL_STATE:
            return path + [current], nodes_expanded
        for nbr in get_neighbors(current):
            if nbr not in visited:
                heapq.heappush(frontier, (
                    cost + 1 + heuristic(nbr),
                    cost + 1,
                    nbr,
                    path + [current]
                ))
    return None, nodes_expanded

def bfs(start):
    queue = deque([(start, [])])
    visited = set()
    nodes_expanded = 0
    while queue:
        current, path = queue.popleft()
        if current in visited:
            continue
        visited.add(current)
        nodes_expanded += 1
        if current == GOAL_STATE:
            return path + [current], nodes_expanded
        for nbr in get_neighbors(current):
            if nbr not in visited:
                queue.append((nbr, path + [current]))
    return None, nodes_expanded

def greedy(start, heuristic):
    frontier = []
    heapq.heappush(frontier, (heuristic(start), start, []))
    visited = set()
    nodes_expanded = 0
    while frontier:
        h_val, current, path = heapq.heappop(frontier)
        if current in visited:
            continue
        visited.add(current)
        nodes_expanded += 1
        if current == GOAL_STATE:
            return path + [current], nodes_expanded
        for nbr in get_neighbors(current):
            if nbr not in visited:
                heapq.heappush(frontier, (heuristic(nbr), nbr, path + [current]))
    return None, nodes_expanded

def dijkstra(start):
    frontier = []
    heapq.heappush(frontier, (0, start, []))
    visited = set()
    nodes_expanded = 0
    while frontier:
        cost, current, path = heapq.heappop(frontier)
        if current in visited:
            continue
        visited.add(current)
        nodes_expanded += 1
        if current == GOAL_STATE:
            return path + [current], nodes_expanded
        for nbr in get_neighbors(current):
            if nbr not in visited:
                heapq.heappush(frontier, (cost + 1, nbr, path + [current]))
    return None, nodes_expanded

class PuzzleApp:
    def __init__(self, root):
        self.root = root
        root.title("8-Puzzle Solver")
        self.frame = tk.Frame(root)
        self.frame.pack(pady=10)

        # Grid de entradas
        self.tiles = [] 
        for i in range(3):
            row = []
            for j in range(3):
                e = tk.Entry(self.frame, width=7, font=('Arial',18), justify='center')
                e.grid(row=i, column=j, padx=5, pady=5)
                row.append(e)
            self.tiles.append(row)

        # Variáveis e comboboxes
        self.algo_var = tk.StringVar(value='A*')
        self.heur_var = tk.StringVar(value='Manhattan')

        ttk.Label(root, text="Algoritmo:").pack()
        self.algo_cb = ttk.Combobox(root, textvariable=self.algo_var, values=['A*','BFS','Busca Gulosa','Dijkstra'], state='readonly')
        self.algo_cb.pack()
        self.algo_cb.bind('<<ComboboxSelected>>', self._update_heur_visibility)

        self.heur_label = ttk.Label(root, text="Heurística (para A*):")
        self.heur_cb = ttk.Combobox(root, textvariable=self.heur_var, values=['Manhattan','Misplaced'], state='readonly')

        # Botões
        btn_frame = tk.Frame(root)
        btn_frame.pack(pady=5)
        tk.Button(btn_frame, text="Resolver", command=self.solve).grid(row=0, column=0, padx=5)
        tk.Button(btn_frame, text="Embaralhar", command=self.shuffle).grid(row=0, column=1, padx=5)
        tk.Button(btn_frame, text="Anterior", command=self.prev_step).grid(row=0, column=2, padx=5)
        tk.Button(btn_frame, text="Próximo", command=self.next_step).grid(row=0, column=3, padx=5)

        # Preenche estado inicial com o objetivo
        self.fill_state(GOAL_STATE)
        self._update_heur_visibility()

        # Variáveis para navegação dos passos
        self.solution_path = []
        self.current_step = 0

    def fill_state(self, seq):
        for idx, val in enumerate(seq):
            i, j = divmod(idx, 3)
            self.tiles[i][j].delete(0, tk.END)
            # Always display the empty space as blank
            if val == 0:
                self.tiles[i][j].insert(0, '')
            else:
                self.tiles[i][j].insert(0, str(val))

    def shuffle(self):
        seq = list(GOAL_STATE)
        while True:
            random.shuffle(seq)
            if self._is_solvable(seq):
                break
        self.fill_state(seq)

    def _is_solvable(self, seq):
        inv = 0
        for i in range(8):
            for j in range(i+1, 9):
                if seq[i] and seq[j] and seq[i] > seq[j]:
                    inv += 1
        return inv % 2 == 0

    def _update_heur_visibility(self, event=None):
        if self.algo_var.get() == 'A*':
            self.heur_label.pack()
            self.heur_cb.pack()
        else:
            self.heur_label.pack_forget()
            self.heur_cb.pack_forget()

    def _get_state(self):
        try:
            s = tuple(
                int(self.tiles[i][j].get()) if self.tiles[i][j].get().strip() != '' else 0
                for i in range(3) for j in range(3)
            )
            if sorted(s) != list(range(9)):
                raise ValueError
            return s
        except:
            messagebox.showerror("Erro", "Valores devem ser 0–8 sem repetição.")
            return None

    def solve(self):
        start = self._get_state()
        if not start:
            return

        algo = self.algo_var.get()
        start_time = time.time()

        if algo == 'A*':
            h = manhattan if self.heur_var.get() == 'Manhattan' else misplaced
            path, nodes = astar(start, h)
        elif algo == 'BFS':
            path, nodes = bfs(start)
        elif algo == 'Busca Gulosa':
            h = manhattan if self.heur_var.get() == 'Manhattan' else misplaced
            path, nodes = greedy(start, h)
        elif algo == 'Dijkstra':
            path, nodes = dijkstra(start)
        else:
            path, nodes = None, 0

        elapsed = time.time() - start_time
        if path:
            steps = len(path)-1
            messagebox.showinfo(
                "Resultado",
                f"Passos: {steps}\nTempo: {elapsed:.4f}s\nNós expandidos: {nodes}"
            )
            self.solution_path = path
            self.current_step = 0
            self.fill_state(self.solution_path[self.current_step])
        else:
            messagebox.showerror("Falha", "Não encontrou solução.")
            self.solution_path = []
            self.current_step = 0

    def next_step(self):
        if self.solution_path and self.current_step < len(self.solution_path) - 1:
            self.current_step += 1
            self.fill_state(self.solution_path[self.current_step])

    def prev_step(self):
        if self.solution_path and self.current_step > 0:
            self.current_step -= 1
            self.fill_state(self.solution_path[self.current_step])

if __name__ == "__main__":
    root = tk.Tk()
    app = PuzzleApp(root)
    root.mainloop()
