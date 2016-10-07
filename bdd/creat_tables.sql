-----------------------------------
-- Script de création des tables --
-----------------------------------
-- A exécuter avec le schéma oifyi


CREATE OR REPLACE TABLE consultant(
	id number,
	constraint pk_consultant primary key (id)
);

CREATE OR REPLACE TABLE client(
	id number,
	constraint pk_client primary key (id)
);

CREATE OR REPLACE TABLE mission(
	id number,
	constraint pk_mission primary key (id)
);

CREATE OR REPLACE TABLE parametrage(
	nom varchar2(30) NOT NULL,
	valeur varchar2(30) NOT NULL
);