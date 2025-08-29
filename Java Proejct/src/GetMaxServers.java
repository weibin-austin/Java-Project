import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class GetMaxServers {
    /**
     * 
     * https://www.fastprep.io/problems/amazon-find-maximum-number-of-servers
     * 
     * Finds the maximum number of servers that can be bought to form a circular network
     * with an adjacent power difference of at most 1.
     *
     * @param powers An array of integers representing the computational power of each server.
     * @return The maximum number of servers that can be bought.
     */
    public int solve(int[] powers) {
        if (powers == null || powers.length == 0) {
            return 0;
        }
        // Step 1: Count the frequency of each power value.
        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int power : powers) {
            freqMap.merge(power, 1, Integer::sum);
        }

        // Step 2: Get a sorted list of unique power values.
        List<Integer> uniquePowers = new ArrayList<>(freqMap.keySet());
        Collections.sort(uniquePowers);

        // uniquePowers = [1, 2, 3, 4, 5]

        if (uniquePowers.isEmpty()) {
            return 0;
        }

        int maxServers = 0;

        // Step 3: Iterate through the sorted unique powers to find the maximum servers.
        for (int i = 0; i < uniquePowers.size(); i++) {
            int currentPower = uniquePowers.get(i);
            int currentCount = freqMap.get(currentPower);
            int currentMaxServers = currentCount;
            int nextPower = currentPower + 1;
            // Try to extend the group by including servers with power +1, +2, ...
            while (freqMap.containsKey(nextPower)) {
                int nextPowerCount = freqMap.get(nextPower);
                currentMaxServers += nextPowerCount;
                if(nextPowerCount > 1) {
                    nextPower++;
                } else {
                    break;
                }
            }
            
            // Update the maximum count found so far.
            maxServers = Math.max(maxServers, currentMaxServers);
        }
        
        return maxServers;
    }

    public static void main(String[] args) {
        GetMaxServers solver = new GetMaxServers();

        // Case 1: Example from the problem description
        int[] powers1 = {4, 3, 5, 1, 2, 2, 1};
        int result1 = solver.solve(powers1);
        System.out.println("Test Case 1:");
        System.out.println("Input: powers = " + java.util.Arrays.toString(powers1));
        System.out.println("Output: " + result1);
        System.out.println("Expected: 5");
        System.out.println("--------------------");

        // Case 2: All same power
        int[] powers2 = {7, 7, 7, 7, 7};
        int result2 = solver.solve(powers2);
        System.out.println("Test Case 2:");
        System.out.println("Input: powers = " + java.util.Arrays.toString(powers2));
        System.out.println("Output: " + result2);
        System.out.println("Expected: 5");
        System.out.println("--------------------");

        // Case 3: Sequential powers
        int[] powers3 = {1, 2, 3, 4, 5, 6};
        int result3 = solver.solve(powers3);
        System.out.println("Test Case 3:");
        System.out.println("Input: powers = " + java.util.Arrays.toString(powers3));
        System.out.println("Output: " + result3);
        System.out.println("Expected: 2");
        System.out.println("--------------------");

        // Case 4: Random powers, multiple groups
        int[] powers4 = {1, 10, 2, 11, 100, 101, 102};
        int result4 = solver.solve(powers4);
        System.out.println("Test Case 4:");
        System.out.println("Input: powers = " + java.util.Arrays.toString(powers4));
        System.out.println("Output: " + result4);
        System.out.println("Expected: 2");
        System.out.println("--------------------");
    }
}
