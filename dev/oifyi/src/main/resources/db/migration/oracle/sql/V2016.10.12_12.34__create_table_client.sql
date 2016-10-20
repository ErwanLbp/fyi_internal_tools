CREATE TABLE client(
	id number NOT NULL,
	nom varchar2(30) NOT NULL,
	prenom varchar2(30) NOT NULL,
	constraint pk_client primary key (id)
);