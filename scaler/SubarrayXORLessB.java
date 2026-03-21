public class Solution {
    static int BITS = 30;
    static long MOD = 1000000000 + 7;
    
    // prefix tree of binary representation
    class PrefixNode {
        boolean bit;
        long count = 0L;
        Map<Boolean, PrefixNode> children = new HashMap<>(2); // 0/1
        
        PrefixNode(){} // blank
        
        PrefixNode(boolean bit) {
            this.bit = bit;
        }
        
        // add child
        PrefixNode add(boolean bit) {
            PrefixNode node;
            if(children.containsKey(bit)) {
                node = children.get(bit);
            } else {
                node = new PrefixNode(bit);
                children.put(bit, node);
            }
            node.count++; // how many times visited
            return node;
        }
    }
    
    // root node
    PrefixNode root = new PrefixNode();
    
    // add x to prefix tree
    void add(int x) {
        PrefixNode node = root;
        // scan from MSB to maximize result
        for(int i = BITS; i >= 0; i--) {
            if((x & (1 << i)) != 0) {
                // set bit
                node = node.add(true);
            } else {
                // reset bit
                node = node.add(false);
            }
        }
    }
    
    // count subarrays XOR less than B
    long count(int X, int B) {
        long c = 0;
        PrefixNode node = root;
        for(int i = BITS; i >= 0; i--) {
            boolean bit = (B & (1 << i)) != 0;
            boolean xbit = (X & (1 << i)) != 0;
            if(bit) {
                // two cases can be
                if(node.children.containsKey(xbit)) {
                    // same bit, full subtree consideration
                    c += node.children.get(xbit).count;
                }
                // take a path of opposite bit for further count
                node = node.children.get(!xbit);
            } else {
                // same bit expected
                node = node.children.get(xbit);
            }
            if(node == null) {
                // no more path available
                break;
            }
        }
        return c;
    }
    
    public int solve(int[] A, int B) {
        // note: subarray XOR (cummulative XOR @ point1 xor @ point2)
        int N = A.length;
        // cummulative XOR prefix tree
        int xor[] = new int[N + 1];
        xor[0] = 0;
        add(0);
        int maxXOR = 0, minl = N, start = 1, end = N;
        long c = 0;
        for(int i = 0; i < N; i++) {
            xor[i + 1] = xor[i] ^ A[i];
            c += count(xor[i + 1], B);
            add(xor[i + 1]);
        }
        // modulo reduction
        return Long.valueOf(c % MOD).intValue();
    }
}
