// https://leetcode.com/problems/shortest-and-lexicographically-smallest-beautiful-string/
// TODO : wrong window slide -- BUG FIX

class Solution {

    // minimize answer given string
    String minimize(String str, String ans) {
        int l1 = str.length();
        int l2 = ans.length();
        if(l1 < l2) {
            ans = str;
        } else if(l1 == l2) {
            if(ans.compareTo(str) > 0) {
                // lexicographically smaller
                ans = str;
            }
        }

        return ans;
    }

    public String shortestBeautifulSubstring(String s, int k) {
        // slide window
        int N = s.length();
        int i = 0, j = 0;

        String ans = s; // assume full length string is the answer
        boolean found = false;
        int c1 = 0;

        while(j < N) {
            int x = s.charAt(j) - '0';
            if(x == 1) {
                if(c1 + 1 > k) {
                    // limit crossed - need to slide from left side
                    x = s.charAt(i) - '0';
                    i++;
                    if(x == 1) {
                        c1--;
                    } else {
                        // it's 0 then we can update out answer
                        String str = s.substring(i, j);
                        ans = minimize(str, ans);
                    }
                } else {
                    c1++;
                    if(c1 == k) {
                        // update answer
                        found = true;
                        String str = s.substring(i, j + 1);
                        ans = minimize(str, ans);
                    }
                    j++;
                }
            } else {
                j++;
            }
        }

        if(found) {
            return ans;
        } else {
            return ""; // not found - empty string
        }
    }
}