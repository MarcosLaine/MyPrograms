-- Populando a tabela Cliente
INSERT INTO Cliente (Codigo, Nome, CPF, Endereco, Telefone, Email, DataNascimento)
VALUES
(1, 'João Silva', '12345678901', 'Av. Paulista, 1000, São Paulo, SP', '(11) 98765-4321', 'joao.silva@example.com', '1985-05-15'),
(2, 'Maria Souza', '23456789012', 'Rua XV de Novembro, 200, Curitiba, PR', '(41) 99876-5432', 'maria.souza@example.com', '1990-07-20'),
(3, 'Pedro Oliveira', '34567890123', 'Rua das Flores, 300, Porto Alegre, RS', '(51) 91234-5678', 'pedro.oliveira@example.com', '1978-03-10'),
(4, 'Ana Lima', '45678901234', 'Rua Augusta, 400, São Paulo, SP', '(11) 97654-3210', 'ana.lima@example.com', '1992-11-25'),
(5, 'Carlos Pereira', '56789012345', 'Av. Atlântica, 500, Rio de Janeiro, RJ', '(21) 98765-4321', 'carlos.pereira@example.com', '1980-08-30');

-- Populando a tabela Fornecedor
INSERT INTO Fornecedor (Codigo, Nome, CNPJ, Endereco, Telefone, Email)
VALUES
(1, 'Farmaceutica A', '12345678000190', 'Av. Brasil, 1500, São Paulo, SP', '(11) 92345-6789', 'contato@farmaceuticaa.com'),
(2, 'Medicamentos B', '23456789000101', 'Av. Rio Branco, 2500, Rio de Janeiro, RJ', '(21) 93456-7890', 'contato@medicamentosb.com'),
(3, 'Saúde e Vida', '34567890000112', 'Av. Beira Mar, 3500, Fortaleza, CE', '(85) 94567-8901', 'contato@saudeevida.com');

-- Populando a tabela Remedio
INSERT INTO Remedio (Codigo, Nome, Codigo_Fornecedor, DataValidade, Prescricao, Preco)
VALUES
(1, 'Paracetamol', 1, '2025-12-31', FALSE, 5.50),
(2, 'Amoxicilina', 2, '2024-11-30', TRUE, 12.75),
(3, 'Ibuprofeno', 1, '2025-10-15', FALSE, 8.25),
(4, 'Dipirona', 3, '2025-06-01', FALSE, 3.75),
(5, 'Cloroquina', 2, '2026-01-01', TRUE, 20.00);

-- Populando a tabela Compra
INSERT INTO Compra (Codigo, DataCompra, CodigoCliente, CodigoRemedio, Quantidade)
VALUES
(1, '2024-01-15', 1, 1, 2),
(2, '2024-02-20', 2, 2, 1),
(3, '2024-03-05', 3, 3, 3),
(4, '2024-03-10', 4, 4, 4),
(5, '2024-04-25', 5, 5, 1);

-- Populando a tabela Promocao
INSERT INTO Promocao (Codigo, CodigoRemedio, DataInicio, DataFim, Descricao, Desconto)
VALUES
(1, 1, '2024-06-01', '2024-06-30', 'Promoção de Verão', 10.00),
(2, 3, '2024-07-01', '2024-07-15', 'Desconto de Inverno', 15.00),
(3, 4, '2024-08-01', '2024-08-31', 'Liquidação de Inverno', 20.00);

-- Populando a tabela Loja
INSERT INTO Loja (Codigo, Nome, Endereco, Telefone)
VALUES
(1, 'Loja Centro', 'Centro, 123, São Paulo, SP', '(11) 98765-1234'),
(2, 'Loja Norte', 'Norte, 456, Rio de Janeiro, RJ', '(21) 99876-2345'),
(3, 'Loja Sul', 'Sul, 789, Porto Alegre, RS', '(51) 91234-3456');

-- Populando a tabela Departamento
INSERT INTO Departamento (Codigo, Nome)
VALUES
(1, 'Farmácia'),
(2, 'Administração'),
(3, 'Vendas');

-- Populando a tabela Funcionario
INSERT INTO Funcionario (Codigo, Nome, CPF, Endereco, Telefone, DataNascimento, DataContratacao, CodigoLoja, CodigoDepto, Cargo, Salario)
VALUES
(1, 'Carlos Mendes', '67890123456', 'Rua da Paz, 1010, São Paulo, SP', '(11) 98765-6789', '1980-02-20', '2020-05-01', 1, 1, 'Farmacêutico', 3500.00),
(2, 'Ana Ferreira', '78901234567', 'Av. Ipiranga, 2020, Rio de Janeiro, RJ', '(21) 99876-7890', '1992-08-15', '2021-07-10', 2, 2, 'Administradora', 4500.00),
(3, 'José Almeida', '89012345678', 'Rua das Acácias, 3030, Porto Alegre, RS', '(51) 91234-8901', '1985-12-05', '2019-11-25', 1, 1, 'Atendente', 2500.00),
(4, 'Mariana Costa', '90123456789', 'Rua do Comércio, 4040, Curitiba, PR', '(41) 93456-7890', '1990-03-22', '2022-01-15', 3, 3, 'Vendedor', 2800.00),
(5, 'Roberto Lima', '01234567890', 'Av. das Nações, 5050, Brasília, DF', '(61) 94567-8901', '1988-09-30', '2018-10-10', 2, 3, 'Gerente de Vendas', 5000.00);
