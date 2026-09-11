// https://leetcode.com/problems/find-unique-binary-string/
// TODO -- INCOMPLETE!
// We have tried solving Prefix Tree.
// TODO Needs to complete -- The catch is when at given point both nodes are availble
// then which one we should choose, we should go with both or else we could save total number
// binary strings exist on that node so based on count we can assume that which one is safe to choose
// to find missing one

class Solution {

    class TrieNode {
        char x;
        // binary
        TrieNode [] = new TrieNode[2];

        TrieNode(char x) {
            this.x = x;
        }
    }

    void add(TrieNode root, String str) {

        TrieNode node = root;

        int N = str.length();
        for(int i = 0; i < N; i++) {
            char x = str.charAt(i);
            int idx = x - '0';
            if(node[idx] == null) {
                node[idx] = new TrieNode(x);
            }

            node = node[idx];
        }
    }

    public String findDifferentBinaryString(String[] A) {
        // either we can generatye all possible string having same length
        // then check for duplicacy

        // but if we go with prefix then then it would be easy to find unique one
        TrieNode root = new TrieNode('R');
        for(String w : A) {
            add(root, w);
        }

        // now find which one is missing
        // TODO Needs to complete -- The catch is when at given point both nodes are availble
        // then which one we should choose, we should go with both or else we could save total number
        // binary strings exist on that node so based on count we can assume that which one is safe to choose
        // to find missing one
    }
}