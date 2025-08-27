import java.util.*;

public class MaximizePagesBeforeSuspension {

	/**
	 * Calculates the maximum number of pages that can be printed before printers get suspended.
	 * 
	 * @param pages      the number of pages each printer can print
	 * @param threshold  the suspension threshold for each printer
	 * @return           the maximum number of pages that can be printed
	 */
	public static int getMaxPages(int[] pages, int[] threshold) {
		// Priority queue to select printers by ascending threshold, then by descending pages if tie
		PriorityQueue<int[]> priorityQueue = new PriorityQueue<>((a, b) -> a[1] == b[1] ? b[0] - a[0] : a[1] - b[1]);

		// Add all printers to the priority queue as [pages, threshold] pairs
		for (int i = 0; i < pages.length; i++) {
			priorityQueue.offer(new int[]{pages[i], threshold[i]});
		}

		int ans = 0; // Total pages printed
		// Deque to keep track of currently operational printers
		Deque<int[]> deque = new LinkedList<>();

		// Process printers until all have been considered
		while (!priorityQueue.isEmpty()) {
			// Activate the next printer (lowest threshold, highest pages if tie)
			int[] poll = priorityQueue.poll();
			ans += poll[0]; // Add its pages to the total
			deque.offerLast(poll); // Mark this printer as operational

			// Suspend any printers in the queue whose threshold is less than or equal to the number of operational printers
			while (!priorityQueue.isEmpty() && priorityQueue.peek()[1] <= deque.size()) {
				priorityQueue.poll();
			}

			int curSize = deque.size(); // Current number of operational printers

			// Suspend any operational printers whose threshold is less than or equal to the current number of operational printers
			while (!deque.isEmpty() && deque.peekFirst()[1] <= curSize) {
				deque.pollFirst();
			}
		}
		return ans;
	}

	// Test cases
	public static void main(String[] args) {
		// Example 1
		int[] pages1 = {4, 1, 5, 2, 3};
		int[] threshold1 = {3, 3, 2, 3, 3};
		System.out.println(getMaxPages(pages1, threshold1)); // Output may vary

		// Example 2
		int[] pages2 = {2, 4, 4, 4, 5, 3};
		int[] threshold2 = {1, 3, 1, 3, 3, 2};
		System.out.println(getMaxPages(pages2, threshold2)); // Output may vary

		// Example 3
		int[] pages3 = {2, 6, 10, 13};
		int[] threshold3 = {2, 1, 1, 1};
		System.out.println(getMaxPages(pages3, threshold3)); // Output may vary

		// Edge: all thresholds high
		int[] pages4 = {1, 2, 3};
		int[] threshold4 = {10, 10, 10};
		System.out.println(getMaxPages(pages4, threshold4)); // Output may vary

		// Edge: all thresholds low
		int[] pages5 = {1, 2, 3};
		int[] threshold5 = {1, 1, 1};
		System.out.println(getMaxPages(pages5, threshold5)); // Output may vary

		// Custom: single printer
		int[] pages6 = {7};
		int[] threshold6 = {1};
		System.out.println(getMaxPages(pages6, threshold6)); // Output: 7

		// Custom: two printers, one suspends immediately
		int[] pages7 = {5, 10};
		int[] threshold7 = {1, 2};
		System.out.println(getMaxPages(pages7, threshold7)); // Output may vary
	}
}
