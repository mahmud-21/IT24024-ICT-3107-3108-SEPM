# Validation of delivered source

Compiled all main and test Java source with Eclipse ECJ 3.40.0, target Java 17; executed on OpenJDK 21.0.13.

JUnit Platform 1.11.4: **6 tests successful; 0 failed**.

- Prime, remainder, trigonometry, tolerance and score tests passed.
- H2 JDBC insertion and top-ten retrieval passed.
- Jetty 12.0.16 started successfully.
- Full Servlet flow tested with Jetty LocalConnector: welcome → start → game → submit → result → leaderboard.
- All correct answers produced 10/10 and one database row.
- Invalid decimal integer answer / NaN submission returned HTTP 400.
- Result refresh and repeated completed submission retained one database row.
- All four real Servlet HTML responses exported to screen-previews/.

Limitations: Maven CLI was unavailable here, so compilation/testing used the same versioned libraries directly, rather than `mvn test`. MySQL configuration is supplied but live MySQL was not available to test. Browser launch failed due to restricted socket access, and the shared browser could not access the local server; visual browser/mobile verification and screenshot capture remain to be done locally. No fabricated browser screenshots are included.

To capture screenshots, start the Java app, then from this project directory:

```sh
npm install --no-save playwright
npx playwright install chromium
node tools/screenshots.cjs
```

This adds one demo score and saves the four README screenshots. Alternatively capture the four screens manually into screenshots/home.png, game.png, result.png and leaderboard.png.
