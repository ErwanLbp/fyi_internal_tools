ALTER TABLE cra_mois ADD CONSTRAINT cra_mois_status_cra_FK FOREIGN KEY ( status_id ) REFERENCES status_cra ( id ) ;
