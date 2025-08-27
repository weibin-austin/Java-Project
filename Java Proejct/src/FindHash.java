import java.util.*;

public class FindHash {

    /**
     * Returns the maximum number of distinct elements possible in the hash array.
     * hash[i] = secretKey[i] % param[i]
     * 
     * https://www.fastprep.io/problems/amazon-find-hash
     * 
     * @param param the different parameters needed for the checksum logic
     * @return the maximum number of distinct elements possible in hash
     */
    public int findHash(int[] param) {
        Arrays.sort(param);
        int distinctCount = 0;
        int targetHash = 0;
        for (int p : param) {
            if (p > targetHash) {
                distinctCount++;
                targetHash++;
            }
        }
        return distinctCount;
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
        // Expected: 3
    }
}
