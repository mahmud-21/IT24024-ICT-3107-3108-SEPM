# YOUR NAME – MATH WHIZ

A four-question mathematical game implemented with **Java Servlets, embedded Jetty 12, and JDBC**. All answers are calculated by Java methods on the server. Includes welcome, game, result and top-ten leaderboard screens.

## Objectives
Practice reusable mathematical methods, HTTP forms, session state, server-side validation, JDBC INSERT/SELECT operations and leaderboard ordering.

## Requirements and technologies
- JDK 17 or newer (Java 21 recommended); Maven 3.9+.
- Jetty 12.0.16 / Jakarta Servlet 6; no separate Jetty installation required.
- H2 file database included; optional MySQL 8+ with Connector/J included.
- JUnit 5 tests. HTML/CSS rendered by a Java Servlet; JSP is not required.

## Run with Jetty

### Fast start (no Maven download needed)
The included `math-whiz.jar` and `lib/` contain a compiled Jetty/H2 build. With Java 17+ installed, double-click `run.bat` on Windows or run `sh run.sh` on macOS/Linux. Open http://localhost:8080/. Keep `lib/` beside the JAR. This bundled launch supports H2; use the Maven build below for MySQL (or add Connector/J to the launch classpath).

### Build and run the source
Open this folder's `pom.xml` as a Maven project in IntelliJ or Eclipse. Set the project SDK to a **JDK**, reload Maven, and run `com.mathwhiz.Main`.

Or, from a terminal inside `MathWhiz`:

```sh
mvn clean test
mvn exec:java
```

Open **http://localhost:8080/**. Stop with Ctrl+C. Optional packaged launch:

```sh
mvn clean package
java -jar target/math-whiz-1.0.0.jar
```

The embedded server is Jetty. Do not deploy this executable JAR into another Jetty instance. This project uses `jakarta.servlet`, not the older `javax.servlet` API.

### Set your title
Default title is `YOUR NAME – MATH WHIZ`. Replace YOUR NAME with your own name before submission. Set `GAME_TITLE` in your IDE run configuration, or use:

Windows PowerShell:
```powershell
$env:GAME_TITLE = 'YOUR NAME – MATH WHIZ'
mvn exec:java
```

macOS/Linux:
```sh
GAME_TITLE='YOUR NAME – MATH WHIZ' mvn exec:java
```

Alternatively edit the default string in `GameServlet.title()`. Set environment variable `PORT` to use a port other than 8080.

## Database configuration
Default H2 URL: `jdbc:h2:./data/math_whiz_db;MODE=MySQL;DATABASE_TO_LOWER=TRUE`, username `sa`, empty password. The application creates the table and persists scores under `data/`, relative to the launch directory. Start from the same directory to retain the same leaderboard. Stop the first instance before starting another with this file database.

### MySQL
Run `database/math_whiz_db.sql` in MySQL Workbench or the MySQL CLI. It creates `math_whiz_db` and `game_scores`. Then configure these environment variables in your IDE:

```text
DB_URL=jdbc:mysql://localhost:3306/math_whiz_db
DB_USER=your_mysql_user
DB_PASSWORD=your_mysql_password
```

PowerShell example:
```powershell
$env:DB_URL = 'jdbc:mysql://localhost:3306/math_whiz_db'
$env:DB_USER = 'your_mysql_user'
$env:DB_PASSWORD = 'your_mysql_password'
mvn exec:java
```

Never commit database credentials. The app automatically creates the table if needed; the MySQL database itself must already exist. Use a database user with the necessary CREATE, INSERT and SELECT permissions.

### Database creation SQL
```sql
CREATE DATABASE IF NOT EXISTS math_whiz_db CHARACTER SET utf8mb4;
USE math_whiz_db;
CREATE TABLE IF NOT EXISTS game_scores (
 id INT PRIMARY KEY AUTO_INCREMENT,
 player_name VARCHAR(100) NOT NULL,
 score INT NOT NULL CHECK (score BETWEEN 0 AND 10),
 difficulty VARCHAR(20) NOT NULL,
 played_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);
SELECT * FROM game_scores
ORDER BY score DESC, played_at ASC, id ASC LIMIT 10;
```

## Game rules and scoring
| Question | Area | Marks |
|---|---|---:|
| Q1 | Prime: enter 1 for prime, otherwise 0 | 2 |
| Q2 | Java integer remainder `%` | 2 |
| Q3 | Sine, cosine or tangent | 3 |
| Q4 | Sine, cosine or tangent | 3 |
| Total | NORMAL difficulty | 10 |

