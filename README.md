# rd-vehicles-api

## Alkalmazás indítása

A Java alapú alkalmazásoknak működik a healthcheck-je is.

```bash
curl -I http://localhost:8080/actuator/health
```

Internet kapcsolatot igényel, a futáshoz szükséges docker image-ek a docker hub-on vannak.
```bash
docker compose up -d
```

## Komponensek és forráskód

* Api: https://hub.docker.com/repository/docker/scrach/rd-vehicles-mongo/general
* Nginx: https://hub.docker.com/repository/docker/scrach/rd-vehicles-nginx/general
* Mongo (offical): https://hub.docker.com/_/mongo
* Src: https://github.com/scrach15/rd-vehicles-api/tree/feature/mongo
