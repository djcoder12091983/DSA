public class Solution {
    // prefix tree node
    class PrefixNode {
        char prefix;
        Map<Character, PrefixNode> children;
        int count = 0;
        
        PrefixNode(char prefix) {
            this.prefix = prefix;
            children = new HashMap<>(2);
        }
        
        PrefixNode addChild(char prefix) {
            PrefixNode child;
            if(children.containsKey(prefix)) {
                child = children.get(prefix);
            } else {
                child = new PrefixNode(prefix);
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
        for(int i = 0; i < l; i++) {
            node = node.addChild(word.charAt(i));
        }
    }
    
    // count prefix
    int countPrefix(String prefix) {
        int l = prefix.length();
        PrefixNode node = root;
        for(int i = 0; i < l; i++) {
            node = node.children.get(prefix.charAt(i));
            if(node == null) {
                break;
            }
        }
        if(node == null) {
            return 0;
        }
        return node.count;
    }
    
    public ArrayList<Integer> solve(ArrayList<Integer> types, ArrayList<String> inputs) {
        int n = types.size();
        ArrayList<Integer> count = new ArrayList<Integer>();
        for(int i = 0; i < n; i++) {
            int type = types.get(i);
            String input = inputs.get(i);
            if(type == 0) {
                // add to prefix tree
                addWord(input);
            } else if(type == 1) {
                // query
                count.add(countPrefix(input));
            }
        }
        return count;
    }
}
