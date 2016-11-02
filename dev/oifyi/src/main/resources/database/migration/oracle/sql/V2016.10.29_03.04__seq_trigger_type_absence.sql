CREATE SEQUENCE seq_id_type_absence;

CREATE TRIGGER before_insert_type_absence
BEFORE INSERT ON type_absence FOR EACH ROW
  BEGIN
    :new.id_type_absence := seq_id_type_absence.nextval;
  END;
/