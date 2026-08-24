// TODO INCOMPLETE SOLUTION, SEEMS to be DIFFICULT to implemet so dropped it

class Solution {

    String target;
    TrieNode root = new TrieNode('R');

    String temp; // temp word subsequence which is unmatched

    // TRIE is for matching
    class TrieNode {
        char x;
        Map<Character, TrieNode> children = new HashMap<>();

        TrieNode(char x) {
            this.x = x;
        }

        // add child
        TrieNode add(char x) {
            if(children.containsKey(x)) {
                return children.get(x);
            }

            TrieNode node = new TrieNode(x);
            children.put(x, node);

            return node;
        }
    }

    // add to trie
    void add(String w, TrieNode root) {
        TrieNode node = root;
        int N = w.length();
        for(int i = 0; i < N; i++) {
            char x = w.charAt(i);
            node = node.add(x);
        }
    }

    // find in TRIE whether a positions matching or not
    boolean find(List<Integer> p, TrieNode node, int idx) {

        int N = target.length();
        if(idx == N) {
            return true; // reach the last
        } 

        if(node == null) {
            return false;
        }
        
        if(p.contains(idx)) {
            // look for particular character in that position
            return find(target, p, node.get(target.charAt(idx), idx + 1));
        } else {
            // otherwise we are free to choose any character
            for(TrieNode child : node.children) {
                if(find(target, p, child, idx + 1)) {
                    return true; // at least one match found
                }
            }

            return false; // it's not found
        }
    }

    boolean subsequenceFind(int idx, List<Integer> p, int limit) {

        int l = word.length();
        int s = p.size();

        if(s == limit) {
            // we have reached the subsequence
            if(!find(p, root, 0)) {
                // unmatched subsequence
                temp = "" + p.get(0) + target.charAt(p.get(0));
                for(int i = 1; i < s; i++) {
                    int diff = p.get(i) - p.get(i - 1) - 1;
                    if(diff > 0) {
                        temp += diff;
                    }
                    temp += target.charAt(p.get(i));
                }
                if(p.get(s - 1) < l - 1) {
                    temp += l - 1 - p.get(s - 1);
                }
                
                return true;
            } else {
                return false;
            }
        }

        if(idx == l || s > limit) {
            return false;
        }

        for(int i = idx + 1; i < limit; i++) {
            p.add(i);
            if(subsequenceFind(i, p)) {
                return true; // one unmatch found
            }
            p.removeAt(i);
        }

        return false;
    }

    // idea is like finding lowest unmatching subsequence of target word among all the same length words
    public String minAbbreviation(String target, String[] dictionary) {

        // initialization of states
        this.target = target;

        int l = target.length();
        for(String word : words) {
            if(word.length() == l) {
                add(word, root);
            }
        }

        // TODO complete it
    }
}