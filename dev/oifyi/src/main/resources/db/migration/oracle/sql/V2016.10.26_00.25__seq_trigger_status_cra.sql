CREATE SEQUENCE seq_id_statusCra;

CREATE TRIGGER before_insert_status_cra
BEFORE INSERT ON status_cra FOR EACH ROW
  BEGIN
    :new.id_status_cra := seq_id_statusCra.nextval;
  END;
/