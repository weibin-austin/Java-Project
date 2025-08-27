import java.util.Arrays;
import java.util.Stack;

class PredictAnswer {
    /**
     * Finds the nearest day with a lower stock price for each query.
     * The method uses a monotonic stack to efficiently pre-compute answers
     * for nearest lower prices to the left and right of each day.
     *
     * @param stockData An integer array denoting the stock prices.
     * @param queries   An integer array of day numbers to query.
     * @return An integer array of answers for each query.
     */
    public static int[] predictAnswer(int[] stockData, int[] queries) {
        int n = stockData.length;
        int q = queries.length;

        int[] leftAnswer = new int[n];
        int[] rightAnswer = new int[n];
        Stack<Integer> stack = new Stack<>();

        // 1. Pre-compute nearest lower price to the left for each day
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && stockData[stack.peek()] >= stockData[i]) {
                stack.pop();
            }
            leftAnswer[i] = stack.isEmpty() ? -1 : stack.peek() + 1;
            stack.push(i);
        }

        // Reset stack for right-side computation
        stack.clear();

        // 2. Pre-compute nearest lower price to the right for each day
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && stockData[stack.peek()] >= stockData[i]) {
                stack.pop();
            }
            rightAnswer[i] = stack.isEmpty() ? -1 : stack.peek() + 1;
            stack.push(i);
        }

        // 3. Process each query
        int[] results = new int[q];
        for (int i = 0; i < q; i++) {
            int dayIndex = queries[i] - 1;
            int leftDay = leftAnswer[dayIndex];
            int rightDay = rightAnswer[dayIndex];

            if (leftDay == -1 && rightDay == -1) {
                results[i] = -1;
            } else if (leftDay == -1) {
                results[i] = rightDay;
            } else if (rightDay == -1) {
                results[i] = leftDay;
            } else {
                // Both left and right answers exist, find the one with the smaller distance.
                // If distances are equal, choose the smaller day number.
                if (Math.abs(leftDay - (dayIndex + 1)) <= Math.abs(rightDay - (dayIndex + 1))) {
                    results[i] = leftDay;
                } else {
                    results[i] = rightDay;
                }
            }
        }
        return results;
    }

     public static void main(String[] args) {
        PredictAnswer solution = new PredictAnswer();

        // Test Case 1: Example from the problem description
        int[] stockData1 = {5, 6, 8, 4, 9, 10, 8, 3, 6, 4};
        int[] queries1 = {6, 5, 4};
        int[] expected1 = {5, 4, 8};
        int[] result1 = solution.predictAnswer(stockData1, queries1);
        System.out.println("Test Case 1:");
        System.out.println("Input stockData: " + Arrays.toString(stockData1));
        System.out.println("Input queries: " + Arrays.toString(queries1));
        System.out.println("Expected output: " + Arrays.toString(expected1));
        System.out.println("Your output: " + Arrays.toString(result1));
        System.out.println("Result matches expected: " + Arrays.equals(expected1, result1));
        System.out.println("----------------------------------------");

        // Test Case 2: No lower price exists for some queries
        int[] stockData2 = {10, 9, 8, 7, 6};
        int[] queries2 = {1, 2, 5};
        int[] expected2 = {-1, -1, -1};
        int[] result2 = solution.predictAnswer(stockData2, queries2);
        System.out.println("Test Case 2:");
        System.out.println("Input stockData: " + Arrays.toString(stockData2));
        System.out.println("Input queries: " + Arrays.toString(queries2));
        System.out.println("Expected output: " + Arrays.toString(expected2));
        System.out.println("Your output: " + Arrays.toString(result2));
        System.out.println("Result matches expected: " + Arrays.equals(expected2, result2));
        System.out.println("----------------------------------------");

        // Test Case 3: Mixed scenario with duplicates
        int[] stockData3 = {3, 5, 5, 2, 4, 6};
        int[] queries3 = {2, 3, 5};
        int[] expected3 = {1, 1, 4};
        int[] result3 = solution.predictAnswer(stockData3, queries3);
        System.out.println("Test Case 3:");
        System.out.println("Input stockData: " + Arrays.toString(stockData3));
        System.out.println("Input queries: " + Arrays.toString(queries3));
        System.out.println("Expected output: " + Arrays.toString(expected3));
        System.out.println("Your output: " + Arrays.toString(result3));
        System.out.println("Result matches expected: " + Arrays.equals(expected3, result3));
        System.out.println("----------------------------------------");

        // Test Case 4: Tie-breaking rule (smaller day number)
        int[] stockData4 = {10, 5, 12, 5, 10};
        int[] queries4 = {3};
        int[] expected4 = {2};
        int[] result4 = solution.predictAnswer(stockData4, queries4);
        System.out.println("Test Case 4:");
        System.out.println("Input stockData: " + Arrays.toString(stockData4));
        System.out.println("Input queries: " + Arrays.toString(queries4));
        System.out.println("Expected output: " + Arrays.toString(expected4));
        System.out.println("Your output: " + Arrays.toString(result4));
        System.out.println("Result matches expected: " + Arrays.equals(expected4, result4));
        System.out.println("----------------------------------------");
    }
}