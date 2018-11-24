# 1819_5ahif_nvs_assignment05_docker

## Quickstart

Zum starten einfach das run.sh file ausführen.
Dieses baut das Microprojekt neu und startet alle Container neu.

## Dockerfiles

### MySQL

- basiert auf [mysql](https://hub.docker.com/_/mysql/)
- setzt das Mysql Root Passwort als Umgebungsvariable
- kopiert das setup.sql Script nach docker-entrypoint-initdb.d **alle Datein in diesem Ordner werden von docker automatisch ausgeführt**

### Nginx

- basiert auf [nginx](https://hub.docker.com/_/nginx/)
- verwendet nginx.conf als Konfig für den Webserver
- der html Ordner wird auch kopiert und enthählt die index.html
- Nginx wird zusätzlich als Reverse Proxy verwendet **../srv/.. wird an Wildfly weitergeleitet**

### PHPMyAdmin

- basiert auf [phpadmin/phpadmin](https://hub.docker.com/r/phpmyadmin/phpmyadmin/)
- ist unter dem port 5050 erreichbar und dient zur config der Mysql DB

### Wildfly

- basiert auf [ubuntu](https://hub.docker.com/_/ubuntu/) (vanilla)
- installiert einen mysql client und openjdk 11
- kopiert das Microprojekt und das Wait Script
- wartet auf MySql Server und startet das Microprojekt 
- **AVA_TOOL_OPTIONS muss auf "-Djava.net.preferIPv4Stack=true -Djava.net.preferIPv4Addresses=true" gesetzt werden sonst verwendet Java IPv6 (Thorntail kann damit standardmäßig nicht umgehen)**

## Cheat Sheet

### ADD / COPY

ADD kann mehr wie COPY. Z.B. können damit URLs als Source angegeben werden und komprimierte Datein werden automatisch entpackt.
Best Practice ist es, außer wenn notwendig, COPY zu verwenden.

### RUN / CMD / ENTRYPOINT

Alle 3 können in exec (\<instruction\> ["executable", "param1", "param2", ...]) und shell (\<instruction\> \<command\>) form verwendet werden.
- RUN wird beim bauen des Dockerfiles ausgeführt und direkt in das entsthende Image gespeichert.
- CMD wird beim starten des Containers als default command ausgeführt, sofern kein Command explizit angegeben wird.
- ENTRYPOINT wird immer beim starten des Containers ausgeführt.

## Docker-Compose

### Beschreibung

Das docker-compose file konfiguriert alle 4 Container (mysql, nginx, phpmyadmin und wildfly). Es ist zuständig für Port Mapping und gibt an welche Containern von welchen abhängig sind.

### Probleme

depends_on wartet zwar darauf, dass der Container gestartet ist, jedoch nicht darauf, dass alle Programme fertig gestartet sind. Wildfly benötigt den Mysql Server. Darum muss dieser ein wait script ausführen und ständig den Mysql Container anpingen, um zu checken, ob die DB gestartet hat.

## Upload in dockerhub

Laden Sie Ihr Arbeitsergbnis in Dockerhub hoch und geben SIe die Koordinaten im README an
