DROP TABLE consultant;

CREATE TABLE consultant(
	id number NOT NULL,
	nom varchar2(30) NOT NULL,
	prenom varchar2(30) NOT NULL,
	constraint pk_consultant primary key (id)
);
