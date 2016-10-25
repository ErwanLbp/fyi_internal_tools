CREATE TABLE client (
  id_client       INTEGER       NOT NULL,
  raison_sociale  VARCHAR2(100) NOT NULL,
  forme_juridique VARCHAR2(30)  NOT NULL,
  siret           VARCHAR2(50)  NOT NULL,
  num_tva         VARCHAR2(50),
  adresse_numero  INTEGER,
  adresse_rue     VARCHAR2(100) NOT NULL,
  adresse_cp      INTEGER       NOT NULL,
  adresse_ville   VARCHAR2(50)  NOT NULL,
  CONSTRAINT client_PK PRIMARY KEY (id_client)
);