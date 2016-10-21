ALTER TABLE mission_consultant ADD CONSTRAINT mission_consultant_mission_FK FOREIGN KEY ( mission_id ) REFERENCES mission ( id ) ;
