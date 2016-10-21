ALTER TABLE cra_mois ADD CONSTRAINT cra_mois_consultant_FK FOREIGN KEY ( consultant_id ) REFERENCES consultant ( id ) ;
