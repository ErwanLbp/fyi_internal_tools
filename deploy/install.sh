#!/bin/bash

echo
echo "******************************************************************"
echo "***** Script d'installation du projet OIFYI du groupe Dab&Co *****"
echo "******************************************************************"
echo

pathTMCT8=/var/lib/tomcat8/webapps/
echo "*** Suppression d'une précédente install du war 'oifyi'"
sudo rm -drf $pathTMCT8/oifyi

echo "*** Copie de l'archive war dans le répertoire tomcat8 ($pathTMCT8)"
sudo cp oifyi.war $pathTMCT8

echo "*** Arret du service tomcat7"
sudo service tomcat7 stop

echo "*** Démarrage du service tomcat8"
sudo service tomcat8 start

echo "*** Démarrage du service oracle-xe"
sudo service oracle-xe start

echo "*** Execution du script de création du schéma de la base de données en local"
echo -n "Login dba : "
read loginSysOracle
echo -n "Password dba : "
read passSysOracle
sqlplus loginSysOracle/passSysOracle @create_user

echo "*** Chargement des migrations flyway dans la base de données"
cd flyway
./flyway.sh migrate



exit 0;