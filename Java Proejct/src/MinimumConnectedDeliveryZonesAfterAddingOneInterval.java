import java.util.*;

public class MinimumConnectedDeliveryZonesAfterAddingOneInterval {

    /**
     * Returns the minimum number of connected delivery zone groups after optimally
     * inserting one new delivery zone of length ≤ k.
     * https://www.fastprep.io/problems/1.amazon-min-disconnected-sets 
     *
     * @param a array of start points (1-based indexing)
     * @param b array of end points (1-based indexing)
     * @param k Maximum allowed length for the new delivery zone
     * @return Minimum number of connected delivery zone groups
     */
    public int minDisconnectedSets(int[] a, int[] b, int k) {
        int n = a.length;
        if (n == 0) return 0;

        // Step 1: Sort intervals by start
        Integer[] indices = new Integer[n];
        for (int i = 0; i < n; i++) indices[i] = i;
        Arrays.sort(indices, Comparator.comparingInt(i -> a[i]));

        // Step 2: Merge intervals
        List<int[]> merged = new ArrayList<>();
        for (int idx : indices) {
            int start = a[idx], end = b[idx];
            if (merged.isEmpty() || start > merged.get(merged.size() - 1)[1]) {
                merged.add(new int[]{start, end});
            } else {
                merged.get(merged.size() - 1)[1] = Math.max(merged.get(merged.size() - 1)[1], end);
            }
        }

        int m = merged.size();
        int mx = 0;
        int right = 0;
        for (int i = 0; i < m; ++i) {
            while (right < m && merged.get(right)[0] <= merged.get(i)[1] + k) {
                ++right;
            }
            mx = Math.max(mx, right - i - 1);
        }
        return m - mx;
    }

    // Test cases
    public static void main(String[] args) {
        MinimumConnectedDeliveryZonesAfterAddingOneInterval solver = new MinimumConnectedDeliveryZonesAfterAddingOneInterval();

        // Provided failing test case 0
        int[] a0 = {1, 2, 6, 7, 16};
        int[] b0 = {5, 4, 6, 14, 19};
        int k0 = 2;
        System.out.println("Case 0 Output: " + solver.minDisconnectedSets(a0, b0, k0));
        // Expected: 2

        // Provided failing test case 1
        int[] a1 = {1, 2, 5, 10};
        int[] b1 = {2, 4, 8, 11};
        int k1 = 2;
        System.out.println("Case 1 Output: " + solver.minDisconnectedSets(a1, b1, k1));
        // Expected: 2

        // Other tests
        int[] a2 = {1, 2, 3};
        int[] b2 = {2, 3, 4};
        int k2 = 1;
        System.out.println("Test 2 Output: " + solver.minDisconnectedSets(a2, b2, k2));
        // Expected: 1

        int[] a3 = {1, 2, 10, 12};
        int[] b3 = {2, 3, 11, 13};
        int k3 = 5;
        System.out.println("Test 3 Output: " + solver.minDisconnectedSets(a3, b3, k3));
        // Expected: 1

        int[] a4 = {1, 2, 10, 12};
        int[] b4 = {2, 3, 11, 13};
        int k4 = 2;
        System.out.println("Test 4 Output: " + solver.minDisconnectedSets(a4, b4, k4));
        // Expected: 2

        int[] a5 = {};
        int[] b5 = {};
        int k5 = 10;
        System.out.println("Test 5 Output: " + solver.minDisconnectedSets(a5, b5, k5));
        // Expected: 0 (no intervals to connect)

        // Overlap/connected test
        int[] a6 = {1, 2, 1};
        int[] b6 = {2, 3, 5};
        int k6 = 1;
        System.out.println("Test 6 Output: " + solver.minDisconnectedSets(a6, b6, k6));
        // Expected: 1 (all can be connected)
    }
}
