# passo a passo do projeto

import pyautogui as pag
import time

pag.PAUSE = 0.5
# abrir o sistema
# https://dlp.hashtagtreinamentos.com/python/intensivao/login

# abrir o navegador
pag.press("win")
pag.write("chrome")
pag.press("enter")

# entrar no site do sistema
pag.write("https://dlp.hashtagtreinamentos.com/python/intensivao/login")
pag.press("enter")

time.sleep(1.5)

# fazer login
pag.click(x=679, y=408)
pag.write("emf3134")

pag.press("tab")
pag.write("senha")

pag.press("tab")
pag.press("enter")

time.sleep(1.5)

# abrir e importar a base de dados dos produtos para cadastrar
import pandas 

tabela = pandas.read_csv("/home/marcoslaine/Downloads/produtos.csv")

# cadastrar um produto
for linha in tabela.index:
    codigo = str(tabela.loc[linha, "codigo"])
    pag.click(x=672, y=295)

    pag.write(codigo)
    pag.press("tab")

    marca = str(tabela.loc[linha, "marca"])
    pag.write(marca)
    pag.press("tab")

    tipo = str(tabela.loc[linha, "tipo"])
    pag.write(tipo)
    pag.press("tab")

    categoria = str(tabela.loc[linha, "categoria"])
    pag.write(categoria)
    pag.press("tab")

    preco = str(tabela.loc[linha, "preco_unitario"])
    pag.write(preco)
    pag.press("tab")

    custo = str(tabela.loc[linha, "custo"])
    pag.write(custo)
    pag.press("tab")

    obs = str(tabela.loc[linha, "obs"])
    if obs != "nan":
        pag.write(obs)
    pag.press("tab")

    pag.press("enter")
    pag.scroll(5000)


# repetir isso ate o final  


# print("hello world")