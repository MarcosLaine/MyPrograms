import tkinter as tk
from datetime import datetime

def atualizar_contador():
    """
    Calcula a diferença entre a data/hora atual e a data inicial e atualiza o rótulo
    com o tempo decorrido formatado.
    """
    # Data inicial: 13 de dezembro de 2023, 19:30:00
    data_inicial = datetime(2023, 12, 13, 19, 30, 0)
    # Data/hora atual
    agora = datetime.now()
    # Calcula a diferença entre as datas
    delta = agora - data_inicial

    # Extrai dias, horas, minutos e segundos da diferença
    dias = delta.days
    horas, resto = divmod(delta.seconds, 3600)
    minutos, segundos = divmod(resto, 60)

    # Formata o texto com o tempo decorrido
    texto = f"Faz {dias} dias, {horas:02d} horas, {minutos:02d} minutos e {segundos:02d} segundos que eu to junto com meu momo, sendo a pessoa mais feliz do mundo"
    
    # Atualiza o rótulo com o texto formatado
    label.config(text=texto)
    
    # Agenda a execução desta função novamente após 1000 milissegundos (1 segundo)
    root.after(1000, atualizar_contador)

# Cria a janela principal
root = tk.Tk()
root.title("Contador desde 13/12/2023 19:30")
root.geometry("600x200")

# Cria um rótulo para exibir o contador e configura a fonte
label = tk.Label(root, text="", font=("Arial", 20))
label.pack(expand=True)

# Inicia a atualização do contador
atualizar_contador()

# Inicia o loop principal da interface gráfica
root.mainloop()
