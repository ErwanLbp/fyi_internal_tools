CREATE TABLE consultant(
	id number NOT NULL,
	nom varchar2(30) NOT NULL,
	prenom varchar2(30) NOT NULL,
	mail varchar2(30),
	constraint pk_consultant primary key (id)
);