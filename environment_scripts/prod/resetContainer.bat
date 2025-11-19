@echo off

echo Resetting the container (without data reset)...
docker compose -p museumbackendprod down
docker compose -p museumbackendprod up -d
