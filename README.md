# Аттестация 3
## Команда для запуска тестов 
```
mvn clean test
```

## Сценарий 1:
+ Открыть страницу https://demoqa.com/login
+ Ввести логин и пароль
+ Перейти в раздел https://demoqa.com/profile
+ Проверить, что таблица пустая

## Сценарий 2:
+ Открыть страницу https://demoqa.com/login
+ Ввести логин и пароль
+ Перейти в раздел https://demoqa.com/books
+ Добавить в коллекцию 6 книг
+ Перейти в раздел https://demoqa.com/profile
+ Проверить, что в коллекции отображается 6 книг

## Сценарий 3:
+ Открыть страницу https://demoqa.com/login
+ Ввести логин и пароль
+ Перейти в раздел https://demoqa.com/books
+ Добавить в коллекцию 2 книги
+ Перейти в раздел https://demoqa.com/profile
+ Проверить, что в коллекции отображается 2 книги
+ Перейти в раздел https://demoqa.com/profile
+ Нажать Delete All Books
+ Вернуться в раздел https://demoqa.com/profile
+ Проверить, что таблица пустая