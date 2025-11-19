#!/bin/bash

echo "Resetting the container (WITH data reset)..."
docker compose -p museumbackendprod down --volumes
docker compose -p museumbackendprod up -d
