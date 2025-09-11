import java.util.List;

public class AmazonOA {
    public static int findLeastPossibleVulnerability(List<Integer> key, int maxChange) {
        

    }

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

    public static void main(String[] args) {
    }


}
