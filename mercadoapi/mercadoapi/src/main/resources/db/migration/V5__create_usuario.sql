CREATE TABLE USUARIO (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    telefone VARCHAR(20) NOT NULL,
    senha VARCHAR(255) NOT NULL,

    id_status INT NOT NULL,
    id_cargo INT NOT NULL,

    data_hora_cadastro DATETIME(2) NOT NULL
        DEFAULT CURRENT_TIMESTAMP(2),

    CONSTRAINT chk_email
        CHECK (email REGEXP '^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$'),

    CONSTRAINT chk_telefone
        CHECK (telefone REGEXP '^[0-9]{10,11}$'),

    CONSTRAINT fk_usuario_status
        FOREIGN KEY (id_status) REFERENCES STATUS(id),

    CONSTRAINT fk_usuario_cargo
        FOREIGN KEY (id_cargo) REFERENCES CARGO(id)
);