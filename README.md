# Password Manager

A simple command-line password manager written in Java. This project was created to practice Java programming, object-oriented programming, and working with JSON files.

## Features

- Add new password entries
- View saved entries
- Search for entries by service name
- Edit existing entries
- Delete entries
- Save and load entries from a JSON file

## Technologies

- Java
- Jackson — JSON serialization and deserialization
- Git and GitHub

## How to Run

1. Clone this repository:
   ```bash
   git clone YOUR_REPOSITORY_URL
   ```
2. Open the project in your Java IDE.
3. Make sure the Jackson dependencies are configured.
4. Run the `Main` class.

## Project Structure

- `Main` — application entry point
- `PasswordEntry` — represents a saved password entry
- `JsonStorage` — handles saving and loading data in JSON format
- `PasswordManager` — manages password-related operations

*Adjust the class names above to match the final structure of your project.*

## Security Notice

This project is a learning exercise and is not intended for storing real passwords. Passwords are currently stored in plaintext in the JSON file. Encryption and secure password storage may be added in future versions.

**Do not use real credentials with this application. Do not commit files containing passwords to GitHub.**

