// https://leetcode.com/problems/palindrome-partitioning-ii/
// TODO TLE - FIX

class Solution {

    // palindrome check
    boolean palindrome(String s) {
        int i = 0, j = s.length() - 1;
        while(i <= j) {
            if(s.charAt(i) != s.charAt(j)) {
                return false;
            }

            i++;
            j--;
        }

        return true;
    }

    public int minCut(String s) {
        // now we will apply DP
        HashMap<Integer, Integer> DP = new HashMap<>();
        HashMap<String, Boolean> p = new HashMap<>(); // palindrome dictionary
        return count(s, 0, DP, p) - 1;
    }

    int count(String s, int idx, HashMap<Integer, Integer> DP, HashMap<String, Boolean> p) {
        int N = s.length();
        if(idx == N) {
            return 0;
        }

        if(DP.containsKey(idx)) {
            // already computed
            return DP.get(idx);
        }

        int min = N;
        for(int i = idx; i < N; i++) {
            String ss = s.substring(idx, i + 1);
            if(p.containsKey(ss) || palindrome(ss)) {
                p.put(ss, true); // store it as palindrome
                min = Math.min(min, 1 + count(s, i + 1, DP, p));
            }
        }

        DP.put(idx, min); // store for further use

        return min;
    }
}