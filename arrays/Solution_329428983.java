class Solution {
    class Point {
        int x, y;
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
        
        @Override
        public int hashCode() {
            int h = 31;
            h += h * 17 + x;
            h += h * 17 + y;
            return h;
        }
        
        @Override
        public boolean equals(Object o) {
            Point p = (Point)o;
            return p.x == this.x && p.y == this.y;
        }
    }
    
    // note: we will use DFS based DP
    Map<Point, Integer> cache = new HashMap<>();
    int rows, cols;
    int grid[][];
    
    // recursive call for DFS
    int recursivePathSum(Point p) {
        if(p.x == rows - 1 && p.y == cols - 1) {
            return grid[p.x][p.y]; // goal reached
        }
        if(cache.containsKey(p)) {
            // already computed optimally
            return cache.get(p);
        }
        int op1 = Integer.MAX_VALUE, op2 = Integer.MAX_VALUE;
        if(p.y + 1 < cols) {
            Point t = new Point(p.x, p.y + 1);
            op1 = recursivePathSum(t);
            cache.put(t, op1);
        }
        if(p.x + 1 < rows) {
            Point t = new Point(p.x + 1, p.y);
            op2 = recursivePathSum(t);
            cache.put(t, op2);
        }
        int tcost = grid[p.x][p.y] + Math.min(op1, op2);
        cache.put(p, tcost);
        return tcost;
    }
    
    public int minPathSum(int[][] grid) {
        rows = grid.length;
        if(rows > 0) {
            cols = grid[0].length;
        } else {
            cols = 0;
        }
        if(rows == 0 && cols == 0) {
            // no path exists
            return 0;
        }
        this.grid = grid;
        // recusive DFS based DP
        return recursivePathSum(new Point(0, 0)); // starts with 0,0
    }
}