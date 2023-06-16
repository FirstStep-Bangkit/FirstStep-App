# Overview
Capstone Project Bangkit Academy 2023 Batch 1 (Mobile Development) <br>
<br>
<img src="https://drive.google.com/uc?export=view&id=1UwsiD3znQRtcY4IJ56r6hUO72F77OE4S" alt="dashboard" width="200" height="400">
<img src="https://drive.google.com/uc?export=view&id=1jAqeefPIdhHVPAs9NAvGt5Tv2QtXTDl8" alt="profile" width="200" height="400">
<img src="https://drive.google.com/uc?export=view&id=1wtf_PeUfFMzXZ8ht6kS3YVAan2_BBGWX" alt="test" width="200" height="400">
<img src="https://drive.google.com/uc?export=view&id=12c3R_6P-wi4cgL2sr3mBikuxvBlc7sKQ" alt="personality" width="200" height="400">
<img src="https://drive.google.com/uc?export=view&id=1j32UQajK3TnDhG3AFvLUVNb2wTwhLJGN" alt="change_password" width="200" height="400">
<img src="https://drive.google.com/uc?export=view&id=177z7wvKsC6llMsbFwvnvNRS_NWEYT_Ke" alt="delete_account" width="200" height="400">

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
| Library                              | Usage                                                                                                                                                             | Dependencies                                                                |
|--------------------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------|-----------------------------------------------------------------------------|
| Android KTX                          | To provide Kotlin extensions for core Android components such as collections, files, preferences, and more                                                        | implementation 'androidx.core:core-ktx:1.8.0'                               |
| Lifecycle                            | To enable observing and responding to life cycle changes of Android components such as activities and fragments.                                                  | implementation 'androidx.lifecycle:lifecycle-runtime-ktx:2.6.1'             |
| Activity Compose                     | To provide Activity-ktx, which is part of Jetpack Compose, for easy integration of Activity with Compose UI.                                                      | implementation 'androidx.activity:activity-compose:1.5.1'                   |
| Compose UI                           | To provide UI basics on Jetpack Compose and layout system to build a declarative and responsive user interface.                                                   | implementation 'androidx.compose.ui:ui'                                     |
| Compose UI Graphic                   | To provide functions and tools for drawing and manipulating graphics within Jetpack Compose.                                                                      | implementation 'androidx.compose.ui:ui-graphics'                            |
| Compose Tooling Preview              | To provide development tools and previews to help develop interfaces with Jetpack Compose.                                                                        | implementation 'androidx.compose.ui:ui-tooling-preview'                     |
| Jetpack Compose Material 3           | To provide Material Design version 3 components for Jetpack Compose, including components such as buttons, cards, text, and more.                                 | implementation 'androidx.compose.material3:material3'                       |
| JUnit                                | To provide Material Design version 3 components for Jetpack Compose, including components such as buttons, cards, text, and more.                                 | testImplementation 'junit:junit:4.13.2'                                     |
| JUnit Test                           | This library is part of AndroidX Test and provides extensions to the JUnit unit testing framework for Android.                                                    | androidTestImplementation 'androidx.test.ext:junit:1.1.5'                   |
| AndroidX Test                        | To provide a powerful and expressive functional testing (UI) framework for Android application testing.                                                           | androidTestImplementation 'androidx.test.espresso:espresso-core:3.5.1'      |
| Compose JUnit Test                   | To provide testing tools and utilities for UI testing with Jetpack Compose using JUnit 4.                                                                         | androidTestImplementation 'androidx.compose.ui:ui-test-junit4'              |
| Compose Tooling                      | To provide development tools and utilities to assist development with Jetpack Compose.                                                                            | debugImplementation 'androidx.compose.ui:ui-tooling'                        |
| Compose Test Manifest                | To provide the manifest required to run tests with Jetpack Compose.                                                                                               | debugImplementation 'androidx.compose.ui:ui-test-manifest'                  |
| Jetpack Compose Material Design Icon | To provide extended Material Design icons for use in applications that use Jetpack Compose.                                                                       | implementation "androidx.compose.material:material-icons-extended:1.4.3"    |
| Jetpack Compose Navigation           | To provide integration between Jetpack Compose and Android Navigation Components, allowing for integrated and easy-to-manage user interface navigation.           | implementation "androidx.navigation:navigation-compose:2.5.3"               |
| Pager Component For Jetpack Compose  | To provide a Pager component for Jetpack Compose, which enables views with swipe navigation such as page views or carousel views.                                 | implementation "com.google.accompanist:accompanist-pager:0.30.1"            |
| Pager Indicator For Jetpack Compose  | To provide a pager indicator for Jetpack Compose, which can be used to display indicators such as dots or arrows to show the current position in the pager view.  | implementation "com.google.accompanist:accompanist-pager-indicators:0.30.1" |
| Retrofit                             | This library is a powerful and lightweight HTTP client for Android and Java, which allows communication with servers using the HTTP protocol and REST API.        | implementation 'com.squareup.retrofit2:retrofit:2.9.0'                      |
| GSON                                 | This library is a Retrofit converter that uses Gson to convert JSON responses from the server into Java/Kotlin objects.                                           | implementation "com.squareup.retrofit2:converter-gson:2.9.0"                |
| OKHTTP3                              | This library is an OkHttp interceptor used to view and log HTTP requests and responses for troubleshooting and debugging purposes.                                | implementation "com.squareup.okhttp3:logging-interceptor:4.9.0"             |
| View Model For Jetpack Compose       | This library provides integration between Jetpack Compose and the ViewModel architecture, allowing the use of ViewModels in Compose UI components.                | implementation "androidx.lifecycle:lifecycle-viewmodel-compose:2.6.1"       |
| Preference                           | This library provides Android preferences as a component of the Jetpack architecture, which allows easy organisation of applications and use of user preferences. | implementation "androidx.preference:preference-ktx:1.2.0"                   |
| Data Store                           | This library is part of the Jetpack DataStore and provides a more secure and efficient data storage mechanism than traditional Android preferences.               | implementation "androidx.datastore:datastore-preferences:1.0.0"             |
| Live Data                            | This library provides Kotlin extensions for the Jetpack LiveData architectural component, which enables reactive observation of data in Android applications.     | implementation "androidx.compose.runtime:runtime-livedata:1.4.3"            |
| Coil Compose                         | This library is an asynchronous image loader library for Android, which provides smooth and efficient image display in apps that use Jetpack Compose.             | implementation("io.coil-kt:coil-compose:1.4.0")                             |                                                                         |
