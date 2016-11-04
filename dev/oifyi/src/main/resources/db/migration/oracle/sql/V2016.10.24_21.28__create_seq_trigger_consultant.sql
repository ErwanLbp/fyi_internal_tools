CREATE SEQUENCE seq_id_consultant;

CREATE TRIGGER before_insert_consultant
BEFORE INSERT ON consultant FOR EACH ROW
  BEGIN
    :new.id_consultant := seq_id_consultant.nextval;
  END;
/