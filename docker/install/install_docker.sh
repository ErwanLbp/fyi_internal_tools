#!/bin/bash

# Ask if you are behind a proxy
echo -n "Proxy? [Y/N]"
while read -r -n 1 -s answer; do
  if [[ $answer = [YyNn] ]]; then
    [[ $answer = [Yy] ]] && proxy=1
    [[ $answer = [Nn] ]] && proxy=0
    break
  fi
done
echo ""
if [ $proxy -eq 1 ]; then
  # Ask password and username
  echo -n "Username : "
  read -s USER
  echo -n "Password : "
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
else
  # Remove apt's proxy config
  if [ -e /etc/apt/apt.conf ]; then
    sudo rm /etc/apt/apt.conf
  fi

  # Remove docker's proxy config
  if [ -e /etc/systemd/system/docker.service.d/http-proxy.conf ]; then
    sudo rm /etc/systemd/system/docker.service.d/http-proxy.conf
  fi
  sudo systemctl daemon-reload
  sudo systemctl restart docker

  # Remove environement variables
  unset HTTP_PROXY
  unset HTTPS_PROXY
  unset FTP_PROXY
  unset http_proxy
  unset https_proxy
  unset ftp_proxy

  # Finish
  echo PROXY OFF
fi
sudo apt-get update
sudo apt-get -y upgrade
sudo apt-get -y install apt-transport-https ca-certificates curl
if [ $proxy -eq 1 ]; then
  sudo apt-key adv --keyserver-options http-proxy=http://$USER:$PASS@proxy.cergy.eisti.fr:3128/ --keyserver hkp://p80.pool.sks-keyservers.net:80 --recv-keys 58118E89F3A912897C070ADBF76221572C52609D
else
  sudo apt-key adv --keyserver hkp://p80.pool.sks-keyservers.net:80 --recv-keys 58118E89F3A912897C070ADBF76221572C52609D
fi
curl -sSL https://get.docker.com/ | sh
sudo groupadd docker
sudo usermod -aG docker $USER
echo "================================="
echo "| Please, reboot your computer. |"
echo "================================="
