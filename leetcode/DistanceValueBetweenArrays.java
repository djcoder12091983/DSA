// https://leetcode.com/problems/find-the-distance-value-between-two-arrays/

class Solution {
    public int findTheDistanceValue(int[] A, int[] B, int d) {
        // if we try to solve it optimally like sort array2 and apply binary serach and see
        // if any element is within that distance d or what

        Arrays.sort(B);

        int c = 0;
        for(int x : A) {

            // right side BS
            // TODO i think left right BS is not required rather we can find the insertion position of x
            // then see left and right elements and see the distance matches or not
            /*
            int l = 0, r = B.length - 1;
            boolean found = false;

            while(l <= r) {
                int mid = (l + r) / 2;
                if(B[mid] <= x + d) {
                    // some elements exist
                    found = true;
                    break;
                } else {
                    r = mid - 1; // now move to left
                }
            }
            */

            int N = B.length;

            // find insertion position of X inside B
            // TODO need to think whether these conditions can be handled by BS itself
            if(x <= B[0]) {
                if(B[0] - x > d) {
                    // no such elements exists within d distance
                    c++;
                }
            } else if(x >= B[N - 1]) {
                if(x - B[N - 1] > d) {
                    // no such elements exists within d distance
                    c++;
                }
            } else {
                // apply BS to find insertion position
                
                int l = 0, r = N - 1;
                int position = -1;
                while(l <= r) {
                    int mid = (l + r) / 2;
                    if(x < B[mid]) {
                        r = mid - 1;
                    } else if(x > B[mid]) {
                        // move right to find suitable position
                        position = mid; // potential position
                        l = mid + 1;
                    } else {
                        position = mid;
                        break;
                    }
                }

                int d1 = x - B[position]; // left side difference
                int d2 = B[position + 1] - x; // right side difference

                // System.out.println("X : " + x + " Left: " + B[position] + " Right: " + B[position + 1]);
                
                if(d1 > d && d2 > d) {
                    c++; // no such elements exists within d distance
                }
            }
        }

        return c;
    }
}