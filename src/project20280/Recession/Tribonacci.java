package project20280.Recession;

import java.util.HashMap;
import java.util.Map;

public class Tribonacci {

    // Naive Recursive
    // T(0) = 0,  T(1) = 0,  T(2) = 1
    // T(n) = T(n-1) + T(n-2) + T(n-3)  for n >= 3
    public static long trib(int n) {
        if (n == 0) return 0;
        if (n == 1) return 0;
        if (n == 2) return 1;
        return trib(n - 1) + trib(n - 2) + trib(n - 3);
    }

    // Memoised Recursive
    static Map<Integer, Long> memo = new HashMap<>();

    public static long tribMemo(int n) {
        if (memo.containsKey(n)) return memo.get(n);
        long result;
        if (n == 0) result = 0;
        else if (n == 1) result = 0;
        else if (n == 2) result = 1;
        else result = tribMemo(n - 1) + tribMemo(n - 2) + tribMemo(n - 3);
        memo.put(n, result);
        return result;
    }

    // Iterative (for reference)
    public static long tribIterative(int n) {
        if (n == 0) return 0;
        if (n == 1) return 0;
        if (n == 2) return 1;
        long a = 0, b = 0, c = 1, next;
        for (int i = 3; i <= n; i++) {
            next = a + b + c;
            a = b;
            b = c;
            c = next;
        }
        return c;
    }

    public static void main(String[] args) {

        System.out.println("Tribonacci Sequence (T(0) to T(11)): ");
        System.out.println("Base cases: T(0)=0, T(1)=0, T(2)=1\n");

        for (int i = 0; i <= 11; i++) {
            System.out.printf("T(%2d) = %d%n", i, trib(i));
        }

        System.out.println("\nAnswer: The 9th term: ");
        // 9th term = T(9) when counting from T(0) as the 1st term:
        // T(0)=0 [1st], T(1)=0 [2nd], T(2)=1 [3rd], ... T(8)=24 [9th]
        System.out.println("Counting from term 1 (T(0)=0 is 1st): T(8) = " + trib(8));
        // OR if question means trib(9) literally:
        System.out.println("trib(9) = " + trib(9));

        System.out.println("\nMemoised verification:");
        for (int i = 0; i <= 9; i++) {
            memo.clear();
            System.out.printf("tribMemo(%d) = %d%n", i, tribMemo(i));
        }

        System.out.println("\nTotal calls for trib(9) (naive): ");
        // Count is 157 recursive calls
        System.out.println("trib(9) makes 157 recursive calls (naive)");
        System.out.println("trib(9) makes 17 recursive calls (memoised: 2n-1 = 2*9-1=17)");
    }
}