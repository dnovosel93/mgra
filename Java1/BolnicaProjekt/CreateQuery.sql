USE MASTER 
GO

IF EXISTS(SELECT * FROM sys.databases WHERE name=N'Bolnica')
DROP DATABASE Bolnica
GO

CREATE DATABASE Bolnica
GO

USE Bolnica
GO

/*OSOBA*/
IF EXISTS(SELECT * FROM sys.tables WHERE name=N'Osoba')
DROP TABLE Osoba
GO

CREATE TABLE Osoba(
	Id INT PRIMARY KEY IDENTITY,
	Ime NVARCHAR(30),
	Prezime NVARCHAR(30),
	OIB nvarchar(11),
	CONSTRAINT UQ_OIB UNIQUE(OIB)
)
GO

/*Create */
CREATE PROC CreateOsoba(@Ime NVARCHAR(30),@Prezime NVARCHAR(30),@OIB NVARCHAR(11),@Id INT OUTPUT)
AS
BEGIN
	INSERT INTO Osoba(Ime,Prezime,OIB) VALUES (@Ime,@Prezime,@OIB)
	SET @Id=SCOPE_IDENTITY()
	RETURN @Id
END
GO

/*DRZAVA*/
IF EXISTS(SELECT * FROM sys.tables WHERE name=N'Drazva')
DROP TABLE Drzava
GO

CREATE TABLE Drzava(
Id INT PRIMARY KEY IDENTITY,
Naziv NVARCHAR(20),
)
GO

/*View*/
CREATE VIEW ReadDrzave
AS
	SELECT Id,Naziv FROM Drzava
GO

/*GRAD*/
IF EXISTS(SELECT * FROM sys.tables WHERE name=N'Grad')
DROP TABLE Grad
GO

CREATE TABLE Grad(
Id INT PRIMARY KEY IDENTITY,
Naziv NVARCHAR(20),
PostanskiBroj NVARCHAR(10),
DrzavaId INT FOREIGN KEY REFERENCES Drzava(Id)
)
GO

CREATE PROC ReadGradoviDrzave(@DrzavaId INT)
AS
BEGIN 
	SELECT Id,Naziv,PostanskiBroj FROM Grad WHERE DrzavaId = @DrzavaId
END
GO

SELECT * FROM Grad

/*ADRESA*/
IF EXISTS(SELECT * FROM sys.tables WHERE name=N'Adresa')
DROP TABLE Adresa
GO

CREATE TABLE Adresa(
	Id INT PRIMARY KEY IDENTITY,
	Ulica NVARCHAR(30),
	KucniBroj NVARCHAR(2),
	GradId INT FOREIGN KEY REFERENCES Grad(Id)
)
GO

CREATE PROC CreateAdreasa(@Ulica NVARCHAR(30),@KucniBroj NVARCHAR(2),@GradId INT,@Id INT OUTPUT)
AS
BEGIN 
	INSERT INTO Adresa(Ulica,KucniBroj,GradId) VALUES (@Ulica,@KucniBroj,@GradId)
	SET @Id=SCOPE_IDENTITY()
	RETURN @Id
END
GO


/*UREDAJ*/
IF EXISTS(SELECT * FROM sys.tables WHERE name=N'Uredaj')
DROP TABLE Uredaj
GO

CREATE TABLE Uredaj(
	Id INT PRIMARY KEY IDENTITY,
	TelefonPosao NVARCHAR(30),
	TelefonKucni NVARCHAR(30),
	Mobitel NVARCHAR(30),
	Pager NVARCHAR(30),
	Fax NVARCHAR(30),
	Email NVARCHAR(50)
)
GO

/*Create*/
CREATE PROC CreateUredaj(@TelefonPosao NVARCHAR(30),@TelefonKucni NVARCHAR(30),@Mobitel nvarchar(30),
	@Pager NVARCHAR(30),@Fax NVARCHAR(30),@Email NVARCHAR(50),@Id INT OUTPUT)
AS
BEGIN 
	INSERT INTO Uredaj(TelefonPosao,TelefonKucni,Mobitel,Pager,Fax,Email) VALUES (@TelefonPosao,@TelefonKucni,@Mobitel,@Pager,@Fax,@Email)
	SET @Id=SCOPE_IDENTITY()
	RETURN @Id
END
GO

/*KONTAKT*/
IF EXISTS(SELECT * FROM sys.tables WHERE name=N'Kontakt')
DROP TABLE Kontakt
GO

