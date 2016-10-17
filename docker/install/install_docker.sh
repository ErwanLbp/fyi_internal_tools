#!/bin/bash

# Remove environement variables
unset HTTP_PROXY
unset HTTPS_PROXY
unset FTP_PROXY
unset http_proxy
unset https_proxy
unset ftp_proxy

# Finish
echo PROXY OFF

sudo apt-get update
sudo apt-get -y upgrade
sudo apt-get -y install apt-transport-https ca-certificates curl

curl -sSL https://get.docker.com/ | sh
sudo sh -c 'curl -L https://github.com/docker/compose/releases/download/1.8.0/docker-compose-\`uname -s\`-\`uname -m\` > /usr/local/bin/docker-compose'
sudo chmod +x /usr/local/bin/docker-compose
sudo groupadd docker
sudo usermod -aG docker $USER
echo "========================"
echo "| Relance un terminal. |"
echo "========================"
