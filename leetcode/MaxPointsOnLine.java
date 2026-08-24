// https://leetcode.com/problems/max-points-on-a-line/

class Solution {

    int gcd(int x, int y) {
        return (y == 0) ? x : gcd(y, x % y);
    }

    // convert x and y difference into gradient key to be grouped
    String gradient(int xdiff, int ydiff) {
        boolean xsign = xdiff < 0;
        boolean ysign = ydiff < 0;

        boolean sign = xsign ^ ysign;
        int x = Math.abs(xdiff);
        int y = Math.abs(ydiff);
        int cf = gcd(x, y);

        return (sign ? "-" : "+") + "[" + (x/cf) + "/" + (y/cf) + "]";
    }

    public int maxPoints(int[][] P) {
        int maxp = 0;

        int N = P.length;
        for(int i = 0; i < N; i++) {
            HashMap<String, Integer> gradients = new HashMap<>(); // gradient group
            int xp = 0, yp =0; // x parallel and y parallel
            for(int j = i + 1; j < N; j++) {
                // pairing
                int xdiff = P[i][0] - P[j][0];
                int ydiff = P[i][1] - P[j][1];

                if(xdiff == 0) {
                    // y parallel
                    yp++;
                } else if(ydiff == 0) {
                    // x parallel
                    xp++;
                } else {
                    // both are non zero
                    String grad = gradient(xdiff, ydiff);
                    gradients.put(grad, gradients.getOrDefault(grad, 0) + 1);
                    maxp = Math.max(maxp, 1 + gradients.get(grad));
                }
            }

            maxp = Math.max(maxp, 1 + Math.max(xp, yp));
        }

        return maxp;
    }
}