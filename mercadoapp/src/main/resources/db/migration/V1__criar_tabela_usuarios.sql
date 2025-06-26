CREATE TABLE usuarios (
 id bigint(20) NOT NULL AUTO_INCREMENT,
 nome varchar(100) DEFAULT NULL,
 telefone varchar(20) DEFAULT NULL,
 email varchar(50) DEFAULT NULL,
 senha varchar(30) DEFAULT NULL,
 PRIMARY KEY (id)
);