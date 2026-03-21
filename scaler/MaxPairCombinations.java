public class Solution {
    
    // binary serach
    int bs(ArrayList<Integer> A, int X) {
        int l = 0, r = A.size() - 1;
        int idx = 0;
        while(l <= r) {
            int mid = (l + r) / 2;
            int a = A.get(mid);
            if(a < X) {
                // take left side
                r = mid - 1;
            } else {
                idx = mid;
                // take right
                l = mid + 1;
            }
        }
        return idx;
    }
    
    public ArrayList<Integer> solve(ArrayList<Integer> A, ArrayList<Integer> B) {
        // sort A and B
        Collections.sort(A, Collections.reverseOrder());
        Collections.sort(B, Collections.reverseOrder());
        
        Queue<Integer> min = new PriorityQueue<>();
        int N = A.size();
        int last = Integer.MAX_VALUE;
        for(int i = 0; i < N; i++) {
            int a = A.get(0) + B.get(i);
            last = a; // last min
            min.add(a);
        }
        for(int i = 1; i < N; i++) {
            int a = A.get(i);
            int X = last - a;
            int idx = bs(B, X); // required to index for B to limit scan B
            boolean ok = true;
            for(int j = 0; j <= idx; j++) {
                int b = a + B.get(j);
                int top = min.peek();
                if(b > top) {
                    // remove from heap
                    min.poll();
                    min.add(b);
                    ok = false;
                }
            }
            if(ok) {
                // heap not modified
                break;
            }
        }
        ArrayList<Integer> max = new ArrayList<>(N);
        for(int i = 0; i < N; i++) {
            // dummy fill
            max.add(0);
        }
        // reverse order fill
        for(int i = N - 1; i >= 0; i--) {
            max.set(i, min.poll());
        }
        return max;
    }
}
