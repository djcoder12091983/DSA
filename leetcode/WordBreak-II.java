// https://leetcode.com/problems/word-break-ii/

class Solution {

    HashSet<String> dict = new HashSet<>();

    public List<String> wordBreak(String s, List<String> wordDict) {
        dict.addAll(wordDict); // create dictionary with hashing

        List<String> ans = new ArrayList<>(); // answer
        find(s, 0, "", ans);

        return ans;
    }

    void find(String s, int idx, String t, List<String> ans) {
        int N = s.length();
        if(idx == N) {
            ans.add(t.substring(1));
            return;
        }

        // now break
        for(int i = idx; i < N; i++) {
            String ss = s.substring(idx, i + 1);
            if(dict.contains(ss)) {
                find(s, i + 1, t + " " + ss, ans);
            }
        }
    }

}