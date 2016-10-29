CREATE SEQUENCE seq_id_statut_absence;

CREATE TRIGGER before_insert_statut_absence
BEFORE INSERT ON statut_absence FOR EACH ROW
  BEGIN
    :new.id_statut_absence := seq_id_statut_absence.nextval;
  END;
/