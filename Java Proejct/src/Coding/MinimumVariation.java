import java.util.Arrays;

public class MinimumVariation {

    /**
     * Calculates the minimum possible total variation after reordering products.
     * 
     * https://www.fastprep.io/problems/amazon-minimize-variation
     *
     * @param productSize An array of integers representing the sizes of the products.
     * @return The minimum total variation.
     */
    public static int minimizeVariation(int[] productSize) {
        int n = productSize.length;
        if (n <= 1)
            return 0;
        Arrays.sort(productSize);
        long[][] dp = new long[n][n];

        for (int i = 0; i < n; i++) {
            dp[i][i] = 0;
        }

        for (int m = 2; m <= n; m++) {
            for (int i = 0; i <= n - m; i++) {
                int j = i + m - 1;
                long currRangeCost = (long) productSize[j] - productSize[i];
                long costFromLeft = dp[i + 1][j] + currRangeCost;
                long costFromRight = dp[i][j - 1] + currRangeCost;

                dp[i][j] = Math.min(costFromLeft, costFromRight);
            }
        }
        return (int) dp[0][n - 1];
    }

    // --- Test Cases ---
    public static void main(String[] args) {
        // Test Case 1 from problem description
        int[] productSize1 = {3, 1, 2};
        long result1 = minimizeVariation(productSize1);
        System.out.println("Input: " + Arrays.toString(productSize1));
        System.out.println("Minimum Variation: " + result1); // Expected: 3
        System.out.println("--------------------");

        // Test Case 2 from problem description
        int[] productSize2 = {6, 1, 4, 2};
        long result2 = minimizeVariation(productSize2);
        System.out.println("Input: " + Arrays.toString(productSize2));
        System.out.println("Minimum Variation: " + result2); // Expected: 9
        System.out.println("--------------------");
        
        // Test Case 3 from problem description
        int[] productSize3 = {4, 5, 4, 2, 6, 1, 1};
        long result3 = minimizeVariation(productSize3);
        System.out.println("Input: " + Arrays.toString(productSize3));
        System.out.println("Minimum Variation: " + result3); // Expected: 16
        System.out.println("--------------------");

        // Additional Test Case: single element array
        int[] productSize4 = {10};
        long result4 = minimizeVariation(productSize4);
        System.out.println("Input: " + Arrays.toString(productSize4));
        System.out.println("Minimum Variation: " + result4); // Expected: 0
        System.out.println("--------------------");

        // Additional Test Case: array with duplicates and negatives
        // Assuming product sizes are non-negative based on problem context,
        // but the logic holds for negatives as well.
        int[] productSize5 = {10, 5, 8, 5, 2};
        long result5 = minimizeVariation(productSize5);
        System.out.println("Input: " + Arrays.toString(productSize5));
        System.out.println("Minimum Variation: " + result5); // Expected: (5-2) + (5-2) + (8-2) + (10-2) = 3+3+6+8 = 20
        System.out.println("--------------------");
    }
}