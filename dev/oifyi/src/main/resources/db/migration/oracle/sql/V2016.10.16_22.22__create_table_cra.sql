CREATE TABLE cra (
  mois         DATE   NOT NULL,
  idConsultant NUMBER NOT NULL,
  idMission    NUMBER NOT NULL,
  idStatus     NUMBER NOT NULL,
  CONSTRAINT pk_cra PRIMARY KEY (mois, idConsultant, idMission)
);