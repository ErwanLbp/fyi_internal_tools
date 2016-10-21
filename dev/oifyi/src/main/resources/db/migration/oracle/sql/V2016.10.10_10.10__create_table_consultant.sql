CREATE TABLE consultant (
  id     NUMBER       NOT NULL,
  nom    VARCHAR2(30) NOT NULL,
  prenom VARCHAR2(30) NOT NULL,
  mail   VARCHAR2(30),
  CONSTRAINT pk_consultant PRIMARY KEY (id)
);