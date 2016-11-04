CREATE TABLE absence (
  id_absence      INTEGER NOT NULL,
  id_consultant   INTEGER NOT NULL,
  id_type_absence INTEGER NOT NULL,
  plus_precision  VARCHAR2(100) ,
  date_debut      DATE  NOT NULL,
  date_fin        DATE  NOT NULL,
  id_statut_absence       INTEGER NOT NULL,
  commentaire     VARCHAR(100),
  CONSTRAINT absence_PK PRIMARY KEY (id_absence),
  CONSTRAINT absence_consultant_FK FOREIGN KEY (id_consultant) REFERENCES consultant (id_consultant),
  CONSTRAINT absence_type_absence_FK FOREIGN KEY (id_type_absence) REFERENCES type_absence (id_type_absence),
  CONSTRAINT absence_statut_absence_FK FOREIGN KEY (id_statut_absence) REFERENCES statut_absence (id_statut_absence)
);