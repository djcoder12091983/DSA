// https://leetcode.com/problems/longest-word-in-dictionary-through-deleting/

class Solution {

    // dictionary for efficient search
    // HashSet<String> dict = new HashSet<>();

    // NOTE: we will use prefix tree for prefix check, so prefix tree we can find the word as well

    class TrieNode {
        char x;
        // TODO asuming all characters are lower we can use fixed size array
        HashMap<Character, TrieNode> child = new HashMap<>(2);
        boolean end = false;

        TrieNode(char x) {
            this.x = x;
        }
    }

    // TRIE root node
    TrieNode root = new TrieNode('0');

    void addWord(String word) {
        TrieNode node = root;
        int N = word.length();
        for(int i = 0; i < N; i++) {
            char x = word.charAt(i);
            if(node.child.containsKey(x)) {
                node = node.child.get(x);
            } else {
                TrieNode next = new TrieNode(x);
                node.child.put(x, next);
                node = next;
            }
        }
        node.end = true; // end of word
    }

    // if find full word, pass boolean flag as true
    boolean findWord(String word, boolean whole) {
        TrieNode node = root;
        int N = word.length();
        for(int i = 0; i < N; i++) {
            char x = word.charAt(i);
            if(!node.child.containsKey(x)) {
                return false; // does not exist
            }

            node = node.child.get(x);
        }

        // as per whole flag it looks for prefix or full word
        if(whole) {
            return node.end;
        }

        return true; // prefix found
    }

    public String findLongestWord(String s, List<String> dictionary) {
        // dict.addAll(dictionary); // add all words to dictionary

        // add words to prefix tree
        for(String word : dictionary) {
            addWord(word);
        }

        // now we will apply DP
        HashMap<String, String> DP = new HashMap<>(); // position and prefix combined - result

        return find(s, 0, "", DP); // find recurisvely
    }

    String find(String s, int start, String prefix, HashMap<String, String> DP) {
        int N = s.length();

        if(start == N) {
            if(findWord(prefix, true)) {
                // full word search
                return prefix; // can be formed
            } else {
                // not possible
                return "";
            }
        }

        // now we will apply DP
        String key = prefix + "-" + start;
        if(DP.containsKey(key)) {
            // System.out.println("Key: " + key + " Computed");
            return DP.get(key);
        }

        // now recursively explore options
        // TODO here before making further recursive call we can check whether that repfix can lead to solution or not
        // note: we can store all prefixes in set or else we can build prefix tree
        String ans1 = "";
        String t = prefix + s.charAt(start);
        if(findWord(t, false)) {
            // if prefix exists then only make further recusive call
            ans1 = find(s, start + 1, t, DP); // choose current character
        }
        String ans2 = find(s, start + 1, prefix, DP); // not choose current charcter

        int l1 = ans1.length(), l2 = ans2.length();
        String ans = "";
        if(l1 > l2) {
            ans = ans1;
        } else if(l2 > l1) {
            ans = ans2;
        } else {
            // lexicographical smaller
            if(ans1.compareTo(ans2) < 0) {
                ans = ans1;
            } else {
                ans = ans2;
            }
        }

        DP.put(key, ans); // store the result into DP

        return ans;
    }
}