// https://leetcode.com/problems/design-neighbor-sum-service/description/

class NeighborSum {

    int grid[][];

    public NeighborSum(int[][] grid) {
        this.grid = grid;
    }

    // apply linear serach because of small dataset constraints
    int[] search(int x) {
        int N = grid.length;
        int M = grid[0].length;
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(grid[i][j] == x) {
                    return new int[]{i, j};
                }
            }
        }

        return null; // won't happen
    }

    public int adjacentSum(int value) {
        // search and check valid points
        int index[] = search(value);
        int i = index[0], j = index[1];

        int N = grid.length;
        int M = grid[0].length;

        int sum = 0;
        if(i - 1 >= 0) {
            // valid top point
            sum += grid[i - 1][j];
        }
        if(i + 1 < N) {
            // valid bottom point
            sum += grid[i + 1][j];
        }
        if(j - 1 >= 0) {
            // valid left point
            sum += grid[i][j - 1];
        }
        if(j + 1 < M) {
            // valid right point
            sum += grid[i][j + 1];
        }

        return sum;
    }
    
    public int diagonalSum(int value) {
        // search and check valid points
        int index[] = search(value);
        int i = index[0], j = index[1];

        int N = grid.length;
        int M = grid[0].length;

        int sum = 0;
        if(i - 1 >= 0 && j - 1 >= 0) {
            // valid top-left point
            sum += grid[i - 1][j - 1];
        }
        if(i + 1 < N && j + 1 < M) {
            // valid bottom-right point
            sum += grid[i + 1][j + 1];
        }
        if(j - 1 >= 0 && i + 1 < N) {
            // valid left-bottom point
            sum += grid[i + 1][j - 1];
        }
        if(j + 1 < M && i - 1 >= 0) {
            // valid right-top point
            sum += grid[i - 1][j + 1];
        }

        return sum;
    }
}

/**
 * Your NeighborSum object will be instantiated and called as such:
 * NeighborSum obj = new NeighborSum(grid);
 * int param_1 = obj.adjacentSum(value);
 * int param_2 = obj.diagonalSum(value);
 */