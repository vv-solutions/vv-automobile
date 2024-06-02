FROM postgres:latest

# used for tigrm index
RUN apt-get update && apt-get install -y postgresql-contrib

# data and init script
COPY init.sql /docker-entrypoint-initdb.d/

EXPOSE 5432