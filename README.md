# WebSocketSample

This is a sample showing a list of messages from mock server and update their names according to messages received from a `WebSocket` connection.

You can find unit tests in `/app/src/test` directory


### Android Development
WebSocketSample attempts to use the latest libraries and tools:

  - Written in [Kotlin](https://kotlinlang.org/)
  - Developed with [MVVM Architectural style](https://developer.android.com/jetpack/docs/guide#recommended-app-arch) recommended by Google
  - Used [Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html) for asynchronous works
  - Used [Architecture Components](https://developer.android.com/topic/libraries/architecture/): LiveData and Lifecycle-components
  - Used [dagger-android](https://google.github.io/dagger/android.html) for Dependency Injection
  - Used [Retrofit](https://square.github.io/retrofit/) for rest requests
  - Used [OkHttp](https://square.github.io/okhttp/) for web socket 

### Code Style

This project uses the following tools to maintain code quality. The configurations can be found in `/qa` directory

- [ktlint](https://ktlint.github.io/)
- [detekt](https://arturbosch.github.io/detekt/)
- [Android Lint](http://tools.android.com/tips/lint)

  ```
  ./gradlew lint detekt ktlintCheck
  ``` 
  
  
