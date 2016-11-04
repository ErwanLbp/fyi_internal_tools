CREATE SEQUENCE seq_id_role;

CREATE TRIGGER before_insert_role
BEFORE INSERT ON role FOR EACH ROW
  BEGIN
    :new.id_role := seq_id_role.nextval;
  END;
/