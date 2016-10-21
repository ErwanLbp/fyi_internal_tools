CREATE TABLE client (
  id     NUMBER       NOT NULL,
  nom    VARCHAR2(30) NOT NULL,
  prenom VARCHAR2(30) NOT NULL,
  CONSTRAINT pk_client PRIMARY KEY (id)
);