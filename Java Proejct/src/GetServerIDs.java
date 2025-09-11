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






 class LeastVulnerabilityFactor {
      int findLeastPossibleVulnerabilityFactor(int[] key, int maxChange) {
        int n = key.length;
        if (n == 1) return 0;

        // 构造关系数组
        int[] rel = new int[n - 1];
        for (int i = 0; i < n - 1; i++) {
            if (gcd(key[i], key[i + 1]) > 1) {
                rel[i] = 1;
            }
        }

        // 二分答案
        int lo = 0, hi = n, ans = n;
        while (lo <= hi) {
            int mid = (lo + hi) / 2;
            if (canReduce(rel, maxChange, mid)) {
                ans = mid;
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }
        return ans;
    }

    // 检查是否能把最长段 <= L
    private static boolean canReduce(int[] rel, int maxChange, int L) {
        int n = rel.length;
        int used = 0;
        int i = 0;

        while (i < n) {
            if (rel[i] == 0) {
                i++;
                continue;
            }
            int j = i;
            while (j < n && rel[j] == 1) j++;
            int len = j - i + 1; // 子数组长度
            if (len > L) {
                int need = (len - L + 1) / 2; // 每改一个点消掉2边
                used += need;
                if (used > maxChange) return false;
            }
            i = j;
        }
        return true;
    }

    private static int gcd(int a, int b) {
        while (b != 0) {
            int t = a % b;
            a = b;
            b = t;
        }
        return a;
    }

    public static void main(String[] args) {
        int[] key1 = {2, 2, 4, 9, 6};
        System.out.println(findLeastPossibleVulnerabilityFactor(key1, 1)); // 2

        int[] key2 = {5, 10, 20, 10, 15, 5};
        System.out.println(findLeastPossibleVulnerabilityFactor(key2, 2)); // 2
    }
}


import java.util.*;

public class MinVulnerabilityFactor {
    
    // GCD function
    private static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    public static int minVulnerability(int[] key, int maxChange) {
        int n = key.length;

        // Step 1: find segments where gcd > 1
        List<Integer> segments = new ArrayList<>();
        int start = 0;
        while (start < n) {
            int g = key[start];
            int end = start;
            while (end + 1 < n && gcd(g, key[end + 1]) > 1) {
                end++;
                g = gcd(g, key[end]);
            }
            segments.add(end - start + 1);
            start = end + 1;
        }

        // Step 2: binary search on answer
        int lo = 1, hi = n, ans = n;
        while (lo <= hi) {
            int mid = (lo + hi) / 2;
            if (canAchieve(mid, segments, maxChange)) {
                ans = mid;
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }

        return ans;
    }

    private static boolean canAchieve(int target, List<Integer> segments, int maxChange) {
        long needed = 0;
        for (int L : segments) {
            if (L > target) {
                needed += (L + target - 1) / target - 1; // ceil(L/target)-1
                if (needed > maxChange) return false;
            }
        }
        return needed <= maxChange;
    }

    // --- Test ---
    public static void main(String[] args) {
        int[] key1 = {5, 10, 20, 10, 15, 5};
        int maxChange1 = 2;
        System.out.println(minVulnerability(key1, maxChange1)); // Expected 2

        int[] key2 = {4, 2, 4};
        int maxChange2 = 1;
        System.out.println(minVulnerability(key2, maxChange2)); // Expected 1
    }
}


import java.util.*;

public class MinVulnerabilityFactor {

    private static int gcd(int a, int b) {
        while (b != 0) {
            int tmp = a % b;
            a = b;
            b = tmp;
        }
        return a;
    }

    public static int minVulnerability(int[] key, int maxChange) {
        int n = key.length;
        List<Integer> segments = new ArrayList<>();

        // Step 1: Build GCD>1 segments
        int start = 0;
        while (start < n) {
            int g = key[start];
            int end = start;
            while (end + 1 < n && gcd(g, key[end + 1]) > 1) {
                end++;
                g = gcd(g, key[end]);
            }
            int length = end - start + 1;
            segments.add(length);
            start = end + 1;
        }

        // Step 2: Max-heap of segments
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[0] - a[0]);
        for (int len : segments) {
            pq.offer(new int[]{len, 1}); // {segment length, parts}
        }

        // Step 3: Apply changes greedily
        for (int c = 0; c < maxChange && !pq.isEmpty(); c++) {
            int[] top = pq.poll();
            int len = top[0], parts = top[1];
            parts++;
            int newMax = (len + parts - 1) / parts; // ceil(len / parts)
            pq.offer(new int[]{newMax, parts});
        }

        // Step 4: The largest chunk left = answer
        return pq.peek()[0];
    }

    public static void main(String[] args) {
        int[] key1 = {5, 10, 20, 10, 15, 5};
        int maxChange1 = 2;
        System.out.println(minVulnerability(key1, maxChange1)); // Expected 2

        int[] key2 = {4, 2, 4};
        int maxChange2 = 1;
        System.out.println(minVulnerability(key2, maxChange2)); // Expected 1
    }
}



public int findLeastPossibleVulnerability(int[] key, int maxChange) {
    int n = key.length;
    if (n == 0) return 0;
    int[] bad = new int[n - 1];
    for (int i = 0; i < n - 1; i++) {
        if (gcd(key[i], key[i + 1]) == 1) {
            bad[i] = 1;
        } else {
            bad[i] = 0;
        }
    }

    int left = 0, countOnes = 0, maxLen = 0;
    for (int right = 0; right < bad.length; right++) {
        if (bad[right] == 1) countOnes++;

        while (countOnes > maxChange) {
            if (bad[left] == 1) countOnes--;
            left++;
        }

        maxLen = Math.max(maxLen, right - left + 1);
    }

    return maxLen + 1;
}

private int gcd(int a, int b) {
    while (b != 0) {
        int tmp = a % b;
        a = b;
        b = tmp;
    }
    return a;
}




import java.util.*;

public class MinVulnerabilityFactor {

    private static int gcd(int a, int b) {
        while (b != 0) {
            int tmp = a % b;
            a = b;
            b = tmp;
        }
        return a;
    }

    public static int minVulnerability(int[] key, int maxChange) {
        int n = key.length;

        // Step 1: Build segments of consecutive GCD>1
        List<Integer> segments = new ArrayList<>();
        int start = 0;
        while (start < n) {
            int g = key[start];
            int end = start;
            while (end + 1 < n && gcd(g, key[end + 1]) > 1) {
                end++;
                g = gcd(g, key[end]);
            }
            segments.add(end - start + 1);
            start = end + 1;
        }

        // Step 2: Binary search on answer
        int lo = 1, hi = n, ans = n;
        while (lo <= hi) {
            int mid = (lo + hi) / 2;
            if (canAchieve(mid, segments, maxChange)) {
                ans = mid;
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }
        return ans;
    }

    private static boolean canAchieve(int target, List<Integer> segments, int maxChange) {
        long needed = 0;
        for (int L : segments) {
            if (L > target) {
                needed += (L + target - 1) / target - 1; // ceil(L/target) - 1
                if (needed > maxChange) return false;
            }
        }
        return needed <= maxChange;
    }

    // --- Test ---
    public static void main(String[] args) {
        int[] key1 = {5, 10, 20, 10, 15, 5};
        int maxChange1 = 2;
        System.out.println(minVulnerability(key1, maxChange1)); // Expected 2

        int[] key2 = {4, 2, 4};
        int maxChange2 = 1;
        System.out.println(minVulnerability(key2, maxChange2)); // Expected 1

        int[] key3 = {6, 5, 10, 20, 10, 15, 5};
        int maxChange3 = 2;
        System.out.println(minVulnerability(key3, maxChange3)); // Expected 2
    }
}

