CREATE TABLE mission (
  id_mission     NUMBER        NOT NULL,
  nom            VARCHAR2(255) NOT NULL,
  numero_contrat VARCHAR2(255) NOT NULL,
  client_id      NUMBER        NOT NULL,
  date_debut     DATE          NOT NULL,
  date_fin       DATE,
  tjm            NUMBER        NOT NULL,
  CONSTRAINT Missions_PK PRIMARY KEY (id_mission),
  CONSTRAINT missions_client_FK FOREIGN KEY (client_id) REFERENCES client (id_client)
);
