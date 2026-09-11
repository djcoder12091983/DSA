// https://leetcode.com/problems/check-if-it-is-a-straight-line/

class Solution {

    int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }

    boolean check(int dx, int dy, int dx1, int dy1) {

        if(dx == 0) {
            // if dx is 0 all other dx should be 0
            // no matter what dy it is
            return dx1 == 0;
        }

        // same as dx is 0
        if(dy == 0) {
            return dy1 == 0;
        }

        // both are non zero
        boolean sign1 = (dx < 0) ^ (dy < 0);
        boolean sign2 = (dx1 < 0) ^ (dy1 < 0);

        if(sign1 != sign2) {
            // sign is not same
            return false;
        }

        dx = Math.abs(dx);
        dy = Math.abs(dy);
        dx1 = Math.abs(dx1);
        dy1 = Math.abs(dy1);
        
        // to avoid floating point error we can see dx dy is same
        // after divide by common factor (gcd) 
        int cf = gcd(dx, dy);
        dx = dx / cf;
        dy = dy / cf;

        cf = gcd(dx1, dy1);
        dx1 = dx1 / cf;
        dy1 = dy1 / cf;

        return dx == dx1 && dy == dy1;
    }

    public boolean checkStraightLine(int[][] coordinates) {
        int N = coordinates.length;
        if(N == 2) {
            return true;
        }

        int dx = coordinates[0][0] - coordinates[1][0];
        int dy = coordinates[0][1] - coordinates[1][1];

        for(int i = 2; i < N; i++) {
            int dx1 = coordinates[0][0] - coordinates[i][0];
            int dy1 = coordinates[0][1] - coordinates[i][1];

            if(!check(dx, dy, dx1, dy1)) {
                // not fall on same line
                return false;
            }
        }

        return true;
    }
}