Angles are in degrees and are converted to radians. Decimal answers have absolute tolerance ±0.01; enter 2–3 decimal places. Q1 and Q2 require exact integer answers. Negative remainders follow Java: `-17 % 5 = -2`. Divisors are never generated as zero. Undefined tangent angles are excluded from questions; the tangent method also rejects them. Numbers less than 2 are not prime.

Start a game with a name of 1–100 characters. Answers and the game token are validated on the server; correct answers are not sent in the game form. Each session holds one active game. Starting again replaces it. Successful submission saves one score and redirects to the result page; refreshing or submitting the same completed form does not add another score in the normal session flow. Persistence errors leave the game available to retry. Timestamps use the database/server time zone and are labeled accordingly.

## Source map and explanation
| File | Responsibility |
|---|---|
| `Main.java` | Initialize JDBC schema; create Jetty and register Servlet |
| `controller/GameServlet.java` | Welcome/game/result/leaderboard rendering, forms, validation and sessions |
| `service/MathService.java` | Prime, modulus, degree conversion, sin/cos/tan and decimal tolerance |
| `model/Game.java` | Random questions, calculated correct answers and 2+2+3+3 scoring |
| `model/GameScore.java` | Database score record |
| `dao/ScoreDAO.java` | JDBC connection, parameterized INSERT and top-ten SELECT |
| `database/math_whiz_db.sql` | MySQL schema |
| `src/test/java/com/mathwhiz/MathWhizTest.java` | Mathematical, scoring and H2 JDBC tests |

Prime testing checks divisors up to the square root using `i <= number / i` to avoid integer overflow. Modulus validates the divisor before `%`. Trigonometry uses `Math.toRadians`, `Math.sin`, `Math.cos`, `Math.tan`. Tangent checks cosine near zero and rejects undefined values. All JDBC connections, statements and results are closed using try-with-resources. Player text is HTML-escaped before display; database inputs use PreparedStatement.

## Application screenshots

**Capture required before submission:** run `tools/screenshots.cjs` using the steps in `VALIDATION.md`, or capture manually. Browser screenshots could not be taken in the build environment. The links below become visible after capture. Four actual server-generated HTML responses are included in `screen-previews/`.
~~~
### Welcome Page
<img width="1788" height="921" alt="Screenshot 2026-11-09 232334" src="https://github.com/user-attachments/assets/e157a41f-4074-4faf-8265-3d3aecbfbc6e" />
~~~
### Game Page
<img width="1863" height="912" alt="Screenshot 2026-11-09 232419" src="https://github.com/user-attachments/assets/77070eab-4849-48dc-af4d-d9f9b6aa118b" />
~~~
### Result Page
<img width="1317" height="912" alt="Screenshot 2026-11-09 232712" src="https://github.com/user-attachments/assets/5d377fd3-539b-4c4d-b0f1-4313864b8840" />
~~~
### Leaderboard
<img width="1806" height="842" alt="Screenshot 2026-11-09 232724" src="https://github.com/user-attachments/assets/95d13352-67b2-4ee6-b5fa-182554d70767" />
## Testing
Run `mvn test`. Six tests cover 7/8/1 prime cases, negative numbers, largest int prime, integer remainder including negative operands, zero divisor, sin30/cos60/tan45, undefined tangent, nonfinite inputs, tolerance, maximum/zero/partial scores, JDBC insertion, special-character names, timestamps and top-ten ordering. See `VALIDATION.md` for checks performed on the delivered files.

## GitHub submission
1. Replace the title with your own name.
2. Run the project and review all four screens.
3. Copy this entire folder into your repository as `MathWhiz`.
4. Commit and push the source, SQL, README and screenshots; exclude `target/` and `data/`.
5. Replace the placeholder below with your actual repository subdirectory link.

**Project directory link (replace before submission):**
`https://github.com/YOUR_USERNAME/YOUR_REPOSITORY/tree/main/MathWhiz`

The assignment's submission field expects a URL; its detailed instructions specify the GitHub subdirectory URL. GitHub Pages cannot run this Java/JDBC backend. If your instructor also requires a live game URL, deploy the executable JAR on Java-capable hosting with a persistent database and use its assigned HTTPS URL. Localhost works only on the computer running the app.

## References
- https://jetty.org/docs/jetty/12/programming-guide/server/http.html
- https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Math.html
- https://docs.oracle.com/javase/tutorial/jdbc/basics/prepared.html

