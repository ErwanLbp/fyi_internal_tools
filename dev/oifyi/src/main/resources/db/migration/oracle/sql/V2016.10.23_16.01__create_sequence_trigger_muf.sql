CREATE SEQUENCE seq_id_mapping_url_fichier;

CREATE TRIGGER before_insert_muf
BEFORE INSERT ON mapping_url_fichier FOR EACH ROW
  BEGIN
    :new.id_muf := seq_id_mapping_url_fichier.nextval;
  END;
/