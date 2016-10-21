CREATE TABLE mission_consultant (
  consultant_id INTEGER NOT NULL,
  mission_id    INTEGER NOT NULL,
  prix          INTEGER,
  tjm           INTEGER NOT NULL,
  CONSTRAINT mission_consultant_PK PRIMARY KEY (consultant_id, mission_id),
  CONSTRAINT mission_consult_consult_FK FOREIGN KEY (consultant_id) REFERENCES consultant (id),
  CONSTRAINT mission_consultant_mission_FK FOREIGN KEY (mission_id) REFERENCES mission (id)
);
