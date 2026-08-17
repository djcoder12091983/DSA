// https://leetcode.com/problems/eliminate-maximum-number-of-monsters/

class Solution {

    // possible function will help to check whether all monsters we can kill within time or what
    boolean possible(int[] dist, int[] speed, int x) {
        // TODO can we think of write something here
    }

    public int eliminateMaximum(int[] dist, int[] speed) {
        // apply binary search, like we will consider out search space from 1 to N monsters we can kill
        // now to kill X number of maximum monsetrs i need at least X - 1 minutes
        // now if we see we can't kill all X monsters in X - 1 minutes then we will move out search space to left side
        // but if we can kill all then we may find more monsters to kill
        int N = dist.length;
        int l = 1, r =   N;
        int ans = 1;
        while(l <= r) {
            int mid = (l + r) / 2;
            if(possible(dist, speed, mid)) {
                // kill all monsters so look for more monsters
                ans = mid;
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }

        return ans;
    }
}