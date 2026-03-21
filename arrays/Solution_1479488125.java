class Solution {
    public int orangesRotting(int[][] grid) {
      int n = grid.length;
        int m = grid[0].length;
        Queue<Pair> queue = new LinkedList<>();
        //int temp = 0;
        for (int i=0; i<n; i++){
            for (int j=0; j<m; j++){
                if (grid[i][j] == 2){
                    //temp = 1;
                    queue.offer(new Pair(i,j,0));
                    //break;
                }
            }
            //if(temp==1) break;
        }
        int result =0;

        while (!queue.isEmpty()){
            Pair cur = queue.poll();
            //result = Math.max(cur.c,result);
            result = cur.c;
            if (cur.x+1<n && grid[cur.x+1][cur.y]==1){
                grid[cur.x+1][cur.y] = 2;
                queue.offer(new Pair(cur.x+1,cur.y,cur.c+1));
            }
            if (cur.x-1>=0 && grid[cur.x-1][cur.y]==1){
                grid[cur.x-1][cur.y] = 2;
                queue.offer(new Pair(cur.x-1,cur.y,cur.c+1));
            }
            if (cur.y-1>=0 && grid[cur.x][cur.y-1]==1){
                grid[cur.x][cur.y-1] = 2;
                queue.offer(new Pair(cur.x,cur.y-1,cur.c+1));
            }
            if (cur.y+1 < m && grid[cur.x][cur.y+1]==1){
                grid[cur.x][cur.y+1] = 2;
                queue.offer(new Pair(cur.x,cur.y+1,cur.c+1));
            }
        }
        for (int i=0; i<n; i++){
            for (int j=0; j<m; j++){
                if (grid[i][j] == 1){
                   return -1;
                }
            }
        }

        return result;
    }
    class Pair{
        int x;
        int y;
        int c;
        public Pair(int x, int y, int c) {
            this.x = x;
            this.y = y;
            this.c = c;
        }
    }
}