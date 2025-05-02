import os
import tkinter as tk
from tkinter import filedialog, messagebox
from docx import Document
from odf.opendocument import load
from odf.text import P
from fpdf import FPDF

def convert_to_pdf(file_path, pdf_filename):
    # Verifica a extensão do arquivo
    ext = os.path.splitext(file_path)[-1].lower()

    # Função auxiliar para evitar erros com linhas vazias
    def safe_multicell(pdf, text):
        if text.strip():  # Somente adiciona o texto se ele não estiver vazio
            pdf.multi_cell(0, 10, text)
        else:
            pdf.multi_cell(0, 10, " ")  # Adiciona uma linha em branco no lugar de linhas vazias

    # Se o arquivo for .docx
    if ext == ".docx":
        doc = Document(file_path)

        pdf = FPDF()
        pdf.set_auto_page_break(auto=True, margin=15)
        pdf.add_page()
        pdf.set_font("Helvetica", size=12)

        for para in doc.paragraphs:
            safe_multicell(pdf, para.text)

        pdf.output(pdf_filename)
        messagebox.showinfo("Sucesso", f"Arquivo PDF gerado: {pdf_filename}")

    # Se o arquivo for .txt
    elif ext == ".txt":
        with open(file_path, 'r', encoding='utf-8') as file:
            lines = file.readlines()

        pdf = FPDF()
        pdf.set_auto_page_break(auto=True, margin=15)
        pdf.add_page()
        pdf.set_font("Helvetica", size=12)

        for line in lines:
            safe_multicell(pdf, line)

        pdf.output(pdf_filename)
        messagebox.showinfo("Sucesso", f"Arquivo PDF gerado: {pdf_filename}")

    # Se o arquivo for .odt
    elif ext == ".odt":
        odt_file = load(file_path)
        paragraphs = odt_file.getElementsByType(P)

        pdf = FPDF()
        pdf.set_auto_page_break(auto=True, margin=15)
        pdf.add_page()
        pdf.set_font("Helvetica", size=12)

        for para in paragraphs:
            text = ''.join([str(node) for node in para.childNodes])
            safe_multicell(pdf, text)

        pdf.output(pdf_filename)
        messagebox.showinfo("Sucesso", f"Arquivo PDF gerado: {pdf_filename}")

    else:
        messagebox.showerror("Erro", "Formato de arquivo não suportado. Selecione um arquivo .docx, .txt ou .odt.")

def open_file_dialog():
    file_path = filedialog.askopenfilename(
        title="Selecione um arquivo",
        filetypes=(("Documentos de texto", "*.docx *.txt *.odt"), ("Todos os arquivos", "*.*"))
    )

    if file_path:
        pdf_filename = os.path.splitext(file_path)[0] + ".pdf"
        convert_to_pdf(file_path, pdf_filename)

# Configuração da interface gráfica
root = tk.Tk()
root.title("Conversor de Arquivos para PDF")
root.geometry("400x200")

label = tk.Label(root, text="Selecione um arquivo .docx, .txt ou .odt para converter em PDF", font=("Arial", 12))
label.pack(pady=20)

btn_select_file = tk.Button(root, text="Selecionar Arquivo", command=open_file_dialog, width=30)
btn_select_file.pack(pady=10)

btn_exit = tk.Button(root, text="Sair", command=root.quit, width=30)
btn_exit.pack(pady=10)

root.mainloop()
