ALTER TABLE Usuario ADD COLUMN idDepartamento varchar(40) NULL;
ALTER TABLE Usuario  ADD FOREIGN KEY (idDepartamento)
      REFERENCES Departamento(id);
ALTER TABLE Departamento ADD COLUMN nome varchar(60) NOT NULL;
ALTER TABLE Sala MODIFY COLUMN idDepartamento varchar(40) NULL;
ALTER TABLE Sala MODIFY COLUMN idPredio varchar(40) NULL;
ALTER TABLE Sala ADD COLUMN numeroSala numeric NOT NULL;
CREATE INDEX sala_numero
ON Sala (numeroSala);
CREATE INDEX usuario_Email
ON Usuario (email);
alter table Predio drop foreign key Predio_ibfk_2;
Alter table Predio drop key idFilial;
alter table Predio drop column idFilial;
DROP TABLE IF EXISTS `filial`;
ALTER TABLE Predio ADD COLUMN nome varchar(60) NOT NULL;