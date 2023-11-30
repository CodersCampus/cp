mvn clean install -DskipTests
wait
code .
wait && sleep 5
java -jar target/SpringWise-0.0.1-SNAPSHOT.jar
