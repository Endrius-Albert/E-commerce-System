FROM debian:bullseye AS builder

WORKDIR /app
COPY . /app

ENV LANG=C.UTF-8
ENV LC_ALL=C.UTF-8


RUN apt-get update --fix-missing \
    && apt-get install -y --no-install-recommends \
       ant openjdk-17-jdk libservlet-api-java \
    && ant war \
    && rm -rf /var/lib/apt/lists/*

FROM tomcat:10.1

ENV LANG=C.UTF-8
ENV LC_ALL=C.UTF-8

RUN rm -rf /usr/local/tomcat/webapps/*

COPY --from=builder /app/dist/*.war /usr/local/tomcat/webapps/ECommerce.war

RUN apt-get update --fix-missing \
    && apt-get install -y --no-install-recommends \
       default-mysql-client dos2unix \
    && rm -rf /var/lib/apt/lists/*

COPY wait-for-db.sh /wait-for-db.sh
RUN dos2unix /wait-for-db.sh && chmod +x /wait-for-db.sh

EXPOSE 8080

CMD ["sh", "/wait-for-db.sh"]
