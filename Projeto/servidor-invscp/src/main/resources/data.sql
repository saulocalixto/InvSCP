USE invscp;

SET FOREIGN_KEY_CHECKS = 0;
TRUNCATE TABLE Usuario;
INSERT INTO Usuario (id, grupo, email, senha, nome, cpf) 
	VALUES (
		'seilaquediabosfazisso',
		'ADMINISTRADOR_DEPARTAMENTO',
		'admin@admin.com',
		'$2a$04$BBoGbYsVvFmu/TkxVA18k.5cuAbGy/JCXWVJgRJRAs.bHk2AJicEC',
		'admin',
		'02883758077'
	);

TRUNCATE TABLE Departamento;
INSERT INTO Departamento (id, nome) VALUES 
	('departamento001', 'Departamento A'),
	('departamento002', 'Departamento B');

TRUNCATE TABLE Endereco;
INSERT INTO Endereco (id, rua, cidade, cep, bairro) VALUES
	('endereco001', 'Rua de Testes', 'Goiânia', '74670000', 'Bairro de Testes');

TRUNCATE TABLE Filial;
INSERT INTO Filial (id) VALUES
	('filial001'),
	('filial002');

TRUNCATE TABLE Predio;
INSERT INTO Predio (id, idEndereco, idFilial) VALUES
	('predio001', 'endereco001', 'filial001');

TRUNCATE TABLE Sala;
INSERT INTO Sala (id, numeroSala, idDepartamento, idPredio) VALUES
	('sala001', 1, 'departamento001', 'predio001'),
	('sala002', 2, 'departamento002', 'predio001'),
	('sala003', 3, 'departamento002', 'predio001');
	
-- A seguinte declaração deve estar sempre ao final do arquivo.
SET FOREIGN_KEY_CHECKS = 1;