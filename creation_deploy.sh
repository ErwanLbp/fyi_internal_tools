#!/bin/bash

echo
echo "*************************************************************************"
echo "***** Script de mise en place de l'archive de rendu du projet OIFYI *****"
echo "*************************************************************************"
echo

pathTarget=dev/oifyi/target

echo "*** Copie du fichier $pathTarget/oifyi.war dans le dossier de deploy"
cp -v $pathTarget/oifyi.war deploy/

if [ ! -f deploy/create_user.sql ]
then
	echo 
	echo "*** Copie du script de création du user dans le dossier deploy/"
	cp -v db/create_user.sql deploy/
fi

echo
echo "*** Archivage du dossier deploy/ dans l'archive dabnco_oifyi.zip"
cd deploy
rm -fv dabnco_oifyi.zip
zip -r dabnco_oifyi.zip *

echo
echo "*** Préparation de l'archive terminée"
echo

exit 0;