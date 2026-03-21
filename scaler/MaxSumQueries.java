public class Solution {
    
    class SegmentTreeNode {
        int prefixsum = 0, suffixsum = 0;
        int maxsum = Integer.MIN_VALUE; // maximum subarray sum
        int total = 0; // total sum
        int start, end;
        SegmentTreeNode left = null, right = null;
        
        SegmentTreeNode(int prefix, int suffix, int total, int max, int start, int end) {
            this.prefixsum = prefix;
            this.suffixsum = suffix;
            this.total = total;
            this.maxsum = max;
            this.start = start;
            this.end = end;
        }
    }
    
    // merge left right child during segment tree build
    SegmentTreeNode merge(SegmentTreeNode left, SegmentTreeNode right, int start, int end) {
        int prefix = Math.max(left.prefixsum, left.total + right.prefixsum);
        int suffix = Math.max(right.suffixsum, right.total + left.suffixsum);
        int total = left.total + right.total;
        int maxsum = Math.max(left.suffixsum + right.prefixsum, Math.max(left.maxsum, right.maxsum));
        
        SegmentTreeNode root = new SegmentTreeNode(prefix, suffix, total, maxsum, start, end);
        root.left = left;
        root.right = right;
        return root;
    }
    
    // merge during update/query
    int[] merge(int left[], int right[]) {
        if(left[3] == Integer.MIN_VALUE) {
            // NULL case handle
            return right;
        }
        if(right[3] == Integer.MIN_VALUE) {
            // NULL case handle
            return left;
        }
        int prefix = Math.max(left[0], left[2] + right[0]);
        int suffix = Math.max(right[1], right[2] + left[1]);
        int total = left[2] + right[2];
        int maxsum = Math.max(left[1] + right[0], Math.max(left[3], right[3]));
        return new int[]{prefix, suffix, total, maxsum};
    }
    
    // build segment tree
    SegmentTreeNode root = null;
    SegmentTreeNode build(ArrayList<Integer> A, int start, int end) {
        if(start == end) {
            int x = A.get(start);
            return new SegmentTreeNode(x, x, x, x, start, end);
        }
        int mid = (start + end) / 2;
        // recursive call
        SegmentTreeNode left = build(A, start, mid);
        SegmentTreeNode right = build(A, mid + 1, end);
        // merging two nodes
        return merge(left, right, start, end);
    }
    
    // update @ point
    int[] update(SegmentTreeNode node, int point, int x) {
        if(node.start == node.end && node.start == point) {
            // update point
            node.prefixsum = node.suffixsum = node.total = node.maxsum = x;
            return new int[]{x, x, x, x};
        } else if(point < node.start || point > node.end) {
            // outside the range (node change)
            return new int[]{node.prefixsum, node.suffixsum, node.total, node.maxsum};
        } else {
            // recursively change and merge
            int left[] = update(node.left, point, x);
            int right[] = update(node.right, point, x);
            int m[] = merge(left, right);
            // node update
            node.prefixsum = m[0];
            node.suffixsum = m[1];
            node.total = m[2];
            node.maxsum = m[3];
            
            return m;
        }
    }
    
    // range query
    int[] query(SegmentTreeNode node, int start, int end) {
        if(node.start >= start && node.end <= end) {
            // inside the range
            return new int[]{node.prefixsum, node.suffixsum, node.total, node.maxsum};
        } else if(start > node.end || end < node.start) {
            // outside the range
            return new int[]{0, 0, 0, Integer.MIN_VALUE};
        } else {
            // recursively call and merge
            int left[] = query(node.left, start, end);
            int right[] = query(node.right, start, end);
            return merge(left, right);
        }
    }
    
    public ArrayList<Integer> solve(ArrayList<Integer> A, ArrayList<ArrayList<Integer>> B) {
        // segment tree build
        root = build(A, 0, A.size() - 1);
        int N = B.size();
        ArrayList<Integer> ans = new ArrayList<>();
        // quries
        for(int i = 0; i < N; i++) {
            ArrayList<Integer> Q = B.get(i);
            int type = Q.get(0);
            if(type == 1) {
                // update point
                update(root, Q.get(1) - 1, Q.get(2));
            } else {
                // query
                ans.add(query(root, Q.get(1) - 1, Q.get(2) - 1)[3]);
            }
        }
        return ans;
    }
}