CREATE TABLE Kontakt(
	Id INT PRIMARY KEY IDENTITY,
	TrenutnaAdresaId INT FOREIGN KEY REFERENCES Adresa(Id),
	StalnaAdresaId INT FOREIGN KEY REFERENCES Adresa(Id),
	UredajId INT FOREIGN KEY REFERENCES Uredaj(Id)
)
GO

/*Create*/
CREATE PROC CreateKontakt(@TrenutnaAdresaId INT,@StalnaAdresaId INT,@UredajId INT,@Id INT OUTPUT)
AS
BEGIN
	INSERT INTO Kontakt(TrenutnaAdresaId,StalnaAdresaId,UredajId) VALUES (@TrenutnaAdresaId,@StalnaAdresaId,@UredajId)
	SET @Id = SCOPE_IDENTITY()
	RETURN @Id
END
GO

/*RODBINA*/
IF EXISTS(SELECT * FROM sys.tables WHERE name=N'Rodbina')
DROP TABLE Rodbina
GO

CREATE TABLE Rodbina(
	Id INT PRIMARY KEY IDENTITY,
	OsobaId INT FOREIGN KEY REFERENCES Osoba(Id),
	OdnosSaPacijentom nvarchar(30),
	KontaktId INT FOREIGN KEY REFERENCES Kontakt(Id)
)
GO

/*Create*/
CREATE PROC CreateRodbina(@OsobaId INT,@OdnosSaPacijentom NVARCHAR(30),@KontaktId INT,@Id INT OUTPUT)
AS
BEGIN
	INSERT INTO Rodbina(OsobaId,OdnosSaPacijentom,KontaktId) VALUES (@OsobaId,@OdnosSaPacijentom,@KontaktId)
	SET @Id=SCOPE_IDENTITY()
	RETURN @Id
END
GO

/*OSOBNI_DETALJ*/
IF EXISTS(SELECT * FROM sys.tables WHERE name=N'OsobnuDetalji')
DROP TABLE OsobniDetalji
GO

CREATE TABLE OsobnIdetalji(
	Id INT PRIMARY KEY IDENTITY,
	BracniStatus BIT,
	BrojUzdrzavanihOsoba INT,
	Visina INT,
	Kilaza INT,
	KrvnaGrupa NVARCHAR(3),
	CONSTRAINT CH_KrvnaGrupa CHECK(KrvnaGrupa IN('A+','B+','AB+','0+','A-','B-','AB-','0-'))
)
GO

/*Create*/
CREATE PROC CreateOsobniDetalj(@BracniStatus BIT,@BrojUzdrzavanihOsoba INT,@Visina INT,@Kilaza INT,@KrvnaGrupa NVARCHAR(3),@Id INT OUTPUT)
AS
BEGIN
	INSERT INTO OsobnIdetalj(BracniStatus,BrojUzdrzavanihOsoba,Visina,Kilaza,KrvnaGrupa) VALUES (@BracniStatus,@BrojUzdrzavanihOsoba,@Visina,@Kilaza,@KrvnaGrupa)
	SET @Id=SCOPE_IDENTITY()
	RETURN @Id
END
GO

/*PROFESIJA*/
IF EXISTS(SELECT * FROM sys.tables WHERE name=N'Profesija')
DROP TABLE Profesija
GO

CREATE TABLE Profesija(
	Id int primary key IDENTITY,
	Naziv nvarchar(30),
	GodisnjiPrihod money
)
GO

/*Create*/
CREATE PROC CreateProfesija(@Naziv NVARCHAR(30),@GodisnjiPrihod MONEY,@Id INT OUTPUT)
AS
BEGIN 
	INSERT INTO Profesija(Naziv,GodisnjiPrihod) VALUES (@Naziv,@GodisnjiPrihod)
	SET @Id=SCOPE_IDENTITY()
	RETURN @Id
END
GO

/*LIFESTYLE*/
IF EXISTS(SELECT * FROM sys.tables WHERE name=N'Lifestyle')
DROP TABLE Lifestyle
GO

CREATE TABLE Lifestyle(
	Id INT PRIMARY KEY IDENTITY,
	Vegetarijanac BIT,
	Pusac BIT,
	AvgBrojCigareta INT,
	AlkoholnaPica BIT,
	AvgBrojAlkoholnihPica INT,
	AvgBrojKava INT,
	AvgBrojSlatkihPica INT,
	RedovniObroci BIT,
	KucnaKuhinja BIT
)
GO

