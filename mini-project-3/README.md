# Simple Cart Program

A Simple cart program implementation supporting CRUD implemention for cart interaction simulating e-commerce application.

## Classes

### Service

The following classes are the main service model for the application:
* Cache
  * Used from mini-project-2
  * Used for efficient searching
  * Updated to use generics for future projects
* Cart
  * A Class to handle CRUD operations of cart
* Store
  * A class the abstract the user interaction to the cart and cache

### Model

The following classes are the data models of the application
* ProductItem
  * Contains the data needed for all product items
* ProductType
  * Contains the type of the product used for categorization
* Executable
  * Used by the CacheService to get all the values needed for efficient searching