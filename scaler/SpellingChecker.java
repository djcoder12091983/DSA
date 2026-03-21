public class Solution {
    // note: for this simple hashing dictionary could work but to save some spaces
    // it needs to design dictionary using prefix tree
    
    // prefix tree node
    class PrefixNode {
        char prefix;
        Map<Character, PrefixNode> children;
        boolean end = false;
        
        PrefixNode(char prefix) {
            this.prefix = prefix;
            children = new HashMap<>(2);
        }
        
        PrefixNode(char prefix, boolean end) {
            this(prefix);
            this.end = end;
        }
        
        PrefixNode addChild(char prefix) {
            return addChild(prefix, false);
        }
        
        PrefixNode addChild(char prefix, boolean end) {
            PrefixNode child;
            if(children.containsKey(prefix)) {
                child = children.get(prefix);
                if(end == true && child.end == false) {
                    end = true; // word ends @ intermediate node
                }
            } else {
                child = new PrefixNode(prefix, end);
                children.put(prefix, child);
            }
            return child;
        }
    }
    
    PrefixNode root = new PrefixNode('R'); // root
    
    // adds word to prefix tree
    void addWord(String word) {
        int l = word.length();
        PrefixNode node = root;
        for(int i = 0; i < l - 1; i++) {
            node = node.addChild(word.charAt(i));
        }
        node = node.addChild(word.charAt(l - 1), true);
    }
    
    // find word
    boolean find(String word) {
        int l = word.length();
        PrefixNode node = root;
        for(int i = 0; i < l; i++) {
            node = node.children.get(word.charAt(i));
            if(node == null) {
                break;
            }
        }
        if(node == null) {
            // not possible
            return false;
        }
        return node.end;
    }
    
    public int[] solve(String[] words, String[] queries) {
        // build dictionary
        for(String word : words) {
            addWord(word);
        }
        // query
        int n = queries.length;
        int ans[] = new int[n];
        for(int i = 0; i < n; i++) {
            ans[i] = find(queries[i]) ? 1 : 0;
        }
        return ans;
    }
}
