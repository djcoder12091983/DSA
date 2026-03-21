public class Solution {
    // note: 2^even % 3 = 1 and 2 for odd
    class SegmentTreeNode {
        boolean bit = false; // applicable for leaf only
        int odd = 0, even = 0; // odd/even positioned set bit
        int start, end;
        SegmentTreeNode left = null;
        SegmentTreeNode right = null;
        
        SegmentTreeNode(boolean bit, int odd, int even, int start, int end) {
            this.bit = bit;
            this.odd = odd;
            this.even = even;
            this.start = start;
            this.end = end;
        }
    }
    
    // build segment tree
    SegmentTreeNode root = null;
    SegmentTreeNode build(String A, int start, int end, int limit) {
        if(start == end) {
            // leaf
            boolean bit = A.charAt(start) == '1';
            if(bit) {
                // set bit
                boolean odd = ((limit - start) & 1) != 0; // scan from right side
                return new SegmentTreeNode(bit, odd ? 1 : 0, odd ? 0 : 1, start, end);
            } else {
                return new SegmentTreeNode(bit, 0, 0, start, end);
            }
        } else {
            int mid = (start + end) / 2;
            // recursive left/right build
            SegmentTreeNode left = build(A, start, mid, limit);
            SegmentTreeNode right = build(A, mid + 1, end, limit);
            SegmentTreeNode root = new SegmentTreeNode(false, left.odd + right.odd, left.even + right.even, start, end);
            root.left = left;
            root.right = right;
            return root;
        }
    }
    
    // update point
    int[] update(SegmentTreeNode node, int idx, int limit) {
        int ans[];
        if(node.start == idx && node.end == idx) {
            // leaf
            if(!node.bit) {
                // flip
                node.bit = true;
                boolean odd = ((limit - idx) & 1) != 0;
                ans = new int[]{odd ? 1 : 0, odd ? 0 : 1};
            } else {
                // no change
                ans = new int[]{node.odd, node.even};
            }
        } else if(idx < node.start || idx > node.end) {
            // outside the range
            ans = new int[]{node.odd, node.even};
        } else {
            // recursive update
            int left[] = update(node.left, idx, limit);
            int right[] = update(node.right, idx, limit);
            ans = new int[]{left[0] + right[0], left[1] + right[1]};
        }
        node.odd = ans[0];
        node.even = ans[1];
        return ans;
    }
    
    // query
    int[] query(SegmentTreeNode node, int start, int end) {
        if(end < node.start || start > node.end) {
            // outside the range
            return new int[]{0, 0};
        } else if(node.start >= start && node.end <= end) {
            // full subtree consideration
            return new int[]{node.odd, node.even};
        } else {
            // both tree consideration
            int left[] = query(node.left, start, end);
            int right[] = query(node.right, start, end);
            return new int[]{left[0] + right[0], left[1] + right[1]};
        }
    }
    
    public ArrayList<Integer> solve(String A, ArrayList<ArrayList<Integer>> B) {
        // build
        int N = A.length();
        root = build(A, 0, N - 1, N - 1);
        int N1 = B.size();
        ArrayList<Integer> ans = new ArrayList<>(N);
        for(int i = 0; i < N1; i++) {
            ArrayList<Integer> Q = B.get(i);
            int type = Q.get(0);
            if(type == 0) {
                // query
                int start = Q.get(1);
                int end = Q.get(2);
                int[] C = query(root, start - 1, end - 1);
                if(((N - end) & 1) != 0) {
                    // flip count
                    int t = C[0];
                    C[0] = C[1];
                    C[1] = t;
                }
                int c1 = (C[0] * 2) % 3; // 2
                int c2 = C[1] % 3; // 1;
                int mod = (c1 + c2) % 3;
                ans.add(mod);
            } else if(type == 1) {
                // update
                update(root, Q.get(1) - 1, N - 1);
                ans.add(-1);
            }
        }
        return ans;
    }
}
