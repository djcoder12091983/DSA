public class Solution {
    // prefix tree node
    class PrefixNode {
        char prefix;
        Map<Character, PrefixNode> children;
        boolean end = false;
        int count = 0;
        
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
            } else {
                child = new PrefixNode(prefix, end);
                children.put(prefix, child);
            }
            child.count++;
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
    
    // shortest unique prefix
    String shortestUniquePrefix(String word) {
        int l = word.length();
        PrefixNode node = root;
        StringBuilder prefix = new StringBuilder();
        for(int i = 0; i < l; i++) {
            char w = word.charAt(i);
            node = node.children.get(w); // always finds
            prefix.append(w);
            if(node.count == 1) {
                // shortest prefix ends
                break;
            }
        }
        return prefix.toString();
    }
    
    public ArrayList<String> prefix(ArrayList<String> words) {
        // note: prefix tree, here does not need to maintain end of word
        // build prefix tree
        for(String word : words) {
            addWord(word);
        }
        // find shortest unique prefix for each word
        ArrayList<String> prefixes = new ArrayList<>();
        for(String word : words) {
            prefixes.add(shortestUniquePrefix(word));
        }
        
        return prefixes;
    }
}
