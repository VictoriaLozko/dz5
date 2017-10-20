*для работы под Ubuntu запустить терминал и  перейти в директорию selenium_grid
*запустить хаб командой sh start-hub.sh
*запустить еще один терминал и  перейти в директорию selenium_grid
*запустить ноду командой sh start-node.sh

Ветка new_branch содержит доработки домашнего задания. 
В ветке master в файле testng.xml опечатка в "Test case on Chrome (Remote execution)" в "class name=myprojects.automation.webinar5.tests.MyTestDesktop" должно быть MyTestDesktop вместо MyTestMobile
# README #

This README would normally document whatever steps are necessary to get your application up and running.

### Selenium Grid ###

* Необходимые файлы для демо находятся в директории `selenium_grid`
* Для поднятия локального грида запустите `start-hub.bat`, затем `start-node.bat`
* После этого статус грида можно мониторить по адресу http://localhost:4444/grid/console

### Headless тестирование ###

* Как обсуждалось на лекции, на данный момент с последними версиями PhantomJS / Selenium работает некорректно
* В качестве примера оставлены заготовки на запуск `phantomjs` браузера

### Desired capabilities ###

* Для драйвера IE дополнительно указаны опции не использовать нативные события и принудительно очищать сесии.
* Отключение нативных событий позволяет ускорить ввод символов
* Принудительная очиста сессии позволяет выполнять прогон тестов как в других браузерах, без сохранения состояния после предыдущего прогона.


### Mobile emulation ###

* Для тестирования мобильной версии используйте браузер Chrome в режиме эмулирования.
* Для настройки ChromeDriver воспольуйтесь информацией, которая находится в разделе справки к драйверу https://sites.google.com/a/chromium.org/chromedriver/mobile-emulation
