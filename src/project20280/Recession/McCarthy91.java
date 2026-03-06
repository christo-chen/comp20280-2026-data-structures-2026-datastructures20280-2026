package project20280.Recession;

// Recursive type is nested recursion
// Because recursion occurs at the parameter position: M(M(n + 11)),
// the return value of the inner call serves as the parameter for the outer call.

public class McCarthy91 {

    // McCarthy-91 function
    // M(n) = n - 10        if n > 100
    // M(n) = M(M(n + 11))  if n <= 100
    public static int M(int n) {
        if (n > 100) {
            return n - 10;
        } else {
            return M(M(n + 11));
        }
    }

    public static void main(String[] args) {
        // Test M(87)
        System.out.println("M(87) = " + M(87));   // 91

        // Show that all n <= 100 return 91
        System.out.println("\nAll M(n) for n = 85 to 105:");
        for (int n = 85; n <= 105; n++) {
            System.out.printf("M(%3d) = %d%n", n, M(n));
        }
    }
}