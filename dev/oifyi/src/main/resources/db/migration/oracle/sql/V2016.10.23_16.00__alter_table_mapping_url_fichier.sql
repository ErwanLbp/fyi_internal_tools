CREATE TABLE mapping_url_fichier (
  id_muf         NUMBER       NOT NULL,
  nom_page       VARCHAR2(30) NOT NULL,
  nom_mode       VARCHAR2(30) NOT NULL,
  chemin_fichier VARCHAR2(30) NOT NULL,
  CONSTRAINT pk_muf PRIMARY KEY (id_muf),
  CONSTRAINT u_muf UNIQUE (nom_page, nom_mode)
);