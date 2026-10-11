

Readme · MD
# SEPM-001: Static vs Non-Static Variables in Multithreading
``` 
**Name: Md Mahmudur Rahman 
**Student ID: IT24024 
**Course : Software Engineering and Project Managemnet
**Date: 10-10-2026
 ```
## 1. Files
 
| File | Description |
|---|---|
| `Firstname_Thread.java` | Single-class Java program (both experiments) |
| `run_tests.bat` | Script that runs all test cases |
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
| TC1 | 1 | 1,000 | 1,000 |1000  |1000  | 1000 |0  |0.0000%
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
 
## 6. Analysis Questions

**Q1. What is the difference between a static variable and a non-static variable in Java?**

A static variable belongs to the class itself. There is only one copy, shared by every object of that class. A non-static (instance) variable belongs to a single object, so each object has its own separate copy. Static versus non-static describes who owns the variable. It says nothing about whether the variable is thread-safe.

| | Static variable | Non-static variable |
|---|---|---|
| Belongs to | The class | One object |
| Number of copies | One, shared by all objects | One per object |
| Accessed with | `ClassName.variable` | `object.variable` |

---

**Q2. Why do all threads share the same static counter?**

The static counter is stored once, at the class level, not inside any object. Every thread runs the same class code, so every thread reads and writes that same single memory location. Creating more threads or more objects does not create more copies of it.

---

**Q3. Why does each thread have its own non-static counter in this experiment?**

The program creates one separate object for each thread (`new Firstname_Thread(...)`). Each object has its own `instanceCounter` field, and each thread only increments the counter of its own object. No two threads touch the same instance counter, so there is no race condition on it. That is why the non-static total is always exactly N x M.

---

**Q4. Why is `join()` required before calculating the final counts?**

`start()` only launches a thread, and the main thread keeps running without waiting for it. If `main` read the counters right away, the worker threads might still be incrementing, and the totals would be incomplete. `join()` makes the main thread wait until each worker has finished, so the final counts are read only after all increments are done.

---

**Q5. Why can the unsynchronized static count be lower than the expected count?**

The statement `count++` looks like one step, but it is really three: read the value, add 1, and write the result back. If two threads read the same value at the same time, both add 1 and both write back the same number, so one increment is lost. Many lost updates add up, so the final static count ends up lower than N x M. The instance counters do not have this problem because each one is used by only one thread.

```
Thread A reads  count = 100
Thread B reads  count = 100
Thread A writes count = 101
Thread B writes count = 101   <- one increment lost (should be 102)
```

---

**Q6. Does increasing the number of threads always increase the percentage difference? Explain using your observations.**

No, not always. With 1 thread there is no concurrency, so the difference is 0%. With more threads there is usually more contention and more lost updates, so the difference often grows. However, the amount of loss depends on thread scheduling, the number of CPU cores, and JIT optimization, so the trend is not strictly increasing.

In my runs, the average difference was ____% for 2 threads, ____% for 10 threads, and ____% for 100 threads.

*(Replace the blanks with your own averages from `results.txt`. Mention one case where a higher thread count had a lower difference than a smaller one, if you saw it, to show the trend is not steady.)*

---

**Q7. Why might two runs with the same number of threads produce different results?**

The operating system decides when each thread runs and when it is paused, and this is not the same every time. The exact order in which threads read and write the shared counter changes from run to run, so a different number of increments gets lost each time. Other programs running on the computer and CPU load also change the timing.

---

**Q8. What changes when `AtomicLong` is used instead of a regular `long`?**

`AtomicLong.incrementAndGet()` performs the read, add, and write as one indivisible (atomic) operation, using a hardware compare-and-swap instruction. No other thread can slip in between the steps, so no increments are lost and the static count always equals N x M. The cost is slightly slower execution, but the result is correct.

| | Regular `long` | `AtomicLong` |
|---|---|---|
| Increment | `count++` (3 steps) | `incrementAndGet()` (1 atomic step) |
| Thread-safe | No | Yes |
| Lost updates | Possible | None |

---

**Q9. How would you modify the program so that all threads share one instance counter as well as the static counter?**

Create only one object and pass it to all the threads, instead of creating one object per thread. Because all threads then use the same object, they share its instance counter. That counter must be made thread-safe too, for example with `AtomicLong`:

```java
// in the class
private final AtomicLong instanceCounter = new AtomicLong(0);

// in run()
instanceCounter.incrementAndGet();

// in main()
Firstname_Thread shared = new Firstname_Thread(increments, threadSafe);
Thread[] workers = new Thread[threads];
for (int i = 0; i < threads; i++) {
    workers[i] = new Thread(shared);   // same object for every thread
}
```

Now both the static counter and the instance counter are shared, so both need atomic or synchronized updates. The other option is to keep a plain `long` and mark the increment code `synchronized`.
## 7. Conclusion
 
_Static vs non-static describes variable ownership, not thread safety. Summarize what your results showed: the thread-safe run matched exactly (0%), while the unsynchronized run lost updates and varied between runs._
 
## 8. Screenshots
 
_Add screenshots of the compile step and sample outputs here._
 


