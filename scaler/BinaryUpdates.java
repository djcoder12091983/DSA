public class Solution {
    class SegmentTreeNode {
        int c1 = 0;
        int start, end;
        SegmentTreeNode left = null;
        SegmentTreeNode right = null;
        
        SegmentTreeNode(int c1, int start, int end) {
            this.c1 = c1;
            this.start = start;
            this.end = end;
        }
    }
    
    // segment tree build
    SegmentTreeNode root = null;
    SegmentTreeNode build(int start, int end) {
        if(start == end) {
            // leaf
            return new SegmentTreeNode(1, start, end);
        } else {
            // recursive call to build left/right subtree
            int mid = (start + end) / 2;
            SegmentTreeNode left = build(start, mid);
            SegmentTreeNode right = build(mid + 1, end);
            SegmentTreeNode root = new SegmentTreeNode(left.c1 + right.c1, start, end);
            root.left = left;
            root.right = right;
            return root;
        }
    }
    
    // update point
    int update(SegmentTreeNode node, int point) {
        if(node.start == point && node.end == point) {
            // leaf found
            if(node.c1 == 1) {
                // reset
                node.c1 = 0;
            }
            return node.c1;
        } else if(point < node.start || point > node.end) {
            // outside the range
            return node.c1;
        } else {
            // recursive call for left/right subtree
            node.c1 = update(node.left, point) + update(node.right, point);
            return node.c1;
        }
    }
    
    // query kth 1 position
    int query(SegmentTreeNode node, int k) {
        if(node.start == node.end) {
            if(k == 1 && node.c1 == 1) {
                // base case
                return node.start;
            } else {
                // not possible
                return -1;
            }
        }
        if(node.left.c1 >= k) {
            // result exists on left side
            return query(node.left, k);
        } else {
            // look for right side with remaining
            return query(node.right, k - node.left.c1);
        }
    }
    
    public ArrayList<Integer> solve(int A, ArrayList<ArrayList<Integer>> B) {
        // build
        root = build(0, A - 1);
        // query
        int N = B.size();
        ArrayList<Integer> ans = new ArrayList<>();
        for(int i = 0; i < N; i++) {
            ArrayList<Integer> Q = B.get(i);
            int type = Q.get(0);
            if(type == 0) {
                // reset
                update(root, Q.get(1) - 1);
            } else if(type == 1) {
                // query
                int p = query(root, Q.get(1));
                if(p != -1) {
                    // index normalize
                    p++;
                }
                ans.add(p);
            }
        }
        return ans;
    }
}
