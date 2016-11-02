CREATE TABLE role (
  id_role NUMBER       NOT NULL,
  libelle VARCHAR2(30) NOT NULL,
  CONSTRAINT pk_role PRIMARY KEY (id_role),
  CONSTRAINT u_role UNIQUE (libelle)
);