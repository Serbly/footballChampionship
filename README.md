# ⚽ Football Championship Manager

Веб-приложение для управления футбольными чемпионатами, командами, игроками, матчами и статистикой.

## 🚀 Стек технологий

- Java 17
- Spring Boot
- Spring Security
- Spring Data JPA
- PostgreSQL
- Thymeleaf
- Bootstrap

## 📌 Функционал

### Публичная часть:
- Просмотр чемпионатов
- Турнирные таблицы
- Матчи
- Голы
- Статистика игроков

### Административная часть:
- CRUD команд
- CRUD игроков
- CRUD матчей
- CRUD чемпионатов
- Управление тренерами
- Импорт данных

## 🏗 Архитектура

Controller → Service → Repository  
DTO → Entity → Mapper  

Проект реализует:
- Многоуровневую архитектуру
- Разделение ролей доступа
- Административную панель

## 🗄 База данных

Таблицы:
- teams
- players
- matches
- championships
- standings
- goals
- coaches

## ⚙️ Запуск

```bash`
git clone https://github.com/Serbly/footballChampionship.git
mvn spring-boot:run

## 🎯 Назначение проекта
Проект создан как полноценная система управления футбольной статистикой и демонстрирует навыки проектирования сложных реляционных баз данных и backend-архитектуры.
