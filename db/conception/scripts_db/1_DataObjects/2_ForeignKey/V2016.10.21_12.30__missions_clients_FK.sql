ALTER TABLE mission ADD CONSTRAINT missions_clients_FK FOREIGN KEY ( clients_id ) REFERENCES clients ( id ) ;
