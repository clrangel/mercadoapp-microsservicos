CREATE TABLE pedidos (
    id BINARY(16) PRIMARY KEY,
    date_moment TIMESTAMP NOT NULL,
    valor_total DECIMAL(19, 2) NOT NULL,
    status VARCHAR(255) NOT NULL,
    usuario_id BIGINT NOT NULL
);

CREATE TABLE item_pedidos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    quantidade INTEGER NOT NULL,
    valor_unitario DECIMAL(19, 2) NOT NULL,
    pedido_id BINARY(16) NOT NULL,
    produto_id BIGINT NOT NULL,
    CONSTRAINT fk_itempedido_pedido FOREIGN KEY (pedido_id) REFERENCES pedidos(id) ON DELETE CASCADE
);
