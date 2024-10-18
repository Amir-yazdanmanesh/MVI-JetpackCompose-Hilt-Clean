# GitComposeApp (Jetpack Compose + MVI + Unit Testing)

![Language](https://img.shields.io/github/languages/top/cortinico/kotlin-android-template?color=blue&logo=kotlin)

GitComposeApp is a modern sample project that demonstrates how to build scalable and maintainable Android applications by leveraging Jetpack Compose and Kotlin's latest features. It integrates Compose with MVI architecture and highlights the use of key Android tools like Kotlin Flow and Hilt for dependency injection.

The app serves as an example of clean Android development, focusing on how to structure an app efficiently using the latest frameworks and libraries.

## Features

* **UI Components**
    * Built entirely with [Jetpack Compose](https://developer.android.com/jetpack/compose), Android's native declarative UI toolkit.
    * Styled with [Material Design](https://material.io/design) guidelines for consistent and engaging user interfaces.

* **Technologies**
    * [Kotlin](https://kotlinlang.org/) used across the project, ensuring concise and expressive code.
    * Asynchronous data handling with [Coroutines](https://kotlinlang.org/docs/coroutines-overview.html) and [Flow](https://developer.android.com/kotlin/flow).
    * [Hilt](https://developer.android.com/training/dependency-injection/hilt-android/) for streamlined dependency injection.
    * [Retrofit](https://square.github.io/retrofit/) for network calls.
    * Image loading with [Coil](https://github.com/coil-kt/coil).

* **Architecture**
    * Single Activity design utilizing [Jetpack Navigation](https://developer.android.com/guide/navigation).
    * MVI (Model-View-Intent) architecture for unidirectional data flow and clear separation of concerns.
    * Android Architecture Components ([ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel), [LiveData](https://developer.android.com/topic/libraries/architecture/livedata)) to handle state and lifecycle-aware components.
    * [KTX Extensions](https://developer.android.com/kotlin/ktx) for simplified Kotlin code in Jetpack.

## Dark Mode & Light Mode Previews
Below are the previews of both dark and light modes, giving users a seamless experience across themes:

| Dark Theme UI                            | Light Theme UI                              |
|------------------------------------------|---------------------------------------------|
| <img src="misc/demo_light_mode.gif" width="360" height="820"> | <img src="misc/demo_dark_mode.gif" width="360" height="820"> |

## App Architecture Overview
The app follows the MVI architectural pattern, separating concerns into distinct layers for better maintainability and scalability. Each screen is driven by states, actions, and effects.

**Architecture layers:**
- **View**: Compose-based UI that listens for state updates and user events.
- **ViewModel**: Handles logic, processes user actions, and updates the view state.
- **Model**: Responsible for fetching and providing data, encapsulating business logic in repositories.

### Core Components:
1. **State** - A data class representing the UI's state (e.g., loading status, data to display).
2. **Event** - A user-triggered action passed from the UI to the ViewModel (e.g., button clicks).
3. **Effect** - One-off events like navigation actions, displaying a SnackBar, or showing a Toast.

These components work together to implement a clear, unidirectional data flow. The ViewModel mediates between the view and model, ensuring separation of concerns and predictable behavior.

---

If you enjoy this project or find it helpful, please give it a star ⭐!
