import java.util.Arrays;

public class GetServerIDs {

    /**
     * Assigns each request to the server with the minimum number of requests among the first requests[i] servers.
     * If there is a tie, assigns to the server with the smallest id.
     *
     * @param num_servers Number of servers (ids 0 to num_servers-1)
     * @param requests    Array where requests[i] means consider servers 0 to requests[i]-1
     * @return Array of assigned server ids for each request
     */
    public static int[] getServerIds(int num_servers, int[] requests) {
        int[] serverLoads = new int[num_servers]; // Track number of requests per server
        int[] result = new int[requests.length];

        for (int i = 0; i < requests.length; i++) {
            int minLoad = Integer.MAX_VALUE;
            int minId = -1;
            // Only consider servers 0 to requests[i]-1
            if (requests[i] == 0) {
                minId = 0;
            } else {
                // Only consider servers 0 to requests[i]-1
                for (int j = 0; j < requests[i]; j++) {
                    if (serverLoads[j] < minLoad) {
                        minLoad = serverLoads[j];
                        minId = j;
                    }
                }
            }
            // Assign request to minId server
            serverLoads[minId]++;
            result[i] = minId;
        }
        return result;
    }

    // Test cases for getServerIds
    public static void main(String[] args) {
        // Test 0
        int num_servers0 = 5;
        int[] requests0 = {4, 0, 2, 2};
        int[] result0 = getServerIds(num_servers0, requests0);
        System.out.println("Test 1 Output: " + Arrays.toString(result0));
        // Expected: [0, 0, 1, 1]

        // Test 1
        int num_servers1 = 3;
        int[] requests1 = {2, 3, 3, 2, 3};
        int[] result1 = getServerIds(num_servers1, requests1);
        System.out.println("Test 1 Output: " + Arrays.toString(result1));
        // Expected: [0, 1, 2, 0, 1]

        // Test 2: All requests to all servers
        int num_servers2 = 4;
        int[] requests2 = {4, 4, 4, 4};
        int[] result2 = getServerIds(num_servers2, requests2);
        System.out.println("Test 2 Output: " + Arrays.toString(result2));
        // Expected: [0, 1, 2, 3]

        // Test 3: Only first server available
        int num_servers3 = 2;
        int[] requests3 = {1, 1, 1};
        int[] result3 = getServerIds(num_servers3, requests3);
        System.out.println("Test 3 Output: " + Arrays.toString(result3));
        // Expected: [0, 0, 0]

        // Test 4: Tie-breaker
        int num_servers4 = 3;
        int[] requests4 = {2, 2, 2, 2};
        int[] result4 = getServerIds(num_servers4, requests4);
        System.out.println("Test 4 Output: " + Arrays.toString(result4));
        // Expected: [0, 1, 0, 1]
    }
}
