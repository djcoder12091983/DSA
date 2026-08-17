class Solution {
    public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
        // sort based on capitals so that it will be easy to fill tasks based on upper limit of capital
        int N = profits.length;
        int A[][] = new int[N][2];
        for(int i = 0;i < N; i++) {
            A[i][0] = profits[i];
            A[i][1] = capital[i];
        }

        Arrays.sort(A, (x, y) -> x[1] - y[1]); // capital based sorting

        PriorityQueue<Integer> Q = new PriorityQueue<>(Collections.reverseOrder()); // max HEAP
        int i = 0;
        // iterate over all the possible profits
        while(i < N && k > 0) {
            // copy the profits values in descending order till current capital w    
            while(i < N && A[i][1] <= w) {
                Q.add(A[i][0]);
                i++;
            }

            if(Q.isEmpty()) {
                // no suitable task available
                break;
            }

            // greedily choose only one task to increase the capital for time being
            // so that we can choose maximum profits next time 
            if(!Q.isEmpty() && k > 0) {
                w += Q.poll();
                k--;
            }
        }

        // we have obtained all tasks and still has some k tasks to choose
        while(!Q.isEmpty() && k > 0) {
            w += Q.poll();
            k--;
        }

        return w;
    }
}