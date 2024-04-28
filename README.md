
# News App Assessment Tata AIG 
The purpose of this repo is to follow up Clean Architecture principles by bringing them to Android. The repo contains a Sample News Application which shows current news from https://newsapi.org/ API.

## Clean Architecture
Clean architecture promotes separation of concerns, making the code loosely coupled. This results in a more testable and flexible code. This approach divides the project into 3 modules: presentation, data, and domain.
* __Presentation__: Layer with the Android Framework, the MVVM pattern and the DI module. Depends on the domain to access the use cases and on di, to inject dependencies.
* __Domain__: Layer with the business logic. Contains the use cases, in charge of calling the correct repository or data member.
* __Data__: Layer with the responsibility of selecting the proper data source for the domain layer. It contains the implementations of  the repositories declared in the domain layer. It may, for example, check if the data in a database is up to date, and retrieve it from service if it’s not.

## Functionality
The app's functionality includes:
1. Fetch Current News data from https://newsapi.org/ & show them in `RecylerView` with smooth pagination.
2. When an item is selected from `RecyclerView` it will load the news article in a `Webview`.
3. From Details view , a news article can be added to Favorite news - which will store the News article in the Room database.
4. From Today's news section users can search for specific news topic & return the search results with pagination.
5. From Bookmark news section users can view all their saved news articles, they can also swipe left/right to delete the article from local database.

## Architecture
The app uses clean architecture with `MVVM(Model View View Model)` design pattern. 
MVVM provides better separation of concern, easier testing, lifecycle awareness, etc.

### UI
The UI consists of two parts
1. `View` - Activity screen, Host the navigation component fragments.
2. `Fragment` - Contains two fragments:

    a) `FeedFragement` - Show & search for today's news. Listen for `onScrollListener` using `EndlessRecyclerOnScrollListener` for Recylcerview.

    b) `BookmarkFragment` - Show bookmarked news articles.

    c) `DetailsFragment` - Show webview with floating action button for loading & saving news article.

### Model
Model is generated from `JSON` data into a Kotlin data class.
In addition entity class has been added for room database along with `Type converter` for saving/retrieving custom object data.

### ViewModel
`MainViewModel.kt`

Used for fetching today's news, searching news & update states. Also send out the status of the network call like Loading, Success, Error using `sealed` class.

The `ViewModel` also responsible for pagination of data using page count.
The `ApplicationModule.kt` class provides  `Singleton` reference for `Retrofit`, `OkHttpClient`, `Repository` etc.

### Network
The network layer is composed of Repository, ApiService.
`NewsApi` - Is an interface containing the suspend functions for retrofit API call.
`NewsRepository` - Holds the definition of the remote/local repository call.

 ## Tech Stack
* [Android appcompat], [KTX],
* [Constraint layout], [Material Support]
* [Android View Binding]
* [Hilt] for dependency injection.
* [Retrofit] for REST API communication
* [Coroutine] for Network call
* [Lifecycle], [ViewModel]
* [Room] for local database.
* [Navigation Component] for supporting navigation through the app.
* [Glide] for image loading.
* [Swipe Refresh Layout] for pull-to-refresh `RecyclerView`.
* [EndlessRecyclerOnScrollListener] for Recylerview Infinite Scroll.
* [Mockito] & [Junit] for Unit testing.
* [Espresso] for UI testing.

## Testing
Unit testing has been added for `MainViewModel` & `NewsRepository`.

### `MainViewModelTest.kt`
Test the viewmodel of the app using `CoroutineRule` & `Stateflow value`.
The test cases comprise of testing different states like Loading, Success, Error with fake data for testing News Response & Search Response.

### `NewsRepositoryTest.kt`
Test the Repository of the app using `Robolectric`.

The test comprises of testing the functionality of Favorite News Room Database like Insertion, Remove, Get saved news etc.

[Mock Webserver] is used to test the Network api response in case of successful data, empty, failed case.




