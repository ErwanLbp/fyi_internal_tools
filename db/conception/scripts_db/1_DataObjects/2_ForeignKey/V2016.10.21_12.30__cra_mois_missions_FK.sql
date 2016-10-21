ALTER TABLE cra_mois ADD CONSTRAINT cra_mois_missions_FK FOREIGN KEY ( mission_id ) REFERENCES mission ( id ) ;
