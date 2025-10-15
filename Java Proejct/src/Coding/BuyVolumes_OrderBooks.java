import java.util.*;

public class BuyVolumes_OrderBooks {

    /**
     * 
     * https://www.fastprep.io/problems/amazon-buy-volumes
     * Returns the volumes purchased each day according to the rules.
     * @param volumes the array where volumes[i] is the volume in stock on day i (1-based volume numbers)
     * @return a 2D array where each row is the list of volumes purchased on that day (or [-1] if none)
     */
    public int[][] buyVolumes(int[] volumes) {
        int n = volumes.length;
        boolean[] owned = new boolean[n + 1]; // owned[volume] = true if owned， 1-based index
        // n = 4, volumes = {2, 1, 4, 3}
        // owned = {_, _, _, _, _}  index: 0, 1, 2, 3, 4
        int nextToBuy = 1; // The next volume number we want to buy
        List<List<Integer>> result = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            
            // Mark the volume available on day i as owned
            owned[volumes[i]] = true; // volumes[i] = {2, 1, 4, 3}
                                      // owned = {_, _, true,_ , _}  index: 0, 1, 2, 3, 4
            List<Integer> today = new ArrayList<>(); 
           
            // Buy as many consecutive volumes as possible starting from nextToBuy
            while (nextToBuy <= n && owned[nextToBuy]) { // owned[nextToBuy] is false when nextToBuy = 1
                today.add(nextToBuy);
                nextToBuy++;
            }
            
            if (today.isEmpty()) { // today is empty
                today.add(-1);
            }
            result.add(today);
        }

        // Convert List<List<Integer>> to int[][]
        int[][] ans = new int[n][];
        for (int i = 0; i < n; i++) {
            List<Integer> day = result.get(i);
            ans[i] = new int[day.size()];
            for (int j = 0; j < day.size(); j++) {
                ans[i][j] = day.get(j);
            }
        }
        return ans;

    }

    // Test cases
    public static void main(String[] args) {
        BuyVolumes_OrderBooks solver = new BuyVolumes_OrderBooks();

        int[] volumes1 = {2, 1, 4, 3};
        int[][] result1 = solver.buyVolumes(volumes1);
        System.out.println(Arrays.deepToString(result1));
        // Expected: [[-1], [1, 2], [-1], [3, 4]]

        int[] volumes2 = {1, 4, 3, 2, 5};
        int[][] result2 = solver.buyVolumes(volumes2);
        System.out.println(Arrays.deepToString(result2));
        // Expected: [[1], [2], [3], [4]]

        int[] volumes3 = {4, 3, 2, 1};
        int[][] result3 = solver.buyVolumes(volumes3);
        System.out.println(Arrays.deepToString(result3));
        // Expected: [[-1], [-1], [-1], [1, 2, 3, 4]]

        int[] volumes4 = {2, 4, 1, 3};
        int[][] result4 = solver.buyVolumes(volumes4);
        System.out.println(Arrays.deepToString(result4));
        // Expected: [[-1], [-1], [1, 2], [3, 4]]
    }
}
