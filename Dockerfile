# === BUILD ===
FROM debian:bullseye AS builder

WORKDIR /app
COPY . /app

RUN sed -i 's|http://deb.debian.org/debian|http://ftp.br.debian.org/debian|g' /etc/apt/sources.list \
    && apt-get update --fix-missing \
    && apt-get install -y ant openjdk-11-jdk --no-install-recommends \
    && ant war \
    && rm -rf /var/lib/apt/lists/*

# === IMAGE TOMCAT ===
FROM tomcat:10.1

RUN rm -rf /usr/local/tomcat/webapps/*

COPY --from=builder /app/dist/*.war /usr/local/tomcat/webapps/ECommerce.war

RUN sed -i 's|http://deb.debian.org/debian|http://ftp.br.debian.org/debian|g' /etc/apt/sources.list \
    && apt-get update --fix-missing \
    && apt-get install -y default-mysql-client dos2unix --no-install-recommends \
    && rm -rf /var/lib/apt/lists/*


COPY wait-for-db.sh /wait-for-db.sh
RUN dos2unix /wait-for-db.sh && chmod +x /wait-for-db.sh

EXPOSE 8080

CMD ["sh", "/wait-for-db.sh"]
