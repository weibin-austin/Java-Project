import java.util.*;

public class FindMaxHealthSum {

    /**
     * Returns the maximum sum of health for up to k distinct server types.
     * https://www.fastprep.io/problems/amazon-get-maximum-sum
     * @param health     health of each server
     * @param serverType type of each server
     * @param k          maximum number of distinct types
     * @return maximum sum of health for up to k types
     */
    public long getMaximumSum(int[] health, int[] serverType, int k) {
        // Map to store total health per server type
        Map<Integer, Long> typeHealthSum = new HashMap<>();
        for (int i = 0; i < health.length; i++) {
            typeHealthSum.put(serverType[i], typeHealthSum.getOrDefault(serverType[i], 0L) + health[i]);
        }

        // Get all health sums and sort descending
        List<Long> sums = new ArrayList<>(typeHealthSum.values());
        sums.sort(Collections.reverseOrder());

        // Sum the top k type healths
        long result = 0;
        for (int i = 0; i < Math.min(k, sums.size()); i++) {
            result += sums.get(i);
        }
        return result;
    }

    // Test cases
    public static void main(String[] args) {
        FindMaxHealthSum solver = new FindMaxHealthSum();

        // Test 1
        int[] health1 = {4, 5, 5, 6};
        int[] serverType1 = {1, 2, 1, 2};
        int k1 = 1;
        System.out.println("Test 1 Output: " + solver.getMaximumSum(health1, serverType1, k1));
        // Expected: 11

        // Test 2
        int[] health2 = {1, 2, 3, 10, 10};
        int[] serverType2 = {3, 3, 1, 2, 5};
        int k2 = 2;
        System.out.println("Test 2 Output: " + solver.getMaximumSum(health2, serverType2, k2));
        // Expected: 20

        // Test 3: k greater than number of types
        int[] health3 = {1, 2, 3};
        int[] serverType3 = {1, 2, 3};
        int k3 = 5;
        System.out.println("Test 3 Output: " + solver.getMaximumSum(health3, serverType3, k3));
        // Expected: 6

        // Test 4: All servers same type
        int[] health4 = {7, 8, 9};
        int[] serverType4 = {1, 1, 1};
        int k4 = 2;
        System.out.println("Test 4 Output: " + solver.getMaximumSum(health4, serverType4, k4));
        // Expected: 24

        // Test 5: k = 0
        int[] health5 = {1, 2, 3};
        int[] serverType5 = {1, 2, 3};
        int k5 = 0;
        System.out.println("Test 5 Output: " + solver.getMaximumSum(health5, serverType5, k5));
        // Expected: 0
    }
}
