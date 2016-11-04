-- La page de connexion est accessible à tous les utilisateurs
INSERT INTO droits_pages (id_page, id_role) VALUES (1,1);
INSERT INTO droits_pages (id_page, id_role) VALUES (1,2);
INSERT INTO droits_pages (id_page, id_role) VALUES (1,3);
INSERT INTO droits_pages (id_page, id_role) VALUES (1,4);
INSERT INTO droits_pages (id_page, id_role) VALUES (1,5);
INSERT INTO droits_pages (id_page, id_role) VALUES (1,6);

-- La page de profil est accessible à tous les consultant
INSERT INTO droits_pages (id_page, id_role) VALUES (2,1);
INSERT INTO droits_pages (id_page, id_role) VALUES (2,2);
INSERT INTO droits_pages (id_page, id_role) VALUES (2,3);
INSERT INTO droits_pages (id_page, id_role) VALUES (2,4);
INSERT INTO droits_pages (id_page, id_role) VALUES (2,5);
INSERT INTO droits_pages (id_page, id_role) VALUES (2,6);

-- La page de déconnexion est accessible à tous les consultants
INSERT INTO DROITS_PAGES (ID_PAGE, ID_ROLE) VALUES (3, 1);
INSERT INTO DROITS_PAGES (ID_PAGE, ID_ROLE) VALUES (3, 2);
INSERT INTO DROITS_PAGES (ID_PAGE, ID_ROLE) VALUES (3, 3);
INSERT INTO DROITS_PAGES (ID_PAGE, ID_ROLE) VALUES (3, 4);
INSERT INTO DROITS_PAGES (ID_PAGE, ID_ROLE) VALUES (3, 5);
INSERT INTO DROITS_PAGES (ID_PAGE, ID_ROLE) VALUES (3, 6);

-- La page d'accueil est accessible à tous les consultants
INSERT INTO DROITS_PAGES (ID_PAGE, ID_ROLE) VALUES (4, 1);
INSERT INTO DROITS_PAGES (ID_PAGE, ID_ROLE) VALUES (4, 2);
INSERT INTO DROITS_PAGES (ID_PAGE, ID_ROLE) VALUES (4, 3);
INSERT INTO DROITS_PAGES (ID_PAGE, ID_ROLE) VALUES (4, 4);
INSERT INTO DROITS_PAGES (ID_PAGE, ID_ROLE) VALUES (4, 5);
INSERT INTO DROITS_PAGES (ID_PAGE, ID_ROLE) VALUES (4, 6);

-- La page de development est accessible à tous les utilisateurs
INSERT INTO droits_pages (id_page, id_role) VALUES (5,1);
INSERT INTO droits_pages (id_page, id_role) VALUES (5,2);
INSERT INTO droits_pages (id_page, id_role) VALUES (5,3);
INSERT INTO droits_pages (id_page, id_role) VALUES (5,4);
INSERT INTO droits_pages (id_page, id_role) VALUES (5,5);
INSERT INTO droits_pages (id_page, id_role) VALUES (5,6);

-- La page Listing clients
INSERT INTO droits_pages (id_page, id_role) VALUES (11,1);
INSERT INTO droits_pages (id_page, id_role) VALUES (11,2);
INSERT INTO droits_pages (id_page, id_role) VALUES (11,3);
INSERT INTO droits_pages (id_page, id_role) VALUES (11,4);

-- La page de saisie client
INSERT INTO droits_pages (id_page, id_role) VALUES (12,1);
INSERT INTO droits_pages (id_page, id_role) VALUES (12,2);
INSERT INTO droits_pages (id_page, id_role) VALUES (12,3);

-- La page de facturation client
INSERT INTO droits_pages (id_page, id_role) VALUES (13,1);
INSERT INTO droits_pages (id_page, id_role) VALUES (13,2);

-- La page de suivi de facturation client
INSERT INTO droits_pages (id_page, id_role) VALUES (14,1);

-- La page de listing consultant
INSERT INTO droits_pages (id_page, id_role) VALUES (15,1);
INSERT INTO droits_pages (id_page, id_role) VALUES (15,2);
INSERT INTO droits_pages (id_page, id_role) VALUES (15,3);
INSERT INTO droits_pages (id_page, id_role) VALUES (15,4);

-- La page de saisie consultant
INSERT INTO droits_pages (id_page, id_role) VALUES (16,1);
INSERT INTO droits_pages (id_page, id_role) VALUES (16,2);

-- La page de suivi de la paye consultant
INSERT INTO droits_pages (id_page, id_role) VALUES (17,1);

-- La page de listing cra
INSERT INTO droits_pages (id_page, id_role) VALUES (18,1);
INSERT INTO droits_pages (id_page, id_role) VALUES (18,5);
INSERT INTO droits_pages (id_page, id_role) VALUES (18,6);

-- La page de saisie cra
INSERT INTO droits_pages (id_page, id_role) VALUES (19,1);
INSERT INTO droits_pages (id_page, id_role) VALUES (19,5);
INSERT INTO droits_pages (id_page, id_role) VALUES (19,6);

-- La page de validation cra FIXME Supprimé nan?
INSERT INTO droits_pages (id_page, id_role) VALUES (20,1);
INSERT INTO droits_pages (id_page, id_role) VALUES (20,2);

-- La page de listing absences
INSERT INTO droits_pages (id_page, id_role) VALUES (21,1);
INSERT INTO droits_pages (id_page, id_role) VALUES (21,6);

-- La page de saisie des absences
INSERT INTO droits_pages (id_page, id_role) VALUES (22,1);
INSERT INTO droits_pages (id_page, id_role) VALUES (22,6);

-- La page de listing missions
INSERT INTO droits_pages (id_page, id_role) VALUES (23,1);
INSERT INTO droits_pages (id_page, id_role) VALUES (23,2);
INSERT INTO droits_pages (id_page, id_role) VALUES (23,3);
INSERT INTO droits_pages (id_page, id_role) VALUES (23,4);

-- La page de facturation missions
INSERT INTO droits_pages (id_page, id_role) VALUES (24,1);

-- La page de suivi de la facturation missions
INSERT INTO droits_pages (id_page, id_role) VALUES (25,1);

-- La page de saisie des missions
INSERT INTO droits_pages (id_page, id_role) VALUES (26,1);
INSERT INTO droits_pages (id_page, id_role) VALUES (26,2);

-- La page de listing des assignations
INSERT INTO droits_pages (id_page, id_role) VALUES (27,1);
INSERT INTO droits_pages (id_page, id_role) VALUES (27,2);

-- La page de saisie des notes de frais
INSERT INTO droits_pages (id_page, id_role) VALUES (28,1);

-- La page de listing des notes de frais
INSERT INTO droits_pages (id_page, id_role) VALUES (29,1);

-- La page de validation des notes de frais
INSERT INTO droits_pages (id_page, id_role) VALUES (30,1);

-- La page de facturation fournisseurs
INSERT INTO droits_pages (id_page, id_role) VALUES (31,1);

-- La page de contrats fournisseurs
INSERT INTO droits_pages (id_page, id_role) VALUES (32,1);

-- La page de facturation des indépendants
INSERT INTO droits_pages (id_page, id_role) VALUES (33,1);
