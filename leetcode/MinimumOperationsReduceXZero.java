// https://leetcode.com/problems/minimum-operations-to-reduce-x-to-zero/

class Solution {
    public int minOperations(int[] A, int x) {
        // prefix  and suffix sum so that we can choose some elements from left and some from right
        // and see if that combination gives sum x because x - x = 0

        // TODO instead of maintaing two sum we can think of pointer movements using one sum array
        // and instead of binary search we could think of using hashmap to track sum vs index

        int N = A.length;
        int P[] = new int[N + 1];
        int S[] = new int[N + 1];

        P[0] = 0;
        for(int i = 0; i < N; i++) {
            P[i + 1] = P[i] + A[i];
        }

        S[N] = 0;
        for(int i = N - 1; i >= 0; i--) {
            S[i] = S[i + 1] + A[i];
        }

        int min = N + 1;
        for(int i = 0; i <= N; i++) {
            int s1 = P[i]; // left sum
            int req = x - s1; // required right sum

            // apply binary search as numbers are postive
            int l = 0, r = N;
            while(l <= r) {
                int mid = (l + r) / 2;
                if(S[mid] == req) {
                    // found so compute min
                    if(mid >= i) {
                        // left index should not cross right index
                        min = Math.min(min, i + N - mid);
                    }
                    break;
                } else if(S[mid] > req) {
                    // move right as sequence is decreasing from 0 to N for suffix sum
                    l = mid + 1;
                } else {
                    // move left
                    r = mid - 1;
                }
            }
        }

        return min == N + 1 ? - 1 : min; // if min is N + 1 then it's not found so return -1
    }
}