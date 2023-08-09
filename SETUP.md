# Project Setup Guide

Welcome to the Bookstore setup guide. This document will guide you through the process of setting up your development environment to build, test, and run the project.
## Prerequisites

    IntelliJ IDEA
    Git

## Steps to Setup

Follow the steps below to set up your development environment. If you run into any issues, feel free to ask on the mailing list.

### Database Setup
To setup a local MSSQL database, please follow the instructions in the [SETUP_DB.md](./docs/SETUP_DB.md) file.

### Application Setup

#### 1. Clone the Repository

`git clone https://github.com/aawhitetech/bookstore.git`

Navigate to the project directory:

`cd [your-project-directory]`

#### 2. Open the Project in IntelliJ IDEA

Upon opening the project, IntelliJ will automatically detect it as a Gradle project and begin the import process. If prompted to use the Gradle wrapper (which is recommended), approve it.
#### 3. Configure the Java SDK

IntelliJ IDEA usually comes with an embedded JDK. If the project requires a specific Java version and it's not already available on your system, IntelliJ will prompt you to download it. Follow the prompts to set up the required version of the Java SDK.
#### 4. Build, Test, and Run

You can manage the entire lifecycle of the application from within IntelliJ IDEA:

    Build: Build the project by clicking on the "Build" option in the top menu and selecting "Build Project".

    Test: To run the tests, right-click on the src/test directory and select "Run 'All Tests'".

    Run: Run the main application by locating the main class, right-clicking on it, and selecting "Run '[MainClassName].main()'".

Alternatively, you can use the Gradle Wrapper from the terminal or command prompt:

#### Build the project
`./gradlew build`

#### Run tests
`./gradlew test`

#### Run the application
`./gradlew run`

For Windows users, replace ./gradlew with gradlew.bat.
### 5. Contribute!

Now you're all set to start contributing to the project! If you face any issues or have questions, feel free to ask the maintainers.