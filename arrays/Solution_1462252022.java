class Solution {
    public int[] maxSlidingWindow(int[] A, int k) {
        Deque<Integer> Q = new ArrayDeque<>(); // position DEQUE
        int N = A.length;
        Q.addLast(0);
        // maintain strictly decreasing sequence in Q
        // to avoid keeping unecessary elements
        for(int i = 1; i < k; i++) {
            if(A[i] >= A[Q.peekLast()]) {
                // remove unwanted elements, which can't be maximum in any window
                while(!Q.isEmpty() && A[i] >= A[Q.peekLast()]) {
                    Q.removeLast();
                }
            }

            Q.addLast(i);
        }
        
        int M = N - k + 1;
        int B[] = new int[M];
        int i = 0;
        B[i++] = A[Q.peekFirst()]; // first window

        int start = 0, end = k;
        // sliding window
        while(end < N) {
            if(start == Q.peekFirst()) {
                //remove it and slide window
                Q.removeFirst();
            }

            if(!Q.isEmpty() && A[end] >= A[Q.peekLast()]) {
                // remove unwanted elements, which can't be maximum in any window
                while(!Q.isEmpty() && A[end] >= A[Q.peekLast()]) {
                    Q.removeLast();
                }
            }

            Q.addLast(end);

            B[i++] = A[Q.peekFirst()]; // window answer

            // slide window
            start++;
            end++;
        }

        return B;
        
    }
}