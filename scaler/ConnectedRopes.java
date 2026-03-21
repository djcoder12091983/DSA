public class Solution {
    public int solve(ArrayList<Integer> A) {
        int N = A.size();
        if(N == 1) {
            return A.get(0);
        }
        // using min heap
        Queue<Integer> min = new PriorityQueue<>();
        for(int i = 0; i < N; i++) {
            min.add(A.get(i));
        }
        int cost = 0;
        while(min.size() > 1) {
            int newl = min.poll() + min.poll();
            cost += newl;
            min.add(newl);
        }
        
        return cost;
    }
}
