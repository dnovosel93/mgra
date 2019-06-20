USE Bolnica
GO

INSERT INTO Drzava(Naziv) VALUES ('Hrvatska')
INSERT INTO Drzava(Naziv) VALUES ('Slovenija')
INSERT INTO Drzava(Naziv) VALUES ('Bosna i Hercegovina')
INSERT INTO Drzava(Naziv) VALUES ('Republika srpska')

INSERT INTO Grad(Naziv,PostanskiBroj,DrzavaId) 
VALUES ('Zagreb','10000',1)
INSERT INTO Grad(Naziv,PostanskiBroj,DrzavaId) 
VALUES ('Varazdin','42000',1)
INSERT INTO Grad(Naziv,PostanskiBroj,DrzavaId) 
VALUES ('Split','21000',1)