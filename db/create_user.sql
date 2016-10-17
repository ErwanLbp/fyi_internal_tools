---------------------------------------------------------------------------
-- Supprime l'user oifyi et reconstruit tout pour un nouveau déploiement --
---------------------------------------------------------------------------

-- *****************************************************
-- J'ai essayé de faire tout ca 
-- avec un role (ce qui est plus propre quand meme)
-- mais ca marche pas, du coup pour l'instant je fais
-- les GRANT direct sur l'user et si tu arrives à le 
-- faire avec le role : chapeau
-- *****************************************************

-- Suppression du role et de l'utilisateur oifyi
DROP USER oifyi CASCADE;
-- DROP ROLE role_oifyi; -- Pb du role


--Creation du role de l'user oifyi et attribution des droits
/* -- Pb du role
CREATE ROLE role_oifyi IDENTIFIED BY oifyi; 

GRANT CREATE SESSION TO role_oifyi;
GRANT CONNECT TO role_oifyi;
GRANT RESOURCE TO role_oifyi;
GRANT CREATE TABLE TO role_oifyi;
GRANT CREATE TRIGGER TO role_oifyi;
GRANT CREATE TYPE TO role_oifyi;
GRANT CREATE ANY PROCEDURE TO role_oifyi;
GRANT CREATE SEQUENCE TO role_oifyi;
*/

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

-- GRANT role_oifyi TO oifyi; -- Pb du role