# techtaskDigitalChief

Для начала нужно скопировать репозиторий на свой локальный компьютер
```bash
git clone https://github.com/Vladislav3421730/techtaskDigitalChief
Далеее нужно запустить сервер, Для этого откройте папку Backend в IntellIdea и нажмите на зелёный треугольник.
Информация из базы данных уже храниться благодаря volumes в compose.yaml.
Индекс автоматически содаётся при переходе на адрес
```bash
http://localhost:8080/api/products/findAll
Также индекс может создаваться сразу же при переходе на frontend часть
Данный приложение уже готовое REST API, которое можно протестировать (например в Postman)
Далее можно запустить React приложение
Для этого нужно открыть папку frontend в Visual Studio Code или в другом редакторе и там перейти в терминал,
либо просто перейти в папку
```bash
cd techtaskDigitalChief/frontend
Затем нужно создать контейнер
```bash
docker build -t frontend .
Далее запустить его
```bash
docker run -p 3000:3000 frontend
Все образы и контейнеры должны автоматически появляться в приложении Docker Desktop
