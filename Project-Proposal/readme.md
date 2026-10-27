# AI-Enabled IoT Smart Irrigation — Academic Starter Package

## Package contents
- `latex/main.tex`: initial LaTeX project proposal with requirements, iterative process model, 8-week schedule, Gantt chart, and six diagrams.
- `java/src/main/java/org/krishibazar/irrigation/`: runnable Java Swing interface prototype using multiple classes from the class diagram.
- `docs/AI_TRAINING_GUIDE.md`: practical plan for training and evaluating the AI model.

## Important honesty note
The supplied Java UI is a working interface prototype. Its `AIRecommendationService` currently uses a clearly labeled demonstration rule and is **not a trained AI model**. This lets you demonstrate the interface before model training. For the final AI claim, train and evaluate a model using appropriate labeled data and replace the placeholder with real model inference. Do not use the demo rule to control a real pump.

## Run the Java interface
Requirements: JDK 17 or later.

From the `java` folder, compile and run:

```bash
mkdir -p out
javac -d out src/main/java/org/krishibazar/irrigation/*.java
java -cp out org.krishibazar.irrigation.IrrigationApp
```

On Windows PowerShell, use:
```powershell
mkdir out
javac -d out src/main/java/org/krishibazar/irrigation/*.java
java -cp out org.krishibazar.irrigation.IrrigationApp
```
If `out` already exists, omit the `mkdir` command.

## Compile the LaTeX report
Upload `latex/main.tex` to Overleaf or compile it locally with a LaTeX installation that includes TikZ and pgfgantt. Replace student details and add actual screenshots, dataset details, and measured test results before submitting.

## Suggested next implementation steps
1. Build and test ESP32 sensor reading independently.
2. Define a dataset with documented feature units and reliable labels.
3. Train a Decision Tree and compare it with a Random Forest.
4. Evaluate using a held-out, time-aware test set and report precision, recall, F1, confusion matrix, and a rule-based baseline.
5. Add Spring Boot + MySQL + MQTT integration.
6. Replace the demonstration service with a real model API or Java inference library.
7. Keep the pump disabled until the entire setup is validated and independent safety limits are implemented.
