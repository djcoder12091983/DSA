public class Solution {
    
    // cumulative XOR binary prefix tree
    class PrefixTreeNode {
        PrefixTreeNode zero = null, one = null;
    }
    
    // add to prefix tree
    void add(PrefixTreeNode root, int x) {
        PrefixTreeNode node = root;
        for(int i = 30; i >= 0; i--) {
            if((x & (1 << i)) == 0) {
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
    
    // maximize xor subarray (partial array)
    int maximizeXOR(PrefixTreeNode root, int x) {
        PrefixTreeNode node = root;
        int max = 0;
        for(int i = 30; i >= 0; i--) {
            if((x & (1 << i)) == 0) {
                // required one
                if(node.one != null) {
                    max += 1 << i;
                    node = node.one;
                } else {
                    node = node.zero;
                }
            } else {
                // required zero
                if(node.zero != null) {
                    max += 1 << i;
                    node = node.zero;
                } else {
                    node = node.one;
                }
            }
        }
        return max;
    }
    
    // maximize XOR for whole array
    int maximize(ArrayList<Integer> A) {
        int N = A.size();
        PrefixTreeNode root = new PrefixTreeNode();
        add(root, 0); // initial value
        int prev = 0, max = 0;
        for(int i = 0; i < N; i++) {
            // find maximum xor
            int x = prev ^ A.get(i);
            max = Math.max(max, maximizeXOR(root, x));
            add(root, x); // add to prefix tree
            prev = x;
        }
        return max;
    }
    
    public int solve(ArrayList<ArrayList<Integer>> A) {
        int r = A.size();
        int c = A.get(0).size();
        ArrayList<ArrayList<Integer>> xor = new ArrayList<>(c + 1);
        ArrayList<Integer> vector = new ArrayList<>(r);
        for(int i = 0;  i < r; i++) {
            vector.add(0);
        }
        xor.add(vector);
        // cumulative XOR
        for(int i = 0; i < c; i++) {
            vector = new ArrayList<>(r);
            for(int j = 0;  j < r; j++) {
                vector.add(xor.get(i).get(j) ^ A.get(j).get(i));
            }
            xor.add(vector);
        }
        // find maximum XOR submatrix
        int max = 0;
        for(int i = 0; i < c; i++) {
            ArrayList<Integer> prefix = xor.get(i);
            for(int j = i + 1; j <= c; j++) {
                vector = new ArrayList<>(r);
                for(int k = 0; k < r; k++) {
                    vector.add(prefix.get(k) ^ xor.get(j).get(k));
                }
                // maximize XOR for this vector
                max = Math.max(max, maximize(vector));
            }
        }
        return max;
    }
}
