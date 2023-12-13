Implemented Functionalities:

mkdir:
Creates a new directory in the file system.

cd:
Changes the current directory, supporting relative and absolute paths.
ls:
Lists the contents of the current directory or a specified directory.
touch:
Creates a new empty file.
echo:
Writes text to a file.
cat:
Displays the contents of a file.
mv:
Moves a file or directory to another location.
cp:
Copies a file or directory to another location.
rm:
Removes a file or directory.
Unit Tests:
Implemented unit tests using JUnit to ensure the correctness of functionalities.

Improvements to Design:

Exception Handling:

Improved error handling to gracefully handle invalid inputs and edge cases.

Code Modularity:

Encapsulated functionalities in methods for better code organization and readability.

Extended Unit Tests:

Added more comprehensive unit tests to cover various scenarios.

Documentation:

Enhanced code documentation, providing insights into data structures, design decisions, and usage instructions.

Instructions to Run:

Setup:

Ensure you have Java and Docker installed on your system.

Run Locally:

Clone the repository.
Compile the Java program (javac InMemoryFileSystem.java).
Run the program (java InMemoryFileSystem).
Enter commands interactively.

Run with Docker:

Build the Docker image (docker build -t in-memory-file-system .).
Run the Docker container (docker run -it in-memory-file-system).
Enter commands interactively.

Run Unit Tests:

Open a terminal and navigate to the project directory.
Run ./gradlew test to execute unit tests.
