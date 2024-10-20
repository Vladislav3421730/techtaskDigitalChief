# techtaskDigitalChief
Для начала нужно скопировать репозиторий на свой локальный компьютер
`git clone https://github.com/Vladislav3421730/techtaskDigitalChief`
Далеее нужно запустить сервер, Для этого откройте папку Backend в IntellIdea и нажмите на зелёный треугольник.
Информация из базы данных уже храниться благодаря volumes в compose.yaml.
Индекс автоматически содаётся при переходе на адрес
`http://localhost:8080/api/products/findAll`
Также индекс может создаваться сразу же при переходе на frontend часть
Данный приложение уже готовое REST API, которое можно протестировать (например в Postman)
Далее можно запустить React приложение
Для этого нужно открыть папку frontend в Visual Studio Code или в другом редакторе и там перейти в терминал,
либо просто перейти в папку
`cd techtaskDigitalChief/frontend`
Затем нужно создать контейнер
`docker build -t frontend .`
Далее запустить его
`docker run -p 3000:3000 frontend`


