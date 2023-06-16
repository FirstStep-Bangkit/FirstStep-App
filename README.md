# Overview
Capstone Project Bangkit Academy 2023 Batch 1 (Mobile Development)

## About Our App
This application is an Indonesian MBTI test (16 personality) application which includes test features, chat with counsellors, personality descriptions and profiles. The test results on the application can be consulted with the counsellor through the chat feature (chat feature is in the development stage). Then on the profile, the user can change the profile photo, password and delete the account.
## Repository Supporting This Application
| Path               | Link Repository |
|--------------------|-----------------|
| Mobile Development | FirstStep-App (this repository)               |
| Cloud Computing    | [FirstStep-API](https://github.com/FirstStep-Bangkit/firststep-api.git)                |
| Machine Learning   | [FirstStep-Model](https://github.com/FirstStep-Bangkit/FirstStep-Model.git)                |
# Usage
## Requireement
1. Android Studio Flamingo (support jetpack compose material 3)
2. Minimum SDK 24 (Android 7.0)
3. PC/Laptop
   * Processor: Intel Core i3 or higher (recomended i5 or higher)
   * RAM 8 GB or higher
   * Disk minimum 8 GB
   * Microsoft Windows 64 bit
## Instalation
1. Clone this repository
2. Open in Android Studio (according to requirements)
3. Connect your device in android studio
   * In virtual device 
   * In real device : Click [Connect With USB Debugging](https://developer.android.com/codelabs/basic-android-kotlin-compose-connect-device#0) or [Connect With Wireless Debugging](https://developer.android.com/studio/run/device)
4. Run your Android Studio: Click [Documentation](https://developer.android.com/studio/run/managing-avds)
## Library
| Library                    | Usage                                                                                                                             | Dependencies                                                            |
|----------------------------|-----------------------------------------------------------------------------------------------------------------------------------|-------------------------------------------------------------------------|
| Android KTX                | To provide Kotlin extensions for core Android components such as collections, files, preferences, and more                        | implementation  'androidx.core:core-ktx:1.8.0'                          |
| Lifecycle                  | To enable observing and responding to life cycle changes of Android components such as activities and fragments.                  | implementation  'androidx.lifecycle:lifecycle-runtime-ktx:2.6.1'        |
| Activity Compose           | To provide Activity-ktx, which is part of Jetpack Compose, for easy integration of Activity with Compose UI.                      | implementation  'androidx.activity:activity-compose:1.5.1'              |
| Compose UI                 | To provide UI basics on Jetpack Compose and layout system to build a declarative and responsive user interface.                   | implementation  'androidx.compose.ui:ui'                                |
| Compose UI Graphic         | To provide functions and tools for drawing and manipulating graphics within Jetpack Compose.                                      | implementation  'androidx.compose.ui:ui-graphics'                       |
| Compose Tooling Preview    | To provide development tools and previews to help develop interfaces with Jetpack Compose.                                        | implementation  'androidx.compose.ui:ui-tooling-preview'                |
| Jetpack Compose Material 3 | To provide Material Design version 3 components for Jetpack Compose, including components such as buttons, cards, text, and more. | implementation  'androidx.compose.material3:material3'                  |
| JUnit                      | To provide Material Design version 3 components for Jetpack Compose, including components such as buttons, cards, text, and more. | testImplementation  'junit:junit:4.13.2'                                |
| JUnit Test                 | This library is part of AndroidX Test and provides extensions to the JUnit unit testing framework for Android.                    | androidTestImplementation  'androidx.test.ext:junit:1.1.5'              |
| AndroidX Test              | To provide a powerful and expressive functional testing (UI) framework for Android application testing.                           | androidTestImplementation  'androidx.test.espresso:espresso-core:3.5.1' |
| Compose JUnit Test         | To provide testing tools and utilities for UI testing with Jetpack Compose using JUnit 4.                                         | androidTestImplementation  'androidx.compose.ui:ui-test-junit4'         |
|                            |                                                                                                                                   |                                                                         |
|                            |                                                                                                                                   |                                                                         |
|                            |                                                                                                                                   |                                                                         |
