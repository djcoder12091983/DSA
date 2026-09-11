// https://leetcode.com/problems/minimum-area-rectangle-ii/

class Solution {

    long dist(int p1[], int p2[]) {
        int x = p1[0] - p2[0], y = p1[1] - p2[1];
        return 1L * x * x + 1L * y * y; // avoiding square root to eliminate floating error
    }

	// TODO ensure correctness!
	// I think we need to check for points colinearity.
	// Failed example: [2,4] [3,4] [2,2] [1,4]
    double area(int p1[], int p2[], int p3[], int p4[]) {
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