# StarWarsCharacters 🚀

Приложение для фанатов «Звёздных войн», позволяющее изучать персонажей и фильмы с их участием. Данные подгружаются из [swapi.info](https://swapi.info/).

## ✨ Особенности
- **Интерактивный UI:** Главный экран с использованием `HorizontalPager` и эффектом Carousel для выбора персонажей.
- **Детальный экран:** Подробная информация о герое и список фильмов, в которых он появлялся, с загрузкой обложек.
- **Кеширование:** Поддержка оффлайн-режима благодаря локальной базе данных.
- **Адаптивный дизайн:** Полная поддержка темной и светлой тем (Material 3).

## 🛠 Стек технологий
- **UI:** Jetpack Compose, Material 3.
- **Архитектура:** MVVM (Clean Architecture approach).
- **DI:** Hilt.
- **Network:** Retrofit 2 + Gson.
- **Database:** Room (для локального кеширования).
- **Navigation:** Compose Navigation (NavHost).
- **Image Loading:** Coil.
- **Components:** HorizontalPager (Foundation).

## 🚀 Как запустить
1. Клонируйте репозиторий:
   ```bash
   git clone https://github.com/capfumm/StarWarsCharacters
2. Откройте проект в Android Studio Jellyfish или новее.
   Дождитесь синхронизации Gradle.
   Запустите приложение на эмуляторе (API 24+).
