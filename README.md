<h1 align="center">Ideavista 🏡</h1>

<p align="center">
  <img src="https://github.com/user-attachments/assets/77479ced-3ebd-43ff-a149-6f4a66e32624" width="400" alt="App food">
</p>

## 📌 Description  
A modern real estate app for Android inspired by **Idealista**, designed for users to search, filter, and save properties based on their preferences. The app allows seamless property browsing, advanced search options (including **drawing on a map** with Google maps API), and direct messaging with property owners.  

## 🚀 Technologies Used  

### **Frontend (Android)**  
- **Language:** ![Kotlin](https://img.shields.io/badge/Kotlin-8b14f9.svg?style=for-the-badge&logo=openjdk&logoColor=white)  
- **UI Framework:** ![Jetpack Compose](https://img.shields.io/badge/Jetpack_Compose-4285F4.svg?style=for-the-badge&logo=jetpack-compose&logoColor=white)  
- **Architecture:** ![MVVM](https://img.shields.io/badge/MVVM-FFCA28.svg?style=for-the-badge&logo=android&logoColor=white)  
- **Navigation:** ![Jetpack Navigation](https://img.shields.io/badge/Jetpack_Navigation-00ACC1.svg?style=for-the-badge&logo=android&logoColor=white)  
- **Dependency Injection:** ![Koin](https://img.shields.io/badge/Koin-3DDC84.svg?style=for-the-badge&logo=android&logoColor=white)  

### **Backend & Cloud Services**  
- **Authentication:** ![Firebase Auth](https://img.shields.io/badge/Firebase_Auth-FFCA28.svg?style=for-the-badge&logo=firebase&logoColor=white)  
- **Database:** ![Firestore](https://img.shields.io/badge/Firestore-FF6D00.svg?style=for-the-badge&logo=firebase&logoColor=white)   
- **Maps API:** ![Google Maps](https://img.shields.io/badge/Google_Maps-4285F4.svg?style=for-the-badge&logo=googlemaps&logoColor=white)  

### **Additional Libraries & Tools**  
- ![Retrofit](https://img.shields.io/badge/Retrofit-4DB33D.svg?style=for-the-badge&logo=android&logoColor=white) - Networking  
- ![DataStore](https://img.shields.io/badge/DataStore-1976D2.svg?style=for-the-badge&logo=android&logoColor=white) - Local preferences  
- ![Coil](https://img.shields.io/badge/Glide-FF5722.svg?style=for-the-badge&logo=android&logoColor=white) - Image loading   

---

## 🏁 **Onboarding Process**  
The onboarding process guides users through selecting **language** and **region**, storing these preferences using **DataStore** for a personalized experience.  

<div align="center">
  <h3>Onboarding preferences</h3>
  <img src="https://github.com/user-attachments/assets/74134d71-dd0c-404b-9d68-70501f6b1a93" width="300" alt="register">
</div>


---

## 🔐 **User Registration**  
Users can **sign up** using **Firebase Authentication** with email/password, Google, or other authentication methods.  

<div align="center">
  <h3>User registration</h3>
  <img src="https://github.com/user-attachments/assets/1612129d-a5b2-4241-abad-71abb8c9e871" width="300" alt="register">
</div>


---

## 🔑 **Login**  
Users can securely log in using their registered credentials. If the user is already created, it'll take the user to the login view, if not, to the registration view  

<div align="center">
  <h3>Login</h3>
  <img src="https://github.com/user-attachments/assets/590ecdb3-0ce1-4a7f-b8d6-a5441c5d02ae" width="300" alt="register">
</div>


---

## 🏡 **Home Screen & Property Search**  
- Browse properties with different filters.  
- Save properties as **favorites** for later viewing.  
- **Search history** and **recent searches** available.  
- **Chat with owners** via integrated messaging.  
- Customize settings (language, region, etc.) in the **Menu** section.  

<div align="center">
  <h3>Home and menu</h3>
  <img src="https://github.com/user-attachments/assets/7454ad59-f4b7-4150-84ed-7565ea664b20" width="300" alt="register">
</div>


---

## 🗺️ **Advanced Search with Google Maps API**  
Users can **draw a custom search area** on the map, and the app will display **all properties within the selected zone**.  

<div align="center">
  <h3>Home and menu</h3>
  <img src="https://github.com/user-attachments/assets/a655efc1-350a-45f1-90a4-a0aa20015fa3" width="300" alt="register">
</div>


---

## 🏠 **Filtering Properties**  
The app provides a **comprehensive filtering system**, allowing users to refine searches based on:  
✅ Property type (House, Apartment, Villa, etc.)  
✅ Price range  
✅ Number of bedrooms/bathrooms  
✅ Garage availability  
✅ Size (square meters)  
✅ Property status (New, Used, etc.)  

<div align="center">
  <h3>Filtering</h3>
  <img src="https://github.com/user-attachments/assets/e973a8c6-97fd-4b80-9723-ae4095a04681" width="300" alt="register">
</div> 


---

## 📌 **More Features Coming Soon!**  
Stay tuned for upcoming features like **push notifications, mortgage calculators, dark theme, etc**! 🚀  

---

## 🚀 Installation & Setup (In Progress)  

This section is currently under development. A complete installation guide will be available soon. Stay tuned! 🚧  

### ⚠️ Setup Notice  

In the meantime, you can clone the repository and explore the project. However, to fully utilize all its features, you will need:  

- Your own **`google-services.json`** file from Firebase for authentication and Firestore database access.  
- Your own **Google Maps API key** to enable map-related functionalities, such as drawing search areas and viewing property locations.  

Make sure to set up these credentials properly to avoid any issues while running the app. 🚀  
