CREATE TABLE mission (
  idMission  NUMBER NOT NULL,
  libelle    VARCHAR2(30),
  date_debut DATE,
  date_fin   DATE,
  CONSTRAINT pk_mission PRIMARY KEY (idMission)
);