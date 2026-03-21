class Solution {
    public int kthSmallest(int[][] A, int k) {

        PriorityQueue<Integer[]> PQ = new PriorityQueue<>(new Comparator<Integer[]>(){

            @Override
            public int compare(Integer[] a, Integer[] b) {
                return a[0] - b[0];
            }
        });

        int n = A.length;
        for(int i = 0;i < n; i++) {
            PQ.add(new Integer[]{A[i][0], i, 0});
        }

        int ans = -1;
        while(k > 0) {

            Integer[] data = PQ.poll();
            ans = data[0];
            int row = data[1];
            int col = data[2];

            if(col < n - 1) {
                PQ.add(new Integer[]{A[row][col + 1], row, col + 1});
            }

            k--;
        }

        return ans;
        
    }
}