public class Solution {
    class SegmentTreeNode {
        int min;
        int start, end;
        SegmentTreeNode left = null;
        SegmentTreeNode right = null;
        
        SegmentTreeNode(int min, int start, int end) {
            this.min = min;
            this.start = start;
            this.end = end;
        }
    }
    
    // segment tree root node
    SegmentTreeNode root = null;
    
    // build segment tree
    SegmentTreeNode build(int A[], int start, int end) {
        if(start == end) {
            // leaf
            return new SegmentTreeNode(A[start], start, end);
        }
        // recursively build left and right node
        int mid = (start + end) / 2;
        SegmentTreeNode left = build(A, start, mid);
        SegmentTreeNode right = build(A, mid + 1, end);
        // root node
        SegmentTreeNode root = new SegmentTreeNode(Math.min(left.min, right.min), start, end);
        root.left = left;
        root.right = right;
        
        // constructed root node
        return root;
    }
    
    // update @ point
    int update(SegmentTreeNode node, int idx, int x) {
        if(node.start == idx && node.end == idx) {
            // point update
            node.min = x;
            return node.min;
        } else if(idx < node.start || idx > node.end) {
            // outside the range
            return node.min;
        } else {
            // recursive approach
            node.min = Math.min(update(node.left, idx, x), update(node.right, idx, x));
            return node.min;
        }
    }
    
    // find RMQ
    int query(SegmentTreeNode node, int start, int end) {
        // recursive approach
        if(start <= node.start && end >= node.end) {
            // full subtree consideration
            return node.min;
        } else if(end < node.start || start > node.end) {
            // outside the range
            return Integer.MAX_VALUE;
        } else {
            // subtree cosideration recursively
            return Math.min(query(node.left, start, end), query(node.right, start, end));
        }
    }
    
    public int[] solve(int[] A, int[][] Q) {
        int N = A.length;
        // build segment tree
        root = build(A, 0, N - 1);
        
        // query execute
        List<Integer> ans = new ArrayList<>();
        N = Q.length;
        for(int i = 0; i < N; i++) {
            int q[] = Q[i];
            int type = q[0];
            if(type == 1) {
                // query
                ans.add(query(root, q[1] - 1, q[2] - 1));
            } else if(type == 0) {
                // update
                update(root, q[1] - 1, q[2]);
            }
        }
        // list to array conversion
        N = ans.size();
        int ansa[] = new int[N];
        for(int i = 0; i < N; i++) {
            ansa[i] = ans.get(i);
        }
        return ansa;
    }
}
