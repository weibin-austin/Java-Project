import java.util.*;

public class GetMinCost {

    private Map<String, Long> memo = new HashMap<>();

    /**
     * Returns the minimum cost to purchase all books using the described scheme.
     * https://www.fastprep.io/problems/amazon-get-min-cost-book
     *
     * @param cost     the cost of each book
     * @param pairCost the cost to purchase the leftmost and rightmost book together
     * @param k        the max number of times pairCost can be used
     * @return the minimum total cost
     */
    public long getMinCost(int[] cost, int pairCost, int k) {
        return dfs(cost, 0, cost.length - 1, k, pairCost);
    }

    private long dfs(int[] cost, int left, int right, int k, int pairCost) {
        if (left > right) return 0;
        String key = left + "," + right + "," + k;
        if (memo.containsKey(key)) return memo.get(key);

        // Option 1: buy left
        long res = cost[left] + dfs(cost, left + 1, right, k, pairCost);
        // Option 2: buy right
        res = Math.min(res, cost[right] + dfs(cost, left, right - 1, k, pairCost));
        // Option 3: use pairCost if possible
        if (left < right && k > 0) {
            res = Math.min(res, pairCost + dfs(cost, left + 1, right - 1, k - 1, pairCost));
        }
        memo.put(key, res);
        return res;
    }

    // Test cases
    public static void main(String[] args) {
        GetMinCost solver = new GetMinCost();

        // Example 1
        int[] cost1 = {1, 2, 3};
        System.out.println("Test 1 Output: " + solver.getMinCost(cost1, 2, 1));
        // Expected: 3

        // Example 2
        int[] cost2 = {1, 1, 1};
        System.out.println("Test 2 Output: " + solver.getMinCost(cost2, 2, 1));
        // Expected: 3

        // Example 3
        int[] cost3 = {9, 11, 13, 15, 17};
        System.out.println("Test 3 Output: " + solver.getMinCost(cost3, 6, 2));
        // Expected: 21

        // Additional test: pairCost never used
        int[] cost4 = {5, 5, 5, 5};
        System.out.println("Test 4 Output: " + solver.getMinCost(cost4, 20, 2));
        // Expected: 20

        // Additional test: pairCost always used
        int[] cost5 = {10, 10, 10, 10};
        System.out.println("Test 5 Output: " + solver.getMinCost(cost5, 5, 2));
        // Expected: 10

        // Additional test: odd number of books
        int[] cost6 = {2, 3, 4, 5, 6};
        System.out.println("Test 6 Output: " + solver.getMinCost(cost6, 7, 2));
        // Expected: 14
    }
}
