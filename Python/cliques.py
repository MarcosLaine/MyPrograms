import ctypes
import time
import PySimpleGUI as sg
import threading
import keyboard

# Definições para interagir com a API do Windows
class MOUSEINPUT(ctypes.Structure):
    _fields_ = [("dx", ctypes.c_long),
                ("dy", ctypes.c_long),
                ("mouseData", ctypes.c_ulong),
                ("dwFlags", ctypes.c_ulong),
                ("time", ctypes.c_ulong),
                ("dwExtraInfo", ctypes.POINTER(ctypes.c_ulong))]

class INPUT(ctypes.Structure):
    _fields_ = [("type", ctypes.c_ulong),
                ("mi", MOUSEINPUT)]

class POINT(ctypes.Structure):
    _fields_ = [("x", ctypes.c_long), ("y", ctypes.c_long)]

# Constantes para ações do mouse
MOUSEEVENTF_LEFTDOWN = 0x0002
MOUSEEVENTF_LEFTUP = 0x0004

stop_clicking = False  # Variável de controle para interromper os cliques

def click(x, y):
    global stop_clicking
    ctypes.windll.user32.SetCursorPos(x, y)
    ctypes.windll.user32.mouse_event(MOUSEEVENTF_LEFTDOWN, x, y, 0, 0)
    ctypes.windll.user32.mouse_event(MOUSEEVENTF_LEFTUP, x, y, 0, 0)
    if stop_clicking:
        return False  # Retorna False para interromper os cliques
    return True

def perform_clicks(num_clicks, interval, x, y):
    global stop_clicking
    for _ in range(num_clicks):
        if not click(x, y):
            break
        time.sleep(interval)
    stop_clicking = False  # Redefine a variável de controle

def main():
    global stop_clicking
    sg.theme('DarkAmber')

    layout = [
        [sg.Text('Número de cliques:'), sg.InputText(key='NUM_CLICKS')],
        [sg.Text('Intervalo entre cliques (segundos):'), sg.InputText(key='INTERVAL')],
        [sg.Button('Iniciar'), sg.Button('Cancelar')]
    ]

    window = sg.Window('Simulador de Cliques', layout)

    while True:
        event, values = window.read(timeout=100)  # Timeout para verificar a tecla ESC
        if event == sg.WIN_CLOSED or event == 'Cancelar' or keyboard.is_pressed('esc'):
            if event == 'Iniciar':
                stop_clicking = True  # Sinaliza para interromper os cliques
            break
        elif event == 'Iniciar':
            try:
                num_clicks = int(values['NUM_CLICKS'])
                interval = float(values['INTERVAL'])
                pos = POINT()
                ctypes.windll.user32.GetCursorPos(ctypes.byref(pos))
                x, y = pos.x, pos.y
                threading.Thread(target=perform_clicks, args=(num_clicks, interval, x, y)).start()
                window.minimize()
            except ValueError:
                sg.popup_error('Por favor, insira valores válidos.')

    window.close()

if __name__ == "__main__":
    main()
