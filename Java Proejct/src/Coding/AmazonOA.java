import java.util.*;

public class AmazonOA {

    static long getStrength(long[][] power) {
        long globalMin = Long.MAX_VALUE;   // min over all machines' minima
        long sumSecond = 0;                // sum of each machine's second-minimum
        long minSecond = Long.MAX_VALUE;   // smallest second-min across machines

        for (long[] machine : power) {
            long min1 = Long.MAX_VALUE, min2 = Long.MAX_VALUE;
            for (long v : machine) {
                if (v < min1) { min2 = min1; min1 = v; }
                else if (v < min2) { min2 = v; }
            }
            globalMin = Math.min(globalMin, min1);
            sumSecond += min2;
            minSecond = Math.min(minSecond, min2);
        }
        return sumSecond - minSecond + globalMin;
    }

    public int maximizeStrengthOfMachines(int n, int m, int[][] machine_power) {
  // write your code here
  int globalMin = Integer.MAX_VALUE;
  int sumSecond = 0;
  int minSecond = Integer.MAX_VALUE;

  for(int[] machine: machine_power) {
    int min1 = Integer.MAX_VALUE, min2 = Integer.MAX_VALUE;
    for(int v: machine) {
      if(v < min1) {
        min2 = min1;
        min1 = v;
      } else if(v < min2) {
        min2 = v;
      }
    }

    globalMin = Math.min(globalMin, min1);
    sumSecond += min2;
    minSecond = Math.min(minSecond, min2);
  }
  return sumSecond - minSecond + globalMin;
}

    /**
     * Returns the number of participants who can win the tournament.
     * @param earnedPoints the initial reward points of each participant
     * @param n the number of points added for the winner
     * @return the number of possible winners
     */
    public int allAboutRewards(int[] earnedPoints, int n) {
        if (earnedPoints.length < 2) {
            return earnedPoints.length;
        }

        int highest = 0;
        for (int p : earnedPoints) {
            highest = Math.max(highest, p + n - 1); // Calculate the highest possible score for other participants
        }

        int ans = 0;
        for (int p : earnedPoints) {
            if (p + n >= highest) { // Check if the current participant's score after winning is >= highest
                ans++;
            }
        }

        return ans;
    }

    // Test cases
    public static void main(String[] args) {
        AmazonOA solver = new AmazonOA();

        int[] rewards1 = {1, 3, 4};
        System.out.println(solver.allAboutRewards(rewards1, 3)); // Expected: 2

        int[] rewards2 = {5, 7, 9, 11};
        System.out.println(solver.allAboutRewards(rewards2, 4)); // Expected: 1

        int[] rewards3 = {8, 10, 9};
        System.out.println(solver.allAboutRewards(rewards3, 3)); // Expected: 2
    }
}
