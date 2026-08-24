// https://leetcode.com/problems/palindrome-partitioning/

class Solution {
    public List<List<String>> partition(String s) {
        List<List<String>> ans = new ArrayList<>();
        List<String> temp = new ArrayList<>();
        partition(s, 0, temp, ans);

        return ans;
    }

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

    void partition(String s, int idx, List<String> temp, List<List<String>> ans) {
        int N = s.length();
        if(idx == N) {
            ans.add(new ArrayList<>(temp));
            return;
        }

        for(int i = idx; i < N; i++) {
            String ss = s.substring(idx, i + 1);
            if(palindrome(ss)) {
                temp.add(ss); // pass on modified partition
                partition(s, i + 1, temp, ans);
                // backtrack
                temp.remove(temp.size() - 1); // removes last one
            }
        }
    }
}