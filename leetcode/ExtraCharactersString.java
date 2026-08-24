// https://leetcode.com/problems/extra-characters-in-a-string/
// TODO need to reduce execution time

class Solution {

    HashSet<String> dict = new HashSet<>();

    public int minExtraChar(String s, String[] dictionary) {
        for(String word : dictionary) {
            dict.add(word);
        }

        // now APPLY DP
        HashMap<Integer, Integer> DP = new HashMap<>();
        return count(s, 0, DP);
    }

    int count(String s, int idx, HashMap<Integer, Integer> DP) {

        int N = s.length();
        if(idx == N) {
            return 0; // 0 missing character
        }

        if(DP.containsKey(idx)) {
            // already computed
            return DP.get(idx);
        }

        int min = N;
        for(int i = idx; i < N; i++) {
            String ss = s.substring(idx, i + 1);
            if(dict.contains(ss)) {
                // choose the whole word then move on
                min = Math.min(min, count(s, i + 1, DP));
            } else {
                // will skip characters and check fo next index
                min = Math.min(min, (i - idx + 1) + count(s, i + 1, DP)); // i - idx + 1 missing character
            }
        }

        DP.put(idx, min); // stored for future use

        return min;
    }
}