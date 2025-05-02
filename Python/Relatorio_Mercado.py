from fpdf import FPDF

class PDF(FPDF):
    def header(self):
        self.set_font('Arial', 'B', 12)
        self.cell(0, 10, 'Relatório de Mercado', 0, 1, 'C')

    def chapter_title(self, title):
        self.set_font('Arial', 'B', 12)
        self.cell(0, 10, title, 0, 1, 'L')
        self.ln(10)

    def chapter_body(self, body):
        self.set_font('Arial', '', 12)
        self.multi_cell(0, 10, body)
        self.ln()

    def add_chapter(self, title, body):
        self.add_page()
        self.chapter_title(title)
        self.chapter_body(body)

    def add_image(self, image_path, x=None, y=None, w=0, h=0):
        self.image(image_path, x, y, w, h)

# Criando o documento PDF
pdf = PDF()
pdf.set_auto_page_break(auto=True, margin=15)

# Adicionando capítulos
pdf.add_chapter('Minimundo', '''
Um mercado é composto por diversas entidades, incluindo produtos, clientes, fornecedores, funcionários, pedidos, categorias, e pagamentos. Cada entidade possui atributos específicos e se relaciona com outras entidades de maneiras distintas.

- Produto: Cada produto tem um identificador único, nome, descrição, preço, quantidade em estoque e pertence a uma categoria.
- Categoria: Cada categoria tem um identificador único e um nome.
- Cliente: Cada cliente possui um identificador único, nome, endereço, telefone e email.
- Fornecedor: Cada fornecedor tem um identificador único, nome, telefone e email.
- Funcionário: Cada funcionário possui um identificador único, nome, função, telefone e email.
- Pedido: Cada pedido possui um identificador único, data do pedido, cliente associado e funcionário responsável.
- Detalhes do Pedido: Esta entidade armazena a quantidade de cada produto em um pedido específico.
- Pagamento: Cada pagamento possui um identificador único, valor, data do pagamento e está associado a um pedido.
- Entrega: Cada entrega possui um identificador único, data da entrega, endereço de entrega e está associada a um pedido.
''')

pdf.add_chapter('Esquema ER', 'Aqui está o diagrama ER do mercado:')
pdf.add_image('Create_a_detailed_Entity-Relationship_(ER)_diagram.png', x=10, y=40, w=190)

pdf.add_chapter('Esquema Relacional', '''
A partir do esquema ER, derivamos as seguintes tabelas:

- Produto (id_produto, nome, descricao, preco, quantidade_estoque, id_categoria)
- Categoria (id_categoria, nome)
- Cliente (id_cliente, nome, endereco, telefone, email)
- Fornecedor (id_fornecedor, nome, telefone, email)
- Funcionário (id_funcionario, nome, funcao, telefone, email)
- Pedido (id_pedido, data_pedido, id_cliente, id_funcionario)
- Detalhes_Pedido (id_pedido, id_produto, quantidade)
- Pagamento (id_pagamento, valor, data_pagamento, id_pedido)
- Entrega (id_entrega, data_entrega, endereco_entrega, id_pedido)
''')

