// https://leetcode.com/problems/longest-word-in-dictionary-through-deleting/

class Solution {

    // dictionary for efficient search
    HashSet<String> dict = new HashSet<>();

    public String findLongestWord(String s, List<String> dictionary) {
        dict.addAll(dictionary); // add all words to dictionary

        return find(s, 0, ""); // find recurisvely
    }

    String find(String s, int start, String prefix) {
        int N = s.length();

        if(start == N) {
            if(dict.contains(prefix)) {
                return prefix; // can be formed
            } else {
                // not possible
                return "";
            }
        }

        // now recursively explore options
        String ans1 = find(s, start + 1, prefix + s.charAt(start)); // choose current character
        String ans2 = find(s, start + 1, prefix); // not choose current charcter

        int l1 = ans1.length(), l2 = ans2.length();
        if(l1 > l2) {
            return ans1;
        } else if(l2 > l1) {
            return ans2;
        } else {
            // lexicographical smaller
            if(ans1.compareTo(ans2) < 0) {
                return ans1;
            } else {
                return ans2;
            }
        }
    }
}