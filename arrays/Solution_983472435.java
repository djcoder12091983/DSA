class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        int N=matrix.length;
        int M=matrix[0].length;
        ArrayList<Integer> ans= new ArrayList<>();
        int x=0;
        int y=0;
        while(N > 1 && M > 1)
        {
            for(int i=0;i<M-1;i++)
            {
               ans.add(matrix[x][y]);
               y+=1;
            }
             for(int i=0;i<N-1;i++)
            {
               ans.add(matrix[x][y]);
               x+=1;
            }
             for(int i=M-1;i>0;i--)
            {
               ans.add(matrix[x][y]);
               y-=1;
            }
             for(int i=N-1;i>0;i--)
            {
               ans.add(matrix[x][y]);
               x-=1;
            }
            x+=1;
            y+=1;
            N -= 2;
            M -= 2;
        }
        //System.out.println(x + " " + y);
        //System.out.println(ans);
        if(N > 1 && M == 1) {
            for(int i = 0; i < N; i++) {
                ans.add(matrix[x][y]);
                x++;
            }
        } else if(M > 1 && N == 1) {
            for(int i = 0; i < M; i++) {
                ans.add(matrix[x][y]);
                y++;
            }
        } else if(N == 1 && M == 1) {
            ans.add(matrix[x][y]);
        }
        return ans;
    }
}