// https://leetcode.com/problems/palindrome-partitioning-ii/
// TODO probabilistic analysis

class Solution {

    // palindrome check
    boolean palindrome(String s) {
        // System.out.println(s);
        int i = 0, j = s.length() - 1;
        while(i <= j) {
            if(s.charAt(i) != s.charAt(j)) {
                return false;
            }

            i++;
            j--;
        }

        return true;
    }

    public int minCut(String s) {
        // we will use TABULAR approach
        // idea is like computing from right to left, when we will be at some point then on left hand side
        // we will look for min cut point if that min cut is possible by checking whether it's palindrome
        // from current point to that point, if not then try out next minimum
        // NOTE: it's kind of GREEDY + DP

        int N = s.length();
        int DP[] = new int[N + 1];
        DP[N] = -1;
        DP[N - 1] = 0;

        for(int i = N - 2; i>= 0; i--) {
            // now greedily look at which point gives min cut
            // instead of exploring all the points
            
            int t[][] = new int[N - i][2];
            int k = 0;
            for(int j = i + 1; j <= N; j++) {
                t[k][0] = DP[j];
                t[k][1] = j;
                k++;
            }

            Arrays.sort(t, (x, y) -> x[0] - y[0]); // sort based on minimum cut

            for(int j = 0; j < k; j++) {
                String ss = s.substring(i, t[j][1]);
				// TODO probabilistic analysis
                if(palindrome(ss)) {
                    // found minimum possible one
                    DP[i] = 1 + t[j][0];
                    break;
                }
            }
        }

        return DP[0];
    }
}