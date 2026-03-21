public class Solution {
    class Data implements Comparable<Data> {
        int val, row, col;
        
        Data(int val, int row, int col) {
            this.val = val;
            this.row = row;
            this.col = col;
        }
        
        @Override
        public int compareTo(Data data) {
            return Integer.valueOf(this.val).compareTo(data.val);
        }
    }
    
    public int solve(ArrayList<ArrayList<Integer>> A, int B) {
        // note: use the approach of merging k sorted array
        Queue<Data> min = new PriorityQueue<>();
        int N = A.size();
        int M = A.get(0).size(); // column size
        for(int i = 0; i < N; i++) {
            ArrayList<Integer> row = A.get(i);
            // put min element from each row
            min.add(new Data(row.get(0), i, 0));
        }
        // now pick top from min heap and then next elment to be pushed from selected row
        int bmin = 0;
        while(--B >= 0) {
            Data top = min.poll();
            bmin = top.val; // current top value
            int row = top.row, col = top.col + 1;
            if(col < M) {
                // next elment to be pushed from selected row, col + 1 (if available)
                min.add(new Data(A.get(row).get(col), row, col));
            }
        }
        return bmin;
    }
}
