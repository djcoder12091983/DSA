// https://leetcode.com/problems/concatenated-words/description/

class Solution {

    HashSet<String> dict = new HashSet<>();

    // maximum words need to form the word
    int find(String s, int idx, HashMap<Integer, Integer> DP) {
        int N = s.length();
        if(idx == N) {
            return 0;
        }

        if(DP.containsKey(idx)) {
            // already computed
            return DP.get(idx);
        }

        // now break
        // note: this c is defined as -31, because of a word length can be maximum 30
        // and if any word takes path where no words found further to fill remaining characters
        // then the count don't be > 1
        int c = -31;
        for(int i = idx; i < N; i++) {
            String ss = s.substring(idx, i + 1);
            if(dict.contains(ss)) {
                c = Math.max(c, 1 + find(s, i + 1, DP));
            }
        }

        DP.put(idx, c); // store the result for future use

        return c;
    }

    // whether the word can be formed by at least two words
    boolean valid(String s) {
        HashMap<Integer, Integer> DP = new HashMap<>();

        int c = find(s, 0, DP);
        // System.out.println("Word: " + s + " " + c);
        if(c > 1) {
            return true;
        }

        return false;
    }

    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        // create dictionary with hashing
        for(String word : words) {
            dict.add(word);
        }

        List<String> ans = new ArrayList<>();
        for(String word : words) {
            if(valid(word)) {
                // part of answer
                ans.add(word);
            }
        }

        return ans;
    }
}