// https://leetcode.com/problems/minimum-bishop-moves-to-reach-target/

class Solution {

    boolean black(int p[]) {
        // x is even and y is odd
        if(p[0] % 2 == 0) {
            // starts with black
            return p[1] % 2 == 1;
        } else {
            // starts with white
            return p[1] % 2 == 0;
        }
    }
    
    public int minBishopMoves(int[] source, int[] target) {
        boolean b1 = black(source);
        boolean b2 = black(target);
        boolean diff = b1 ^ b2;
        if(diff) {
            // different color box then not possible
            return -1;
        } else {
            // either we can move direct or can at most two steps
            int diff1 = Math.abs(source[0] - target[0]);
            int diff2 = Math.abs(source[1] - target[1]);

            if(diff1 == diff2) {
                return 1; // direct move
            } else {
                return 2; // we can take at most 2 steps
            }
        }
    }
}