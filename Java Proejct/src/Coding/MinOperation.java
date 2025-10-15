import java.util.HashMap;
import java.util.Map;
import java.util.Arrays;

class MinOperation {
    /**
     * Calculates the minimum number of operations to ship all products.
     *
     * @param m The total number of products. This parameter is redundant but included for a matching function signature.
     * @param locations An array of integers representing the location of each product.
     * @return The minimum number of operations.
     */
    public static int minOperation(int m, int[] locations) {
        int n = locations.length;
        if (n == 0) {
            return 0;
        }

        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int location : locations) {
            freqMap.put(location, freqMap.getOrDefault(location, 0) + 1);
        }

        int maxFreq = 0;
        for (int freq : freqMap.values()) {
            if (freq > maxFreq) {
                maxFreq = freq;
            }
        }

        int nonMaxFreqCount = n - maxFreq;

        if (maxFreq > nonMaxFreqCount) {
            return maxFreq;
        } else {
            return (n + 1) / 2;
        }
    }

    public static void main(String[] args) {
        MinOperation solver = new MinOperation();

        // Case 0: Correct
        int m0 = 5;
        int[] locations0 = {1, 8, 6, 7, 7};
        int output0 = solver.minOperation(m0, locations0);
        System.out.println("Case 0:");
        System.out.println("inputs:");
        System.out.println("m = " + m0);
        System.out.println("locations = " + Arrays.toString(locations0));
        System.out.println("your output: " + output0);
        System.out.println("expected output: 3");
        System.out.println("--------------------");

        // Case 1: Correct
        int m1 = 4;
        int[] locations1 = {1, 3, 1, 2};
        int output1 = solver.minOperation(m1, locations1);
        System.out.println("Case 1:");
        System.out.println("inputs:");
        System.out.println("m = " + m1);
        System.out.println("locations = " + Arrays.toString(locations1));
        System.out.println("your output: " + output1);
        System.out.println("expected output: 2");
        System.out.println("--------------------");

        // Case 2: All same location
        int m2 = 4;
        int[] locations2 = {1, 1, 1, 1};
        int output2 = solver.minOperation(m2, locations2);
        System.out.println("Case 2:");
        System.out.println("inputs:");
        System.out.println("m = " + m2);
        System.out.println("locations = " + Arrays.toString(locations2));
        System.out.println("your output: " + output2);
        System.out.println("expected output: 4");
        System.out.println("--------------------");

        // Case 3: All unique locations
        int m3 = 6;
        int[] locations3 = {1, 2, 3, 4, 5, 6};
        int output3 = solver.minOperation(m3, locations3);
        System.out.println("Case 3:");
        System.out.println("inputs:");
        System.out.println("m = " + m3);
        System.out.println("locations = " + Arrays.toString(locations3));
        System.out.println("your output: " + output3);
        System.out.println("expected output: 3");
        System.out.println("--------------------");
    }
}