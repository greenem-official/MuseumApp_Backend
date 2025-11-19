@echo off

echo Creating the environment dev container...
docker compose -p museum_backend_dev down --volumes
docker compose -p museum_backend_dev up -d
