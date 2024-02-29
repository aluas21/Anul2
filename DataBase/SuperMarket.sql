    -----LAB 1-------
	
	--CREARE BAZA DE DATE
create database SuperMarket;
use SuperMarket;

	--CREARE TABELE
create table Client(
	idClient int,
	nume varchar(20),
	varsta int,
	primary key(idClient)
);

create table SuperMarket(
	idSuperMarket int,
	denumire varchar(20),
	adresa varchar (20),
	nrTelefon int,
	primary key(idSupermarket)
);


create table Sectie(
	idSectie int,
	denumire varchar(20),
	capacitate int,
	idSuperMarket int foreign key references SuperMarket(idSuperMarket)
	on delete cascade
	on update cascade,
	primary key(idSectie)
);


create table Produs(
	idProdus int,
	idSectie int foreign key references Sectie(idSectie)
	on delete cascade
	on update cascade,
	nume varchar(20),
	pret int,
	primary key(idProdus)
)


create table Achizitie(
	idAchizitie int,
	idClient int foreign key references Client(idClient)
	on delete cascade
	on update cascade,
	idProdus int foreign key references Produs(idProdus)
	on delete cascade
	on update cascade,
	primary key(idAchizitie)
);
			-------LAB 2--------

	--INSERARE INSTANTE
insert into SuperMarket values(1,'LIDL','Marasti',07680242)

insert into Sectie values(1,'Alimente',30,1)
insert into Sectie values(2,'Rechizite',30,1)
insert into Sectie values(3,'Fructe',30,1)
insert into Sectie values(4,'Sport',30,1)

insert into Produs values(1,1,'paste',5)
insert into Produs values(2,1,'paine',10)
insert into Produs values(3,2,'ghiozdan',21)
insert into Produs values(4,2,'penar',19)
insert into Produs values(5,2,'pix',80)
insert into Produs values(6,3,'mere',29)
insert into Produs values(7,3,'pere',30)
insert into Produs values(8,4,'minge',12)
insert into Produs values(9,4,'adidasi',1)
insert into Produs values(10,4,'coarda',76)
insert into Produs(idProdus,idSectie,nume) values(11,4,'coarda2')
insert into Produs values(12,4,'coarda',40)


insert into Client values(1,'Alin',19)
insert into Client values(2,'Ana',20)
insert into Client values(3,'Alex',32)
insert into Client values(4,'Alndreea',29)
insert into Client values(5,'Alina',87)
insert into Client values(6,'Stefania',28)

insert into Achizitie values(1,1,1)
insert into Achizitie values(2,1,10)
insert into Achizitie values(3,2,4)
insert into Achizitie values(4,2,7)
insert into Achizitie values(5,3,5)
insert into Achizitie values(6,4,8)
insert into Achizitie values(7,4,2)
insert into Achizitie values(8,4,3)
insert into Achizitie values(9,5,6)
insert into Achizitie values(10,2,1)
insert into Achizitie values(12,2,10)
insert into Achizitie values(13,1,1)
insert into Achizitie values(14,2,10)
insert into Achizitie values(15,2,5)
insert into Achizitie values(16,3,9)
insert into Achizitie values(17,4,8)
insert into Achizitie values(18,4,8)
insert into Achizitie values(19,5,5)


select * from SuperMarket
select * from Client
select * from Achizitie
select * from Produs
select * from Sectie

	--STERGERE
delete from Produs
where pret between 1 and 10

delete from Produs
where pret is null

delete from Produs
where nume like 'penar'

select *from Produs

delete from Client
where varsta < 10 or varsta >60

select* from Client

	--UPDATE
update Produs
set pret = 20
where pret < 20

update Client
set varsta = 30
where varsta < 25


--------LABORATOR 3--------

---- UNION ------
select denumire from Sectie
where Sectie.idSectie = 1
union
select nume from Produs
where produs.idSectie = 1

---- JOIN ---
select c.nume, p.nume from Client c
inner join Achizitie a on c.idClient=a.idClient
inner join Produs p on a.idProdus = p.idProdus
where p.pret>10

select c.nume, p.nume, p.idSectie from Client c
right join Achizitie a on c.idClient=a.idClient
right join Produs p on a.idProdus = p.idProdus
where p.idSectie in (1,2)

select c.nume, p.nume, p.pret from Client c
left join Achizitie a on c.idClient=a.idClient
left join Produs p on a.idProdus = p.idProdus
where p.pret>10 and p.pret<70

------ GROUP BY ----

select idClient, count(idProdus) as nr from Achizitie
group by idClient
having count(idProdus)>1

select idSectie, count(idProdus) as NrProdus from Produs
group by idSectie
having count(idProdus) >2

select idProdus, count(idClient) as nrClienti from Achizitie
group by idProdus
having count(idClient)>=avg(idClient)


