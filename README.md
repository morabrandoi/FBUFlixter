# Project 2 - *FlixeterApp*

**Flixter App** shows the latest movies currently playing in theaters. The app utilizes the Movie Database API to display images and basic information about these movies to the user.

Time spent: **20ish** hours spent in total

## User Stories

The following **required** functionality is completed:

* [☑️] User can **scroll through current movies** from the Movie Database API
* [☑️] Display a nice default [placeholder graphic](https://guides.codepath.org/android/Displaying-Images-with-the-Glide-Library#advanced-usage) for each image during loading
* [☑️] For each movie displayed, user can see the following details:
* [☑️] Title, Poster Image, Overview (Portrait mode)
* [☑️] Title, Backdrop Image, Overview (Landscape mode)
* [☑️] Allow user to view details of the movie including ratings and popularity within a separate activity

The following **stretch** features are implemented:

* [☑️] Improved the user interface by experimenting with styling and coloring.
* [☑️] Apply rounded corners for the poster or background images using [Glide transformations](https://guides.codepath.org/android/Displaying-Images-with-the-Glide-Library#transformations)
* [☑️] Apply the popular [View Binding annotation library](http://guides.codepath.org/android/Reducing-View-Boilerplate-with-ViewBinding) to reduce boilerplate code.
* [☑️] Allow video trailers to be played in full-screen using the YouTubePlayerView from the details screen.

The following **additional** features are implemented:

* [☑️] Added a play icon onto the image which launches youtube as well as making image tint darker

## Video Walkthrough

Here's a walkthrough of implemented user stories:

Portrait:
![Portrait Gif](screenshots/Portrait.gif)

Landscape:
![Landscape Gif](screenshots/Landscape.gif)

## Notes

I was very focused on making the UI look nice so I even found how to make the background of the current 
activity transparent so that the previous activity could be seen. Unfortunately, this made the landscape 
mode very difficult to work with so I had to revert back. Other than that I found utilizing the API
endpoints to be the most interesting aspect of this project since I feel it is a tool which drastically 
increases the space of what is possible in an app. 

## Open-source libraries used

- [Android Async HTTP](https://github.com/loopj/android-async-http) - Simple asynchronous HTTP requests with JSON parsing
- [Glide](https://github.com/bumptech/glide) - Image loading and caching library for Android

## License

    Copyright 2020 Brando Mora

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
