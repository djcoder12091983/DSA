// https://leetcode.com/problems/longest-word-with-all-prefixes/

class Solution {
    public String longestWord(String[] W) {
        // we can easily solve using prefix tree, build prefix tree then to scan every word through prefix tree
        // and check till leaf every node should be flagged as end of the word

        // but we can think in a different way to solve like sort the string array then
        // check all prefix exists on left side only because it's sorted and to avoid repetitive prefix check or sequence
        // of prefix check, we can use counter variable associated with each word
        // if word has all prefix in the dictionary then the counter will be associated with same length

        // we will go with second approach
        Arrays.sort(W);
        // true means word has all prefixs are in dictionary
        // TODO we can think of maintaining the counter as we discussed earlier
        HashMap<String, Boolean> trackPrefix = new HashMap<>();

        int N = W.length;
        String ans = "";
        for(int i = 0; i < N; i++) {
            int M = W[i].length();
            boolean possible = false;
            if(M == 1) {
                // base case
                possible = true;
            } else {
                // maximum prefix
                String prefix = W[i].substring(0, M - 1);
                // it's possible the current word all prefixes exist in the dictionary
                possible = trackPrefix.containsKey(prefix) && trackPrefix.get(prefix);
            }

            trackPrefix.put(W[i], possible); // set the flag associated with word
            if(possible) {
                // we will maximize answer
                if(M > ans.length()) {
                    // strictly greater, otherwise for same length it's automatically
                    // track lexicographically  smaller answer
                    ans = W[i];
                }
            }
        }

        return ans;
    }
}