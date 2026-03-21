public class Solution {
    // prefix tree node
    class PrefixNode {
        char prefix;
        Map<Character, PrefixNode> children;
        int id = -1;
        
        PrefixNode(char prefix) {
            this.prefix = prefix;
            children = new HashMap<>(2);
        }
        
        PrefixNode(char prefix, int id) {
            this(prefix);
            this.id = id;
        }
        
        PrefixNode addChild(char prefix) {
            return addChild(prefix, -1); // intermediate node
        }
        
        PrefixNode addChild(char prefix, int id) {
            PrefixNode child;
            if(children.containsKey(prefix)) {
                child = children.get(prefix);
                if(id > 0 && child.id == -1) {
                    child.id = id; // update the ID if words ends @ intermediate node
                }
            } else {
                child = new PrefixNode(prefix, id);
                children.put(prefix, child);
            }
            return child;
        }
    }
    
    PrefixNode root = new PrefixNode('R'); // root
    
    // adds word to prefix tree
    void addWord(String word, int id) {
        int l = word.length();
        PrefixNode node = root;
        for(int i = 0; i < l - 1; i++) {
            node = node.addChild(word.charAt(i));
        }
        node = node.addChild(word.charAt(l - 1), id);
    }
    
    // list of words for a given prefix
    ArrayList<Integer> prefixWords(String prefix) {
        int l = prefix.length();
        PrefixNode node = root;
        ArrayList<Integer> list = new ArrayList<Integer>();
        for(int i = 0; i < l; i++) {
            node = node.children.get(prefix.charAt(i));
            if(node == null) {
                break;
            }
        }
        if(node != null) {
            // find list of words using BFS
            Queue<PrefixNode> track = new LinkedList<>();
            track.add(node);
            while(!track.isEmpty()) {
                node = track.poll();
                if(node.id != -1) {
                    // word ID
                    list.add(node.id);
                }
                track.addAll(node.children.values());
            }
        }
        return list;
    }
    
    // check whether prefix and suffix same
    boolean prefixSuffix(String word, String prefix) {
        int l = prefix.length();
        int l1 = word.length();
        if(l1 < l) {
            return false;
        }
        boolean ok = true;
        int j = 0;
        for(int i = l1 - l; i < l1; i++) {
            if(prefix.charAt(j++) != word.charAt(i)) {
                // not possible
                ok = false;
                break;
            }
        }
        
        return ok;
    }
    
    public ArrayList<Integer> solve(ArrayList<String> words, ArrayList<String> prefixes) {
        // build prefix tree
        Map<String, Integer> counter = new HashMap<>(); // handle duplicates
        int l = words.size();
        for(int i = 0; i < l; i++) {
            String word = words.get(i);
            if(counter.containsKey(word)) {
                // counter track
                counter.put(word, counter.get(word) + 1);
            } else {
                addWord(word, i);
                counter.put(word, 1);
            }
        }
        // find prefix and suffix count
        ArrayList<Integer> count = new ArrayList<Integer>(); // prefix suffix count
        for(String prefix : prefixes) {
            ArrayList<Integer> list = prefixWords(prefix); // list of possible word with given prefix
            int c = 0;
            for(int id : list) {
                String word = words.get(id);
                // System.out.println(word);
                if(prefixSuffix(word, prefix)) {
                    // prefix suffix same
                    c += counter.get(word); // add duplicate words
                }
            }
            count.add(c);
        }
        return count;
    }
}
