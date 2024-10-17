import os
import subprocess

def criar_executavel(script_path):
    try:
        # Comando para gerar o arquivo .exe com bibliotecas adicionais
        comando = f'pyinstaller --onefile --hidden-import scipy --hidden-import numpy --hidden-import tkinter --hidden-import matplotlib --hidden-import sympy "{script_path}"'
        
        # Executando o comando no terminal
        subprocess.run(comando, check=True, shell=True)
        
        # Mensagem de sucesso
        print("Arquivo .exe criado com sucesso! Verifique a pasta 'dist' para encontrar o executável.")
        
    except subprocess.CalledProcessError as e:
        print(f"Erro ao criar o executável: {e}")

if __name__ == "__main__":
    caminho_script = "C:\\Users\\kino1\\Desktop\\Programacao\\CalculadoradeIntegral\\IntegralCalculator\\src\\IntegralCalculator.py"  # Altere para o caminho do seu script Python
    criar_executavel(caminho_script)