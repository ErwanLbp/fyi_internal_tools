CREATE SEQUENCE seq_id_client;

CREATE TRIGGER before_insert_client
BEFORE INSERT ON client FOR EACH ROW
  BEGIN
    :new.id_client := seq_id_client.nextval;
  END;
/