## Top100 
[![Build Status](https://travis-ci.org/clh161/Top100.svg?branch=master)](https://travis-ci.org/clh161/Top100)
[![Gitter](https://img.shields.io/gitter/room/nwjs/nw.js.svg)](https://gitter.im/clh161-top100/Lobby)
[![codecov](https://codecov.io/gh/clh161/Top100/branch/master/graph/badge.svg)](https://codecov.io/gh/clh161/Top100)
[![API](https://img.shields.io/badge/API-21%2B-brightgreen.svg?style=flat-square)](https://android-arsenal.com/api?level=21)

This is a showcase app created to fetches top free apps and top grossing apps from iTunes APIs and displays as a list of information.

# Architecture:
MVP - The business logic of an activity is extracted into a class Presenter and the data management is handled by another class Interactor. This architecture increases testability to expand unit test coverage. 

# Dependencies injection
[Dagger](https://github.com/google/dagger) is applied to provide a better dependencies management and also increase testability of the program because singleton class is not mockable while Dagger can replace traditional singleton class by maintaining an instance during the app run time.

# RxAndroid
[RxAndroid](https://github.com/ReactiveX/RxAndroid) is very useful for async task and data manipulation such as http calls because this provides an easy way to manage threading and also has many methods for edit and pass  data from different observables. 

# Unit test
Part of the business logic is covered by unit test.
