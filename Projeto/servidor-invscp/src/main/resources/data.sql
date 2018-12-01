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
	
TRUNCATE TABLE BemPatrimonial;
INSERT INTO BemPatrimonial (id,	localAtual,	numeroDeTombamento,	denominacao, dataDeAquisicao,
							especificacao, garantia, marca, valorDeCompra, situacao, notaFiscal, 
							grupoDeMaterial, ordemDeServico) VALUES
	('bem001', 'sala001', '666', 'Computador', '20180202', 'especificacao', 'garantia', 'LG', 666.66, 'Coisado', '34234253452', 'Hardware', 'OS001'),
	('bem002', 'sala002', '111', 'Cadeira', '20180202', 'especificacao', 'garantia', 'Onion', 123.00, 'Coisado', '34234253452', 'Furniture', 'OS003'),
	('bem003', 'sala003', '999', 'Livro', '20180202', 'especificacao', 'garantia', 'Penguin', 32.32, 'Coisado', '34234253452', 'Assets', 'OS005');

	
-- A seguinte declaração deve estar sempre ao final do arquivo.
SET FOREIGN_KEY_CHECKS = 1;