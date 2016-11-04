CREATE TABLE cra_jour (
  id_cramois INTEGER NOT NULL,
  jour       INTEGER NOT NULL,
  travail    NUMBER  NOT NULL,
  CONSTRAINT cra_jour_PK PRIMARY KEY (id_cramois, jour),
  CONSTRAINT cra_jour_cra_mois_FK FOREIGN KEY (id_cramois) REFERENCES cra_mois (id_cra_mois)
);
