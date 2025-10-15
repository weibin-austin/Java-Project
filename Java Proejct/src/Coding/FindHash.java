import java.util.*;

public class FindHash {

    /**
     * Returns the maximum number of distinct elements possible in the hash array.
     * hash[i] = secretKey[i] % param[i]
     * basically, find the distinct elements in param array
     * 
     * https://www.fastprep.io/problems/amazon-find-hash
     * 
     * @param param the different parameters needed for the checksum logic
     * @return the maximum number of distinct elements possible in hash
     */
    public int findHash(int[] param) {
        Arrays.sort(param); // param1 = {1, 2, 4} -> param1 = {1, 2, 4}
        int distinctCount = 0;
        int targetHash = 0;
        for (int p : param) { // param2 = {1, 1, 1} 
             // distinctCount = 0, targetHash = 0 
            if (p > targetHash) { // p = 1, p = 1, p = 1
                    // 1 > 0, distinctCount = 1, targetHash = 1
                    // 1 > 1 false
                    // 1 > 1 false  
                distinctCount++;
                targetHash++;
            }
        }
        return distinctCount; // param1 = {1, 2, 4} return 3
                           // param2 = {1, 1, 1} return 1
    }

    // Test cases
    public static void main(String[] args) {
        FindHash solver = new FindHash();

        // Example 1
        int[] param1 = {1, 2, 4};
        System.out.println("Test 1 Output: " + solver.findHash(param1));
        // Expected: 3

        // Example 2
        int[] param2 = {1, 1, 1};
        System.out.println("Test 2 Output: " + solver.findHash(param2));
        // Expected: 1

        // Test 3: All unique params
        int[] param3 = {2, 3, 4, 5};
        System.out.println("Test 3 Output: " + solver.findHash(param3));
        // Expected: 4

        // Test 4: Some duplicates
        int[] param4 = {2, 2, 3, 3, 4};
        System.out.println("Test 4 Output: " + solver.findHash(param4));
        // Expected: 3

        // Test 5: Single param
        int[] param5 = {7};
        System.out.println("Test 5 Output: " + solver.findHash(param5));
        // Expected: 1

        // Test 6: Mix of 1s and others
        int[] param6 = {1, 2, 1, 3, 1, 4};
        System.out.println("Test 6 Output: " + solver.findHash(param6));
        // Expected: 4
    }
}
