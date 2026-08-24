// https://leetcode.com/problems/valid-word-abbreviation/

class Solution {
    public boolean validWordAbbreviation(String A, String B) {
        int N = B.length();
        int M = A.length();

        int i = 0, j = 0;
        StringBuilder dummy = new StringBuilder();
        while(j < N) {
            char x = B.charAt(j);
            if(x >= '0' && x <= '9') {
                // now extract number once digit is encountered
                int k = j;
                while(k < N) {
                    x = B.charAt(k);
                    if(x < '0' || x > '9') {
                        break;
                    }

                    k++;
                }

                String t = B.substring(j, k);
                if(t.charAt(0) == '0') {
                    // the length can't have leading 0's
                    return false;
                }
                int d = Integer.parseInt(t); // number
                if(d > M) {
                    // if length itself > length of original string
                    return false;
                }

                // reset pointers
                i = k;
                j = k;

                // now copy 'd' number of some dummy character like 'X' which does not exist
                for(int p = 0; p < d; p++) {
                    dummy.append('X');
                }
            } else {
                dummy.append(x);
                j++;
            }
        }

        // System.out.println(A + " " + dummy);

        // now compare with original string and see other than dummy characters all other matched
        N = A.length();
        M = dummy.length();
        if(N != M) {
            return false; // not possible
        }

        i = 0;
        while(i < N) {
            char x = A.charAt(i);
            char y = dummy.charAt(i);

            if(y == 'X') {
                i++;
                continue; // ignore because it can be replaced by any character
            }

            if(x != y) {
                // other than dummy charcter if there is a mismatch
                // then it's not possible
                return false;
            }

            i++;
        }

        return true; // all matched
    }
}