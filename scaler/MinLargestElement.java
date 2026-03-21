public class Solution {
    class Data implements Comparable<Data> {
        int original;
        int current;
        
        Data(int original) {
            this.original = original;
            this.current = original;
        }
        
        // comparison is based on next current which is
        // current + original
        @Override
        public int compareTo(Data data) {
            return Integer.valueOf(this.original + this.current).compareTo(data.original + data.current);
        }
    }
    
    public int solve(ArrayList<Integer> A, int B) {
        // maintain min heap based on next value
        // always choose minimum one
        int max = Integer.MIN_VALUE;
        Queue<Data> min = new PriorityQueue<>();
        int N = A.size();
        for(int i = 0; i < N; i++) {
            int a = A.get(i);
            min.add(new Data(a));
            // max
            max = Math.max(max, a);
        }
        // now choose minimum and try to change
        while(--B >= 0) {
            Data top = min.poll();
            top.current = top.original + top.current; // apply change
            // new max if exists
            max = Math.max(max, top.current);
            // add to heap to choose next minimum
            min.add(top);
        }
        
        return max;
    }
}
