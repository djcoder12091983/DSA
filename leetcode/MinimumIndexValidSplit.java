// https://leetcode.com/problems/minimum-index-of-a-valid-split/

class Solution {

    public int minimumIndex(List<Integer> A) {
        // majority element from right side
        int N = A.size();

        if(N == 1) {
            return -1; // not possible for length 1
        }
        
        // majority element algorithm + frequency tracker to see whether majority element is actually majority
        HashMap<Integer, Integer> f = new HashMap<>();
        int S[] = new int[N];
        S[N - 1] = A.get(N - 1);
        f.put(A.get(N - 1), 1);

        int m = A.get(N - 1);
        int c = 1;

        for(int i = N - 2; i >= 0; i--) {
            
            int x = A.get(i);
            f.put(x, f.getOrDefault(x, 0) + 1); // frequency tracker
            
            if(x == m) {
                c++; // voting
            } else if(c == 0) {
                // new majority element
                m = x;
                c++;
            } else {
                c--; // de-voting
            }

            // now check whether m is actually majority using frequency
            if(f.get(m) > (N - i) / 2) {
                S[i] = m; // found
            } else {
                S[i] = -1;
                // not found majority element
            }
        }

        // now do the same thing from left side and whenever split point found return the minimum index
        if(A.get(0) == S[1]) {
            return 0; // index 0 found split point
        }

        // reset all
        f = new HashMap<>();
        f.put(A.get(0), 1);

        m = A.get(0);
        c = 1;

        for(int i = 1; i < N - 1; i++) {
            
            int x = A.get(i);
            f.put(x, f.getOrDefault(x, 0) + 1); // frequency tracker

            // now check every possible split point
            if(x == m) {
                c++; // voting
            } else if(c == 0) {
                // new majority element
                m = x;
                c++;
            } else {
                c--; // de-voting
            }

            // now check whether m is actually majority using frequency
            if(f.get(m) > (i + 1) / 2) {
                // found majority element
                if(m == S[i + 1]) {
                    // split point found
                    return i;
                }
            }
        }

        return -1;
    }
}