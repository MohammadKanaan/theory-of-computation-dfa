# DFA Date Validator

This project is a Java-based implementation of a Deterministic Finite Automaton (DFA) to validate date strings. The DFA processes input strings and determines whether they represent valid dates based on predefined rules.

## Features
- Implements a DFA with states and transitions to validate date formats.
- Accepts user input via the console to check date validity.
- Provides detailed state transition logs during processing.

## Files
- `src/DFA.java`: Contains the DFA implementation, including states, transitions, and the logic to process input strings.
- `src/Main.java`: Provides a console-based interface for users to input dates and check their validity.

## How to Run
1. Compile the project using a Java compiler or an IDE like IntelliJ IDEA.
2. Run the `Main` class.
3. Enter a date string when prompted.
4. The program will indicate whether the date is valid or invalid.

## Example
```
Your date: 
12/31/2023
12/31/2023 is valid
Do you want to enter a new date? (y/n)
```

## Requirements
- Java 8 or higher
- IntelliJ IDEA or any Java-compatible IDE

## License
This project is for educational purposes and is not licensed for commercial use.
