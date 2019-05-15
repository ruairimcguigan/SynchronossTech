# SynchronossTech

#### Simple fetch display and persistence of data using MVVM with Jetpack components. 
Using, single Activity-> multiple Fragment based structure, packaged by feature

## Tech used
* Kotlin
* Androidx
* Dagger
* Retrofit
* Architecture Components incl: Livedata, ViewModel, Lifecycle-Aware Components, Room, WorkManager, Navigation lib, Databinding
* Gson

## Dependencies
* Google Play Services
* Timber
* Okhttp
* Stetho


## Suggested improvements
* UI improvements, the UI here is the very minimim for displaying data
* Correctly handle error states
* Provide rationale for requesting user's location 


* With more time, comprehensive testing, including for:
  - Espresso - with more time the view would be covered by integration tests
  - Viewmodel - business logic tested here using mockito
  - Test permissions handling using the GrantPermissionRule
  - Unit test remote layer
  - Unit test cache layer
  - Error states
