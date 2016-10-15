-----------------------------------
-- Script de création des tables --
-----------------------------------
-- A exécuter avec le schéma oifyi

DROP TABLE consultant;
DROP TABLE client;
DROP TABLE mission;
DROP TABLE parametrage;


CREATE TABLE consultant(
	id number,
	constraint pk_consultant primary key (id)
);

CREATE TABLE client(
	id number,
	constraint pk_client primary key (id)
);

CREATE TABLE mission(
	id number,
	constraint pk_mission primary key (id)
);

CREATE TABLE parametrage(
	nom varchar2(30) NOT NULL,
	valeur varchar2(30) NOT NULL
);