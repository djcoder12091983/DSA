public class Solution {
    static long MOD = 1000000007;
    
    public int nchoc(int A, ArrayList<Integer> B) {
        // using max heap (greedy approach)
        int N = B.size();
        Queue<Integer> max = new PriorityQueue<>(N, Collections.reverseOrder());
        for(int i = 0;  i< N; i++) {
            max.add(B.get(i));
        }
        long C = 0;
        for(int i = 0; i < A; i++) {
            int top = max.poll();
            C += top;
            // add half to it
            max.add(top / 2);
            if(C >= MOD) {
                C -= MOD;
            }
        }
        
        return Long.valueOf(C % MOD).intValue();
    }
}
