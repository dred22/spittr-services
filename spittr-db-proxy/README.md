Project exposes the different databases implementation via IPA

To remove all containers from windows CLI
FOR /f "tokens=*" %i IN ('docker ps -aq') DO docker rm %i

To remove all containers from linux CLI
docker rm $(docker ps -aq)