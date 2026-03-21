class Solution {

    // 1 based indexing i and j
    int solve(String W1, String W2, int i, int j, HashMap<String, Integer> DP) {
        if(i < 0) {
            // insert all
            return j + 1;
        }
        if(j < 0) {
            // delete all
            return i + 1;
        }

        String key = i+"-"+j;
        if(DP.containsKey(key)) {
          return DP.get(key);
        }

        char x = W1.charAt(i);
        char y = W2.charAt(j);

        int minop;
        if(x == y) {
            // perfect match
            minop = solve(W1, W2, i - 1, j - 1, DP);
        } else {
          // go with 3 options
          // insert
          int op1 = 1 + solve(W1, W2, i - 1, j, DP);
          // delete
          int op2 = 1 + solve(W1, W2, i, j - 1, DP);
          // replace
          int op3 = 1 + solve(W1, W2, i - 1, j - 1, DP);

          // return minimum operations
          minop = Math.min(op1, Math.min(op2, op3));
        }
        DP.put(key, minop);

        return minop;
    }
    public int minDistance(String word1, String word2) {
        return solve(word1, word2, word1.length() - 1, word2.length() - 1, new HashMap<String, Integer>());
    }
}