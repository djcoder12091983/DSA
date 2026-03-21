public class Solution {
    static int MAX = 1000000;
    
    // prime detection using seive
    boolean primes[] = new boolean[MAX + 1];
    void findPrimes() {
        // initially all true
        for(int i = 0; i <= MAX; i++) {
            primes[i] = true;
        }
        for(int p = 2; p*p <= MAX; p++) {
            if(primes[p]) {
                // mark multiples as non prime
                // already p*p marked
                for(int i = p*p; i <= MAX; i += p) {
                    primes[i] = false;
                }
            }
        }
    }
    
    class SegmentTreeNode {
        int count;
        int start, end;
        SegmentTreeNode left = null;
        SegmentTreeNode right = null;
        
        SegmentTreeNode(int count, int start, int end) {
            this.count = count;
            this.start = start;
            this.end = end;
        }
    }
    
    // segment tree root node
    SegmentTreeNode root = null;
    
    // build segment tree
    SegmentTreeNode build(ArrayList<Integer> A, int start, int end) {
        if(start == end) {
            // leaf
            return new SegmentTreeNode(primes[A.get(start)] ? 1 : 0, start, end);
        }
        // recursively build left and right node
        int mid = (start + end) / 2;
        SegmentTreeNode left = build(A, start, mid);
        SegmentTreeNode right = build(A, mid + 1, end);
        // root node
        SegmentTreeNode root = new SegmentTreeNode(left.count + right.count, start, end);
        root.left = left;
        root.right = right;
        
        // constructed root node
        return root;
    }
    
    // update @ point
    int update(SegmentTreeNode node, int idx, int x) {
        if(node.start == idx && node.end == idx) {
            // point update
            if(primes[x]) {
                node.count = 1;
            } else {
                node.count = 0;
            }
            return node.count;
        } else if(idx < node.start || idx > node.end) {
            // outside the range
            return node.count;
        } else {
            // recursive approach
            node.count = update(node.left, idx, x) + update(node.right, idx, x);
            return node.count;
        }
    }
    
    // find primes count
    int query(SegmentTreeNode node, int start, int end) {
        // recursive approach
        if(start <= node.start && end >= node.end) {
            // full subtree consideration
            return node.count;
        } else if(end < node.start || start > node.end) {
            // outside the range
            return 0;
        } else {
            // subtree consideration recursively
            return query(node.left, start, end) + query(node.right, start, end);
        }
    }
    
    public ArrayList<Integer> solve(ArrayList<Integer> A, ArrayList<String> B, ArrayList<Integer> C, ArrayList<Integer> D) {
        // primes computation
        findPrimes();
        // build segment tree
        root = build(A, 0, A.size() - 1);
        int N = B.size();
        ArrayList<Integer> ans = new ArrayList<>();
        for(int i = 0; i < N; i++) {
            String type = B.get(i);
            if(type.equals("A")) {
                // query
                ans.add(query(root, C.get(i) - 1, D.get(i) - 1));
            } else if(type.equals("C")) {
                // update
                update(root, C.get(i) - 1, D.get(i));
            }
        }
        return ans;
    }
}
