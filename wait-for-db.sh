#!/bin/sh
set -e

echo "DB_HOST=$DB_HOST"
echo "DB_PORT=$DB_PORT"
echo "DB_USER=$DB_USER"
echo "Waiting for DB to be ready..."

TIMEOUT=60
SECONDS=0

until mysqladmin ping -h"$DB_HOST" -P"$DB_PORT" -u"$DB_USER" -p"$DB_PASSWORD" --silent; do
    echo "DB not ready yet..."
    sleep 2
    if [ $SECONDS -ge $TIMEOUT ]; then
        echo "Timeout reached. DB is still not ready. Exiting."
        exit 1
    fi
done

echo "DB is ready! Starting Tomcat..."
catalina.sh run
