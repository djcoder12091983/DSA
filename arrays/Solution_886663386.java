class Solution {
    public int numIslands(char[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int count = 0;
        int[][] visited = new int[n][m];
        for(int i = 0; i < n ; i ++) {
            for(int j = 0; j < m ; j ++) {
                if(grid[i][j] == '1' && visited[i][j] == 0) {
                    dfs(i, j, grid, n, m, visited);
                    count++;
                }
            }
        }
        return count;
    }
    void dfs(int i , int j, char[][] grid, int n, int m, int[][] visited) {
        if(visited[i][j] == 1) {
            return;
        }
        visited[i][j] = 1;
        if(i < n - 1 && grid[i + 1][j] == '1') dfs(i + 1, j, grid, n, m, visited);
        if(i > 0 && grid[i - 1][j] == '1') dfs(i - 1, j, grid, n, m, visited);
        if(j < m - 1  && grid[i][j + 1] == '1') dfs(i, j + 1, grid, n, m, visited);
        if(j > 0 && grid[i][j -1] == '1') dfs(i, j - 1, grid, n, m, visited);
    }
}