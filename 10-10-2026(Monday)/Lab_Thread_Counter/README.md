

Readme · MD
# SEPM-001: Static vs Non-Static Variables in Multithreading
``` 
**Name:** Md Mahmudur Rahman 
**Student ID:** IT24024 
**Course / Section:** Software Engineering and Project Managemnet
**Date:** 10-10-2026
 ```
## 1. Files
 
| File | Description |
|---|---|
| `Firstname_Thread.java` | Single-class Java program (both experiments) |
| `run_tests.sh` | Script that runs all test cases |
| `results.txt` | Raw, unedited program output |
| `README.md` | This report |
 
## 2. How to compile and run
 
```bash
javac Firstname_Thread.java
java Firstname_Thread <threads> <increments> <true|false>
# true  = thread-safe (AtomicLong)
# false = unsynchronized (plain static long)
```
 
## 3. Formulas
 
- Expected = N x M
- Absolute difference = |Static count - Non-static total|
- Percentage difference = (Absolute difference / Non-static total) x 100
## 4. Experiment A: Thread-safe results (AtomicLong)
 
| Test | Threads (N) | Increments (M) | Expected | Static count | Non-static total | Abs. diff | Diff (%) |
|---|---|---|---|---|---|---|---|
| TC1 | 1 | 1,000 | 1,000 |  |  |  |  |
| TC2 | 2 | 10,000 | 20,000 |  |  |  |  |
| TC3 | 5 | 10,000 | 50,000 |  |  |  |  |
| TC4 | 10 | 50,000 | 500,000 |  |  |  |  |
| TC5 | 20 | 50,000 | 1,000,000 |  |  |  |  |
| TC6 | 50 | 50,000 | 2,500,000 |  |  |  |  |
| TC7 | 100 | 50,000 | 5,000,000 |  |  |  |  |
 
## 5. Experiment B: Unsynchronized results (5 runs per thread count)
 
Fill every cell from `results.txt`. Do not edit the numbers.
 
| Test | Threads | Run | Expected | Static count (unsafe) | Non-static total | Abs. diff | Diff (%) |
|---|---|---|---|---|---|---|---|
| TC1 | 1 | 1 | 1,000 |  |  |  |  |
| TC1 | 1 | 2 | 1,000 |  |  |  |  |
| TC1 | 1 | 3 | 1,000 |  |  |  |  |
| TC1 | 1 | 4 | 1,000 |  |  |  |  |
| TC1 | 1 | 5 | 1,000 |  |  |  |  |
| TC2 | 2 | 1 | 20,000 |  |  |  |  |
| TC2 | 2 | 2 | 20,000 |  |  |  |  |
| TC2 | 2 | 3 | 20,000 |  |  |  |  |
| TC2 | 2 | 4 | 20,000 |  |  |  |  |
| TC2 | 2 | 5 | 20,000 |  |  |  |  |
| TC3 | 5 | 1 | 50,000 |  |  |  |  |
| TC3 | 5 | 2 | 50,000 |  |  |  |  |
| TC3 | 5 | 3 | 50,000 |  |  |  |  |
| TC3 | 5 | 4 | 50,000 |  |  |  |  |
| TC3 | 5 | 5 | 50,000 |  |  |  |  |
| TC4 | 10 | 1 | 500,000 |  |  |  |  |
| TC4 | 10 | 2 | 500,000 |  |  |  |  |
| TC4 | 10 | 3 | 500,000 |  |  |  |  |
| TC4 | 10 | 4 | 500,000 |  |  |  |  |
| TC4 | 10 | 5 | 500,000 |  |  |  |  |
| TC5 | 20 | 1 | 1,000,000 |  |  |  |  |
| TC5 | 20 | 2 | 1,000,000 |  |  |  |  |
| TC5 | 20 | 3 | 1,000,000 |  |  |  |  |
| TC5 | 20 | 4 | 1,000,000 |  |  |  |  |
| TC5 | 20 | 5 | 1,000,000 |  |  |  |  |
| TC6 | 50 | 1 | 2,500,000 |  |  |  |  |
| TC6 | 50 | 2 | 2,500,000 |  |  |  |  |
| TC6 | 50 | 3 | 2,500,000 |  |  |  |  |
| TC6 | 50 | 4 | 2,500,000 |  |  |  |  |
| TC6 | 50 | 5 | 2,500,000 |  |  |  |  |
| TC7 | 100 | 1 | 5,000,000 |  |  |  |  |
| TC7 | 100 | 2 | 5,000,000 |  |  |  |  |
| TC7 | 100 | 3 | 5,000,000 |  |  |  |  |
| TC7 | 100 | 4 | 5,000,000 |  |  |  |  |
| TC7 | 100 | 5 | 5,000,000 |  |  |  |  |
 
### Average percentage difference
 
| Threads | Run 1 | Run 2 | Run 3 | Run 4 | Run 5 | Average (%) |
|---|---|---|---|---|---|---|
| 1 |  |  |  |  |  |  |
| 2 |  |  |  |  |  |  |
| 5 |  |  |  |  |  |  |
| 10 |  |  |  |  |  |  |
| 20 |  |  |  |  |  |  |
| 50 |  |  |  |  |  |  |
| 100 |  |  |  |  |  |  |
 
### Observations
 
_Write 3 to 5 sentences about your actual results: which thread counts lost the most increments, how much the runs varied, and whether the trend was steady._
 
## 6. Analysis questions
 
**Q1. What is the difference between a static variable and a non-static variable in Java?**  
_Answer:_ 
 
**Q2. Why do all threads share the same static counter?**  
_Answer:_ 
 
**Q3. Why does each thread have its own non-static counter in this experiment?**  
_Answer:_ 
 
**Q4. Why is `join()` required before calculating the final counts?**  
_Answer:_ 
 
**Q5. Why can the unsynchronized static count be lower than the expected count?**  
_Answer:_ 
 
**Q6. Does increasing the number of threads always increase the percentage difference? Explain using your observations.**  
_Answer:_ 
 
**Q7. Why might two runs with the same number of threads produce different results?**  
_Answer:_ 
 
**Q8. What changes when `AtomicLong` is used instead of a regular `long`?**  
_Answer:_ 
 
**Q9. How would you modify the program so that all threads share one instance counter as well as the static counter?**  
_Answer:_ 
 
## 7. Conclusion
 
_Static vs non-static describes variable ownership, not thread safety. Summarize what your results showed: the thread-safe run matched exactly (0%), while the unsynchronized run lost updates and varied between runs._
 
## 8. Screenshots
 
_Add screenshots of the compile step and sample outputs here._
 


