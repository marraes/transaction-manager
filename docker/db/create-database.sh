#!/bin/bash
set -e
export PGPASSWORD=$POSTGRES_PASSWORD;
echo "SELECT 'CREATE DATABASE \"transaction-manager\"' WHERE NOT EXISTS (SELECT FROM pg_database WHERE datname = 'transaction-manager')\gexec" | psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" --dbname postgres
