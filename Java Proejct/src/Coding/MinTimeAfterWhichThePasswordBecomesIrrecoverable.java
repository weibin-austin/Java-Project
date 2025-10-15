public class MinTimeAfterWhichThePasswordBecomesIrrecoverable {
	/**
	 * Returns the minimum time after which the password becomes irrecoverable.
	 * @param password the initial password
	 * @param attackOrder permutation array of integers [1, 2, ..., n]
	 * @param m the recoverability parameter
	 * @return the minimum time after which the password becomes irrecoverable
	 */
	public static int helpAmazonFindMinTimeAgain(String password, int[] attackOrder, int m) {
		int n = password.length();
		char[] pwd = password.toCharArray();
		boolean[] attacked = new boolean[n];
		// If already irrecoverable at the start
		if (countSubstringsWithStar(pwd, m) >= m) return 1;
		for (int t = 0; t < attackOrder.length; t++) {
			int idx = attackOrder[t] - 1; // attackOrder is 1-based
			pwd[idx] = '*';
			attacked[idx] = true;
			if (countSubstringsWithStar(pwd, m) >= m) {
				return t + 1;
			}
		}
		return attackOrder.length;
	}

	// Helper: count substrings containing at least one '*'
	private static int countSubstringsWithStar(char[] pwd, int m) {
		int n = pwd.length;
		int count = 0;
		for (int i = 0; i < n; i++) {
			for (int j = i; j < n; j++) {
				boolean hasStar = false;
				for (int k = i; k <= j; k++) {
					if (pwd[k] == '*') {
						hasStar = true;
						break;
					}
				}
				if (hasStar) count++;
				if (count >= m) return count; // early exit
			}
		}
		return count;
	}

	// Test cases
	public static void main(String[] args) {
		// Example 1
		String pwd1 = "bcced";
		int[] attackOrder1 = {2, 3, 1, 4, 5};
		int m1 = 10;
		System.out.println(helpAmazonFindMinTimeAgain(pwd1, attackOrder1, m1)); // Expected: 2

		// Test: already irrecoverable at start
		String pwd2 = "*cced";
		int[] attackOrder2 = {2, 3, 1, 4, 5};
		int m2 = 10;
		System.out.println(helpAmazonFindMinTimeAgain(pwd2, attackOrder2, m2)); // Expected: 1

		// Test: m = 1
		String pwd3 = "abcde";
		int[] attackOrder3 = {1, 2, 3, 4, 5};
		int m3 = 1;
		System.out.println(helpAmazonFindMinTimeAgain(pwd3, attackOrder3, m3)); // Expected: 1

		// Test: m = n*(n+1)/2 (all substrings)
		String pwd4 = "abc";
		int[] attackOrder4 = {1, 2, 3};
		int m4 = 6;
		System.out.println(helpAmazonFindMinTimeAgain(pwd4, attackOrder4, m4)); // Expected: 3

		// Test: m = 2, star at end
		String pwd5 = "abcde";
		int[] attackOrder5 = {5, 4, 3, 2, 1};
		int m5 = 2;
		System.out.println(helpAmazonFindMinTimeAgain(pwd5, attackOrder5, m5)); // Expected: 1
	}
}
