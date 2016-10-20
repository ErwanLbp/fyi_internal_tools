DROP TABLE consultant CASCADE CONSTRAINTS;

CREATE TABLE consultant
  (
    id       INTEGER NOT NULL ,
    nom      VARCHAR2 (50) NOT NULL ,
    prenom   VARCHAR2 (50) NOT NULL ,
    username VARCHAR2 (50) NOT NULL ,
    password VARCHAR2 (50) NOT NULL ,
    role_id  INTEGER NOT NULL,
    constraint consultant_PK primary key (id),
    CONSTRAINT consultant_role_FK FOREIGN KEY ( role_id ) REFERENCES role ( id )
  ) ;