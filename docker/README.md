# Docker Compose (`docker-compose` >= 1.8.0)

## Tomcat

### Ports

- WebApp : 8888

## Oracle-XE

### Ports

- SSH : 10022
- DataBase : 11521
- Oracle WebApp : 18080

### Login informations

Key      | Value
-------- | ---------
hostname | localhost
sid      | xe
username | system
password | oracle

### SSH

You can login by SSH on the Oracle-XE container with this command : `ssh root@localhost -p 10022`

The password is : `admin`
