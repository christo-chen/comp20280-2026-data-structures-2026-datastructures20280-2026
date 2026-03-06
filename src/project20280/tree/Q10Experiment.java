package project20280.tree;

public class Q10Experiment {

    public static void main(String[] args) {

        int[] sizes = {10, 100, 500, 1000, 2000, 3000, 4000, 5000, 6000, 7000, 8000, 9000, 10000};

        System.out.println("n,time_ns");  // CSV — paste directly into Excel/Sheets

        for (int n : sizes) {
            LinkedBinaryTree<Integer> bt = LinkedBinaryTree.makeRandom(n);

            // Warm-up run (lets JVM JIT-compile the method before measuring)
            bt.inorder();

            // Average over 5 timed runs to reduce noise
            int runs = 5;
            long total = 0;
            for (int i = 0; i < runs; i++) {
                long start = System.nanoTime();
                bt.inorder();
                long end   = System.nanoTime();
                total += (end - start);
            }

            long avgNs = total / runs;
            System.out.println(n + "," + avgNs);
        }
    }
}