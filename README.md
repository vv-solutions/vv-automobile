# vv-automobile

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

## Running the application in dev mode

You can run this application in dev mode that enables live coding using:
```shell script
./mvnw compile quarkus:dev
```

# Architecture
![overview.png](src%2Fmain%2Fresources%2Fimages%2Foverview.png)


# Databases

## Postgresql: 
To setup the Postgresql database, open a terminal in the folder “vv-automobile-pg-repl” and “run docker-compose up -d”
![pg.png](src%2Fmain%2Fresources%2Fimages%2Fpg.png)

## Neo4j:
To setup the Neo4j database, create a new database from the neo4j.dump file, located in the neo4j folder. 

![neo4j.png](src%2Fmain%2Fresources%2Fimages%2Fneo4j.png)

## Redis:
To setup the Redis database use this command: docker run --name vv-automobile-cart -d -p 6379:6379 redis
