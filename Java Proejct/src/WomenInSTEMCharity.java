public class WomenInSTEMCharity {
	/**
	 * Returns the sum of the minimum-weight cans picked in each selection.
	 * @param cans the weights of the cans on the beach
	 * @return the sum of the minimum-weight cans at each selection
	 */
	public static int findTotalWeight(int[] cans) {
		// Use a list to allow removal of arbitrary elements
		java.util.List<Integer> canList = new java.util.ArrayList<>();
		for (int w : cans) canList.add(w);
		int total = 0;
		while (!canList.isEmpty()) {
			// Find the index of the minimum weight can (smallest index if tie)
			int minIdx = 0;
			for (int i = 1; i < canList.size(); i++) {
				if (canList.get(i) < canList.get(minIdx)) {
					minIdx = i;
				}
			}
			// Add the minimum weight to the total
			total += canList.get(minIdx);
			// Remove up to 3 adjacent cans: minIdx-1, minIdx, minIdx+1
			int left = Math.max(0, minIdx - 1);
			int right = Math.min(canList.size() - 1, minIdx + 1);
			// Remove from right to left to avoid index shifting
			for (int i = right; i >= left; i--) {
				canList.remove(i);
			}
		}
		return total;
	}

	// Test cases
	public static void main(String[] args) {
		// Example 1
		int[] cans1 = {5, 4, 1, 3, 2};
		System.out.println(findTotalWeight(cans1)); // Expected: 3

		// Test: all same weight
		int[] cans2 = {2, 2, 2, 2, 2};
		System.out.println(findTotalWeight(cans2)); // Expected: 4 (2+2)

		// Test: strictly increasing
		int[] cans3 = {1, 2, 3, 4, 5};
		System.out.println(findTotalWeight(cans3)); // Expected: 1+4=5

		// Test: strictly decreasing
		int[] cans4 = {5, 4, 3, 2, 1};
		System.out.println(findTotalWeight(cans4)); // Expected: 1+4=5

		// Test: minimum at edge
		int[] cans5 = {1, 5, 6, 7, 8};
		System.out.println(findTotalWeight(cans5)); // Expected: 1+8=9

		// Test: minimum at end
		int[] cans6 = {8, 7, 6, 5, 1};
		System.out.println(findTotalWeight(cans6)); // Expected: 1+8=9

		// Test: 3 cans
		int[] cans7 = {3, 2, 1};
		System.out.println(findTotalWeight(cans7)); // Expected: 1

		// Test: 4 cans
		int[] cans8 = {4, 1, 3, 2};
		System.out.println(findTotalWeight(cans8)); // Expected: 1+2=3
	}
}
