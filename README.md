Small Utilities Compilation
===========================

A collection of small, handy **Java-based utilities** for file manipulation, text/CSV processing, SQLite parsing, HTML data parsing, Quizlet helpers, and logging customization.The goal of this project is to provide reusable, lightweight tools that can be easily integrated into other Java projects.

⚙️ Technologies
---------------

*   **Java 17+**

*   **Maven** (build & dependency management)

*   **SQLite** (for parsing utilities)

*   **Logback** (logging, with custom color formatting)


📂 Project structure
--------------------

The repository is organized into independent utility packages:

*   **exceptions** → Custom exception classes (DataFetchException, JsonNullException, etc.)

*   **json\_data\_parser\_from\_html** → Utilities for parsing HTML and extracting data (LotteryPredictor, DrawResultParser, etc.)

*   **loggers** → Logging helpers with Loggable annotation and logback color customizer.

*   **manipulations\_with\_common\_files** → File chooser and general file utilities.

*   **manipulations\_with\_textfiles\_and\_csv** → Readers, savers, and converters for text and CSV files.

*   **quizlet\_helper\_utils** → Helpers for Quizlet, including CSV utilities, formatters, and German article helpers.

*   **sql\_lite\_parser\_into\_csv** → Convert SQLite databases into CSV format.


🚀 Getting Started
------------------

### Clone the repository


```
git clone https://github.com/jvatechs/Small_utilities_compilation.git
cd Small_utilities_compilation
```

### Build with Maven

`mvn clean install
`
### Run inside IntelliJ IDEA

1.  Open the project in **IntelliJ IDEA**.

2.  Make sure Maven has downloaded all dependencies.

3.  Run any utility class (for example SQLParseIntoCSV or LocalMain).


🛠️ Examples
------------

### Convert SQLite into CSV

```
SQLParseIntoCSV parser = new SQLParseIntoCSV("database.db", "output.csv");
parser.parse(); 
```

📜 License
----------

This project is distributed under the MIT License.Feel free to use and adapt these utilities in your own projects.