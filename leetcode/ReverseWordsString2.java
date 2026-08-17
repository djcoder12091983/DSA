// https://leetcode.com/problems/reverse-words-in-a-string-ii/

class Solution {

    // reverse by range
    void reverse(char s[], int l, int r) {
        while(l < r) {
            char t = s[l];
            s[l] = s[r];
            s[r] = t;

            l++;
            r--;
        }
    }

    public void reverseWords(char[] s) {
        // TODO may need to think how we can solve in O(1) by reversing entire string then
        // reverse each word and all
        /*
        String words[] = String.valueOf(s).split(" +"); // split by spaces it handles more spaces in between
        int l = words.length;
        StringBuilder ans = new StringBuilder();
        for(int i = l - 1; i > 0; i--) {
            ans.append(words[i]).append(" ");
        }
        ans.append(words[0]);

        return ans.toString().trim();
        */

        // assuming that string is spaced by single space and no leading or trailing spaces
        // now we can think of solving by reversing the entire the array then reverse each word

        int N = s.length;
        reverse(s, 0, N - 1);

        // now reverse each word seeparated by space
        int l = 0, r = 0;
        while(r < N) {
            if(s[r] == ' ') {
                // this is the word split point
                reverse(s, l, r - 1);

                // reset l and r
                // assume only one single in between
                r++;
                l = r;
            } else {
                r++;
            }
        }

        // last word reevrse
        reverse(s, l, r - 1);
    }
}