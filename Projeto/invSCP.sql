CREATE DATABASE invscp
  CHARACTER SET utf8
  COLLATE utf8_general_ci;
 
USE invscp;


CREATE USER IF NOT EXISTS 'new_user'@'localhost' IDENTIFIED BY '123456';

GRANT ALL PRIVILEGES ON invscp To 'amdin'@'localhost' IDENTIFIED BY '123456';
 
CREATE TABLE IF NOT EXISTS Usuario (
    id varchar(40) NOT NULL PRIMARY KEY,
    grupo ENUM('Administrador de departamento','Chefe do patrimônio','Funcionário') NOT NULL,
    email varchar(60) NOT NULL UNIQUE,
    senha char(60) NOT NULL,
    nome char(255) NOT NULL,
    cpf varchar(11) NOT NULL UNIQUE
);
 
CREATE TABLE IF NOT EXISTS Departamento(
	id varchar(40) NOT NULL PRIMARY KEY
);

CREATE TABLE IF NOT EXISTS Predio(
	id varchar(40) NOT NULL PRIMARY KEY
);

CREATE TABLE IF NOT EXISTS Filial(
	id varchar(40) NOT NULL PRIMARY KEY
);

CREATE TABLE IF NOT EXISTS Endereco(
	id varchar(40) NOT NULL PRIMARY KEY,
	rua varchar(80) NOT NULL,
	cidade varchar(80) NOT NULL,
	cep varchar(8) NOT NULL,
	bairro varchar(80) NOT NULL
);

CREATE TABLE IF NOT EXISTS Sala(
	id varchar(40) NOT NULL PRIMARY KEY
);

CREATE TABLE IF NOT EXISTS Movimentacao (
    id varchar(40) NOT NULL PRIMARY KEY,
    dataDeRegistro date NOT NULL,
    motivo  ENUM('Doação', 'Permuta', 'Venda', 'Decisão Administrativa'),
    situacao ENUM('Aceite de Saída', 'Aceite de Entrada', 'Concluído') NOT NULL,
    origem varchar(40) NOT NULL,
    destino varchar(40) NOT NULL,
    FOREIGN KEY (origem)
        REFERENCES Sala(id),
    FOREIGN KEY (destino)
        REFERENCES Sala(id)
);
 
CREATE TABLE IF NOT EXISTS AceiteMovimentacao (
    idDoAceiteDeMovimentacao varchar(40) NOT NULL PRIMARY KEY,
    aprovado bit NOT NULL,
    dataAceite date NOT NULL
);