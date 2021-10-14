create database oficina;

create table usuarios(
	id int primary key auto_increment,
    usuario varchar(50) not null,
    login varchar(50) not null unique,
    senha varchar(250) not null,
    perfil varchar(50) not null
);

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

describe usuarios;

create table clientes(
	idcli int primary key auto_increment,
    nome varchar(50) not null,
    doc varchar(250) not null,
    cep char(8),
    endereco varchar(50) not null,
    numero varchar(12) not null,
    complemento varchar(30),
    bairro varchar(50) not null,
    cidade varchar(50) not null,
    uf char(2) not null,
    fone varchar(15),
    cel varchar(15) not null,
    email varchar(100) unique
);

describe clientes;

insert into clientes(nome,doc,cep,endereco,numero,complemento,bairro,cidade,uf,fone,cel,email)
values(
	'Heder','5827364728','04673645','Rua Um','168','Casa','Vila Um','São Paulo','SP','','11973849285','example@heder.com'
);
insert into clientes(nome,doc,cep,endereco,numero,complemento,bairro,cidade,uf,fone,cel,email)
values(
	'Vitor','87654321213','07485394','Rua Dois','867','Casa','Vila Dois','São Paulo','SP','','11987654321','example@vitor.com'
);
insert into clientes(nome,doc,cep,endereco,numero,complemento,bairro,cidade,uf,fone,cel,email)
values(
	'Cassio','54862514586','87654321','Rua Tres','145','Casa','Vila Tres','São Paulo','SP','','987654321','example@cassio.com'
);
insert into clientes(nome,doc,cep,endereco,numero,complemento,bairro,cidade,uf,fone,cel,email)
values(
	'Ismael','52586895963','87654321','Rua Quatro','589','Casa','Vila Quatro','São Paulo','SP','','987654321','example@ismael.com'
);
insert into clientes(nome,doc,cep,endereco,numero,complemento,bairro,cidade,uf,fone,cel,email)
values(
	'Daniel','45825214145','87654321','Rua Cinco','595','Casa','Vila Cinco','São Paulo','SP','','987654321','example@daniel.com'
);

select * from clientes;

create table tbos(
	os int primary key auto_increment,
    datacad timestamp default current_timestamp,
    tipo varchar(20) not null,
    defeitocli varchar(200) not null,
    defeitotec varchar(200),
	modelo varchar(50) not null,
    fabricante varchar(50) not null,
    ano char(4) not null,
    placa char(7) unique,
    combustivel varchar(20) not null,
    tecnico varchar(50),
    valor decimal(10,2),
    chassi char(17) not null unique,
    idcli int not null, 
    foreign key(idcli) references clientes(idcli)
);

drop table tbos;
describe tbos;

insert into tbos(tipo,defeitocli,defeitotec,modelo,fabricante,ano,placa,combustivel,tecnico,valor,chassi,idcli)
values(
	'Orçamento','Corrente frouxa','Corrente frouxa','Vespa','Lambretta','2021','HGJ6458','Gasolina','Maria','250','42043lXZuYw011150','1'
);

insert into tbos(tipo,defeitocli,defeitotec,modelo,fabricante,ano,placa,combustivel,tecnico,valor,chassi,idcli)
values(
	'Orçamento','Problema na partida','Relé de partida quebrado','Dark Horse','Indian','2016','ADS4568','Gasolina','Anderson','5000','6CTl4uKCA5B4X0532','2'
);

insert into tbos(tipo,defeitocli,defeitotec,modelo,fabricante,ano,placa,combustivel,tecnico,valor,chassi,idcli)
values(
	'Serviço','Ajuste de freio','Troca da lona','Fan 150','Honda','2017','DSH4652','Gasolina','Andrew','300','7F1jEs91ll1E76580','3'
);

insert into tbos(tipo,defeitocli,defeitotec,modelo,fabricante,ano,placa,combustivel,tecnico,valor,chassi,idcli)
values(
	'Orçamento','Vela','Chicote','Virago 535','Yamaha','2005','NJD1325','Gasolina','Andrew','500','4LlGA9R9sXKAy5626','3'
);

select * from tbos;