CREATE TABLE produto (
    id serial PRIMARY KEY,
    nome VARCHAR(50),
    quantidade INTEGER
);

INSERT INTO produto (id, nome, quantidade) VALUES (1,'banana', 150);
INSERT INTO produto (id, nome, quantidade) VALUES (2,'laranja', 300);

select * from produto
