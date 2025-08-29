import java.util.*;

public class GetMaximumSum {

    /**
     * Returns the maximum sum of exactly x elements from the matrix data,
     * where at most factor[i] elements can be chosen from row i.
     * Returns -1 if not possible.
     * https://www.fastprep.io/problems/amazon-get-max-sum
     *
     * @param data   n x n matrix of integers
     * @param factor array of length n, factor[i] is max elements from row i
     * @param x      total number of elements to choose
     * @return maximum sum or -1 if not possible
     */
    public long getMaximumSum(int[][] data, int[] factor, int x) {
        if(Arrays.stream(factor).sum() < x) return -1;
        int n = data.length;
        List<Integer> candidates = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int[] row = Arrays.copyOf(data[i], n);
            Arrays.sort(row); // ascending
            // Take the largest factor[i] elements from this row
            for (int j = n - 1; j >= n - factor[i] && j >= 0; j--) {
                candidates.add(row[j]);
            }
        }
        // if (candidates.size() < x) return -1;
        // Pick the largest x elements from all candidates
        candidates.sort(Collections.reverseOrder());
        long sum = 0;
        for (int i = 0; i < x; i++) {
            sum += candidates.get(i);
        }
        return sum;
    }

    // Test cases
    public static void main(String[] args) {
        GetMaximumSum gms = new GetMaximumSum();

        // Test 1: Basic
        int[][] data1 = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };
        int[] factor1 = {1, 2, 1};
        int x1 = 2;
        System.out.println("Test 1 Output: " + gms.getMaximumSum(data1, factor1, x1));
        // Expected: 9 + 6 = 15

        // Test 2: Not enough elements
        int[][] data2 = {
            {1, 2},
            {3, 4}
        };
        int[] factor2 = {1, 1};
        int x2 = 3;
        System.out.println("Test 2 Output: " + gms.getMaximumSum(data2, factor2, x2));
        // Expected: -1

        // Test 3: All elements allowed
        int[][] data3 = {
            {10, 20},
            {30, 40}
        };
        int[] factor3 = {2, 2};
        int x3 = 4;
        System.out.println("Test 3 Output: " + gms.getMaximumSum(data3, factor3, x3));
        // Expected: 40 + 30 + 20 + 10 = 100

        // Test 4: Only one row allowed
        int[][] data4 = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };
        int[] factor4 = {1, 2, 3};
        int x4 = 2;
        System.out.println("Test 4 Output: " + gms.getMaximumSum(data4, factor4, x4));
        // Expected: 15

        // Test 5: Edge case, x = 0
        int[][] data5 = {
            {1, 2},
            {3, 4}
        };
        int[] factor5 = {1, 1};
        int x5 = 0;
        System.out.println("Test 5 Output: " + gms.getMaximumSum(data5, factor5, x5));
        // Expected: 0
    }
}
