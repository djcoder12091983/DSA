// https://leetcode.com/problems/valid-palindrome/

class Solution {

    // check alphanumeric
    boolean accept(char x) {
        return (x >= 'a' && x <= 'z') || (x >= '0' && x <= '9');
    }

    public boolean isPalindrome(String s) {
        // trying to solve it without using extra space
        int N = s.length();
        int i = 0, j = N - 1;
        while(i < j) {
            char x = s.charAt(i);
            if(x >= 'A' && x <= 'Z') {
                x += 'a' - 'A';
            }

            char y = s.charAt(j);
            if(y >= 'A' && y <= 'Z') {
                y += 'a' - 'A';
            }

            // TODO may need to think how this can be more simpler
            if(!accept(x)) {
                // other than letters we can ignore
                i++;
            } else {
                if(!accept(y)) {
                    j--;
                } else {
                    // both are lettes so compare and pass
                    if(x != y) {
                        // mismatch so plaindrome is not possible
                        return false;
                    } else {
                        // move both pointers
                        i++;
                        j--;
                    }
                }
            }
        }

        return true; // all are matched
    }
}