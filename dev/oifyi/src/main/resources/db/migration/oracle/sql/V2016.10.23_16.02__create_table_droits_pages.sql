-- creation de la table pour les droits d'accès aux pages
CREATE TABLE droits_pages (
  id_page NUMBER NOT NULL,
  id_role NUMBER NOT NULL,
  CONSTRAINT pk_droits_pages PRIMARY KEY (id_page, id_role),
  CONSTRAINT fk_droits_pages_role FOREIGN KEY (id_role) REFERENCES ROLE (id_role),
  CONSTRAINT fk_droits_pages_page FOREIGN KEY (id_page) REFERENCES MAPPING_URL_FICHIER (id_muf)
);