
CREATE TABLE PRODUTO (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    descricao VARCHAR(255) NOT NULL,
    codigo_barras VARCHAR(50) NOT NULL,
    quantidade_estoque INT NOT NULL DEFAULT 0,
    preco DECIMAL(10,2) NOT NULL,
    desconto DECIMAL(5,2) NOT NULL DEFAULT 0,
    validade DATE,

    categoria_id INT NOT NULL,

    CONSTRAINT fk_produto_categoria
     FOREIGN KEY (categoria_id)
         REFERENCES CATEGORIA(id)
);
