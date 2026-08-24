// https://leetcode.com/problems/distinct-subsequences/
// TODO need to use 2D tabular approach

class Solution {
    public int numDistinct(String s, String t) {
        // now we will apply DP
        Map<String, Integer> DP = new HashMap<>();
        return count(s, t, 0, 0, DP);
    }

    int count(String s, String t, int idx1, int idx2, Map<String, Integer> DP) {

        int N = s.length();
        int M = t.length();

        if(idx2 == M) {
            // when index2 reaches at the end then only one way
            return 1;
        }

        if(idx1 == N) {
            // s reacehes at the end
            return 0;
        }

        String key = idx1 + "|" + idx2;
        if(DP.containsKey(key)) {
            return DP.get(key);
        }

        int c = 0;
        if(s.charAt(idx1) == t.charAt(idx2)) {
            // here if it's same
            c += count(s, t, idx1 + 1, idx2 + 1, DP);
        }
        // we can take off the current character of source even if it's same
        c += count(s, t, idx1 + 1, idx2, DP);

        DP.put(key, c);

        return c;
    }
}