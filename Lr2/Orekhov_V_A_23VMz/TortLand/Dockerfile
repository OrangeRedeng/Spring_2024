FROM openjdk:17
ARG jarFile=target/TortLand-0.0.3-SNAPSHOT.jar
#Куда переместить варник внутри контейнера
WORKDIR /opt/app
#копируем наш джарник в новый внутри контейнера
COPY ${jarFile} TortLand.jar
#Открываем порт
EXPOSE 9090
#Команда для запуска
ENTRYPOINT ["java", "-jar", "TortLand.jar"]
