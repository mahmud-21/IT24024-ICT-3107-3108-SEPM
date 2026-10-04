# ICT-3107-3108-SEPM-
# lecture -01 (03-10-2026,Saturday)
## The code of `Non-Staic`
```java
public class Student {
     int count=0;
    Student(){
        count++;
    }
}
public class Main{
   public static void main(String[] args) {
        Student s1=new Student();
       Student s2=new Student();
       Student s3=new Student();
       System.out.println(s2.count);

    }
}
```
## The code of `Static`
```java

public class Student {
     static int count=0;
    Student(){
        count++;
    }
}
public class Main{
   public static void main(String[] args) {
        Student s1=new Student();
       Student s2=new Student();
       Student s3=new Student();
       System.out.println(s2.count);

    }
}
```
## The Code of Thread with Static and Non-Static
```java public class ThreadMain {
    public static void main(String[] args) {

        CookingTask task1 = new CookingTask("Cooking");
        CookingTask task2 = new CookingTask("Washing");
        CookingTask task3 = new CookingTask("Cleaning");

        task1.start();
        task2.start();
        task3.start();

        System.out.println("All tasks started...");
    }
}
public class CookingTask extends Thread {
        private String taskName;

        // ONE copy shared by ALL CookingTask objects
        static int staticCount = 0;

        // ONE copy for EACH CookingTask object
        int nonStaticCount = 0;


        public CookingTask(String taskName) {
            this.taskName = taskName;
        }


        @Override
        public void run() {

            long startTime = System.currentTimeMillis();

            for (;;) {

                // Increase both counters
                staticCount++;
                nonStaticCount++;

                System.out.println(
                        Thread.currentThread().getName()
                                + " | " + taskName
                                + " | Static Count = " + staticCount
                                + " | Non-Static Count = " + nonStaticCount
                );

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    break;
                }

                // Stop after approximately 10 seconds
                if (System.currentTimeMillis() - startTime >= 10_000) {
                    break;
                }
            }

            System.out.println(
                    taskName + " finished. "
                            + "Final Non-Static Count = " + nonStaticCount
            );
        }
}
```
### Output of the code
```java
All tasks started...
Thread-1 | Washing | Static Count = 2 | Non-Static Count = 1
Thread-2 | Cleaning | Static Count = 3 | Non-Static Count = 1
Thread-0 | Cooking | Static Count = 1 | Non-Static Count = 1
Thread-2 | Cleaning | Static Count = 4 | Non-Static Count = 2
Thread-1 | Washing | Static Count = 4 | Non-Static Count = 2
Thread-0 | Cooking | Static Count = 5 | Non-Static Count = 2
Thread-1 | Washing | Static Count = 6 | Non-Static Count = 3
Thread-2 | Cleaning | Static Count = 7 | Non-Static Count = 3
Thread-0 | Cooking | Static Count = 8 | Non-Static Count = 3
Thread-1 | Washing | Static Count = 9 | Non-Static Count = 4
Thread-2 | Cleaning | Static Count = 9 | Non-Static Count = 4
Thread-0 | Cooking | Static Count = 10 | Non-Static Count = 4
Thread-2 | Cleaning | Static Count = 11 | Non-Static Count = 5
Thread-1 | Washing | Static Count = 11 | Non-Static Count = 5
Thread-0 | Cooking | Static Count = 12 | Non-Static Count = 5
Thread-1 | Washing | Static Count = 13 | Non-Static Count = 6
Thread-2 | Cleaning | Static Count = 14 | Non-Static Count = 6
Thread-0 | Cooking | Static Count = 15 | Non-Static Count = 6
Thread-1 | Washing | Static Count = 16 | Non-Static Count = 7
Thread-2 | Cleaning | Static Count = 17 | Non-Static Count = 7
Thread-0 | Cooking | Static Count = 18 | Non-Static Count = 7
Thread-1 | Washing | Static Count = 19 | Non-Static Count = 8
Thread-2 | Cleaning | Static Count = 20 | Non-Static Count = 8
Thread-0 | Cooking | Static Count = 21 | Non-Static Count = 8
Thread-1 | Washing | Static Count = 22 | Non-Static Count = 9
Thread-2 | Cleaning | Static Count = 23 | Non-Static Count = 9
Thread-0 | Cooking | Static Count = 24 | Non-Static Count = 9
Thread-1 | Washing | Static Count = 25 | Non-Static Count = 10
Thread-2 | Cleaning | Static Count = 26 | Non-Static Count = 10
Thread-0 | Cooking | Static Count = 27 | Non-Static Count = 10
Washing finished. Final Non-Static Count = 10
Cooking finished. Final Non-Static Count = 10
Cleaning finished. Final Non-Static Count = 10

Process finished with exit code 0
```
## The code of thread without Static and Non-Static
```java
public class ThreadMain {
    public static void main(String[] args) {

        CookingTask task1 = new CookingTask("Cooking");
        CookingTask task2 = new CookingTask("Washing");
        CookingTask task3 = new CookingTask("Cleaning");

        task1.start();
        task2.start();
        task3.start();

        System.out.println("All tasks started...");
    }
}
public class CookingTask extends Thread {
    private String taskName;

    public CookingTask(String taskName) {
        this.taskName = taskName;
    }

    @Override
    public void run() {

        long startTime = System.currentTimeMillis();

        while (true) {

            System.out.println(
                    Thread.currentThread().getName()
                            + " - Running: " + taskName
            );

            // Wait 1 second before next iteration
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println(taskName + " interrupted.");
                break;
            }

            // Stop after 10 seconds
            if (System.currentTimeMillis() - startTime >= 10_000) {
                break;
            }
        }

        System.out.println(taskName + " finished.");
    }
}
````
### The Output of the code 
```java
All tasks started...
Thread-1 - Running: Washing
Thread-0 - Running: Cooking
Thread-2 - Running: Cleaning
Thread-0 - Running: Cooking
Thread-2 - Running: Cleaning
Thread-1 - Running: Washing
Thread-0 - Running: Cooking
Thread-2 - Running: Cleaning
Thread-1 - Running: Washing
Thread-0 - Running: Cooking
Thread-2 - Running: Cleaning
Thread-1 - Running: Washing
Thread-1 - Running: Washing
Thread-0 - Running: Cooking
Thread-2 - Running: Cleaning
Thread-2 - Running: Cleaning
Thread-1 - Running: Washing
Thread-0 - Running: Cooking
Thread-1 - Running: Washing
Thread-2 - Running: Cleaning
Thread-0 - Running: Cooking
Thread-0 - Running: Cooking
Thread-1 - Running: Washing
Thread-2 - Running: Cleaning
Thread-1 - Running: Washing
Thread-0 - Running: Cooking
Thread-2 - Running: Cleaning
Thread-1 - Running: Washing
Thread-2 - Running: Cleaning
Thread-0 - Running: Cooking
Washing finished.
Cooking finished.
Cleaning finished.

Process finished with exit code 0
```
