class Solution {
    
    int compute(int[][] matrix, int i, int j, int[][] dist) {
        int N = matrix.length;
        int M = matrix[0].length;
        
        if(dist[i][j] > 1) {
            // already computed
            return dist[i][j];
        }
        int directions[][] = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        int max = 1;
        for(int k = 0; k < 4; k++) {
            int nexti = i + directions[k][0];
            int nextj = j + directions[k][1];
            
            if(nexti < 0 || nexti == N || nextj < 0 || nextj == M || matrix[nexti][nextj] <= matrix[i][j]) {
                continue; // out of the boundary or invalid cell
            }
            
            max = Math.max(max, 1 + compute(matrix, nexti, nextj, dist));
        }
        
        dist[i][j] = max; // update it
        return max;
    }
    
    public int longestIncreasingPath(int[][] matrix) {
        int N = matrix.length;
        int M = matrix[0].length;
        int dist[][] = new int[N][M];
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                dist[i][j] = 1; // default distance
            }
        }
        
        int max = 1;
        // for each cell find maximum increasing sequence
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(dist[i][j] == 1) {
                    // not computed yet
                    dist[i][j] = compute(matrix, i, j, dist);
                }
                max = Math.max(max, dist[i][j]); // maximize the distance
            }
        }
        
        return max;
    }
}