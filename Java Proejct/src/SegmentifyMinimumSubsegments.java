public class SegmentifyMinimumSubsegments {

    /**
     * 
     * https://www.fastprep.io/problems/amazon-get-min-subsegments
     * Returns the minimum number of even-length subsegments after minimum flips.
     * @param frames the binary string representing video frames
     * @return the minimum number of subsegments
     */
    public int getminSubsegments(String frames) {
        int n = frames.length();
        // Step 1: Build segments of consecutive same characters
        java.util.List<int[]> segments = new java.util.ArrayList<>();
        int i = 0;
        while (i < n) {
            int j = i;
            while (j < n && frames.charAt(j) == frames.charAt(i)) j++;
            segments.add(new int[]{frames.charAt(i) - '0', j - i});
            i = j;
        }

        // Step 2: Adjust odd-length segments by borrowing from neighbors
        for (int idx = 0; idx < segments.size() - 1; idx++) {
            if (segments.get(idx)[1] % 2 == 1) {
                if (segments.get(idx)[1] > segments.get(idx + 1)[1]) {
                    segments.get(idx + 1)[1] -= 1;
                    segments.get(idx)[1] += 1;
                } else {
                    segments.get(idx)[1] -= 1;
                    segments.get(idx + 1)[1] += 1;
                }
            }
        }

        // Step 3: Count valid segments (even length, not same as previous)
        int cnt = 0;
        Integer prev = null;
        for (int[] seg : segments) {
            if (seg[1] >= 2 && (prev == null || seg[0] != prev)) {
                prev = seg[0];
                cnt++;
            }
        }
        return cnt;
    }

    // Test cases
    public static void main(String[] args) {
        SegmentifyMinimumSubsegments solver = new SegmentifyMinimumSubsegments();
        String[] testCases = {
            "00", "11", "01", "10", "0101", "0011", "1001", "1110011000",
            "110011", "100110", "101010", "0110", "10", "111000101010",
            "110011001100", "00001111000011", "1111000011110000",
            "00000000000000", "111111111111"
        };
        for (String tc : testCases) {
            int result = solver.getminSubsegments(tc);
            System.out.println(tc + " | " + result);
        }
    }
}

/*
 * 
        Time Complexity:

        Building segments: O(n), where n is the length of frames (single pass to group consecutive characters).
        Adjusting odd-length segments: O(m), where m is the number of segments (at most O(n) in the worst case).
        Counting valid segments: O(m).
        Total: O(n)
        Space Complexity:

        Segments list: O(m), at most O(n) in the worst case (if every character is different).
        Other variables: O(1)
        Total: O(n)
        Summary:

        Time: O(n)
        Space: O(n)
 */
