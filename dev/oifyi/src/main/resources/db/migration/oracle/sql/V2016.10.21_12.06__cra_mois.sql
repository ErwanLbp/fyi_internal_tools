CREATE TABLE cra_mois (
  id            INTEGER NOT NULL,
  mission_id    INTEGER NOT NULL,
  consultant_id INTEGER NOT NULL,
  mois_annee    DATE    NOT NULL,
  status_id     INTEGER NOT NULL,
  CONSTRAINT CRA_PK PRIMARY KEY (id),
  CONSTRAINT cra_mois__UN UNIQUE (mission_id, consultant_id, mois_annee),
  CONSTRAINT cra_mois_consultant_FK FOREIGN KEY (consultant_id) REFERENCES consultant (id),
  CONSTRAINT cra_mois_missions_FK FOREIGN KEY (mission_id) REFERENCES mission (id),
  CONSTRAINT cra_mois_status_cra_FK FOREIGN KEY (status_id) REFERENCES status_cra (id)
);
