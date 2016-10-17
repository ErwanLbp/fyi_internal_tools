#!/bin/bash
# Ask password and username
echo "Password : "
read -s PASS

# Set apt's proxy config
if [ -e /etc/apt/apt.conf ]; then
  sudo rm /etc/apt/apt.conf
fi
echo "Acquire::http::proxy \"http://$USER:$PASS@proxy.cergy.eisti.fr:3128/\";" | sudo tee -a /etc/apt/apt.conf > /dev/null

# Set docker's proxy config
if [ ! -d /etc/systemd/system/docker.service.d/ ]; then
  sudo mkdir /etc/systemd/system/docker.service.d/
fi
if [ -e /etc/systemd/system/docker.service.d/http-proxy.conf ]; then
  sudo rm /etc/systemd/system/docker.service.d/http-proxy.conf
fi
echo "[Service]" | sudo tee -a /etc/systemd/system/docker.service.d/http-proxy.conf > /dev/null
echo "Environment=\"HTTP_PROXY=http://$USER:$PASS@proxy.cergy.eisti.fr:3128/\" \"NO_PROXY=localhost, 127.0.0.1/8, ::1, vpn.eisti.fr, vagrant.dev, 192.168.33.10, local.dev\"" | sudo tee -a /etc/systemd/system/docker.service.d/http-proxy.conf > /dev/null
sudo systemctl daemon-reload
sudo systemctl restart docker

# Set environement variables
export http_proxy=http://$USER:$PASS@proxy.cergy.eisti.fr:3128
export https_proxy=http://$USER:$PASS@proxy.cergy.eisti.fr:3128
export ftp_proxy=http://$USER:$PASS@proxy.cergy.eisti.fr:3128
export HTTP_PROXY=http://$USER:$PASS@proxy.cergy.eisti.fr:3128
export HTTPS_PROXY=http://$USER:$PASS@proxy.cergy.eisti.fr:3128
export FTP_PROXY=http://$USER:$PASS@proxy.cergy.eisti.fr:3128

# Finish
pass=""
echo PROXY ON
