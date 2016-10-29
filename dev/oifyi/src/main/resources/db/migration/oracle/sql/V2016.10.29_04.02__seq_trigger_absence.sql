CREATE SEQUENCE seq_id_absence;

CREATE TRIGGER before_insert_absence
BEFORE INSERT ON absence FOR EACH ROW
  BEGIN
    :new.id_absence := seq_id_absence.nextval;
  END;
/