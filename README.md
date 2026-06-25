## Mobile Screen Sample :

  <img src="https://github.com/user-attachments/assets/47aa58fd-ebb4-4bde-a5f0-d920f6085639" width="24%" />
  <img src="https://github.com/user-attachments/assets/75dac8fd-f5d7-49eb-b5e4-3939553d21cf" width="24%" />
  <img src="https://github.com/user-attachments/assets/9c0a93bf-e72f-41ad-9360-b18666572bee" width="24%" />
  <img src="https://github.com/user-attachments/assets/b8dd2c4a-d12d-46c0-805d-ad4ff96adaa1" width="24%" />

## Tech Stack

- **Language:** [Kotlin](https://kotlinlang.org/) - 100% Type-safe and modern language.
- **UI Framework:** [Jetpack Compose](https://developer.android.com/compose) - Declarative UI for a responsive and modern user interface.
- **Dependency Injection:** [Hilt](https://developer.android.com/training/dependency-injection/hilt-android) - Standard library for DI in Android.
- **Networking:** [Retrofit](https://square.github.io/retrofit/) with **Gson Converter** - For efficient API communication and JSON parsing.
- **Navigation:** [Jetpack Navigation (Compose)](https://developer.android.com/compose/navigation) - Utilizing **Type-Safe Navigation** with Kotlin Serialization.
- **Asynchronous Flow:** [Kotlin Coroutines & Flow](https://kotlinlang.org/docs/coroutines-overview.html) - For handling background tasks and reactive data streams.
- **Image Loading:** [Coil](https://coil-kt.github.io/coil/) - Optimized image loading for Jetpack Compose.
- **Serialization:** [Kotlinx Serialization](https://kotlinlang.org/docs/serialization.html) - For type-safe navigation arguments.


---




##  Architecture

The application follows a strict **Clean Architecture** pattern divided into three distinct layers, ensuring a separation of concerns, testability, and maintainability:

### 1. Data Layer (`data/`)
- **Remote**: Defines API interfaces (`ApiService`) and Data Transfer Objects (DTOs) for network communication.
- **Mappers**: Handles the transformation of raw API response models into clean Domain models via extension functions (`toDomain()`).
- **Repository Implementation**: Implements the repository contracts using the API service and emits data through Kotlin Flows.

### 2. Domain Layer (`domain/`)
- **Models**: Pure Kotlin data classes representing the business entities, completely independent of external libraries or serialization.
- **Repository Contracts**: Interfaces that define the operations available for the data layer to implement.
- **Use Cases**: Single-purpose classes (`GetCourseUseCase`) that encapsulate specific business logic and interact with repositories.
- **State Handling**: A generic `ResultState` wrapper to communicate Loading, Success, and Error states to the UI.

### 3. Presentation Layer (`presentation/`)
- **UI States**: Sealed classes representing the state of the UI at any given moment.
- **ViewModels**: Hilt-powered ViewModels that manage state, handle user interactions, and execute use cases.
- **Screens**: Modular Composable screens (`HomeScreen`, `CourseDetailScreen`, `LessonScreen`) that react to state changes.
- **Components/Elements**: Reusable UI atoms like `LoadingScreen` and `ErrorScreen`.

---

##  AI Implementation & Collaboration

This project was developed with a high level of integration with AI tools to ensure efficiency and high-quality standards:

- **UI Development**: I utilized AI to architect the **basic structure of the UI code**. This helped in rapidly prototyping the layout systems (LazyColumns, Rows, and Boxes) and ensuring the Teal + Cream design system was consistently applied across the Browse, Detail, and Lesson flows.
- **Code Documentation**: AI played a pivotal role in generating **comprehensive KDoc documentation** across the entire codebase. Every function, class, and parameter is professionally documented to follow industry standards, improving code readability and maintainability for future developers.
- **Code Refactoring**: AI was used to refactor navigation logic from ID-based parameters to modern Type-Safe routes, ensuring the latest Android development best practices were met.

---
