// https://leetcode.com/problems/shortest-and-lexicographically-smallest-beautiful-string/
// TODO wrong-messy approach -- need to think clean

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

        if(k == 1) {
            // edge case handle
            if(s.contains("1")) {
                return "1";
            } else {
                return "";
            }
        }

        // slide window -- here k will be always > 1
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
                    // NOTE:here the trick is we will keep on slide window from left till we see exactly 2 1's
                    int c = 0;
                    while(i < j) {
                        x = s.charAt(i) - '0';
                        if(x == 1) {
                            c++;
                        }

                        if(c == 2) {
                            break;
                        }

                        i++;
                        if(c1 - c == k) {
                            String str = s.substring(i, j);
                            // System.out.println("1: " + str);
                            ans = minimize(str, ans);
                        }
                    }

                    c1--; // we have skipped exactly 1's
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