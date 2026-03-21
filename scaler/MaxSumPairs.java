public class Solution {
    // prefix tree node
    class PrefixTreeNode {
        char x;
        int v = 0; // cumulative value
        Map<Character, PrefixTreeNode> children = new HashMap<>(2);
        
        PrefixTreeNode(char x) {
            this.x = x;
        }
        
        PrefixTreeNode add(char x) {
            if(!children.containsKey(x)) {
                // add new
                children.put(x, new PrefixTreeNode(x));
            }
            return children.get(x);
        }
        
        PrefixTreeNode find(char x) {
            if(!children.containsKey(x)) {
                return null; // not found
            }
            return children.get(x);
        }
    }
    
    // track which exists in prefix tree or not
    Map<String, Integer> hash = new HashMap<>();
    // root node
    PrefixTreeNode root = new PrefixTreeNode('R');
    
    // add key-value
    void add(String key, int v) {
        int sub = 0, add = v;
        if(hash.containsKey(key)) {
            // remove value from each node in visiting path in case of existing key
            sub = hash.get(key);
        }
        hash.put(key, v);
        // add to prefix tree
        int l = key.length();
        PrefixTreeNode node = root;
        for(int i = 0; i < l; i++) {
            char x = key.charAt(i);
            PrefixTreeNode cnode = node.add(x);
            cnode.v -= sub; // remove value in case of existing key
            cnode.v += add; // add new value
            node = cnode; // next node to visit
        }
    }
    
    int query(String prefix) {
        int l = prefix.length();
        PrefixTreeNode node = root;
        for(int i = 0; i < l; i++) {
            char x = prefix.charAt(i);
            node = node.find(x);
            if(node == null) {
                // prefix not found
                return 0;
            }
        }
        return node.v; // returns cumulative sum on last node
    }
    
    public ArrayList<Integer> solve(ArrayList<String> A, ArrayList<Integer> B) {
        ArrayList<Integer> ans = new ArrayList<>();
        int Q = B.size();
        for(int i = 0; i < Q; i++) {
            String k = A.get(i);
            int v = B.get(i);
            // query execute
            if(v == -1) {
                // query
                ans.add(query(k));
            } else {
                // add key-value pair
                add(k, v);
            }
        }
        return ans;
    }
}
