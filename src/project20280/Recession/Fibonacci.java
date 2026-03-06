package project20280.Recession;

import java.util.HashMap;
import java.util.Map;

public class Fibonacci {

    // Binary Recursive (naive)
    static long recursiveCalls = 0;

    public static long fib(int n) {
        recursiveCalls++;
        if (n <= 1) return n;
        return fib(n - 1) + fib(n - 2);
    }

    // Memoised Recursive
    static Map<Integer, Long> memo = new HashMap<>();
    static long memoCalls = 0;

    public static long fibMemo(int n) {
        memoCalls++;
        if (memo.containsKey(n)) return memo.get(n);
        if (n <= 1) {
            memo.put(n, (long) n);
            return n;
        }
        long result = fibMemo(n - 1) + fibMemo(n - 2);
        memo.put(n, result);
        return result;
    }

    public static void main(String[] args) {

        // Binary recursive — find largest n under 1 minute
        System.out.println("Binary Recursive Fibonacci: ");
        int[] testNs = {30, 35, 40, 42, 43, 44, 45};
        for (int n : testNs) {
            recursiveCalls = 0;
            long start = System.currentTimeMillis();
            long result = fib(n);
            long elapsed = System.currentTimeMillis() - start;
            System.out.printf("fib(%2d) = %12d  |  time = %6d ms  |  calls = %,d%n",
                    n, result, elapsed, recursiveCalls);
            if (elapsed > 60_000) {
                System.out.println("Exceeded 1 minute at n=" + n);
                break;
            }
        }

        // Memoised — find largest n under 1 minute
        System.out.println("\nMemoised Fibonacci: ");
        // With long: overflows after fib(92) = 7540113804746346429
        // fib(93) overflows long → use BigInteger or cap at 92
        for (int n = 70; n <= 93; n++) {
            memo.clear();
            memoCalls = 0;
            long start = System.currentTimeMillis();
            long result = fibMemo(n);
            long elapsed = System.currentTimeMillis() - start;
            System.out.printf("fibMemo(%2d) = %20d  |  time = %3d ms  |  calls = %d%n",
                    n, result, elapsed, memoCalls);
        }
        System.out.println("Note: fib(93) overflows Java long. " +
                "With BigInteger, memoised version runs essentially instantly for any n.");
    }
}