/*Create*/
CREATE PROC CreteLifestyle(@Vegetarijanac BIT,@Pusac BIT,@AvgBrojCigareta INT,@AlkoholnaPica BIT,@AvgBrojAlkoholnihPica INT,@AvgBrojKava INT,@AvgBrojSlatkihPica INT,@RedovniObroci BIT,@KucnaKuhinja BIT,@Id INT OUTPUT)
AS
BEGIN
	INSERT INTO Lifestyle(Vegetarijanac,Pusac,AvgBrojCigareta,AlkoholnaPica,AvgBrojAlkoholnihPica,AvgBrojKava,AvgBrojSlatkihPica,RedovniObroci,KucnaKuhinja)
	VALUES (@Vegetarijanac,@Pusac,@AvgBrojCigareta,@AlkoholnaPica,@AvgBrojAlkoholnihPica,@AvgBrojKava,@AvgBrojSlatkihPica,@RedovniObroci,@KucnaKuhinja)
	SET @Id=SCOPE_IDENTITY()
	RETURN @Id
END
GO

/*OSNOVNA_PRITUZBA*/
IF EXISTS(SELECT * FROM sys.tables WHERE name=N'OsnovnaPrituzba')
DROP TABLE OsnovnaPrituzba
GO

CREATE TABLE OsnovnaPrituzba(
	Id INT PRIMARY KEY IDENTITY,
	Izjava NVARCHAR(300),
	PrijasnjiTretmani NVARCHAR(300),
	BolnickoLjecenje BIT
)
GO

/*Create*/
CREATE PROC CreateOsnovnaPrituzba(@Izjava NVARCHAR(300),@PrijasnjiTretmani NVARCHAR(300),@BolnickoLjecenje BIT,@Id INT OUTPUT)
AS
BEGIN 
	INSERT INTO OsnovnaPrituzba(Izjava,PrijasnjiTretmani,BolnickoLjecenje) values (@Izjava,@PrijasnjiTretmani,@BolnickoLjecenje)
	set @Id=SCOPE_IDENTITY()
	RETURN @Id
END 
GO 

/*MEDICINSKI_DETALJI*/
IF EXISTS(SELECT * FROM sys.tables WHERE name=N'MedicinskiDetalji')
DROP TABLE MedicinskiDetalji
GO

CREATE TABLE MedicinskiDetalji(
	Id INT PRIMARY KEY IDENTITY,
	Dijabeticar BIT,
	Hipertenzivna BIT,
	SrcanoStanje NVARCHAR(300) ,
	RespiratornoStanje NVARCHAR(300),
	ProbavnoStanje NVARCHAR(300),
	OrtopedskoStanje NVARCHAR(300),
	MisicnoStanje NVARCHAR(300),
	NeuroloskoStanje NVARCHAR(300),
	Alergije NVARCHAR(300),
	LijekoviNuspojave NVARCHAR(300),
	PrijasnjeOperacije NVARCHAR(300)
)
GO

/*Create*/
CREATE PROC CreateMedicinskiDetalji(@Dijabeticar BIT,@Hipertenzivna BIT,@SrcanoStanje NVARCHAR(300),@RespiratornoStanje NVARCHAR(300),@ProbavnoStanje NVARCHAR(300),@OrtopedskoStanje NVARCHAR(300),
@MisicnoStanje NVARCHAR(300),@NeuroloskoStanje NVARCHAR(300),@Alergije NVARCHAR(300),@LijekoviNuspojave NVARCHAR(300),@PrijasnjeOperacije NVARCHAR(300),@Id INT OUTPUT)
AS
BEGIN
	INSERT INTO MedicinskiDetalji(Dijabeticar,Hipertenzivna,SrcanoStanje,RespiratornoStanje,ProbavnoStanje,OrtopedskoStanje,MisicnoStanje,NeuroloskoStanje,Alergije,LijekoviNuspojave,PrijasnjeOperacije)
	VALUES (@Dijabeticar,@Hipertenzivna,@SrcanoStanje,@RespiratornoStanje,@ProbavnoStanje,@OrtopedskoStanje,@MisicnoStanje,@NeuroloskoStanje,@Alergije,@LijekoviNuspojave,@PrijasnjeOperacije)
	SET @Id=SCOPE_IDENTITY()
	RETURN @Id
END
GO

/*CRF*/
IF EXISTS(SELECT * FROM sys.tables WHERE name=N'CRF')
DROP TABLE CRF
GO

