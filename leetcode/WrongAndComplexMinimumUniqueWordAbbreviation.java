// https://leetcode.com/problems/minimum-unique-word-abbreviation/
// TODO: WRONG approach + bit COMPLEX + try brute force approach

class Solution {

    class TrieNode {
        char x;
        Map<Character, TrieNode> children = new HashMap<>();

        boolean flag = false; // whether every node contains reaming length

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

    // add to trie and last parameter is important because this indicates the length of target word
    // this length will help to update the flag of every node 
    void add(String w, TrieNode root, int len) {
        TrieNode node = root;
        int N = w.length();
        for(int i = 0; i < N; i++) {
            if(N - i == len - i) {
                // remaining length of traget word
                node.flag = true;
            }
            char x = w.charAt(i);
            node = node.add(x);
        }
    }

    // reverse string
    String reverse(String w) {
        char x[] = w.toCharArray();
        int l = 0, r = x.length - 1;
        while(l < r) {
            char t = x[l];
            x[l] = x[r];
            x[r] = t;

            l++;
            r--;
        }

        return String.valueOf(x);
    }

    // try to find abbreviate word using prefix tree
    String shortestPrefix(String target, TrieNode node) {
        // first we will look into prefix tree find shortest uncommon prefix + remaining length
        int N = target.length();
        String ans = "";
        for(int i = 0; i < N; i++) {
            if(!node.flag) {
                // shortest prefix + remaining length
                // NOTE: here we don't include that character
                break;
            }

            char x = target.charAt(i);
            if(!node.children.containsKey(x)) {
                ans += x; // when there is uncommon character then we need to include that
                // System.out.println("Ans: " + ans);
                break;
            }

            ans += x;
            node = node.children.get(x);
        }

        return ans;
    }

    public String minAbbreviation(String target, String[] dictionary) {

        int l = target.length();

        // build prefix tree from left
        TrieNode left = new TrieNode('R');
        for(String word : dictionary) {
            add(word, left, l);
        }

        String ans = target; // full length abbreviation
        
        String prefix = shortestPrefix(target, left);
        System.out.println("Left Prefix: " + prefix);
        // now try different possible abbreviations
        int t = prefix.length();
        String ab = prefix + (l - t);
        if(ab.length() < ans.length()) {
            ans = ab;
        }

        // System.out.println("Ans-1: " + ans);

        if(t > 2) {
            char ch = prefix.charAt(t - 1);
            ab = "" + prefix.charAt(0) + (t - 2) + ch + (l - t);
            // System.out.println("Next AB: " + ab);
            if(ab.length() < ans.length()) {
                ans = ab;
            }

            // further optimization like last character of prefix and we will replace
            // first and second substring by their length

            ab =  "" + (t - 1) + ch + (l - t);
            int idx = t - 1;
            boolean duplicate = false;
            for(String word : dictionary) {
                int n = word.length();
                if(idx < n && word.charAt(idx) == ch && n == l) {
                    // duplciate word is found, so this further abbreviation
                    // is not possible from left prefix tree
                    duplicate = true;
                    break;
                }
            }

            if(!duplicate) {
                if(ab.length() < ans.length()) {
                    ans = ab;
                }
            }
        }

        System.out.println("LEFT AB: " + ans);

        // build prefix tree from right
        TrieNode right = new TrieNode('R');
        for(String word : dictionary) {
            add(reverse(word), right, l);
        }

        prefix = reverse(shortestPrefix(reverse(target), right));
        // System.out.println("Right Prefix: " + prefix);
        // now try different possible abbreviations
        t = prefix.length();
        ab = prefix + (l - t);
        if(ab.length() < ans.length()) {
            ans = ab;
        }

        if(t > 2) {
            char ch = prefix.charAt(t - 1);
            ab = "" + (l - t) + prefix.charAt(0) + (t - 2) + ch;
            // System.out.println("Next AB: " + ab);
            if(ab.length() < ans.length()) {
                ans = ab;
            }

            // further optimization like last character of prefix and we will replace
            // first and second substring by their length

            ab =  "" + (l - t) + ch + (t - 1);
            int idx = l - t;
            boolean duplicate = false;
            for(String word : dictionary) {
                int n = word.length();
                if(idx < n && word.charAt(idx) == ch && n == l) {
                    // duplciate word is found, so this further abbreviation
                    // is not possible from left prefix tree
                    duplicate = true;
                    break;
                }
            }

            if(!duplicate) {
                if(ab.length() < ans.length()) {
                    ans = ab;
                }
            }
        }

        return ans;        
    }
}