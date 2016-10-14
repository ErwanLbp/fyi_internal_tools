#!/bin/bash
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
