# NumberFun

NumberFun is an Android mathematics education application developed using Kotlin and Jetpack Compose.

The application is designed to help users practise basic mathematics skills through interactive quizzes while tracking their results and progress.

## Features

- Interactive mathematics quizzes
- Easy, Medium and Hard difficulty levels
- Multiple-choice questions
- Immediate answer feedback
- Score tracking
- Quiz statistics and previous results
- Settings for quiz difficulty and sound
- Online maths fact functionality
- Local data storage using Room
- Navigation between Home, Quiz, Statistics and Settings screens

## Technologies Used

- Kotlin
- Android Studio
- Jetpack Compose
- MVVM architecture
- Navigation Compose
- Room Database
- Retrofit
- Kotlin Coroutines and StateFlow
- JUnit unit testing
- Git version control

## Application Structure

The application separates responsibilities into several components:

- UI screens display the application interface.
- ViewModels manage UI state and application logic.
- Repository classes manage access to application data.
- Room provides persistent local storage for quiz results.
- Retrofit provides access to an external web service.
- Navigation Compose manages movement between application screens.

## Testing

Unit tests are included in the project to test the quiz question generation logic.

The tests verify:

- Easy difficulty number ranges
- Medium difficulty number ranges
- Hard difficulty number ranges
- Correct answer calculation
- Generation of four answer options including the correct answer

## Running the Application

1. Open the project in Android Studio.
2. Allow Gradle to sync.
3. Select an Android emulator or connected Android device.
4. Run the `app` configuration.
5. The application will open on the NumberFun home screen.

## Author

Developed for CP3406/CP5307 Mobile Computing.
