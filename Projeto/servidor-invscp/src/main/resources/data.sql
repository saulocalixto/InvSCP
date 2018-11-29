USE invscp;

INSERT INTO Usuario (id, grupo, email, senha, nome, cpf) 
	VALUES (
		'seilaquediabosfazisso',
		'ADMINISTRADOR_DEPARTAMENTO',
		'admin@admin.com',
		'$2a$04$BBoGbYsVvFmu/TkxVA18k.5cuAbGy/JCXWVJgRJRAs.bHk2AJicEC',
		'admin',
		'02883758077'
	);

INSERT INTO Departamento (id, nome) VALUES 
	('departamento001', 'Departamento A'),
	('departamento002', 'Departamento B');

INSERT INTO Endereco (id, rua, cidade, cep, bairro) VALUES
	('endereco001', 'Rua de Testes', 'Goiânia', '74670000', 'Bairro de Testes');

INSERT INTO Filial (id) VALUES
	('filial001'),
	('filial002');

INSERT INTO Predio (id, idEndereco, idFilial) VALUES
	('predio001', 'endereco001', 'filial001');

INSERT INTO Sala (id, idDepartamento, idPredio) VALUES
	('sala001', 'departamento001', 'predio001'),
	('sala002', 'departamento002', 'predio001'),
	('sala003', 'departamento002', 'predio001');