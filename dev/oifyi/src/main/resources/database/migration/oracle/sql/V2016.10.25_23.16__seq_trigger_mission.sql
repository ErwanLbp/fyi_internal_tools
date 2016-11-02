CREATE SEQUENCE seq_id_mission;

CREATE TRIGGER before_insert_mission
BEFORE INSERT ON mission FOR EACH ROW
  BEGIN
    :new.id_mission := seq_id_mission.nextval;
  END;
/