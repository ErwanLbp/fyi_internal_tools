ALTER TABLE cra_mois ADD CONSTRAINT CRA_PK PRIMARY KEY ( id ) ;
ALTER TABLE cra_mois ADD CONSTRAINT cra_mois__UN UNIQUE ( mission_id , consultant_id , mois_annee ) ;
