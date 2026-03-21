public class Solution {
    
    class PrefixTreeNode {
        char d;
        Map<Character, PrefixTreeNode> children = new HashMap<>(2);
        
        PrefixTreeNode(char d) {
            this.d = d;
        }
        
        // add to node
        PrefixTreeNode add(char d) {
            if(!children.containsKey(d)) {
                // new node added
                PrefixTreeNode cnode = new PrefixTreeNode(d);
                children.put(d, cnode);
                return cnode;
            }
            return null; // prefix exists
        }
    }
    
    PrefixTreeNode root = new PrefixTreeNode('R'); // root node
    
    // add to dict, returns false in case of prefix exists
    // otherwise returns true
    boolean add(String no) {
        int l = no.length();
        PrefixTreeNode node = root;
        for(int i = 0; i < l; i++) {
            char d = no.charAt(i);
            PrefixTreeNode cnode = node.add(d);
            if(cnode == null) {
                return false; // can't be added, coz prefix exists
            }
            node = cnode;
        }
        return true; // sucessfully added
    }
    
    public int solve(ArrayList<String> A) {
        int N = A.size();
        for(int i = 0; i < N; i++) {
            String no = A.get(i);
            if(!add(no)) {
                // can't be added sucessfully, prefix exists
                return 0;
            }
        }
        return 1; // all added successfully
    }
}
