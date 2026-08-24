// https://leetcode.com/problems/word-break/

class Solution {

    HashSet<String> dict = new HashSet<>();

    public boolean wordBreak(String s, List<String> wordDict) {
        dict.addAll(wordDict); // create dictionary with hashing

        // now we will apply DP
        HashMap<Integer, Boolean> DP = new HashMap<>();

        return find(s, 0, DP);
    }

    boolean find(String s, int idx, HashMap<Integer, Boolean> DP) {
        int N = s.length();
        if(idx == N) {
            return true;
        }

        if(DP.containsKey(idx)) {
            // already computed
            return DP.get(idx);
        }

        // now break
        boolean found = false;
        for(int i = idx; i < N; i++) {
            String ss = s.substring(idx, i + 1);
            if(dict.contains(ss) && find(s, i + 1, DP)) {
                found = true;
                break;
            }
        }

        DP.put(idx, found); // store the result for future use

        return found;
    }
}