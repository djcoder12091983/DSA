// https://leetcode.com/problems/longest-word-in-dictionary-through-deleting/

class Solution {

    // dictionary for efficient search
    HashSet<String> dict = new HashSet<>();

    public String findLongestWord(String s, List<String> dictionary) {
        dict.addAll(dictionary); // add all words to dictionary

        // now we will apply DP
        HashMap<String, String> DP = new HashMap<>(); // position and prefix combined - result

        return find(s, 0, "", DP); // find recurisvely
    }

    String find(String s, int start, String prefix, HashMap<String, String> DP) {
        int N = s.length();

        if(start == N) {
            if(dict.contains(prefix)) {
                return prefix; // can be formed
            } else {
                // not possible
                return "";
            }
        }

        // now we will apply DP
        String key = prefix + "-" + start;
        if(DP.containsKey(key)) {
            return DP.get(key);
        }

        // now recursively explore options
        String ans1 = find(s, start + 1, prefix + s.charAt(start), DP); // choose current character
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