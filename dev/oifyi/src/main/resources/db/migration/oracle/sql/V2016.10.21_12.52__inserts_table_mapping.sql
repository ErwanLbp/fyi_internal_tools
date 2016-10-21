UPDATE mapping_url_fichier SET chemin_fichier='accueil/acueil.jsp' WHERE nom_page='accueil' AND nom_mode='view' AND chemin_fichier='accueil.jsp';
UPDATE mapping_url_fichier SET nom_page='accueil', nom_mode='connexion', chemin_fichier='accueil/connexion.jsp' WHERE nom_page='connexion', nom_mode='view', chemin_fichier='connexion.jsp';

INSERT INTO mapping_url_fichier (nom_page, nom_mode, chemin_fichier) VALUES ('administration','list_url','administration/list_url.jsp');
INSERT INTO mapping_url_fichier (nom_page, nom_mode, chemin_fichier) VALUES ('administration','parametrage','administration/parametrage.jsp');
INSERT INTO mapping_url_fichier (nom_page, nom_mode, chemin_fichier) VALUES ('clients','list','clients/listing_client.jsp');
INSERT INTO mapping_url_fichier (nom_page, nom_mode, chemin_fichier) VALUES ('consultant','new','consultant/new_consultant.jsp');

INSERT INTO mapping_url_fichier (nom_page, nom_mode, chemin_fichier) VALUES ('profil','update','profil/modifier.jsp');