<p align="center">
TaipeiTour is a sample tour application based on modern Android application tech-stacks and MVVM architecture.
</p>

## Feature

1. Display attractions when launch app.

2. Switch corresponding app's locale when selecting the language on the main screen's actionBar's
   overflowMenu .

3. Display corresponding attraction details when clicking attraction.

4. Open official site when clicking the official site url in attraction details. If url is start
   with https, the content will be displayed in the app. Otherwise, the content will be displayed in
   the browser.

## Tech stack & Open-source libraries

- Minimum SDK level 26
- [Kotlin](https://kotlinlang.org/)
  based, [Coroutines](https://github.com/Kotlin/kotlinx.coroutines)
    + [Flow](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/)
      for asynchronous.
- [Gradle Kotlin DSL](https://docs.gradle.org/current/userguide/kotlin_dsl.html) - For writing
  Gradle build scripts using Kotlin.

- [Version Catalog](https://developer.android.com/build/migrate-to-catalogs) - For manage
  dependencies
- Jetpack
    - [Databinding](https://developer.android.com/topic/libraries/data-binding) - Bind UI components
      in the layouts to data sources in the app.
    - [Hilt](https://dagger.dev/hilt/) - For dependency injection.
    - [Lifecycle](https://developer.android.com/topic/libraries/architecture/lifecycle) - Build
      lifecycle-aware components, like ViewModel and LiveData that can automatically adjust their
      behavior based on the current lifecycle state of an activity or fragment.
    - [Navigation](https://developer.android.com/guide/navigation) - Navigate across, into, and back
      out from the different pieces of content within the app.
    - [Paging3](https://developer.android.com/topic/libraries/architecture/paging/v3-overview) - To
      load and display pages of data from a larger dataset.
    - [Recyclerview](https://developer.android.com/guide/topics/ui/layout/recyclerview) - Use to
      efficiently display large sets of data.
- Third Party Library
    - [Coil](https://github.com/coil-kt/coil) - Loading images.
    - [OkHttp3](https://github.com/square/okhttp) - Log the outgoing request and the incoming
      response.
    - [Retrofit2](https://github.com/square/retrofit) - Send requests to API and receive response.
- [Material](https://github.com/material-components/material-components-android) - Help to build
  material components like bottom navigation bar, floating action button.
- [Kotlin serialization](https://kotlinlang.org/docs/serialization.html) - Parse and convert a JSON
  object into Kotlin objects.
- Architectural and Design pattern
    - MVVM
    - Observer
    - Adapter
    - Dependency Injection
    - Singleton

## Open API

TaipeiTour using the [Taipei travel API](https://www.travel.taipei/open-api/swagger/ui/index#/) for
obtaining remote data.
