/**
	Projeto de gestão de OS para oficina de motos
    @author Ismael de Sousa, Heder Santos, Aryon Rabello
*/

create database oficina;
use oficina;

create table usuarios(
	id int primary key auto_increment,
    usuario varchar(50) not null,
    login varchar(50) not null unique,
    senha varchar(250) not null,
    perfil varchar(50) not null
);

update usuarios set senha = md5('123') where id = 10;

insert into usuarios(usuario, login, senha, perfil)
values(
	'Maria','admin',md5('123@senac'),'administrador'
);

insert into usuarios(usuario, login, senha, perfil)
values(
	'Anderson','anderson',md5('123@senac'),'operador'
);

insert into usuarios(usuario, login, senha, perfil)
values(
	'Andrew','andrew',md5('123@senac'),'operador'
);

insert into usuarios(usuario, login, senha, perfil)
values(
	'Joaquin','joaquin',md5('123@senac'),'atendente'
);

-- CRUD Update
update usuarios set senha = md5('123@Senac') where id = 1; 
update usuarios set perfil = 'gerente' where id = 2;

describe usuarios;
select * from usuarios;

create table clientes(
	idcli int primary key auto_increment,
    nome varchar(50) not null,
    cnh char(11) unique,
    cpf char(11) unique,
    cep char(8),
    endereco varchar(50) not null,
    numero varchar(12) not null,
    complemento varchar(30),
    bairro varchar(50) not null,
    cidade varchar(50) not null,
    uf char(2) not null,
    fone1 char(11) not null,
    fone2 varchar(11),
    email varchar(100) unique
);

describe clientes;

insert into clientes(nome,cnh,cpf,cep,endereco,numero,complemento,bairro,cidade,uf,fone1,fone2,email) values(
'Heder Santos','97196646900','32746418045','57040230','Rua Caetés','115','','Jacintinho','Maceió','AL','11987458745','11954987986','hedersantos@email.com'
);

insert into clientes(nome,cnh,cpf,cep,endereco,numero,complemento,bairro,cidade,uf,fone1,fone2,email) values(
'Vitor Andrade','77845675150','39215001093','79010916','Avenida Coronel Antonino','12','Casa','Coronel Antonino','Campo Grande','MS','11987487458','','vitorandrade@email.com'
);

insert into clientes(nome,cnh,cpf,cep,endereco,numero,complemento,bairro,cidade,uf,fone1,fone2,email) values(
'Cleber Silva','76576288791','20637279026','58067004','Rua Benedito Damázio da Silva','89','Bloco 1 AP 2','Gramame','João Pessoa','PB','11985236471','1123587498','clebersilva@email.com'
);


-- CRUD Update
update clientes set fone2 = '11987654321' where idcli = 1;
update clientes set fone2 = '11987984321' where idcli = 2;

select * from clientes;

create table tbos(
	os int primary key auto_increment,
    datacad timestamp default current_timestamp,
    tipo varchar(20) not null,
    statusos varchar(30) not null,
    defeitocli varchar(200) not null,
    defeitotec varchar(200),
	modelo varchar(50) not null,
    fabricante varchar(50) not null,
    ano char(4) not null,
    placa char(7) unique,
    combustivel varchar(20) not null,
    tecnico int not null,
    foreign key(tecnico) references usuarios(id),
    valor decimal(10,2),
    chassi char(17) not null unique,
    idcli int not null, 
    foreign key(idcli) references clientes(idcli),
    garantia date,
    datasaida date
);

describe tbos;

insert into tbos(tipo,statusos,defeitocli,defeitotec,modelo,fabricante,ano,placa,combustivel,tecnico,valor,chassi,idcli)
values(
	'Orçamento','Aguardando aprovação','Corrente frouxa','Corrente frouxa','Vespa','Lambretta','2021','HGJ6458','Gasolina',1,250,'42043lXZuYw011150','1'
);

insert into tbos(tipo,statusos,defeitocli,defeitotec,modelo,fabricante,ano,placa,combustivel,tecnico,valor,chassi,idcli)
values(
	'Orçamento','Aguardando aprovação','Problema na partida','Relé de partida quebrado','Dark Horse','Indian','2016','ADS4568','Gasolina',2,5000,'6CTl4uKCA5B4X0532','2'
);

insert into tbos(tipo,statusos,defeitocli,defeitotec,modelo,fabricante,ano,placa,combustivel,tecnico,valor,chassi,idcli,garantia)
values(
	'Serviço','retirado','Ajuste de freio','Troca da lona','Fan 150','Honda','2017','DSH4652','Gasolina',3,300,'7F1jEs91ll1E76580','3',20220116
);

-- CRUD Update
update tbos set valor = 85 where os = 5;
update tbos set valor = 550 where os = 4;

select * from tbos;

-- relatorio de tipo da OS 1
select
	date_format(tbos.datacad,'%d/%m/%Y - %H:%i') as entrada,clientes.nome,clientes.fone1 as Fone1,clientes.fone2 as fone2,
    tbos.fabricante,tbos.modelo,tbos.defeitocli as defeito_cliente,tbos.defeitotec as defeito_tecnico,tbos.valor,tbos.tipo
from tbos inner join clientes on tbos.idcli = clientes.idcli where tipo = 'Orçamento';

-- relatorio de tipo da OS 2
select
	date_format(tbos.datacad,'%d/%m/%Y - %H:%i') as entrada,clientes.nome,clientes.fone1 as Fone1,clientes.fone2 as fone2,
    tbos.fabricante,tbos.modelo,tbos.defeitocli as defeito_cliente,tbos.defeitotec as defeito_tecnico,tbos.valor,tbos.tipo
from tbos inner join clientes on tbos.idcli = clientes.idcli where tipo = 'Serviço';

-- relatorio de tipo da OS 3
select
	date_format(tbos.datacad,'%d/%m/%Y - %H:%i') as entrada,clientes.nome,clientes.fone1 as Fone1,clientes.fone2 as fone2,
    tbos.fabricante,tbos.modelo,tbos.defeitocli as defeito_cliente,tbos.defeitotec as defeito_tecnico,tbos.valor,tbos.tipo,
    date_format(tbos.datasaida,'%d/%m/%Y - %H:%i') as retirada,date_format(tbos.garantia,'%d/%m/%Y - %H:%i') as garantia
from tbos inner join clientes on tbos.idcli = clientes.idcli where tipo = 'Finalizado';

-- relatorio do faturamento
select sum(valor) as total from tbos;

-- relatorio garantia
select 
	tbos.os as OS,clientes.nome,tbos.modelo,date_format(tbos.datasaida,'%d/%m/%Y') as retirada,tbos.valor,datediff(tbos.garantia,curdate()) as garantia_restante
from tbos inner join clientes on tbos.idcli = clientes.idcli where tipo = 'Finalizado';