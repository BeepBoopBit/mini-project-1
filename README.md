# Stratpoint Mini-Project-1
Create a simple console-based calculator that can perform basic arithmetic operations like addition, subtraction, multiplication, and division.

## Tasks
1. Set up the Java development environment.
2. Create a Calculator class with methods for addition, subtraction, multiplication, and division.
3. Implement a main method to interact with the user and perform calculations.
   Use java.util.Scanner. (
   https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/Scanner.html)
3. Handle exceptions for invalid input and division by zero.


## Implementation
In this section, we'll discuss the quick overview of different implementation details of the project

### Description
Created a simple calculator that accept expression as input `String` with precedence support for most arithmetic operations.

### Classes
There are 4 main classes in the project:
1. Calculator
2. Checker
3. Parser
4. Tree

Each of which have very specific functionalities

#### Calculator
Calculates the result of the expression base on the Expression Tree made by the `Parser`

#### Checker
Abstract the implementation of checking individual characters of the expression for tokenization

#### Parser
Parse the expression into an Expression Tree used for obtaining the result.

#### Tree
A Tree Data structure implementation modified for Expression Tree.

## Example Input
Here are some example inputs for your reference:

1. "1+2+3/4*2*9+8-2"
2. "9 + 4+2 +10 - 8 * 3/2"

## Limitation
Limitation that haven't been implemented

1. Currently Doesn't support Parenthesis
2. No Decimal support