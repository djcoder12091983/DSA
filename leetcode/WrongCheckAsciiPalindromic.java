// https://leetcode.com/problems/check-ascii-palindromic/
// TODO: need to think why this logic is not correct

class Solution {

    boolean palindrome(int ch) {
        // check charcter ascii itself is palindrome or what
        int l = 0, r = 7;
        while(l < r) {
            boolean bit1 = (ch & (1 << l)) > 0;
            boolean bit2 = (ch & (1 << r)) > 0;

            if(bit1 != bit2) {
                return false;
            }

            l++;
            r--;
        }

        return true;
    }

    public boolean isPalindromic(String s) {

        int l = 0, r = s.length() - 1;
        while(l <= r) {
            if(l < r) {
                char ch1 = s.charAt(l);
                char ch2 = s.charAt(r);
                if(ch1 != ch2 || !palindrome(ch1)) {
                    return false; // charcter is same and they are palindrome
                }
            } else {
                if(!palindrome(s.charAt(l))) {
                    return false; // middle is not palindrome
                }
            }

            l++;
            r--;
        }

        return true;
    }
}