// https://leetcode.com/problems/maximum-gap-between-stations/

class Solution {
    public int maximumGap(String skill, String station) {
        
        // note: we will track minimum position in "station" string where a prefix and suffix of "skill" fit into
        // prefix tracks minimum position from left and suffix tracks minimum position from right
        // so automatically it will create a maximum GAP

        int n = skill.length();
        if(n == 1) {
            // only one skill set is there so only one station is required
            return 0; // edge case
        }

        int s = station.length();
        int P[] = new int[n];

        // assuming a valid sequence always exists so j boundary check is not required
        
        int i = 0, j = 0;
        while(i < n) {
            if(skill.charAt(i) == station.charAt(j)) {
                // we will move both
                P[i] = j;
                i++;
                j++;
            } else {
                // wait i for next match
                j++;
            }
        }

        // suffix part
        int S[] = new int[n];
        i = n - 1;
        j = s - 1;
        while(i >= 0) {
            if(skill.charAt(i) == station.charAt(j)) {
                // we will move both
                S[i] = j;
                i--;
                j--;
            } else {
                // wait i for next match
                j--;
            }
        }

        // now check for every pairs
        int ans = 1;
        i = 0;
        while(i < n - 1) {
            int p1 = P[i];
            int p2 = S[i + 1];

            ans = Math.max(ans, p2 - p1);

            i++;
        }

        return ans;
    }
}