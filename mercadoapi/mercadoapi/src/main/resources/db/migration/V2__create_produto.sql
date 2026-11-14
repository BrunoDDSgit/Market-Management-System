CREATE TABLE PRODUTO (
id INT AUTO_INCREMENT PRIMARY KEY,
descricao VARCHAR(255),
codigo_barras VARCHAR(50) NOT NULL,
quantidade_estoque INT,
preco DECIMAL(10,2) NOT NULL,
categoria_id INT NOT NULL,

CONSTRAINT fk_produto_categoria
    FOREIGN KEY (categoria_id)
    REFERENCES CATEGORIA(id)
);