CREATE TABLE CRF(
	IdOutpatient INT PRIMARY KEY IDENTITY,
	OsobaId INT FOREIGN KEY REFERENCES Osoba(Id),
	DatumRodjenja DATE,
	Spol NVARCHAR(1),	
	KontaktId INT FOREIGN KEY REFERENCES Kontakt(Id),
	RodbinaId INT FOREIGN KEY REFERENCES Rodbina(Id),
	OsobniDetaljiId INT FOREIGN KEY REFERENCES OsobniDetalji(Id),
	ProfesijaId INT FOREIGN KEY REFERENCES Profesija(Id),
	LifestyleId INT FOREIGN KEY REFERENCES Lifestyle(Id),
	OsnovnaPrituzbaId INT FOREIGN KEY REFERENCES OsnovnaPrituzba(Id),
	MedicinskiDetaljiId INT FOREIGN KEY REFERENCES MedicinskIdetalji(Id),
	DatumRegistracije DATE,
	CONSTRAINT CH_Spol CHECK(Spol IN('M','Z'))
)
GO

/*Create*/
CREATE PROC CreateCRF(@OsobaId INT,@DatumRodjenja DATE,@Spol NVARCHAR(1),@KontaktId INT,@RodbinaId INT,@OsobnIdetaljiId INT,@ProfesijaId INT,@LifestyleId INT,@OsnovnaPrituzbaId INT,@MedicinskiDetaljiId INT,@DatumRegistracije DATE,@Id INT OUTPUT)
AS
BEGIN
	INSERT INTO CRF(OsobaId,DatumRodjenja,Spol,KontaktId,RodbinaId,OsobniDetaljiId,ProfesijaId,LifestyleId,OsnovnaPrituzbaId,MedicinskiDetaljiId,DatumRegistracije)
	VALUES(@OsobaId,@DatumRodjenja,@Spol,@KontaktId,@RodbinaId,@OsobniDetaljiId,@ProfesijaId,@LifestyleId,@OsnovnaPrituzbaId,@MedicinskiDetaljiId,@DatumRegistracije)
	SET @Id = SCOPE_IDENTITY()
	RETURN @Id
END
GO

/*MRF*/
IF EXISTS(SELECT * FROM sys.tables WHERE name=N'MRF')
DROP TABLE MRF
GO

CREATE TABLE MRF(
	Id INT PRIMARY KEY IDENTITY,
	OsobaId INT FOREIGN KEY REFERENCES Osoba(Id),
	Spol NVARCHAR(1),
	DatumRodjenja DATE,
	OpciZapis NVARCHAR(300),
	Kontakt1 NVARCHAR(20),
	Kontakt2 NVARCHAR(20),
	RodbinaId INT null FOREIGN KEY REFERENCES Osoba(Id),
	CONSTRAINT CH_Spol_MRF CHECK(Spol IN('M','Z'))
)
GO

/*Create*/
CREATE PROC CreateMRF(@OsobaId INT,@Spol NVARCHAR(1),@DatumRodjenja DATE,@OpciZapis NVARCHAR(300),@Kontakt1 NVARCHAR(20),@Kontakt2 NVARCHAR(20),@RodbinaId INT,@Id INT OUTPUT)
AS
BEGIN
	INSERT INTO MRF(OsobaId,Spol,DatumRodjenja,OpciZapis,Kontakt1,Kontakt2,RodbinaId) values (@OsobaId,@Spol,@DatumRodjenja,@OpciZapis,@Kontakt1,@Kontakt2,@RodbinaId)
	SET	@Id=SCOPE_IDENTITY()
	RETURN @Id
END
GO

/*TIP ZAPOSLENIKA*/
IF EXISTS(SELECT * FROM sys.tables WHERE name=N'TipZaposlenika')
DROP TABLE TipZaposlenika
GO

CREATE TABLE TipZaposlenika(
	Id INT PRIMARY KEY IDENTITY,
	Naziv NVARCHAR(20)
)
GO

/*ZAPOSLENIK*/
IF EXISTS(SELECT * FROM sys.tables WHERE name = N'Zaposlenik')
DROP TABLE Zaposlenik
GO

CREATE TABLE Zaposlenik(
	Id INT PRIMARY KEY IDENTITY,
	OsobaId INT FOREIGN KEY REFERENCES Osoba(Id),
	TipZaposlenika INT FOREIGN KEY REFERENCES TipZaposlenika(Id),
	Placa MONEY
)
GO

/*KREDITNA KARTICA*/
IF EXISTS(SELECT * FROM sys.tables WHERE name = N'KreditnaKartica')
DROP TABLE KreditnaKartica
GO

CREATE TABLE KreditnaKartica(
	Id INT PRIMARY KEY IDENTITY,
	BrojKartice NVARCHAR(12),
	DatumIsteka DATE,
	Tip NVARCHAR(15)
)
GO

