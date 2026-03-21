public class Solution {
    static int BITS = 30;
    // prefix tree of binary representation
    class PrefixNode {
        boolean bit;
        int idx = -1;
        Map<Boolean, PrefixNode> children = new HashMap<>(2); // 0/1
        
        PrefixNode(){} // blank
        
        PrefixNode(boolean bit) {
            this.bit = bit;
        }
        
        // add child
        PrefixNode add(boolean bit, int idx) {
            PrefixNode node;
            if(children.containsKey(bit)) {
                node = children.get(bit);
            } else {
                node = new PrefixNode(bit);
                children.put(bit, node);
            }
            node.idx = idx;
            return node;
        }
    }
    
    // root node
    PrefixNode root = new PrefixNode();
    
    // add x to prefix tree
    void add(int x, int idx) {
        PrefixNode node = root;
        // scan from MSB to maximize result
        for(int i = BITS; i >= 0; i--) {
            // note: @ leaf set idx
            if((x & (1 << i)) != 0) {
                // set bit
                node = node.add(true, i == 0 ? idx : -1);
            } else {
                // reset bit
                node = node.add(false, i == 0 ? idx : -1);
            }
        }
    }
    
    // maximize XOR for a given number X
    // by choosing opposite bit if exists
    int[] maximizeXOR(int x) {
        PrefixNode node = root;
        int maxXOR = 0;
        // scan from MSB to maximize result
        for(int i = BITS; i >= 0; i--) {
            boolean required;
            if((x & (1 << i)) != 0) {
                // set bit, find reset bit if exists
                required = false;
            } else {
                // reset bit, find set bit if exists
                required = true;
            }
            if(node.children.containsKey(required)) {
                // found opposite bit
                node = node.children.get(required);
                maxXOR += 1 << i;
            } else {
                node = node.children.get(!required);
            }
        }
        return new int[]{maxXOR, node.idx}; // max XOR along with idx
    }
    
    public int[] solve(int[] A) {
        // note: subarray XOR (cummulative XOR @ point1 xor @ point2)
        int N = A.length;
        // cummulative XOR prefix tree
        int xor[] = new int[N + 1];
        xor[0] = 0;
        add(0, 0);
        int maxXOR = 0, minl = N, start = 1, end = N;
        for(int i = 0; i < N; i++) {
            xor[i + 1] = xor[i] ^ A[i];
            int ans[] = maximizeXOR(xor[i + 1]);
            int l = i + 1 - ans[1];
            if(ans[0] > maxXOR) {
                // max XOR
                maxXOR = ans[0];
                minl = l;
                start = ans[1] + 1;
                end = i + 1;
            } else if(ans[0] == maxXOR) {
                if(l < minl) {
                    // minimum length
                    minl = l;
                    start = ans[1] + 1;
                    end = i + 1;
                }
            }
            add(xor[i + 1], i + 1);
        }
        return new int[]{start, end};
    }
}
