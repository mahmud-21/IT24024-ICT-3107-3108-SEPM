# Lecture -02 (05-10-2026)--Monday
```java
class MyThread extends Thread {
    @Override
    public void run() {
        for (int i = 1; i <= 5; i++){
        System.out.println( Thread.currentThread().getName() + " : " + i );
            try { Thread.sleep(1000); // Pause for 1 second
                } catch (InterruptedException e)
            { System.out.println("Thread was interrupted!");
                return;
            }
        }
    }
}
public class ThreadTest {
    public static void main(String[] args) {
        MyThread t1 = new MyThread();
        MyThread t2 = new MyThread();
        // 1. setName()
         t1.setName("Thread-ONE");
         t2.setName("Thread-TWO");
         // 2. start()
        t1.start();
        t2.start(); //
        // 3. getName()
        System.out.println("Thread 1 Name: " + t1.getName());
        System.out.println("Thread 2 Name: " + t2.getName());
        // 4. isAlive()
        System.out.println("Is Thread 1 alive? " + t1.isAlive());
        // 5. join()
        try { t1.join();
            t2.join();
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted!");
            Thread.currentThread().interrupt(); }
        // 6. currentThread()
        System.out.println( "Current Thread: " + Thread.currentThread().getName() );
        // 7. isAlive() after completion
        System.out.println( "Is Thread 1 alive after completion? " + t1.isAlive() );
        System.out.println("Main thread finished!");
    }
}


```
