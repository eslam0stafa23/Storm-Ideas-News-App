# WeatherForecast

StormIdeas Android developer assessment

this app utilize the News API https://newsapi.org/ to show most recent news articles for 
different categories

## How to run?

This project needs Android Studio 4.0.0 or above with Android Gradle plugin 7.0.2+

It's recommended to open it using <B>Android Studio Artic Fox</B>.

And you can download the APK file [here](https://raw.githubusercontent.com/eslam0stafa23/Storm-Ideas-News-App/master/app/build/outputs/apk/debug/NewsApp.apk)

## Architecture
Clean architecture based on MVVM (Model-View-ViewModel)
The following diagram shows all the layers and how each layer interact with each other. This architecture using a layered software architecture.
![MVVM](https://github.com/eslam0stafa23/Storm-Ideas-News-App/blob/master/art/mvvm_architecture.png)
![Clean Architecture](https://github.com/eslam0stafa23/Storm-Ideas-News-App/blob/master/art/clean_architecture.png)
## Built With 🛠
* [Kotlin](https://kotlinlang.org/) - official programming language for Android development.
* [Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html) - for asynchronous or non-blocking programming.
* [Android Architecture Components](https://developer.android.com/topic/libraries/architecture) - Part of Jetpack it's a set of libraries that help you design robust, testable, and maintainable apps.
    - [LiveData](https://developer.android.com/topic/libraries/architecture/livedata) - an observable data holder class.
    - [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - store and manage UI-related data in a lifecycle conscious way.
    - [Room](https://developer.android.com/topic/libraries/architecture/room) - persistence library provides an abstraction layer over SQLite to allow for more robust database access while harnessing the full power of SQLite.
    - [Navigation](https://developer.android.com/guide/navigation) - Navigation refers to the interactions that allow users to navigate across, into, and back out from the different pieces of content within your app.
* [Dagger2](https://github.com/google/dagger) - Dependency Injection Framework.
* [Retrofit](https://square.github.io/retrofit/) - A type-safe HTTP client for Android.
* [Gson](https://github.com/google/gson) A Java serialization/deserialization library to convert Java Objects into JSON and back.
* [sdp](https://github.com/intuit/sdp) - size unit scales with the screen size.
* [Material Design](https://material.io/design/guidelines-overview) are interactive building blocks for creating a friendly user interface.
* [Glide](https://github.com/bumptech/glide) An image loading and caching library.
* [Timber](https://github.com/JakeWharton/timber) A logger API.
