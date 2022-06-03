# mostpopular NYTimes

## About
Android App written in Kotlin using MVVM Clean and single activity Architecture. 
Listing the most popular news articles from New York Times API.

## Technologies Used
-Kotlin
-Coroutines
-LiveData
-MVVM Clean Architecture
-Android Architecture Components under Android Jetpack
-Room Database for local caching
-ViewModel for a lifecycle aware datastore
-DaggerHilt for Dependency Injection
-Retrofit for remote API calls
-Moshi library 

## Built With
- [Kotlin](https://kotlinlang.org/)  
- [Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html)
- [Android Architecture Components](https://developer.android.com/topic/libraries/architecture)
  - [LiveData](https://developer.android.com/topic/libraries/architecture/livedata)
  - [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel)
  - [ViewBinding](https://developer.android.com/topic/libraries/view-binding)
  - [Room](https://developer.android.com/topic/libraries/architecture/room)
- [Dependency Injection](https://developer.android.com/training/dependency-injection)
  - [Hilt-Dagger](https://dagger.dev/hilt/)
  - [Hilt-ViewModel](https://developer.android.com/training/dependency-injection/hilt-jetpack)
- [Retrofit](https://square.github.io/retrofit/)
- [Moshi](https://github.com/square/moshi)
- [Moshi Converter](https://github.com/square/retrofit/tree/master/retrofit-converters/moshi)
- [Coil-kt](https://coil-kt.github.io/coil/)


##  Setup & Installation
 
Build & Run the project using Run > Run 'app' or the following command ./gradlew installDebug

## Unit testing

Run the tests using either class by class Right-Click > Run 'TestClassName' or by using the following command from terminal window ./gradlew test