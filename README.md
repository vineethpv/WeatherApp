# 🌦️ Weather Forecast Android App

## 📱 Overview
This project is an Android weather forecast application. The app retrieves and displays weather forecast data based on the user’s current location and dynamically updates the UI according to weather conditions (Sunny, Cloudy, Rainy).

---

## 🚀 Features

- 📍 **Location-Based Forecast**
  - Automatically fetches the user’s current location.
  - Displays weather data relevant to the detected location.

- 🌤️ **Dynamic UI Backgrounds**
  - Background gradient changes based on weather condition:
    - ☀️ Sunny
    - ☁️ Cloudy
    - 🌧️ Rainy

- 📊 **5-Day Forecast**
  - Uses OpenWeatherMap 5-day / 3-hour forecast API.

- 🔐 **API Key Handling**
  - API key is stored in `local.properties`.
  - Accessed via Gradle build configuration(WEATHER_API_KEY).

---

## 🛠️ Tech Stack

- **Language:** Kotlin
- **UI Framework:** Jetpack Compose + Material 3
- **Architecture:** MVI (Model-View-Intent) + Clean Architecture
- **DI:** Hilt
- **Networking:** Retrofit / OkHttp
- **Image Loading:** Coil
- **Location Services:** FusedLocationProviderClient
- **Testing:** JUnit, Turbine

---

## 🧪 Testing

- Unit tests implemented for:
  - ViewModels  
  - Repository layer
  - Domain(UseCase, Mapper)   
- Fakes(FakeRepository, FakeLocatonProvider, FakeWeatherApi)  

---
## ▶️ How to Run

- Clone the repository and checkout **master branch**.
  - Add your API key to local.properties.
  - Build and run on an emulator or physical device.
  - Grant location permissions and turn on location when prompted.

---
## Screenshots

<p align="center">
  <img src="https://github.com/user-attachments/assets/9f742709-2e5a-4158-a242-f64ba9beaf3b" width="300" alt="rainy" />
  <img src="https://github.com/user-attachments/assets/90625d9b-7c07-4420-870b-e5e8954dd6fe" width="300" alt="sunny" />
  <img src="https://github.com/user-attachments/assets/aacb09d5-8c87-4fd5-bfe1-2794c388db84" width="300" alt="cloudy" />
</p>

