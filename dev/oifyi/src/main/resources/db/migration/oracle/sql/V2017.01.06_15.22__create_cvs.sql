CREATE TABLE cvs_consultant (
  id_consultant INTEGER       NOT NULL,
  nom_fichier   VARCHAR2(255) NOT NULL,
  chemin        VARCHAR2(255) NOT NULL,
  CONSTRAINT cvs_consultant_PK PRIMARY KEY (nom_fichier),
  CONSTRAINT cvs_cons_consultant_FK FOREIGN KEY (id_consultant) REFERENCES consultant (id_consultant)
);
