import java.util.concurrent.atomic.AtomicLong;

public class Mahmud_Thread implements Runnable {

    // ---- Static (shared by ALL objects of this class) ----
    static final AtomicLong safeStaticCounter = new AtomicLong(0); // Experiment A
    static long unsafeStaticCounter = 0;                           // Experiment B (race condition)

    // ---- Non-static (each object/thread has its own copy) ----
    private long instanceCounter = 0;

    private final long increments;
    private final boolean threadSafe;

    public Mahmud_Thread(long increments, boolean threadSafe) {
        this.increments = increments;
        this.threadSafe = threadSafe;
    }

    @Override
    public void run() {
        for (long i = 0; i < increments; i++) {
            if (threadSafe) {
                safeStaticCounter.incrementAndGet(); // atomic increment
            } else {
                unsafeStaticCounter++;               // NOT atomic: read-modify-write
            }
            instanceCounter++;                       // only this thread touches it
        }
    }
    public static void main(String[] args) throws InterruptedException {
        if (args.length != 3) {
            System.out.println("Usage: java Firstname_Thread <threads> <increments> <true|false>");
            return;
        }

        int threads;
        long increments;
        boolean threadSafe;
        try {
            threads = Integer.parseInt(args[0]);
            increments = Long.parseLong(args[1]);
            threadSafe = Boolean.parseBoolean(args[2]);
        } catch (NumberFormatException e) {
            System.out.println("Error: threads and increments must be integers.");
            return;
        }
        if (threads < 1 || increments < 0) {
            System.out.println("Error: threads must be >= 1 and increments >= 0.");
            return;
        }

        // Reset static counters
        safeStaticCounter.set(0);
        unsafeStaticCounter = 0;

        Mahmud_Thread[] tasks = new Mahmud_Thread[threads];
        Thread[] workers = new Thread[threads];

        for (int i = 0; i < threads; i++) {
            tasks[i] = new Mahmud_Thread(increments, threadSafe); // own instance counter
            workers[i] = new Thread(tasks[i], "Worker-" + (i + 1));
        }

        for (Thread t : workers) t.start();
        for (Thread t : workers) t.join();   // wait for all threads before reading results

        // Final counts
        long expected = (long) threads * increments;
        long staticCount = threadSafe ? safeStaticCounter.get() : unsafeStaticCounter;
        long nonStaticTotal = 0;
        for (Mahmud_Thread t : tasks) nonStaticTotal += t.instanceCounter;

        long absDiff = Math.abs(staticCount - nonStaticTotal);

        String percentDiff;
        if (nonStaticTotal == 0) {
            percentDiff = (staticCount == 0) ? "0.0000%" : "undefined";
        } else {
            percentDiff = String.format("%.4f%%", (absDiff * 100.0) / nonStaticTotal);
        }

        System.out.println("Mode                 : " + (threadSafe ? "Thread-safe (AtomicLong)" : "Unsynchronized (long)"));
        System.out.println("Threads (N)          : " + threads);
        System.out.println("Increments/thread (M): " + increments);
        System.out.println("Expected count       : " + expected);
        System.out.println("Static count         : " + staticCount);
        System.out.println("Non-static total     : " + nonStaticTotal);
        System.out.println("Absolute difference  : " + absDiff);
        System.out.println("Percentage difference: " + percentDiff);
        System.out.println("-------------------------------------------");
    }
}