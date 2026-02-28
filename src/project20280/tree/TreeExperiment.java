package project20280.tree;

import project20280.tree.LinkedBinaryTree;
import java.util.ArrayList;
import java.util.List;

public class TreeExperiment {

    public static void main(String[] args) {
        System.out.println("Starting Q6 random tree experiment...");
        System.out.println("N, Average_Height, Average_Diameter"); // CSV header for easy copying to Excel

        // Loop: size n from 50 to 5000, in steps of 50
        for (int n = 50; n <= 5000; n += 50) {
            long totalHeight = 0;
            long totalDiameter = 0;
            int iterations = 100;

            // For each size n, generate 100 random trees
            for (int i = 0; i < iterations; i++) {
                LinkedBinaryTree<Integer> bt = LinkedBinaryTree.makeRandom(n);
                totalHeight += bt.height();
                totalDiameter += bt.diameter();
            }

            // Calculate the averages
            double avgHeight = (double) totalHeight / iterations;
            double avgDiameter = (double) totalDiameter / iterations;

            // Print the results (comma-separated for easy pasting into Excel)
            System.out.printf("%d, %.2f, %.2f%n", n, avgHeight, avgDiameter);
        }
        System.out.println("Experiment completed. Please copy the data above to Excel/Google Sheets for plotting.");
    }
}