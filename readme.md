# Tango Test

![1](https://github.com/ispam/ZemogaApp/blob/master/gif/space_news.gif)

**Objective:** Build a REST app to show Articles and its details

## Architectural Pattern

** I choose to go with a MVVM pattern since for the app to build I believe its more suited**

The architectural pattern are presented by modules:
-	*Presentation Module:* This module includes all components related to the UI
-	*Domain Module:* This is an extension of the business logic, its commonly used to satisfy the business model.
-	*Data Module:* This module functionality allows the app to have a clear communication of what to read and delete
-   *Common Module:* This module is in charge to hold all the common extensions and other functions
-   *Di Module:* This module is responsible for dealing with all the dependency injections
-   *UI Module:* This module is for all the UI components
-   *Utils Module:* This module holds all utility classes

## Libraries
- [Dagger](https://github.com/google/dagger) by Google
- [Retrofit](https://github.com/square/retrofit) by Square
- [okHTTP](https://github.com/square/okhttp) by Square
- [Picasso](https://github.com/square/picasso) by Square

## License

Copyright [2021] [Diego Fernando Urrea Gutiérrez]

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.