pdf.add_chapter('Script SQL', '''
CREATE DATABASE Mercado;

USE Mercado;

CREATE TABLE Categoria (
    id_categoria INT PRIMARY KEY,
    nome VARCHAR(100)
);

CREATE TABLE Produto (
    id_produto INT PRIMARY KEY,
    nome VARCHAR(100),
    descricao TEXT,
    preco DECIMAL(10, 2),
    quantidade_estoque INT,
    id_categoria INT,
    FOREIGN KEY (id_categoria) REFERENCES Categoria(id_categoria)
);

CREATE TABLE Cliente (
    id_cliente INT PRIMARY KEY,
    nome VARCHAR(100),
    endereco TEXT,
    telefone VARCHAR(15),
    email VARCHAR(100)
);

CREATE TABLE Fornecedor (
    id_fornecedor INT PRIMARY KEY,
    nome VARCHAR(100),
    telefone VARCHAR(15),
    email VARCHAR(100)
);

CREATE TABLE Funcionário (
    id_funcionario INT PRIMARY KEY,
    nome VARCHAR(100),
    funcao VARCHAR(50),
    telefone VARCHAR(15),
    email VARCHAR(100)
);

CREATE TABLE Pedido (
    id_pedido INT PRIMARY KEY,
    data_pedido DATE,
    id_cliente INT,
    id_funcionario INT,
    FOREIGN KEY (id_cliente) REFERENCES Cliente(id_cliente),
    FOREIGN KEY (id_funcionario) REFERENCES Funcionário(id_funcionario)
);

CREATE TABLE Detalhes_Pedido (
    id_pedido INT,
    id_produto INT,
    quantidade INT,
    PRIMARY KEY (id_pedido, id_produto),
    FOREIGN KEY (id_pedido) REFERENCES Pedido(id_pedido),
    FOREIGN KEY (id_produto) REFERENCES Produto(id_produto)
);

CREATE TABLE Pagamento (
    id_pagamento INT PRIMARY KEY,
    valor DECIMAL(10, 2),
    data_pagamento DATE,
    id_pedido INT,
    FOREIGN KEY (id_pedido) REFERENCES Pedido(id_pedido)
);

CREATE TABLE Entrega (
    id_entrega INT PRIMARY KEY,
    data_entrega DATE,
    endereco_entrega TEXT,
    id_pedido INT,
    FOREIGN KEY (id_pedido) REFERENCES Pedido(id_pedido)
);

-- Inserindo alguns dados iniciais

INSERT INTO Categoria (id_categoria, nome) VALUES
(1, 'Alimentos'),
(2, 'Bebidas'),
(3, 'Limpeza');

INSERT INTO Produto (id_produto, nome, descricao, preco, quantidade_estoque, id_categoria) VALUES
(1, 'Arroz', 'Arroz branco tipo 1', 5.99, 100, 1),
(2, 'Feijão', 'Feijão carioca', 4.49, 150, 1),
(3, 'Macarrão', 'Macarrão espaguete', 3.79, 200, 1);

INSERT INTO Cliente (id_cliente, nome, endereco, telefone, email) VALUES
(1, 'João Silva', 'Rua A, 123', '123456789', 'joao@example.com'),
(2, 'Maria Souza', 'Rua B, 456', '987654321', 'maria@example.com');

INSERT INTO Fornecedor (id_fornecedor, nome, telefone, email) VALUES
(1, 'Fornecedor A', '111111111', 'fornecedorA@example.com'),
(2, 'Fornecedor B', '222222222', 'fornecedorB@example.com');

INSERT INTO Funcionário (id_funcionario, nome, funcao, telefone, email) VALUES
(1, 'Carlos Lima', 'Caixa', '333333333', 'carlos@example.com'),
(2, 'Ana Pereira', 'Gerente', '444444444', 'ana@example.com');

INSERT INTO Pedido (id_pedido, data_pedido, id_cliente, id_funcionario) VALUES
(1, '2024-05-16', 1, 1),
(2, '2024-05-17', 2, 2);

INSERT INTO Detalhes_Pedido (id_pedido, id_produto, quantidade) VALUES
(1, 1, 2),
(1, 2, 3),
(2, 3, 1);

INSERT INTO Pagamento (id_pagamento, valor, data_pagamento, id_pedido) VALUES
(1, 20.47, '2024-05-16', 1),
(2, 3.79, '2024-05-17', 2);

INSERT INTO Entrega (id_entrega, data_entrega, endereco_entrega, id_pedido) VALUES
(1, '2024-05-18', 'Rua A, 123', 1),
(2, '2024-05-19', 'Rua B, 456', 2);
''')

# Salvando o arquivo PDF
pdf_output = 'Relatorio_Mercado.pdf'
pdf.output(pdf_output)

print(f"PDF gerado com sucesso: {pdf_output}")
