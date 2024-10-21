# techtaskDigitalChief
Для начала нужно скопировать репозиторий на свой локальный компьютер<br>
```git clone https://github.com/Vladislav3421730/techtaskDigitalChief```<br>
Далеее нужно запустить сервер, Для этого откройте папку Backend в IntellIdea и нажмите на зелёный треугольник.
Информация из базы данных уже храниться благодаря volumes в compose.yaml.
Индекс автоматически создаётся при переходе на адрес<br>
```http://localhost:8080/api/products/findAll```<br>
Также индекс может создаваться сразу же при переходе на frontend часть.
Данное приложение уже готовое REST API, которое можно протестировать (например в Postman).
Далее можно запустить React приложение.
Для этого нужно открыть папку frontend в Visual Studio Code или в другом редакторе и там перейти в терминал,
либо просто перейти в папку<br>
```cd techtaskDigitalChief/frontend```<br>
Затем нужно создать контейнер<br>
```docker build -t frontend .```<br>
Далее запустить его<br>
```docker run -p 3000:3000 frontend```<br>
После запуска все образы и контейнеры должны автоматически появиться в приложении Docker Desktop.
Если по каким-то причинам БД не была проиниализирована данными, то можно перейти <br>
```cd techtaskDigitalChief/Backend```<br>
Затем ввести команды по очереди<br>
```docker-compose exec postgres sh```<br>
```psql -U postgres -d techtaskdb```<br>
И далее скопировать скрпит из файла init.sql в консоль
