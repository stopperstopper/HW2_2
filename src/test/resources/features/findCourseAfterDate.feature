
Feature: Поиск курсов после указанной даты

  @date
  Scenario: Поиск курсов после указанной даты
    Given Главная страница "Онлайн‑курсы для профессионалов, дистанционное обучение современным профессиям" открыта
    When Ищем курсы после 01 мая 2022
    Then Курсы после указанной даты найдены