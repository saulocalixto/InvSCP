CREATE DATABASE IF NOT EXISTS invscp
  CHARACTER SET utf8
  COLLATE utf8_general_ci;
 
USE invscp;

DROP USER 'admin'@'%';
CREATE USER IF NOT EXISTS 'admin'@'%' IDENTIFIED WITH mysql_native_password BY '123456';
ALTER USER 'admin'@'%' IDENTIFIED WITH mysql_native_password BY '123456';
GRANT ALL PRIVILEGES ON invscp.* TO 'admin'@'%';

DROP USER 'admin'@'localhost';
CREATE USER IF NOT EXISTS 'admin'@'localhost' IDENTIFIED WITH mysql_native_password BY '123456';
ALTER USER 'admin'@'localhost' IDENTIFIED WITH mysql_native_password BY '123456';
GRANT ALL PRIVILEGES ON invscp.* TO 'admin'@'localhost';

FLUSH PRIVILEGES;

SET FOREIGN_KEY_CHECKS = 0;
DROP TABLE IF EXISTS `aceitemovimentacao`;        
DROP TABLE IF EXISTS `departamento`;               
DROP TABLE IF EXISTS `endereco`;
DROP TABLE IF EXISTS `login`;                      
DROP TABLE IF EXISTS `movimentacao`;               
DROP TABLE IF EXISTS `predio`;                     
DROP TABLE IF EXISTS `sala`;                       
DROP TABLE IF EXISTS `sessao`;                     
DROP TABLE IF EXISTS `usuario`;
DROP TABLE IF EXISTS `grupoDeMaterial`;
DROP TABLE IF EXISTS `ordemDeServico`;
DROP TABLE IF EXISTS `bempatrimonial`;
SET FOREIGN_KEY_CHECKS = 1;

CREATE TABLE IF NOT EXISTS Departamento(
	id varchar(40) NOT NULL PRIMARY KEY,
	nome varchar(60) NOT NULL
);

CREATE TABLE IF NOT EXISTS Usuario (
    id varchar(40) NOT NULL PRIMARY KEY,
    grupo ENUM('ADMINISTRADOR_DEPARTAMENTO','CHEFE_PATRIMONIO','FUNCIONARIO') NOT NULL,
    email varchar(60) NOT NULL UNIQUE,
    senha char(60) NOT NULL,
    nome char(255) NOT NULL,
    cpf varchar(11) NOT NULL UNIQUE,
    idDepartamento varchar(40) NOT NULL,
    FOREIGN KEY (idDepartamento)
			REFERENCES Departamento(id)
);
 
CREATE TABLE IF NOT EXISTS Login (
    id varchar(40) NOT NULL PRIMARY KEY,
    token varchar(60) NULL UNIQUE,
    idUsuario varchar(40) NOT NULL,
    FOREIGN KEY (idUsuario)
            REFERENCES Usuario(id)
);

CREATE TABLE IF NOT EXISTS Sessao(
	id varchar(40) NOT NULL PRIMARY KEY,
	token varchar(40) NOT NULL UNIQUE,
	idUsuario varchar(40) NOT NULL,
	FOREIGN KEY (idUsuario)
            REFERENCES Usuario(id)
);

CREATE TABLE IF NOT EXISTS Endereco(
	id varchar(40) NOT NULL PRIMARY KEY,
	rua varchar(80) NOT NULL,
	cidade varchar(80) NOT NULL,
	cep varchar(8) NOT NULL,
	bairro varchar(80) NOT NULL
);

CREATE TABLE IF NOT EXISTS Predio(
	id varchar(40) NOT NULL PRIMARY KEY,
	idEndereco varchar(40) NOT NULL,
	nome varchar(60) NOT NULL,
	FOREIGN KEY (idEndereco)
        REFERENCES Endereco(id)
);

CREATE TABLE IF NOT EXISTS Sala(
	id varchar(40) NOT NULL PRIMARY KEY,
	numeroSala numeric NOT NULL,
	idDepartamento varchar(40) NULL,
	idPredio varchar(40) NULL,
	FOREIGN KEY (idDepartamento)
        REFERENCES Departamento(id),
	FOREIGN KEY (idPredio)
        REFERENCES Predio(id)
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
    id varchar(40) NOT NULL PRIMARY KEY,
    aprovado bit NOT NULL,
    dataAceite date NOT NULL
);

CREATE TABLE IF NOT EXISTS GrupoDeMaterial (
	id varchar(40) NOT NULL PRIMARY KEY,
	vidaUtil numeric NOT NULL,
	taxaDeDepreciacao numeric NOT NULL
);

CREATE TABLE IF NOT EXISTS OrdemDeServico (
	id varchar(40) NOT NULL PRIMARY KEY,
	identificadorDaOS varchar(40) NOT NULL,
	bem varchar(40) NOT NULL,
	motivo varchar(40) NOT NULL,
	observacao varchar(40) NULL,
	dataAbertura varchar(40) NOT NULL,
	dataEncerramento varchar(40) NULL,
	nomeDaPrestadora varchar(40) NOT NULL,
	situacao varchar(40) NOT NULL
);

CREATE TABLE IF NOT EXISTS BemPatrimonial (
    id varchar(40) NOT NULL PRIMARY KEY,
	localAtual varchar(40) NOT NULL,
	numeroDeTombamento varchar(40) NOT NULL,
	denominacao varchar(40) NOT NULL,
	dataDeAquisicao varchar(40) NOT NULL,
	especificacao varchar(40) NOT NULL,
	garantia varchar(40) NOT NULL,
	marca varchar(40) NOT NULL,
	valorDeCompra numeric NOT NULL,
	situacao varchar(40) NOT NULL,
	notaFiscal varchar(40) NOT NULL,
	grupoDeMaterial varchar(40) NOT NULL,
	ordemDeServico varchar(40) NULL,
	FOREIGN KEY (grupoDeMaterial)
        REFERENCES GrupoDeMaterial(id),
	FOREIGN KEY (localAtual)
        REFERENCES Sala(id),
	FOREIGN KEY (ordemDeServico)
        REFERENCES OrdemDeServico(id)
);

CREATE TABLE IF NOT EXISTS Baixa (
	id varchar(40) NOT NULL PRIMARY KEY,
	idBem varchar(40) NOT NULL,
	dataBaixa varchar(20) NOT NULL,
	observacao varchar(100) NULL,
	motivo numeric NULL,
	FOREIGN KEY (idBem)
	REFERENCES BemPatrimonial(id)
	);

CREATE INDEX sala_numero
ON Sala (numeroSala);

CREATE INDEX usuario_Email
ON Usuario (email);