------ SELECT DISTINCT ------
select distinct idClient from Achizitie


------

select nume from Client
where idClient in (select a.idClient from Achizitie a)

select * from Client
select * from Achizitie
select* from Produs


------ LABORATOR 4 ------
---------1-------

-----Adaugare Client-----
create function countClienti()   --signatura functiei
returns int as		--tipul de variabila pe care l returneaza
begin		--blocul functiei
	declare @NR int		--declarare variabila
	select @NR = count(*) from Client	--numara toti clientii din tabela Clienti
	return @NR			--returneaza valoare retinuta in variabila NR
end


create procedure AdaugaClient	--Procedura Adaugare Client
@nume varchar(20),@varsta int	--parametrii
as
begin
	declare @id int			--declaram o variabila
	select @id= dbo.countClienti()
	print @id				
	set @id = @id+1
	insert into Client
	values (@id,@nume,@varsta)
end
go

execute AdaugaClient 'Alex',19
select * from Client

------Adaugare Produs------
create function countProduse()
returns int as
begin
	declare @nr int
	select @nr = count(*) from Produs
	return @nr
end

create function IdSectieExists(	---verificam daca exista sectia cu id-ul dat
	@idSectie int
)
Returns bit as
begin
	declare @validator bit
	set @validator = 'true'
	declare @SectieNumber int
	select @SectieNumber = count(*) from Sectie
	where idSectie =@idSectie
	if(@SectieNumber = 0)
		set @validator = 'false'
	return @validator
end

create function ValidatePret(	---verificam daca exista sectia cu id-ul dat
	@pret int
)
Returns bit as
begin
	declare @validator bit
	set @validator = 'true'
	if(@pret <= 0)
		set @validator = 'false'
	return @validator
end

create procedure AdaugaProdus
@idSectie int,@nume varchar(20),@pret int
as
begin
	declare @idProdus int
	select @idProdus = dbo.countProduse()
	set @idProdus = @idProdus+1
	if(dbo.IdSectieExists(@idSectie) = 'false')
		raiserror ('Id-ul secctiei nu exista',10,1)
	else
		if(dbo.ValidatePret(@pret) = 'true')
			Insert into Produs
			values (@idProdus,@idSectie,@nume,@pret)
		else
			raiserror ('Pret incorect',10,1)
end
go

execute AdaugaProdus 4,'casti',21
select * from Produs

-----Adaugare Achizitie-----
create FUNCTION intVerif(
@int varchar(50)
)
returns bit
as
begin
	if  isnumeric(@int) = 1
		return 1
	return 0
end
go

create function countAchizitii()
returns int as
begin
	declare @nr int
	select @nr = count(*) from Achizitie
return @nr
end


create function IdProdusExists(
	@idProdus int
)
returns bit as
begin
declare @nr int
select @nr = count(*) from Produs
where idProdus = @idProdus
declare @validator bit
set @validator = 'true'
if(@nr = 0)
	set @validator = 'false'
return @validator
end

create function IdClientExists(
	@idClient int
)
returns bit as
begin
declare @nr int
select @nr = count(*) from Client
where idClient = @idClient
declare @validator bit
set @validator = 'true'
if(@nr = 0)
	set @validator = 'false'
return @validator
end

create procedure AdaugaAchizitie
@idClient int,@idProdus int
as
begin
	
	declare @idAchizitie int
	select @idAchizitie = dbo.countAchizitii()
	set @idAchizitie = @idAchizitie + 1
	if(dbo.IdClientExists(@idClient) = 'false')
		raiserror ('Clientul nu exista',10,1)
	else
		if(dbo.IdProdusExists(@idProdus) = 'true')
			Insert into Achizitie
			values (@idAchizitie,@idClient,@idProdus)
		else
			raiserror ('Produsul nu exista',10,1)
end
go

execute AdaugaAchizitie 7,1

select * from Achizitie


---2-----
create view View1 as
select p.nume,
s.denumire
from Produs as p
inner join Sectie as s
on p.idSectie = s.idSectie

select * from View1

-----3-----
create trigger tr_produs_insert
on Produs
after insert
as
begin
    set nocount on;

    declare @operatie_tip varchar(10);
    set @operatie_tip = 'insert';

    select 'data si ora: ' + convert(varchar, getdate(), 120) + '; tip operatie: ' + @operatie_tip + '; nume tabel: produs;' as mesaj;
end;


create trigger tr_produs_delete
on Produs
after delete
as
begin
    set nocount on;

    declare @operatie_tip varchar(10);
    set @operatie_tip = 'delete';

    select 'data si ora: ' + convert(varchar, getdate(), 120) + '; tip operatie: ' + @operatie_tip + '; nume tabel: produs;' as mesaj;
end;