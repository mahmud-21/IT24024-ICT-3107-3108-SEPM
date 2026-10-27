# AI Training Guide for the Smart Irrigation Project

## 1. Define the prediction task
First-version task: binary classification.
- Inputs: calibrated soil moisture, temperature, humidity; optionally rainfall, crop type, soil type, and growth stage when reliable data exists.
- Target: `irrigation_needed` (0 or 1), with labels established by documented agronomic guidance or reliable field observations.

## 2. Data sources
A public dataset is useful only if it contains appropriate features and meaningful irrigation labels. Crop recommendation datasets do not automatically provide irrigation labels. You can collect your own measurements, but sensor readings alone do not tell you the correct label. Define a labeling protocol with an agricultural reference or expert guidance.

Useful starting points:
- UCI Machine Learning Repository: https://archive.ics.uci.edu/
- Kaggle Datasets: https://www.kaggle.com/datasets
- FAO AQUASTAT for background water data: https://www.fao.org/aquastat/en/

## 3. Data preparation
- Verify feature units and sensor calibration.
- Remove or investigate invalid/missing values.
- Check duplicates and class imbalance.
- Keep feature order and preprocessing identical during training and inference.
- For time-series readings, avoid random splitting that leaks near-duplicate readings across train and test. Prefer chronological splits or group splits by plot/device/season as appropriate.

## 4. Candidate models
- Decision Tree: interpretable first baseline.
- Random Forest: candidate to compare against the tree.
- Logistic Regression: simple additional baseline.

Compare all models on the same validation protocol. Do not choose a model based only on training accuracy.

## 5. Evaluation
Report:
- Accuracy
- Precision and recall for the irrigation-needed class
- F1-score
- Confusion matrix
- Results of a documented rule-based baseline
- Dataset source, labeling method, and limitations

Recall is important because missed irrigation need can be harmful, but false-positive recommendations also waste water. Discuss both.

## 6. Avoid false claims
A synthetic dataset created by a fixed moisture threshold can demonstrate the pipeline, but the model may simply learn that threshold. It does not prove improved agricultural decision-making. Do not claim water savings, yield improvements, or field reliability without suitable real-world evaluation.

## 7. Integration options
Option A: train with Python/scikit-learn and expose inference through a small HTTP API; Java Spring Boot sends the features and receives the prediction.
Option B: export a compatible model format and run inference from Java using a compatible runtime.
Store model version and prediction timestamp alongside each result.

## 8. Pump safety
Start in monitoring/recommendation mode. If automatic control is later added, use independent safety limits, maximum pump runtime, stale-data checks, sensor-failure handling, manual stop, and qualified electrical installation. Never rely only on an unvalidated AI prediction.
