mvn clean install -DskipTests
wait && sleep 5
java -jar target/cp-0.0.1-SNAPSHOT.jar
