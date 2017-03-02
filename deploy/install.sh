#!/bin/bash

echo
echo "******************************************************************"
echo "***** Script d'installation du projet OIFYI du groupe Dab&Co *****"
echo "******************************************************************"
echo

pathTMCT8=/var/lib/tomcat8/webapps
pathMigSQL=oifyi/WEB-INF/classes/db/migration/oracle/sql
pathExec=`pwd`

echo "*** Suppression d'une précédente install du war 'oifyi'"
sudo rm -drf $pathTMCT8/oifyi/
sudo rm -vdrf $pathTMCT8/oifyi.war
sudo mkdir -v $pathTMCT8/oifyi

echo
echo "*** Arret du service tomcat7"
sudo service tomcat7 stop

echo
echo "*** Démarrage du service tomcat8"
sudo service tomcat8 restart

echo
echo "*** Démarrage du service oracle-xe"
sudo service oracle-xe restart

echo `pwd`
echo "*** Copie de l'archive war dans le répertoire tomcat8 ($pathTMCT8)"
sudo cp -v oifyi.war $pathTMCT8/oifyi

echo
echo "*** Extraction de l'archive war dans le répertoire tomcat8"
cd $pathTMCT8/oifyi
sudo jar -xfv oifyi.war
cd $pathExec

echo 
echo "*** Suppression du war"
sudo rm -drfv $pathTMCT8/oifyi/oifyi.war

echo 
echo "*** Creation du dossier data_oifyi"
sudo mkdir /home/eisti/data_oifyi/
sudo chmod 777 /home/eisti/data_oifyi/

echo
echo "*** Execution du script de création du schéma de la base de données en local"
echo -n "Login dba : "
read loginSysOracle
echo -n "Password dba : "
read -s passSysOracle
unset no_proxy
echo exit | sqlplus $loginSysOracle/$passSysOracle @create_user

echo
echo "*** Creation des dossiers flyway dans : `pwd`"
sudo mkdir flyway/
sudo mkdir flyway/sql/

echo
echo "*** Copie des migrations flyway dans le dossier d'install"
sudo cp -v $pathTMCT8/$pathMigSQL/* flyway/sql/

echo
echo "*** Chargement des migrations flyway dans la base de données"
cd flyway/
sudo ./flyway.sh migrate

echo 
echo "*** Suppression des migrations du dossier temporaire"
sudo rm -rdfv sql/*

echo
echo "*** Installation réussie dans le répertoire $pathTMCT8"
echo "*** Le site est disponible à l'adresse http://localhost:8088/"

exit 0;