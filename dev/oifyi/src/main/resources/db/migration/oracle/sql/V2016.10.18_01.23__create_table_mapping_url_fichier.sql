CREATE TABLE mapping_url_fichier(
    nom_page VARCHAR2(30) NOT NULL,
    nom_mode VARCHAR2(30) NOT NULL,
    chemin_fichier VARCHAR2(30) NOT NULL,
    CONSTRAINT pk_muf PRIMARY KEY (nom_page,nom_mode)
);