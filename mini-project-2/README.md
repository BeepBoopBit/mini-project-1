# Library Management System

A Simple library Management system with optimize searching capabilities

## Environment

The below section indicates the requirement and running instruction for the program

### Requirement
* Java Runtime Environment 17+

### Running
1. Open the project in your terminal
2. Type `java -cp target\LibraryManagementSystem-1.0-SNAPSHOT.jar org.stratpoint.Main`
3. There should no be errors unless there's no java in the environment

## Classes

Below this section we'll discuss the individual function of the classes

### LMS

Contains all the library-related classes such as:
* Cache
  * An Implementation for fast searching using hashmap. This is used to cacheServiceImpl words in the books for faster searching.
* Library
  * The Main Library file to run. It abstracts the Library Management System for easier use of other programs. It handles the menu for the Library
* Library Management System
  * The main system that manages all other classes. It abstracts the complexities of managing library features such as `search`, `add`, `delete`, and `display`


### Book

Contains all the implementation of the `Book` class for genre-specific books. Currently, it contains these following genres:
* Fiction
* NonFiction

```info
Note: Each book should implement the getAllString(), otherwise it'll be the default.
```