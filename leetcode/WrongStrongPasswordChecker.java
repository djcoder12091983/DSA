// https://leetcode.com/problems/strong-password-checker/
// TODO need to bug fix

class Solution {
    public int strongPasswordChecker(String password) {
        return operate(password, 0, 0, 0, 0, "", 0);
    }

    // idx - current index
    // lc - lower case count
    // uc - upper case count
    // dc - digit count
    // last2 - last 2 characters we have considered
    int operate(String password, int idx, int lc, int uc, int dc, String last2, int len) {
        
        int N = password.length();
        
        if(len == 20) {
            // limit reached, remove extra characters
            int extra = N - idx;
            // check if any criteria missing like lower case, upper case or digit
            extra += Math.max(1 - lc, 0) + Math.max(1 - uc, 0) + Math.max(1 - dc, 0);

            return extra;
        }

        if(idx == N) {
            // reached last character
            // check if any criteria missing like lower case, upper case or digit
            return Math.max(0, 6 - len) + Math.max(1 - lc, 0) + Math.max(1 - uc, 0) + Math.max(1 - dc, 0);
        }

        char ch = password.charAt(idx);

        // check for 3 sequence
        int M = last2.length();
        if(M == 2) {
            char ch1 = last2.charAt(0);
            char ch2 = last2.charAt(1);

            if(ch == ch1 && ch == ch2) {
                // we need remove one
                return operate(password, idx + 1, lc, uc, dc, last2, len);
            }
        }

        String newlast2 = (M > 0 ? last2.substring(1) : "") + ch; // remove last and add current one

        // now operate, we can remove and not remove
        int newlc = lc;
        int newuc = uc;
        int newdc = dc;
        if(ch >= 'a' && ch <= 'z') {
            newlc++;
        } else if(ch >= 'A' && ch <= 'Z') {
            newuc++;
        } else if(ch >= '0' && ch <= '9') {
            newdc++;
        }
        // consider current character
        int op1 = operate(password, idx + 1, newlc, newuc, newdc, newlast2, len + 1);
        // not consider current character
        int op2 = operate(password, idx + 1, lc, uc, dc, last2, len);

        return Math.min(op1, op2);
    }
}