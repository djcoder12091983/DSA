// https://leetcode.com/problems/word-abbreviation/
// TODO may need to solve using sorting instead of prefix-tree
// see INCOMPLETE version of this program

class Solution {

    class PrefixNode {
        char x;
        int c = 1; // how many words share the same prefix
        Map<Character, PrefixNode> child = new HashMap<>(2);

        PrefixNode(char x) {
            this.x = x;
        }

        PrefixNode addChild(char x) {
            if(child.containsKey(x)) {
                child.get(x).c++; // increment count
            } else {
                child.put(x, new PrefixNode(x));
            }

            return child.get(x);
        }

        PrefixNode getChild(char x) {
            return this.child.get(x);
        }
    }

    // add all words to prefix tree
    PrefixNode addAll(List<String> words) {

        PrefixNode root = new PrefixNode('R');

        for(String word : words) {

            // add individual word
            PrefixNode node = root;
            int N = word.length();
            for(int i = 0; i < N; i++) {
                char x = word.charAt(i);
                node = node.addChild(x);
            }
        }

        return root;
    }

    String abbreviate(String word, PrefixNode root) {

        PrefixNode node = root;

        int N = word.length();
        int i = 0;
        while(i < N) {
            char x = word.charAt(i);
            node = node.getChild(x);

            if(node.c == 1) {
                break;
            }

            i++;
        }

        String prefix = word.substring(0, i);
        int l1 = prefix.length();
        int l2 = N;
        String ab;
        if(l1 + 2 < l2) {
            // can be abbreviated
            ab = prefix + word.charAt(l1) + (l2 - 2 - l1) + word.charAt(l2 - 1);
            if(ab.length() == l2) {
                // keep it original
                ab = word;
            }
        } else {
            // keep it as it is
            ab = word;
        }

        return ab;
    }

    public List<String> wordsAbbreviation(List<String> words) {
        // first we will group based on first + last character and length of the string
        HashMap<String, List<String>> group = new HashMap<>();
        // TODO instead of taking position based map we could think of attaching the index with the word itself
        HashMap<String, Integer> position = new HashMap<>(); // position based map
        int N = words.size();
        for(int i = 0; i < N; i++) {
            
            String word = words.get(i);
            position.put(word, i);

            int l = word.length();
            String key = word.charAt(0) + "-" + word.charAt(l - 1) + "-" + l;

            if(!group.containsKey(key)) {
                group.put(key, new ArrayList<>());
            }

            group.get(key).add(word);
        }

        List<String> ans = new ArrayList<>();
        for(int i = 0; i < N; i++) {
            ans.add(""); // dummy value
        }

        // now for each group
        for(List<String> gwords : group.values()) {
            
            // add to prefix tree then abbreviate
            PrefixNode root = addAll(gwords);

            // abbreviate individual word
            for(String w : gwords) {
                String ab = abbreviate(w, root);
                ans.set(position.get(w), ab);
            }
        }

        return ans;
    }
}