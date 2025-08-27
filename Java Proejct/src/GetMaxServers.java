import java.util.HashMap;
import java.util.Map;

class GetMaxServers {
    /**
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

        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int power : powers) {
            freqMap.put(power, freqMap.getOrDefault(power, 0) + 1);
        }

        int maxServers = 0;
        
        // Handle the case of only one unique power value
        if (freqMap.size() == 1) {
            return powers.length;
        }

        for (int power : freqMap.keySet()) {
            int currentCount = freqMap.get(power);
            if (freqMap.containsKey(power + 1)) {
                currentCount += freqMap.get(power + 1);
            }
            if (currentCount > maxServers) {
                maxServers = currentCount;
            }
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
        System.out.println("Expected: 3");
        System.out.println("--------------------");
    }
}
