class Solution {
    public int findMinArrowShots(int[][] P) {
        // sort based on start time
        Arrays.sort(P, (int[] i1, int[] i2) -> Integer.compare(i1[0],i2[0]));

        // now group based on some one interval
        int prev = P[0][1]; // previous end
        int ans = 1; // minimum one shooter is required
        int N = P.length;
        for(int i = 1; i < N; i++) {
            if(P[i][0] <= prev) {
                // if current interval intersects with previous one
                // update previous end to form group correctly
                prev = Math.min(prev, P[i][1]);
                continue; // previous shooter is enough
            } else {
                // this will form a different group
                // next shooter
                ans++;
                prev = P[i][1];
            }
        }

        return ans;
    }
}