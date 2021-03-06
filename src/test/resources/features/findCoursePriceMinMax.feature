Feature: Поиск курсов с min/max ценой

  @price
  Scenario: Поиск курса с min/max ценой
    Given Главная страница "Онлайн‑курсы для профессионалов, дистанционное обучение современным профессиям" открыта
    When Открываем меню "Курсы" и выбираем "Подготовительные курсы"
    When Ищем курс с минимальной ценой
    Then Курс с минимальной ценой найден
    When Ищем курс с максимальной ценой
    Then Курс с максимальной ценой найден

