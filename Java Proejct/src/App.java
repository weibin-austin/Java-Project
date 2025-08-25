import java.util.Arrays;

public class App {
    /**
     * Returns the minimum number of operations required to ensure that
     * the difference between the maximum and minimum prices is strictly less than d.
     *
     * @param prices array of product prices
     * @param k      maximum adjustment per operation
     * @param d      target difference
     * @return minimum number of operations
     */
    public static int minOperations(int[] prices, int k, int d) {
        int[] arr = Arrays.copyOf(prices, prices.length); // Copy to avoid mutating input
        int ops = 0;
        while (true) {
            int minIdx = 0, maxIdx = 0;
            for (int i = 1; i < arr.length; i++) {
                if (arr[i] < arr[minIdx]) minIdx = i;
                if (arr[i] > arr[maxIdx]) maxIdx = i;
            }
            int diff = arr[maxIdx] - arr[minIdx];
            if (diff < d) break;
            int move = Math.min(k, diff - d + 1); // Move enough to get closer to target
            arr[maxIdx] -= move;
            arr[minIdx] += move;
            ops++;
        }
        return ops;
    }

    // Test cases for minOperations
    public static void main(String[] args) {
        // Test 1: Example from prompt
        int[] prices1 = {1, 4, 6};
        int k1 = 1, d1 = 2;
        System.out.println("Test 1 Output: " + minOperations(prices1, k1, d1)); // Expected: 2

        // Test 2: Already within target difference
        int[] prices2 = {5, 6, 7};
        int k2 = 2, d2 = 3;
        System.out.println("Test 2 Output: " + minOperations(prices2, k2, d2)); // Expected: 0

        // Test 3: Large difference, larger k
        int[] prices3 = {1, 10, 20};
        int k3 = 5, d3 = 4;
        System.out.println("Test 3 Output: " + minOperations(prices3, k3, d3)); // Expected: 3

        // Test 4: All prices the same
        int[] prices4 = {7, 7, 7};
        int k4 = 1, d4 = 1;
        System.out.println("Test 4 Output: " + minOperations(prices4, k4, d4)); // Expected: 0

        // Test 5: Only two products
        int[] prices5 = {1, 100};
        int k5 = 10, d5 = 5;
        System.out.println("Test 5 Output: " + minOperations(prices5, k5, d5)); // Expected: 5
    }
}
