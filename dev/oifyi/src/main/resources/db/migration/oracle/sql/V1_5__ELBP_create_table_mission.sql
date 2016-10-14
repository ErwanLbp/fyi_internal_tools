CREATE TABLE mission(
	idMission number NOT NULL,
	libelle varchar2(30),
	date_debut date,
	date_fin date,
	constraint pk_mission primary key (idMission)
);