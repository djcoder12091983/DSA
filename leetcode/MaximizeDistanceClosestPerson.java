// https://leetcode.com/problems/maximize-distance-to-closest-person/

class Solution {
    public int maxDistToClosest(int[] seats) {
        int left = -1; // track left index where a person sits
        
        int i = 0;
        while(seats[i] != 1) {
            i++;
        }

        // note at least one seat occupied so right will be found always
        int right = i; // track the right occupancy index

        int N = seats.length;
        int ans = 0;
        i = 0;
        while(i < N) {
            if(seats[i] == 1) {
                // occupied
                left = i; // next index will be on right side of current index
                int j = i + 1;
                while(j < N && seats[j] != 1) {
                    j++;
                }
                right = j; // if not found on right side then it will be N
            } else {

                if(left == -1) {
                    // only considers right side as closed
                    int d = right - i;
                    ans = Math.max(ans, d);
                } else if(right == N) {
                    // only considers left side as closed
                    int d = i - left;
                    ans = Math.max(ans, d);
                } else {
                    int d1 = i - left; // left distance
                    int d2 = right - i; // right distance

                    // maximize distance, note: why MIN because of closest person
                    ans = Math.max(ans, Math.min(d1, d2));
                }
            }

            i++;
        }

        return ans;
    }
}