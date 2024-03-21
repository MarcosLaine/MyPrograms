-- Criando um esquema chamado "meu_esquema"
CREATE SCHEMA meu_esquema;

-- Criando a tabela Usuário
CREATE TABLE meu_esquema.Usuario (
    cpf SERIAL PRIMARY KEY, --será o ID do usuário
    nome VARCHAR(100) NOT NULL,
    sexo CHAR(1) CHECK (sexo IN ('M', 'F', 'O')),--masculino, feminino, outros
    nascimento DATE,
    email VARCHAR(100) UNIQUE NOT NULL,
    senha VARCHAR(100) NOT NULL
);

-- Criando a tabela Gerenciamento
CREATE TABLE meu_esquema.gerenciamento (
    id SERIAL PRIMARY KEY,
    descricao VARCHAR(100),
    categoria VARCHAR(100),
    valor INT,
    tipo VARCHAR(100),
    resumo VARCHAR(100),
    --transacao,
    usuario_id INT REFERENCES meu_esquema.Usuario(cpf)
);

/*-- Criando a tabela relatórios
CREATE TABLE meu_esquema.relatorios (
    transacao
);*/

-- Criando a tabela Calculadora
CREATE TABLE meu_esquema.calculadoras (
    id SERIAL PRIMARY KEY,
    aportes float[()],
    tempo float[()],
    taxa float[()],
    valor_inicial float[()]
);

-- Criando a tabela indicações
CREATE TABLE meu_esquema.indicacoes (
    ativo VARCHAR(7)--permite apenas 7 caracteres representando o ticker da empresa, por exemplo "ABCDF11"
);

-- Criando a tabela Perfil do Investidor
CREATE TABLE meu_esquema.perfil_investidor (
    id SERIAL PRIMARY KEY,
    foco CHAR(1) CHECK (foco IN ('C', 'M', 'L')),--curto, medio ou longo prazo
    risco CHAR(1) CHECK (risco IN('A', 'B', 'M')),--alto, baixo ou moderado
    onjetivo VARCHAR(100),
    salario INT,
    conhecimento CHAR(1) CHECK (risco IN('A', 'B', 'M'))--alto, baixo ou moderado
);

-- Criando a tabela Investimento
CREATE TABLE meu_esquema.investimento (
    categoria
    risco CHAR(1) CHECK (risco IN('A', 'B', 'M')),--alto, baixo ou moderado
    setor VARCHAR(100),
    descricao VARCHAR(100),
    ativo VARCHAR(7)
);