/*RACUN*/
IF EXISTS(SELECT * FROM sys.tables WHERE name = N'Racun')
DROP TABLE Racun
GO

CREATE TABLE Racun(
	BrojRacuna NVARCHAR(12) PRIMARY KEY,
	UkupnaCijena MONEY,
	KreditnaKarticaId INT NULL FOREIGN KEY REFERENCES KreditnaKartica(Id)
)
GO

/*ProdanaStavka*/
IF EXISTS(SELECT * FROM sys.tables WHERE name = N'ProdanaStavka')
DROP TABLE ProdanaStavka
GO

CREATE TABLE ProdanaStavka(
	Id INT PRIMARY KEY IDENTITY,
	RacunId NVARCHAR(12) FOREIGN KEY REFERENCES Racun(BrojRacuna)	
)
GO

/*LabaratorijskoTestiranje*/
IF EXISTS(SELECT * FROM sys.tables WHERE name = N'LabaratorijskoTestiranje')
DROP TABLE LabaratorijskoTestiranje
GO

CREATE TABLE LabaratorijskoTestiranje(
	Id INT PRIMARY KEY IDENTITY,
	Naziv NVARCHAR(50) 
)
GO

/*NARUCENALABARATORIJSKATESTIRANA*/
IF EXISTS(SELECT * FROM sys.tables WHERE name = N'NarucenaLabaratorijskaTestirana')
DROP TABLE NarucenaLabaratorijskaTestirana
GO

CREATE TABLE NarucenaLabaratorijskaTestirana(
	Id INT PRIMARY KEY IDENTITY,
	CrfId INT FOREIGN KEY REFERENCES Crf(IdOutpatient),
	LabaratorijskoTestiranjeId INT FOREIGN KEY REFERENCES LabaratorijskoTestiranje(Id),
	RezultatTestiranja NVARCHAR(200),
	DatumNarudbe DATE,
	DatumIsporuke DATE,
	ProdanaStavkaId INT NULL FOREIGN KEY REFERENCES ProdanaStavka(Id)
)
GO

/*TIP TRETMANA*/
IF EXISTS(SELECT * FROM sys.tables WHERE name = N'TipTretmana')
DROP TABLE TipTretmana
GO

CREATE TABLE TipTretmana(
	Id INT PRIMARY KEY IDENTITY,
	Naziv NVARCHAR(20)
)
GO

/*TRETMAN*/
IF EXISTS(SELECT * FROM sys.tables WHERE name = N'Tretman')
DROP TABLE Tretman
GO

CREATE TABLE Tretman(
	Id INT PRIMARY KEY IDENTITY,
	Naziv NVARCHAR(20),
	Cijena MONEY,
	PotrebnaOprema NVARCHAR(100),
	TipTretmanaId INT FOREIGN KEY REFERENCES TipTretmana(Id)
)
GO

/*NARUCENITRETMANI*/
IF EXISTS(SELECT * FROM sys.tables WHERE name = N'NaruceniTretmani')
DROP TABLE NaruceniTretmani
GO

CREATE TABLE NaruceniTretmani(
	Id INT PRIMARY KEY IDENTITY,
	CrfId INT FOREIGN KEY REFERENCES Crf(IdOutpatient),
	IzvrsiteljTretmanaId INT FOREIGN KEY REFERENCES Zaposlenik(Id),
	TretmanId INT FOREIGN KEY REFERENCES Tretman(Id),
	DatumNarudbe DATE,
	DatumIsporuke DATE,
	ProdanaStavkaId INT FOREIGN KEY REFERENCES ProdanaStavka(Id)
)
GO


/*LIJEK*/
IF EXISTS(SELECT * FROM sys.tables WHERE name = N'Lijek')
DROP TABLE Lijek
GO

CREATE TABLE Lijek(
	Id INT PRIMARY KEY IDENTITY,
	Naziv NVARCHAR(20),
	Cijena MONEY
)
GO

/*KUPLJENILIJEK*/
IF EXISTS(SELECT * FROM sys.tables WHERE name = N'KupljeniLijek')
DROP TABLE KupljeniLijek
GO

CREATE TABLE KupljeniLijek(
	Id INT PRIMARY KEY IDENTITY,
	LijekID INT FOREIGN KEY REFERENCES Lijek(Id),
	DatumKupnje DATE,
	StavkaId INT FOREIGN KEY REFERENCES ProdanaStavka(Id)
)
GO
