ALTER TABLE Usuario ADD COLUMN idDepartamento varchar(40) NULL;
ALTER TABLE Usuario  ADD FOREIGN KEY (idDepartamento)
      REFERENCES Departamento(id);
ALTER TABLE Departamento ADD COLUMN nome varchar(60) NOT NULL;
ALTER TABLE Sala MODIFY COLUMN idDepartamento varchar(40) NULL;
ALTER TABLE Sala MODIFY COLUMN idPredio varchar(40) NULL;
ALTER TABLE Sala ADD COLUMN numeroSala numeric NOT NULL;
CREATE INDEX sala_numero
ON Sala (numeroSala);
