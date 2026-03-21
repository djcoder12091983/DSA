public class Solution {
    
    class SegTreeNode {
        int count; // 1's count
        int start, end; // [start, end]
        SegTreeNode left, right;
        
        SegTreeNode(int count, int start, int end){
            this.count = count;
            this.start = start;
            this.end = end;
        }
    }
    
    // SegTreeNode root = null;
    
    // build tree
    SegTreeNode build(int start, int end) {
        if(start == end) {
            return new SegTreeNode(0, start, end);
        }
        int mid = (start + end) / 2;
        // recursive call
        SegTreeNode left = build(start, mid);
        SegTreeNode right = build(mid + 1, end);
        SegTreeNode root = new SegTreeNode(left.count + right.count, start, end);
        root.left = left;
        root.right = right;
        return root;
    }
    
    // update 1's count
    void update(SegTreeNode node, int idx, int x) {
        int start = node.start, end = node.end;
        if(idx < start || idx > end) {
            // outside the range
            return;
        }
        if(start == end) {
            // update point
            node.count = Math.max(node.count + x, 0);
            return;
        } else {
            // recursive call to update
            update(node.left, idx, x);
            update(node.right, idx, x);
            node.count = node.left.count + node.right.count;
        }
    }
    
    // count 1's for a given range
    int count(SegTreeNode node, int l, int r) {
        int start = node.start, end = node.end;
        if(l > end || r < start) {
            // outside the range
            return 0;
        }
        if(start >= l && end <= r) {
            return node.count; // ful subtree count
        } else {
            // partial, need to do recursive call
            return count(node.left, l, r) + count(node.right, l, r);
        }
    }
    
    public ArrayList<Integer> solve(int A, ArrayList<ArrayList<Integer>> B) {
        SegTreeNode root = build(0, A - 1); // tree construction
        // queries
        ArrayList<Integer> ans = new ArrayList<Integer>();
        for(ArrayList<Integer> q : B) {
            int t = q.get(0);
            int x = q.get(1);
            int y = q.get(2);
            switch(t) {
                case 1:
                    // update with +1 count (2*i + 1)
                    update(root, x - 1, 1);
                    break;
                case 2:
                    // update with -1 count (i/2)
                    update(root, x - 1, -1);
                    break;
                case 3:
                    // find
                    ans.add(count(root, x - 1, y - 1));
            }
        }
        
        return ans;
    }
}
