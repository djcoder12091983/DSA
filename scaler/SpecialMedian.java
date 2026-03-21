public class Solution {
    
    boolean check(ArrayList<Integer> A) {
        // note: to check median we will maintain 2 almost equal size max, min heap
        Queue<Integer> max = new PriorityQueue<>(Collections.reverseOrder());
        Queue<Integer> min = new PriorityQueue<>();
        
        int N = A.size();
        for(int i = 0; i < N; i++) {
            int a = A.get(i);
            int s1 = max.size();
            int s2 = min.size();
            // always left side heap (max heap) contains elements
            if(s1 > 0) {
                // check median
                if(s1 > s2) {
                    if(a == max.peek()) {
                        // median found
                        return true;
                    }
                } else {
                    // middle of 2
                    int s = max.peek() + min.peek();
                    if(s % 2 == 0 && a == s/2) {
                        // median found
                        return true;
                    }
                }
            }
            if(max.isEmpty()) {
                max.add(a);
            } else {
                if(s1 > s2) {
                    // balance it
                    int top = max.peek();
                     min.add(a);
                    if(top > a) {
                        // move to right side heap (min heap)
                        max.poll();
                        int top1 = min.peek();
                        min.poll();
                        max.add(top1);
                        min.add(top);
                    }
                } else {
                    int top = min.peek();
                    max.add(a);
                    if(a > top) {
                        // move to left side heap (max heap)
                        min.poll();
                        int top1 = max.peek();
                        max.poll();
                        min.add(top1);
                        max.add(top);
                    }
                }
            }
        }
        return false;
    }
    
    public int solve(ArrayList<Integer> A) {
        // left to right check
        boolean check1 = check(A);
        if(check1) {
            return 1;
        }
        // right to left
        Collections.reverse(A);
        boolean check2 = check(A);
        if(check2) {
            return 1;
        }
        return 0;
    }
}
