worker: sh target/bin/Temiryul
web: java -Dspring.profiles.active=default -Dserver.port=$PORT -jar target/Temiyul-1.0-SNAPSHOT.jar
heroku ps:scale web=1