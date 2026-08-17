// https://leetcode.com/problems/maximum-area-of-two-non-overlapping-square-submatrices/

class Solution {

    int[][] area(int A[][]) {
        int N = A.length;
        int M = A[0].length;

        int B[][] = new int[N + 1][M + 1];
        Arrays.fill(B[0], 0);
        // row wise prefix sum
        for(int i = 0; i < N; i++) {
            B[i + 1][0] = 0;
            for(int j = 0; j < M; j++) {
                B[i + 1][j + 1] = B[i + 1][j] + A[i][j];
            }
        }

        // column wise prefix sum
        for(int j = 1; j <= M; j++) {
            for(int i = 1; i <= N; i++) {
                B[i][j] += B[i - 1][j];
            }
        }

        return B;
    }

    // find area
    int findArea(int B[][], int x1, int y1, int x2, int y2) {
        int a = B[x2 + 1][y2 + 1] - B[x1][y2 + 1] - B[x2 + 1][y1] + B[x1][y1];
        //System.out.println("[" + x1 + ", " + y1 + ", " + x2 + ", " + y2 + "] => " + a);

        return a;
    }

    // two non-overlapping sqaure for given size exists or not
    // for a given range
    boolean exists(int B[][], int k, int top, int left, int bottom, int right) {

        if(top > bottom || left > right) {
            return false; // not possible, invalid region
        }

        // apply sliding square window
        // TODO can we think of applying DP
        for(int i = top; i <= bottom - k + 1; i ++) {
            for(int j = left; j <= right - k + 1; j ++) {
                int x1 = i, x2 = i + k - 1;
                int y1 = j, y2 = j + k - 1;

                int a = findArea(B, x1, y1, x2, y2);
                if(a == k * k) {
                    // stop the process return true
                    return true;
                }
            }
        }

        return false;
    }

    // check wheather 
    boolean possible(int B[][], int k) {
        // fix one square then take four partition like
        // left - right and top - bottom
        // then see whether other sqaure of same exists or not
        int N = B.length - 1;
        int M = B[0].length - 1;
        for(int i = 0; i <= N - k; i++) {
            for(int j = 0; j <= M - k; j++) {
                
                int x1 = i, x2 = i + k - 1;
                int y1 = j, y2 = j + k - 1;

                //System.out.println("Fixed: [" + x1 + ", " + y1 + ", " + x2 + ", " + y2 + "]");
                
                int a = findArea(B, x1, y1, x2, y2);
                if(a == k * k) {
                    // then find another non-overlaping square in four overlapping partitions
                    int regions[][] = new int[][]{
                        {0, 0, N - 1, y1 - 1},
                        {0, y2 + 1, N - 1, M - 1},
                        {0, 0, x1 - 1, M - 1},
                        {x2 + 1, 0, N - 1, M - 1}
                    };
                    for(int region[] : regions) {
                        boolean found = exists(B, k, region[0], region[1], region[2], region[3]);
                        //System.out.println("Region: [" + region[0] + ", " + region[1] + ", " + region[2] + ", " + region[3] + "] => " + found);
                        if(found) {
                            return true; // stop the process and return true
                        }
                    }
                }
            }
        }

        return false; // not possible
    }

    public int maxArea(int[][] A) {

        int B[][] = area(A);
        
        // testing purpose
        // TODO to be removed
        /*int N = B.length;
        int M = B[0].length;

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                System.out.print(B[i][j] + " ");
            }
            System.out.println();
        }*/

        int N = A.length;
        int M = A[0].length;

        int left = 1, right = Math.max(N, M) / 2;
        // apply BS to find optimal k
        int ans = 0;
        while(left <= right) {
            int mid = (left + right) / 2;
            boolean found = possible(B, mid);
            //System.out.println("Found: " + mid + " => " + found);
            if(found) {
                // potential answer
                ans = mid * mid;
                // move right to find more
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return ans;
    }
}