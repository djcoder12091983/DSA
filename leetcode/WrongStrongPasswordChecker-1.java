// https://leetcode.com/problems/strong-password-checker/
// TODO need to work ON

class Solution {
    public int strongPasswordChecker(String password) {
        return operate(password, 0, 0, 0, 0, "", 0);
    }

    // check if any criteria missing like lower case, upper case or digit
    int missingCount(int lc, int uc, int dc) {
        int c = 0;
        if(lc == 0) {
            c++;
        }
        if(uc == 0) {
            c++;
        }
        if(dc == 0) {
            c++;
        }

        return c;
    }

    // 3 characters are same or not
    boolean sequence3(String seq, char ch) {
        int M = seq.length();
        if(M == 2) {
            char ch1 = seq.charAt(0);
            char ch2 = seq.charAt(1);

            if(ch == ch1 && ch == ch2) {
                return true;
            }
        }

        return false;
    }

    // update count for replace
    int[] replaceCount(int lc, int uc, int dc) {
        if(lc == 0) {
            lc++;
        } else if(uc == 0) {
            uc++;
        } else if(dc == 0) {
            dc++;
        }

        return new int[]{lc, uc, dc};
    }

    // update count for not replace
    int[] updateCount(int lc, int uc, int dc, char ch) {
        int nc[] = new int[]{lc, uc, dc};
        if(digit(ch)) {
            nc[0]++;
        } else if(lower(ch)) {
            nc[1]++;
        } else if(upper(ch)) {
            nc[2]++;
        }

        return nc;
    }

    boolean digit(char ch) {
        return ch >= '0' && ch <= '9'; 
    }

    boolean upper(char ch) {
        return ch >= 'A' && ch <= 'Z';
    }

    boolean lower(char ch) {
        return ch >= 'a' && ch <= 'z';
    }

    // idx - current index
    // lc - lower case count
    // uc - upper case count
    // dc - digit count
    // last2 - last 2 characters we have considered
    int operate(String password, int idx, int lc, int uc, int dc, String seq, int len) {
        
        int N = password.length();

        if(len == 20) {
            // limit reached, remove extra characters
            if(missingCount(lc, uc, dc) > 0) {
                // if lower case, upper case or digit is missing
                return Integer.MAX_VALUE; // not possible
            }
            return N - idx; // removeal
        }

        if(idx == N) {
            // reached last character
            int c = missingCount(lc, uc, dc);
            int rem = Math.max(0, 6 - len);
            if(rem < c) {
                // we can't fill missing criteria from remaining fill
                return Integer.MAX_VALUE; // not possible
            }

            return rem;
        }

        boolean replace = false;

        char ch = password.charAt(idx);
        // check for 3 sequence
        if(sequence3(seq, ch)) {
            // may need to replace
            replace = true;
        }

        if((lc == 0 && !lower(ch)) || (uc == 0 && !upper(ch)) || (dc == 0 && !digit(ch))) {
            // we can replace current character with missing character
            replace = true;
        }

        // first we will try with remove and not remove
        int minOp = 1 + operate(password, idx + 1, lc, uc, dc, seq, len); // remove
        
        // replacement of character
        if(replace) {
            // note: '#' does not exist and it's ensured that we can replace with some valid character as per length
            String newseq = (seq.length() > 0 ? seq.substring(1) : "") + "#";
            int count[] = replaceCount(lc, uc, dc);
            minOp = Math.min(minOp, 1 + operate(password, idx + 1, count[0], count[1], count[2], newseq, len + 1));
        } else {
            // not remove, but not replace
            String newseq = (seq.length() > 0 ? seq.substring(1) : "") + ch;
            int count[] = updateCount(lc, uc, dc, ch);
            minOp = Math.min(minOp, operate(password, idx + 1, count[0], count[1], count[2], newseq, len + 1));
        }

        return minOp;
    }
}