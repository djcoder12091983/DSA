public class Solution {
    static long MOD = 1000000007;
    
    class SegmentTreeNode {
        long sum;
        int start, end;
        SegmentTreeNode left = null;
        SegmentTreeNode right = null;
        
        SegmentTreeNode(long sum, int start, int end) {
            this.sum = sum;
            this.start = start;
            this.end = end;
        }
    }
    
    // segment tree tracks sum
    SegmentTreeNode T1 = null;
    // segment tree tracks how many elements cleared
    SegmentTreeNode T2 = null;
    
    // build segment tree
    SegmentTreeNode build(ArrayList<Integer> A, int start, int end) {
        if(start == end) {
            // leaf
            return new SegmentTreeNode(A.get(start), start, end);
        }
        // recursively build left and right node
        int mid = (start + end) / 2;
        SegmentTreeNode left = build(A, start, mid);
        SegmentTreeNode right = build(A, mid + 1, end);
        // root node
        SegmentTreeNode root = new SegmentTreeNode(left.sum + right.sum, start, end);
        root.left = left;
        root.right = right;
        
        // constructed root node
        return root;
    }
    
    // update @ point
    long update(SegmentTreeNode node, int idx, long x) {
        if(node.start == idx && node.end == idx) {
            // point update
            node.sum = x;
            return node.sum;
        } else if(idx < node.start || idx > node.end) {
            // outside the range
            return node.sum;
        } else {
            // recursive approach
            node.sum = update(node.left, idx, x) + update(node.right, idx, x);
            return node.sum;
        }
    }
    
    // find range sum
    long query(SegmentTreeNode node, int start, int end) {
        // recursive approach
        if(start <= node.start && end >= node.end) {
            // full subtree consideration
            return node.sum;
        } else if(end < node.start || start > node.end) {
            // outside the range
            return 0;
        } else {
            // subtree consideration recursively
            return query(node.left, start, end) + query(node.right, start, end);
        }
    }
    
    // find range a for a given sum
    // it's required to find actual position from T2 after clearing some points
    long range(SegmentTreeNode node, long p) {
        if(node.left != null && node.left.sum >= p) {
            return range(node.left, p);
        } else if(node.left != null && node.right != null) {
            return (node.left.end - node.left.start + 1) + range(node.right, p - node.left.sum);
        } else {
            return node.end - node.start + 1;
        }
    }
    
    public ArrayList<Integer> solve(ArrayList<Integer> A, ArrayList<ArrayList<Integer>> B) {
        // create segment tree of N + Q each to support push_backs
        // when clear a point then it will be updated by 0 in both tree
        // when clear happens then actual position obtained using 'range' call
        // T1 maintains actual range sum and T2 tracks how many points cleared
        int N = A.size();
        int last = N;
        ArrayList<Integer> P = new ArrayList<>();
        for(int i = 0; i < N; i++) {
            P.add(1);
        }
        N = B.size();
        for(int i = 0; i < N; i++) {
            // dummy value
            A.add(0);
            P.add(0);
        }
        N = A.size();
        T1 = build(A, 0, N - 1);
        T2 = build(P, 0, N - 1);
        
        // query execution
        N = B.size();
        ArrayList<Integer> ans = new ArrayList<>();
        for(int i = 0; i < N; i++) {
            ArrayList<Integer> Q = B.get(i);
            int t = Q.get(0);
            if(t == 1) {
                // push back
                long v = Q.get(1);
                update(T1, last, v);
                update(T2, last, 1L); // positions track
                last++;
            } else if(t == 2) {
                // update point
                int x = Long.valueOf(range(T2, Q.get(1))).intValue();
                long v = Q.get(2);
                update(T1, x - 1, v);
            } else if(t == 3) {
                // clear point
                int x = Long.valueOf(range(T2, Q.get(1))).intValue(); // normalize position
                update(T1, x - 1, 0L); // clear
                update(T2, x - 1, 0L); // clear position
            } else if(t == 4) {
                // range sum query
                // normalize positions
                int x = Long.valueOf(range(T2, Q.get(1))).intValue();
                int y = Long.valueOf(range(T2, Q.get(2))).intValue();
                ans.add(Long.valueOf(query(T1, x - 1, y - 1) % MOD).intValue());
            }
        }
        
        return ans;
    }
}
