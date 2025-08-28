import java.lang.Math;

class CountPossibleWinners_AllAboutRewards {
    /**
     * Calculates the number of "possible winners". Winner socre which is not smaller than 2nd participant scores.
     * https://www.fastprep.io/problems/amazon-all-about-rewards
     * @param initialRewards An array of initial reward points.
     * @param n The number of points to be added.
     * @return The number of participants who are considered possible winners.
     */
    public static int countPossibleWinners(int[] initialRewards, int n) {
        if (initialRewards.length < 2) {
            return initialRewards.length;
        }
        
        int highestPotentialScore = Integer.MIN_VALUE;
        for (int p : initialRewards) {
            highestPotentialScore = Math.max(highestPotentialScore, p + n - 1);
        }
        // initialRewards = [1, 3, 4], n = 3
        // highestPotentialScore = 4 + 3 - 1 = 6
        // n-1 is the second highest score a player can reach.
        // the initial highest score player + second higest score is the threadhold 

        int possibleWinnersCount = 0;
        for (int p : initialRewards) {
            // if a player's potential highest score (p + n) is at least the highestPotentialScore,
            // then this player is a possible winner.
            if (p + n >= highestPotentialScore) {
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
