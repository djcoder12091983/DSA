// https://leetcode.com/problems/minimum-area-rectangle-ii/
// TODO -- can we do better like given four points how to check whether rectangle formed or not

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

    long dist(int p1[], int p2[]) {
        int x = p1[0] - p2[0], y = p1[1] - p2[1];
        return 1L * x * x + 1L * y * y; // avoiding square root to eliminate floating error
    }

    double area(int p1[], int p2[], int p3[], int p4[]) {

        // check colinearity
        boolean flag1 = check(p1[0] - p2[0], p1[1] - p2[1], p1[0] - p3[0], p1[1] - p3[1]);
        boolean flag2 = check(p1[0] - p2[0], p1[1] - p2[1], p1[0] - p4[0], p1[1] - p4[1]);
        if(flag1 || flag2) {
            return 0; // colinear
        }

        long edges[] = new long[6];

        // all 6 edges
        edges[0] = dist(p1, p2);
        edges[1] = dist(p1, p3);
        edges[2] = dist(p1, p4);
        edges[3] = dist(p2, p3);
        edges[4] = dist(p2, p4);
        edges[5] = dist(p3, p4);

        Arrays.sort(edges);

        for(int i = 0; i < 4; i++) {
            if(edges[i] == 0) {
                // if any side is 0
                return 0;
            }
        }

        // expected first 2 edges and second 2 edges will be same
        // and follow the diagonal length rule
        if(edges[0] != edges[1] || edges[2] != edges[3] || edges[4] != edges[5]) {
            return 0;
        }

        // check diagonal rule
        if(edges[0] + edges[2] != edges[4]) {
            return 0;
        }

        // all set
        return Math.sqrt(1L * edges[0] * edges[2]);
    }

    public double minAreaFreeRect(int[][] points) {
        int N = points.length;
        double ans = 0;
        for(int p1 = 0; p1 < N; p1++) {
            for(int p2 = p1 + 1; p2 < N; p2++) {
                for(int p3 = p2 + 1; p3 < N; p3++) {
                    for(int p4 = p3 + 1; p4 < N; p4++) {
                        double x = area(points[p1], points[p2], points[p3], points[p4]); 
                        if(x > 0) {
                            if(ans == 0) {
                                // TODO we could initialize ans with some Double +Inf
                                // first time
                                ans = x;
                            } else {
                                ans = Math.min(ans, x);
                            }
                        }
                    }
                }
            }
        }

        return ans;
    }
}