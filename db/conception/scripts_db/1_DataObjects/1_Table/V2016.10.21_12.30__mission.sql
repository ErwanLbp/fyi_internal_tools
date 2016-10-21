CREATE TABLE mission
  (
    id                          INTEGER NOT NULL ,
    nom                         VARCHAR2 (50) NOT NULL ,
    numero_contrat              INTEGER ,
    clients_id                  INTEGER NOT NULL ,
    date_debut                  DATE NOT NULL ,
    date_fin                    DATE ,
    REFERENCE                   VARCHAR2 (50) NOT NULL ,
    poste_mission               VARCHAR2 (50) ,
    adresse_facturation         VARCHAR2 (100) ,
    modalite_reglement          VARCHAR2 (50) NOT NULL ,
    respect_preavis_resiliation VARCHAR2 (50) NOT NULL ,
    lieu_execution              VARCHAR2 (50) ,
    condition_renouvellement    VARCHAR2 (50)
  ) ;
