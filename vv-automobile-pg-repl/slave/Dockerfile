FROM postgres:latest

# for tigrm index
RUN apt-get update && apt-get install -y postgresql-contrib

# fixes error error on startup "directory exists, but is not empty"
RUN rm -rf /var/lib/postgresql/data/*


COPY postgresql.conf /etc/postgresql/postgresql.conf
COPY standby.signal /docker-entrypoint-initdb.d/

EXPOSE 5432