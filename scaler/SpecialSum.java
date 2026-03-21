public class Solution {
    // note: summation of (i - l + 1)*A[i], i moves from L to R
    // sum(i*A[i]) - (l - 1)*sum(A[i])
    
    static long MOD = 1000000007;
    
    // segment tree node
    class SegmentTreeNode {
        int start, end;
        long S1 = 0L, S2 = 0L; // S1 => sum(i*A[i]) S2 => sum(A[i])
        SegmentTreeNode left, right;
        
        SegmentTreeNode(int start, int end, long S1, long S2) {
            this.start = start;
            this.end = end;
            this.S1 = S1;
            this.S2 = S2;
        }
    }
    
    // build segment tree
    SegmentTreeNode build(ArrayList<Integer> A, int l, int r) {
        if(l == r) {
            // leaf node
            long x = A.get(l);
            return new SegmentTreeNode(l, r, (l*x) % MOD, x);
        }
        // recursively build
        int mid = (l + r) / 2;
        SegmentTreeNode left = build(A, l, mid);
        SegmentTreeNode right = build(A, mid + 1, r);
        
        // merging nodes
        long s1 = (left.S1 + right.S1) % MOD;
        long s2 = (left.S2 + right.S2) % MOD;
        SegmentTreeNode node = new SegmentTreeNode(l, r, s1, s2);
        node.left = left;
        node.right = right;
        return node;
    }
    
    // update @ point
    long[] update(SegmentTreeNode node, int idx, long X) {
        if(node.start == node.end && node.start == idx) {
            // update point
            node.S1 = (idx * X) % MOD;
            node.S2 = X;
            return new long[]{node.S1, node.S2};
        }
        if(idx < node.start || idx > node.end) {
            // outside the range
            return new long[]{node.S1, node.S2}; 
        }
        // recursively solve
        long left[] = update(node.left, idx, X);
        long right[] = update(node.right, idx, X);
        
        // merging
        long s1 = (left[0] + right[0]) % MOD;
        long s2 = (left[1] + right[1]) % MOD;
        node.S1 = s1;
        node.S2 = s2;
        return new long[]{node.S1, node.S2};
    }
    
    // range query
    long[] query(SegmentTreeNode node, int l, int r) {
        if(node.start >= l && node.end <= r) {
            // full subtree consideration
            return new long[]{node.S1, node.S2};
        } else if(node.start > r ||  node.end < l) {
            // outside the range
            return new long[]{0, 0};
        }
        // recursively solve
        long left[] = query(node.left, l, r);
        long right[] = query(node.right, l, r);
        
        // merging result
        long s1 = (left[0] + right[0]) % MOD;
        long s2 = (left[1] + right[1]) % MOD;
        return new long[]{s1, s2};
    }
    
    public ArrayList<Integer> solve(ArrayList<Integer> A, ArrayList<ArrayList<Integer>> B) {
        // build segment tree
        SegmentTreeNode root = build(A, 0, A.size() - 1);
        
        // query execution
        int N = B.size();
        ArrayList<Integer> ans = new ArrayList<>();
        for(int i = 0; i < N; i++) {
            ArrayList<Integer> Q = B.get(i);
            int t = Q.get(0);
            if(t == 1) {
                // update point
                update(root, Q.get(1) - 1, Q.get(2));
            } else {
                // query
                int l = Q.get(1) - 1, r = Q.get(2) - 1;
                // apply above mentioned formula
                long q[] = query(root, l, r);
                long a = q[0];
                long b = (l - 1) * q[1];
                if(b < 0) {
                    b %= MOD;
                    b += MOD;
                }
                long s = a - b;
                if(s < 0) {
                    s %= MOD;
                    s += MOD;
                }
                ans.add(Long.valueOf(s).intValue());
            }
        }
        
        return ans;
    }
}
