## Необходимое окружение :
- Git
- Java 11
- ОС : **Windows 10 64-bit**
- IDE : **IntelliJ IDEA **
- Браузер : **Google Chrome**
- Docker


## Запуск :
1. Клонирование репозитория git@github.com:AEryushova/BuyingATour.git командой _git clone_
2. Запуск Docker-контейнеров (mysql,postgres,node) командой : _docker-compose up --build_
3. Запуск SUT командой:
   - С использованием **Postgres**: _java "-Dspring.datasource.url=jdbc:postgresql://localhost:5432/app" -jar artifacts/aqa-shop.jar_
   - С использованием **MySQL**: _java "-Dspring.datasource.url=jdbc:mysql://localhost:3306/app" -jar artifacts/aqa-shop.jar_      
4. Запуск тестов :
   - С использованием **Postgres**: _./gradlew clean test "-Ddb.url=jdbc:postgresql://localhost:5432/app"_
   - С использованием **MySQL**: _./gradlew clean test "-Ddb.url=jdbc:mysql://localhost:3306/app"_


- Остановка SUT : _CTRL + C_
- Остановка контейнеров : _docker-compose stop_  
- Удаление контейнеров : _docker-compose down_