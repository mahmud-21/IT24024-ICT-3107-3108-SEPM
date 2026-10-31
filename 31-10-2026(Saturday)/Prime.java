import java.io.*;
import static java.lang.System.in;

class Prime {
    public void checkPrime(int... numbers) {
        for (int n : numbers) {
            if (n < 2) {
                continue;
            }

            boolean isPrime = true;

            for (int i = 2; i <= n / i; i++) {
                if (n % i == 0) {
                    isPrime = false;
                    break;
                }
            }

            if (isPrime) {
                System.out.print(n + " ");
            }
        }

        System.out.println();
    }
}
