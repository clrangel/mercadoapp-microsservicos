CREATE TABLE pagamentos (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    pedido_id BINARY(16) NOT NULL,
    valor DECIMAL(10,2) NOT NULL,
    forma_pagamento VARCHAR(30) NOT NULL,
    status VARCHAR(30) NOT NULL,
    data_pagamento TIMESTAMP(6)
);
