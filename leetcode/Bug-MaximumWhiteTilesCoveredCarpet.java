// https://leetcode.com/problems/maximum-white-tiles-covered-by-a-carpet/
// TODO: last block white cells -- TODO -- BUG FIX

class Solution {
    public int maximumWhiteTiles(int[][] T, int L) {
        // sort the tiles based on start index
        Arrays.sort(T, (x, y) -> x[0] - y[0]);

        // now we will do prefix sum so that when for a given block of tiles gives me end index
        // so to get maximum white tiles in O(1)
        int N = T.length;
        int P[] = new int[N];
        for(int i = 1; i < N; i++) {
            P[i] = P[i - 1] + (T[i][1] - T[i][0] + 1);
        }

        // now we will start from every tiles to cover maximum number of white
        // then we will find right end where it will fit
        int ans = 0;
        for(int i = 0; i < N; i++) {
            int start = T[i][0];
            int end = start + L - 1;

            // now see in which block end will fit
            int l = i, r = N - 1;
            int idx = N - 1; // always it will be updated by correct block
            while(l <= r) {
                int mid = (l + r) / 2;
                if(T[mid][0] > end) {
                    r = mid - 1;
                } else {
                    // potential block
                    idx = mid;
                    l = mid + 1;
                }
            }

            // last block white cells
            int t = end - T[idx][0] + 1;
            // TODO need to work on this
            
            if(idx > 0) {
                t += P[idx - 1];
                if(i > 0) {
                    t -= P[i - 1];
                }
            }
            ans = Math.max(ans, t);
        }

        return ans;
    }
}