import java.lang.Math;

class CountPossibleWinners_AllAboutRewards {
    /**
     * Calculates the number of "possible winners".
     *
     * @param initialRewards An array of initial reward points.
     * @param n The number of points to be added.
     * @return The number of participants who are considered possible winners.
     */
    public static int countPossibleWinners(int[] initialRewards, int n) {
        if (initialRewards.length < 2) {
            return initialRewards.length;
        }
        
        long highestPotentialScore = Long.MIN_VALUE;
        for (int p : initialRewards) {
            highestPotentialScore = Math.max(highestPotentialScore, (long) p + n - 1);
        }

        int possibleWinnersCount = 0;
        for (int p : initialRewards) {
            if ((long) p + n >= highestPotentialScore) {
                possibleWinnersCount++;
            }
        }
        
        return possibleWinnersCount;
    }


    // Test cases
    public static void main(String[] args) {
        CountPossibleWinners_AllAboutRewards solver = new CountPossibleWinners_AllAboutRewards();

        int[] rewards1 = {1, 3, 4};
        System.out.println(solver.countPossibleWinners(rewards1, 3)); // Expected: 2

        int[] rewards2 = {5, 7, 9, 11};
        System.out.println(solver.countPossibleWinners(rewards2, 4)); // Expected: 1

        int[] rewards3 = {8, 10, 9};
        System.out.println(solver.countPossibleWinners(rewards3, 3)); // Expected: 1
    }
}
