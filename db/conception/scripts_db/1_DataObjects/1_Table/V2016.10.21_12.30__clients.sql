CREATE TABLE clients
  (
    id                         INTEGER NOT NULL ,
    raison_sociale             VARCHAR2 (100) NOT NULL ,
    forme_juridique            VARCHAR2 (20) NOT NULL ,
    siret                      VARCHAR2 (50) NOT NULL ,
    num_tva                    VARCHAR2 (50) ,
    RCS                        VARCHAR2 (50) ,
    adresse_numero             INTEGER ,
    adresse_rue                VARCHAR2 (100) NOT NULL ,
    adresse_cp                 INTEGER NOT NULL ,
    adresse_ville              VARCHAR2 (50) NOT NULL ,
    telephone                  INTEGER NOT NULL ,
    capital                    INTEGER ,
    ville_inscription          VARCHAR2 (50) ,
    representant_nom           VARCHAR2 (50) NOT NULL ,
    representant_fonction      VARCHAR2 (50) NOT NULL ,
    respo_client_tel           INTEGER ,
    contact_achats_nom         VARCHAR2 (50) ,
    contact_achats_tel         INTEGER ,
    respo_fournisseur_nom      VARCHAR2 (50) ,
    respo_fournisseur_fonction VARCHAR2 (50)
  ) ;