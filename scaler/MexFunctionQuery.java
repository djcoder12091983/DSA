public class Solution {
    
    class PrefixTreeNode {
        PrefixTreeNode zero = null, one = null;
        int c = 0;
        
        // add to prefix tree node
        PrefixTreeNode add(boolean bit) {
            if(bit) {
                // one
                if(one == null) {
                    one = new PrefixTreeNode();
                }
                one.c++;
                return one;
            } else {
                // zero
                if(zero == null) {
                    zero = new PrefixTreeNode();
                }
                zero.c++;
                return zero;
            }
        }
    }
    
    PrefixTreeNode root = new PrefixTreeNode(); // root node
    
    // add to prefix tree
    void add(int x) {
        PrefixTreeNode node = root;
        node.c++;
        for(int i = 29; i >= 0; i--) {
            if((x & (1 << i)) == 0) {
                // zero
                node = node.add(false);
            } else {
                // one
                node = node.add(true);
            }
        }
    }
    
    // find mex
    int findMEX(int x) {
        PrefixTreeNode node = root;
        int h = 30; // height
        int i = 30;
        int mex = 0;
        while(h > 0) {
            h--;
            i--;
            boolean zero = (x & (1 << i)) == 0;
            int total = 1 << h; // expected count
            if(zero) {
                // no bit flip
                if(node.zero == null) {
                    // mex found
                    break;
                } else if(node.zero.c == total) {
                    // explore right side (one)
                    mex += total; // full subtree consideration
                    if(node.one != null) {
                        node = node.one;
                    } else {
                        // no option
                        break;
                    }
                } else {
                    // explore left side (zero)
                    node = node.zero;
                }
            } else {
                // bit flip
                if(node.one == null) {
                    // mex found
                    break;
                } else if(node.one.c == total) {
                    // explore zero
                    mex += total; // full subtree consideration
                    if(node.zero != null) {
                        node = node.zero;
                    } else {
                        // no option
                        break;
                    }
                } else {
                    // explore one
                    node = node.one;
                }
            }
        }
        return mex;
    }
    
    // maintain a prefix tree of original array elements with bits
    // note: cumulative XOR bit is one then flips all the bits @ that level
    public ArrayList<Integer> solve(ArrayList<Integer> A, ArrayList<Integer> B) {
        int N = A.size();
        // prefix tree of original array elements with bits
        Set<Integer> unique = new HashSet<>();
        for(int i = 0; i < N; i++) {
            int a = A.get(i);
            if(unique.add(a)) {
                // add unique elemement
                add(a);
            }
        }
        // mex query
        N = B.size();
        ArrayList<Integer> mex = new ArrayList<>(N);
        int xor = 0;
        for(int i = 0; i < N; i++) {
            xor ^= B.get(i); // cumulative xor
            mex.add(findMEX(xor));
        }
        return mex;
    }
}
