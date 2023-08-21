# PixabayX

PixabayX 🖼️ is an Android app that fetch and cache images from [Pixabay API](https://pixabay.com/api/docs/), this app shows the best practices when you want to apply paging and caching for the paginated data.
 <p align="center">
        <img src="https://github.com/nedaluof/PixabayX/blob/master/art/bixapay_x_animated.gif" width="200">
        <img src="https://github.com/nedaluof/PixabayX/blob/master/art/pixabay_x_1.PNG" width="200">
        <img src="https://github.com/nedaluof/PixabayX/blob/master/art/pixabay_x_2.PNG" width="200">
        <img src="https://github.com/nedaluof/PixabayX/blob/master/art/pixabay_x_3.PNG" width="200">
  </p>
  
## 🖼️ Tech stack 👑
- [Kotlin](https://kotlinlang.org/)
  based, [Coroutines](https://github.com/Kotlin/kotlinx.coroutines)
    + [Flow](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/)
      for asynchronous tasks.
- [Material Components for Android](https://github.com/material-components/material-components-android)
  .
- Jetpack
    - [Android Architecture Components](https://developer.android.com/topic/libraries/architecture)
        - Lifecycle: Observe Android lifecycles and handle UI states based on the lifecycle changes.
        - ViewModel: Manages UI-related data.
        - DataBinding: support library that allows you to bind UI components in your layouts to data
          sources in your app using a declarative format rather than programmatically.
        - Room: used to construct reliable caching for offline support.
        - [Paging 3](https://developer.android.com/topic/libraries/architecture/paging/v3-overview)
          helps you load and display pages of data from a larger dataset from local storage or over
          the network.
            - [Remote Mediator API](https://developer.android.com/reference/kotlin/androidx/paging/RemoteMediator)
        - [Hilt](https://dagger.dev/hilt/): for DI (dependency injection) .
- [Retrofit2 & OkHttp3](https://github.com/square/retrofit): REST APIs.
- [Moshi](https://github.com/square/moshi/): A modern JSON library for Kotlin and Java.
- [Coil](https://github.com/coil-kt/coil): Loading images from network and caches it.
- [Timber](https://github.com/JakeWharton/timber): A logger with a small API footprint.
- Architecture
    - The bixapayX app follows MVVM - Repository Pattern Clean Architecture (View - ViewModel - Model) - Domain layer - Data
      layer.

<p align="center">
    <img src="https://github.com/nedaluof/Quotes/blob/master/screen_shots/mad_arch_overview.png?raw=true" width="350">
</p>

- The data is bumped directly from the database to the UI over
      the [Paging 3](https://developer.android.com/topic/libraries/architecture/paging/v3-overview)
      PagingSource Assisted
      with [Remote Mediator](https://developer.android.com/reference/kotlin/androidx/paging/RemoteMediator)
      which controls the process of loading new pages from the Remote Source
      and cache it on the database, then the database notifies the UI about the new changes over the Flow API, so based on this mechanism the database becomes the source of truth of the data in the app, and the remote mediator calculates the pages based on stored PagingKeys
      that contains:
  
        - nextKey -> next page number
        - prevKey -> previous page number
        - currentPage -> current page number



  The following image demonstrates the flow of the process
         

<p align="center">
  <br/>
    <img src="https://developer.android.com/static/topic/libraries/architecture/images/paging3-layered-architecture.svg" width="800">
</p>

<br/>
<br/>
<br/>

### License

```
Copyright 2023 Nedal Hasan ABDALLAH (NedaluOf)

Licensed under the Apache License, Version 2.0 (the "License");
You won't be using this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing,
software distributed under the License is distributed on an 
"AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
either express or implied. See the License for the specific 
language governing permissions and limitations under the License.

```
