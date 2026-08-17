// https://leetcode.com/problems/regular-expression-matching/
// TODO minimize execution using DP may be tabular or recursive way
// TODO need to think how we can generalize the logic in a clean way like avoiding unnecessary if else blocks

class Solution {
    public boolean isMatch(String s, String p) {
        // try to solve it using recursion + DP (may be)
        // TODO will solve it using tabular approach

        return match(s, p, 0, 0);
    }

    // TODO need to think how we can generalize the logic in a clean way like avoiding unnecessary if else blocks

    // recursive approach
    boolean match(String S, String P, int s1, int s2) {
        int l1 = S.length(), l2 = P.length();

        if(s1 == l1 && s2 == l2) {
            return true; // matched
        }

        if(s2 == l2) {
            return false; // pattern ends but source string has something
        }

        if(s1 == l1) {
            // source string ends but pattern has something

            for(int i = s2; i < l2; i += 2) {
                if(i + 1 == l2 || P.charAt(i + 1) != '*') {
                    // if the pattern is not X* then it can't match with empty string
                    return false;
                }
            }

            // all X*, it's possible, for example empty string but all X*X*....
            return true;
        }

        // TODO need to think how we can generalize the logic in a clean way like avoiding unnecessary if else blocks
        if(s2 + 1 < l2 && P.charAt(s2 + 1) == '*') {
            // pattern matching for '*'
            if(P.charAt(s2) == '.') {
                // zero match or more match
                return match(S, P, s1, s2 + 2) | match(S, P, s1 + 1, s2);
            } else {
                if(S.charAt(s1) == P.charAt(s2)) {
                    // zero match or more match
                    return match(S, P, s1, s2 + 2) | match(S, P, s1 + 1, s2);
                } else {
                    // zero match
                    return match(S, P, s1, s2 + 2);
                }
            }
        } else if(S.charAt(s1) == P.charAt(s2) || P.charAt(s2) == '.') {
            // both matches, single dot matches any single character
            return match(S, P, s1 + 1, s2 + 1);
        } else {
            // not possible
            return false;
        }
    }
}