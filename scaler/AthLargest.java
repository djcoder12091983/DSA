public class Solution {
    public ArrayList<Integer> solve(int A, ArrayList<Integer> B) {
        Queue<Integer> min = new PriorityQueue<>();
        int N = B.size();
        ArrayList<Integer> ans = new ArrayList<>(N);
        for(int i = 0; i < N; i++) {
            int b = B.get(i);
            if(min.size() == A) {
                // need to remove or not from min heap
                int top = min.peek();
                if(b > top) {
                    // need to remove from min heap
                    min.poll();
                    min.add(b);
                }
            } else {
                min.add(b);
            }
            if(min.size() < A) {
                ans.add(-1);
            } else {
                ans.add(min.peek());
            }
        }
        return ans;
    }
}
