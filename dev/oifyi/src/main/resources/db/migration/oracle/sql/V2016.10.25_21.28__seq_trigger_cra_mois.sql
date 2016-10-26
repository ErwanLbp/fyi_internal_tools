CREATE SEQUENCE seq_id_cra_mois;

CREATE TRIGGER before_insert_cra_mois
BEFORE INSERT ON cra_mois FOR EACH ROW
  BEGIN
    :new.id_cra_mois := seq_id_cra_mois.nextval;
  END;
/