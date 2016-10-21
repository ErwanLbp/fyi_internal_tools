CREATE TABLE cra_jour (
  id_cramois     INTEGER NOT NULL,
  jour           DATE    NOT NULL,
  heures_travail INTEGER NOT NULL,
  CONSTRAINT cra_jour_PK PRIMARY KEY (id_cramois, jour),
  CONSTRAINT cra_jour_cra_mois_FK FOREIGN KEY (id_cramois) REFERENCES cra_mois (id)
);
