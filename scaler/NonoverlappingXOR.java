public class Solution {
    // build prefix tree of binary sum
    class PrefixTreeNode {
        PrefixTreeNode zero = null, one = null;
        
        PrefixTreeNode(){
            // blank
        }
    }
    
    PrefixTreeNode root = new PrefixTreeNode();
    
    // add to prefix tree
    void add(int sum) {
        PrefixTreeNode node = root;
        for(int i = 30; i >= 0; i--) {
            if((sum & (1 << i)) == 0) {
                // zero
                if(node.zero == null) {
                    node.zero = new PrefixTreeNode();
                }
                node = node.zero;
            } else {
                // one
                if(node.one == null) {
                    node.one = new PrefixTreeNode();
                }
                node = node.one;
            }
        }
    }
    
    // max XOR
    int maximize(int sum) {
        PrefixTreeNode node = root;
        int xor = 0;
        for(int i = 30; i >= 0; i--) {
            if((sum & (1 << i)) == 0) {
                // zero, find one bit
                if(node.one != null) {
                    node = node.one;
                    xor += 1 << i;
                } else {
                    // no option
                    node = node.zero;
                }
            } else {
                // one, find zero bit
                if(node.zero != null) {
                    node = node.zero;
                    xor += 1 << i;
                } else {
                    // no option
                    node = node.one;
                }
            }
        }
        return xor;
    }
    
    public int solve(ArrayList<Integer> A) {
        // prefix sum
        int N = A.size();
        int prefix[] = new int[N];
        prefix[0] = A.get(0);
        for(int i = 1; i < N; i++) {
            prefix[i] = prefix[i - 1] + A.get(i);
        }
        // now form pair
        // keep on building prefix sum tree from the left to right
        add(prefix[0]);
        int max = Integer.MIN_VALUE;
        for(int i = 1; i < N; i++) {
            // all possible subarry check to maximize prefix sum
            for(int j = i; j < N; j++) {
                int s = prefix[j] - prefix[i - 1];
                // try to get maximize XOR
                max = Math.max(max, maximize(s));
            }
            add(prefix[i]); // keep on adding
        }
        return max;
    }
}
