# Code for problem 01 
```java
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
```
# Code for problem 02
```java
import java.math.BigInteger;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        BigInteger number = scanner.nextBigInteger();

        if (number.compareTo(BigInteger.ONE) > 0
                && number.isProbablePrime(100)) {
            System.out.println("prime");
        } else {
            System.out.println("not prime");
        }

        scanner.close();
    }
}
```
<img width="1037" height="666" alt="Screenshot 2026-10-31 125540" src="https://github.com/user-attachments/assets/398790dc-35e4-466c-a4fd-7048d444bfd2" />
<img width="1852" height="845" alt="Screenshot 2026-10-31 125920" src="https://github.com/user-attachments/assets/5e5dc463-c99e-4a7e-8259-0c52e5b57c11" />
<img width="1247" height="566" alt="Screenshot 2026-10-31 125949" src="https://github.com/user-attachments/assets/06a663af-8932-45b3-920b-009864602480" />
<img width="1250" height="642" alt="Screenshot 2026-10-31 130031" src="https://github.com/user-attachments/assets/482256f3-eaa7-4666-a3f0-81bdccc508b9" />


