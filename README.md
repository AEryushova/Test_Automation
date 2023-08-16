# Запуск автотестов для WEB-сервиса [Покупка тура](http://localhost:8080/)

## Необходимое окружение :
- Git
- Java 11
- ОС : **Windows 10 64-bit**
- IDE : **IntelliJ IDEA**
- Браузер : **Google Chrome**
- Docker
- DBeaver


## Запуск :
1. Клонирование репозитория git@github.com:AEryushova/BuyingATour.git командой _git clone_
2. Запуск Docker-контейнеров (mysql,postgres,node) командой : _docker-compose up --build_
3. Запуск SUT командой:
   - С использованием **PostgreSQL**: _java "-Dspring.datasource.url=jdbc:postgresql://localhost:5432/app" -jar artifacts/aqa-shop.jar_
   - С использованием **MySQL**: _java "-Dspring.datasource.url=jdbc:mysql://localhost:3306/app" -jar artifacts/aqa-shop.jar_      
4. Запуск тестов :
   - С использованием **PostgreSQL**: _./gradlew clean test "-Ddb.url=jdbc:postgresql://localhost:5432/app"_
   - С использованием **MySQL**: _./gradlew clean test "-Ddb.url=jdbc:mysql://localhost:3306/app"_
5. Генерация отчета Allure командой : _./gradlew allureserve_

- Остановка SUT : _CTRL + C_
- Остановка контейнеров : _docker-compose stop_  
- Удаление контейнеров : _docker-compose down_

## Документация :
- [План автоматизации](https://github.com/AEryushova/Test_Automation/blob/main/docs/Plan.md)
- [Отчет по итогам тестирования](https://github.com/AEryushova/Test_Automation/blob/main/docs/Report.md)
- [Отчет по итогам автоматизации](https://github.com/AEryushova/Test_Automation/blob/main/docs/Summary.md)
