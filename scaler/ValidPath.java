public class Solution {
    
    int moves[][] = {
        {0, 1}, {1, 0}, {0, -1}, {-1, 0}
    };
    
    void dfsSolve(int X, int Y, int A, int B, boolean M[][], boolean visited[][]) {
        visited[X][Y] = true;
        for(int move[] : moves) {
            int i = X + move[0], j = Y + move[1];
            if(i >= 0 && i <= A && j >= 0 && j <= B && M[i][j] && !visited[i][j]) {
                // next DFS node
                dfsSolve(i, j, A, B, M, visited);
            }
        }
    }
    
    public String solve(int A, int B, int C, int D, ArrayList<Integer> E, ArrayList<Integer> F) {
        boolean visited[][] = new boolean[A + 1][B + 1];
        boolean M[][] = new boolean[A + 1][B + 1];
        // base cases
        for(int i = 0; i <= A; i++) {
            for(int j = 0; j <= B; j++) {
                visited[i][j] = false;
                M[i][j] = true;
            }
        }
        // draw the matrix with block cells
        for(int i = 0; i < C; i++) {
            int x = E.get(i), y = F.get(i);
            int x1 = Math.max(0, x - D), y1 = Math.max(0, y - D);
            int x2 = Math.min(A, x + D), y2 = Math.min(B, y + D);
            for(int j = x1; j <= x2; j++) {
                for(int k = y1; k <= y2; k++) {
                    // block cell if it's inside the circle
                    double t1 = j - x, t2 = k - y;
                    int d = Double.valueOf(Math.ceil(Math.sqrt(t1*t1 + t2*t2))).intValue();
                    if(d <= D) {
                        // inside the circle
                        M[j][k] = false;
                    }
                }
            }
        }
        
        if(M[0][0] && M[A][B]) {
            // source and destination not blocked
            dfsSolve(0, 0, A, B, M, visited);
            return visited[A][B] ? "YES" : "NO";
        } else {
            return "NO";
        }
    }
}