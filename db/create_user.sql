---------------------------------------------------------------------------
-- Supprime l'user oifyi et reconstruit tout pour un nouveau déploiement --
---------------------------------------------------------------------------

-- Suppression du role et de l'utilisateur oifyi
DROP USER oifyi CASCADE;

-- Creation de l'user oifyi et attribution du role
CREATE USER oifyi IDENTIFIED BY oifyi QUOTA UNLIMITED ON system;

GRANT CREATE SESSION TO oifyi;
GRANT CONNECT TO oifyi;
GRANT RESOURCE TO oifyi;
GRANT CREATE TABLE TO oifyi;
GRANT CREATE TRIGGER TO oifyi;
GRANT CREATE TYPE TO oifyi;
GRANT CREATE ANY PROCEDURE TO oifyi;
GRANT CREATE SEQUENCE TO oifyi;