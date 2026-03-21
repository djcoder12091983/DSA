public class Solution {
    
    class DP {
        boolean ok = false;
        int min = Integer.MAX_VALUE;
        
        DP(boolean ok) {
            this.ok = ok;
        }
        
        DP(boolean ok, int min) {
            this.ok = ok;
            this.min = min;
        }
    }
    
    public int solve(final List<Integer> A) {
        // subset sum problem with minimum choose
        int N = A.size();
        int S = 0;
        for(int i = 0; i < N; i++) {
            S += A.get(i);
        }
        // tracking selecting a subset of i elements sum j can be possible or not,
        // if possible then how many minimum choose required
        DP track[][] = new DP[N + 1][S + 1];
        // fill default cases
        for(int i = 0; i <= N; i++) {
            for(int j = 0; j <= S; j++) {
                track[i][j] = new DP(false);
            }
        }
        // fill base cases
        for(int i = 0; i <= N; i++) {
            DP t = track[i][0];
            t.ok = true;
            t.min = 0;
        }
        // fill the table, top down manner
        for(int i = 1; i <= N; i++) {
            int a = A.get(i - 1);
            for(int j = 1; j <= S; j++) {
                DP t = track[i][j];
                // two cases: not selecting a or selecting a
                DP t1 = track[i - 1][j];
                if(t1.ok) {
                    t.ok = true;
                    t.min = Math.min(t.min, t1.min);
                }
                int req = j - a;
                if(req >= 0) {
                    t1 = track[i - 1][req];
                    if(t1.ok) {
                        t.ok = true;
                        t.min = Math.min(t.min, 1 + t1.min);
                    }
                }
            }
        }
        int mdiff = Integer.MAX_VALUE, mcount = Integer.MAX_VALUE;
        for(int i = 0; i <= N; i++) {
            for(int j = 0; j <= S; j++) {
                DP t = track[i][j];
                if(t.ok) {
                    int s1 = S - j;
                    if(j <= s1) {
                        int diff = s1 - j;
                        if(diff < mdiff) {
                            // change minimum diff
                            mdiff = diff;
                            mcount = t.min;
                        } else if(diff == mdiff) {
                            // change minimum count
                            mcount = Math.min(mcount, t.min);
                        }
                    }
                }
            }
        }
        
        return mcount; // returns minimum count
    }
}
