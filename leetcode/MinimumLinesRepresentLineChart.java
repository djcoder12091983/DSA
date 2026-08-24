// https://leetcode.com/problems/minimum-lines-to-represent-a-line-chart/description/

class Solution {

    int gcd(int x, int y) {
        return (y == 0) ? x : gcd(y, x % y);
    }

    // convert x and y difference into gradient key to be grouped
    String gradient(int xdiff, int ydiff) {

        if(xdiff == 0) {
            return "y-parallel";
        }

        if(ydiff == 0) {
            return "x-parallel";
        }

        // System.out.println("Diff: " + xdiff + " " + ydiff);

        boolean xsign = xdiff < 0;
        boolean ysign = ydiff < 0;

        boolean sign = xsign ^ ysign;
        int x = Math.abs(xdiff);
        int y = Math.abs(ydiff);
        int cf = gcd(x, y);

        return (sign ? "-" : "+") + "[" + (x/cf) + "/" + (y/cf) + "]";
    }

    public int minimumLines(int[][] A) {

        int N = A.length;
        if(N < 3) {
            return N - 1;
        }

        // sort based on day points
        // TODOL note: i think data is sorted based on day beforehand, may be sorting not required
        Arrays.sort(A, (p1, p2) -> p1[0] - p2[0]);

        // gradient of first pair
        String grad = gradient(A[0][0] - A[1][0], A[0][1] - A[1][1]);
        
        int ans = 1; // 1 line required
        int i = 2;
        while(i < N) {
            String cgrad = gradient(A[i - 1][0] - A[i][0], A[i - 1][1] - A[i][1]);
            // System.out.println("Grad: " + grad + " CGrad: " + cgrad);
            // see gradient change or what
            if(!cgrad.equals(grad)) {
                // line needed to manage gradient flactuation
                grad = cgrad;
                ans++;
            }

            i++;
        }

        return ans;
    }
}