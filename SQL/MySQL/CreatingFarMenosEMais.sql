CREATE TABLE Cliente (
    Codigo INT PRIMARY KEY,
    Nome VARCHAR(100),
    CPF VARCHAR(11),
    Endereco VARCHAR(255),
    Telefone VARCHAR(20),
    Email VARCHAR(100),
    DataNascimento DATE
);

CREATE TABLE Fornecedor (
    Codigo INT PRIMARY KEY,
    Nome VARCHAR(100),
    CNPJ VARCHAR(14),
    Endereco VARCHAR(255),
    Telefone VARCHAR(20),
    Email VARCHAR(100)
);

CREATE TABLE Remedio (
    Codigo INT PRIMARY KEY,
    Nome VARCHAR(100),
    Codigo_Fornecedor INT,
    DataValidade DATE,
    Prescricao BOOLEAN,
    Preco DECIMAL(10, 2),
    FOREIGN KEY (Codigo_Fornecedor) REFERENCES Fornecedor(Codigo)
);

CREATE TABLE Compra (
    Codigo INT PRIMARY KEY,
    DataCompra DATE,
    CodigoCliente INT,
    CodigoRemedio INT,
    Quantidade INT,
    FOREIGN KEY (CodigoCliente) REFERENCES Cliente(Codigo),
    FOREIGN KEY (CodigoRemedio) REFERENCES Remedio(Codigo)
);

CREATE TABLE Promocao (
    Codigo INT PRIMARY KEY,
    CodigoRemedio INT,
    DataInicio DATE,
    DataFim DATE,
    Descricao TEXT,
    Desconto DECIMAL(5, 2),
    FOREIGN KEY (CodigoRemedio) REFERENCES Remedio(Codigo)
);

CREATE TABLE Loja (
    Codigo INT PRIMARY KEY,
    Nome VARCHAR(100),
    Endereco VARCHAR(255),
    Telefone VARCHAR(20)
);

CREATE TABLE Departamento (
    Codigo INT PRIMARY KEY,
    Nome VARCHAR(100)
);

CREATE TABLE Funcionario (
    Codigo INT PRIMARY KEY,
    Nome VARCHAR(100),
    CPF VARCHAR(11),
    Endereco VARCHAR(255),
    Telefone VARCHAR(20),
    DataNascimento DATE,
    DataContratacao DATE,
    CodigoLoja INT,
    CodigoDepto INT,
    Cargo VARCHAR(50),
    Salario DECIMAL(10, 2),
    FOREIGN KEY (CodigoLoja) REFERENCES Loja(Codigo),
    FOREIGN KEY (CodigoDepto) REFERENCES Departamento(Codigo)
);
