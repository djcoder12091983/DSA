// https://leetcode.com/problems/find-the-index-of-permutation/
// solve optimally using binary index tree 

class Solution {

    static final int MOD = 1000000000 + 7;

    long[] fact(int N) {
        long f[] = new long[N + 1];
        f[0] = 1;
        for(int i = 1; i <= N; i++) {
            f[i] = (f[i - 1] * i) % MOD;
        }

        return f;
    }

    // set 1
    void set(int BIT[], int N, int index) {
        // delta (change) between the new value and the current value
        int delta = 1; // always - 1

        // Move to 1-based indexing for the BIT
        int i = index;
        while (i <= N) {
            // Add the delta to the current node
            BIT[i] += delta;
            // Move to the next responsible index (cascade up)
            i += i & (-i);
        }
    }

    // range sum from [1, index]
    int query(int BIT[], int index) {
        int sum = 0;
        int i = index;
        while (i > 0) {
            // Add current range sum
            sum += BIT[i];
            // Move to the parent node (cascade down)
            i -= i & (-i);
        }
        return sum;
    }

    public int getPermutationIndex(int[] A) {
        // first try BRUTE-FORCE approach at least then it does not work for 100000
        // then we can think of some optimization
        int N = A.length;
        long f[] = fact(N);

        // binary-index-tree
        int BIT[] = new int[N + 1];
        Arrays.fill(BIT, 0);

        long ans = 0;
        for(int i = 0; i < N; i++) {

            // how many digits are lesser than A[i] on right side
            // those many formed permutations we need skip and add to answer 
            
            // TODO here we will use BIT-Binary Index Tree
            // to find how many elements are lesser than current element on right side
            set(BIT, N, A[i]); // update BIT
            int c = A[i] - query(BIT, A[i]); // elements lesser than A[i] on right side

            ans = (ans + (c * f[N - 1 - i]) % MOD) % MOD; // contribute answer
        }

        // we have skipped all permutations now next permutation is the answer
        // ans = (ans + 1) % MOD; // This is required when rank is 1 based

        return Long.valueOf(ans).intValue();
    }
}