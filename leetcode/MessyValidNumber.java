// https://leetcode.com/problems/valid-number/
// TODO code is quite messy can we think of make it simpler

class Solution {

    // check exponent and return next index to epxlore
    int checkExponent(String s, int idx) {
        int n = s.length();
        if(idx >= n) {
            return -1;
        }
        char x = s.charAt(idx);
        if(x >= '0' && x <= '9') {
            return idx + 1;
        }

        if(x == '-' || x == '+') {
            if(idx + 1 >= n) {
                return -1;
            }
            x = s.charAt(idx + 1);
            if(x >= '0' && x <= '9') {
                return idx + 2;
            } else {
                return -1;
            }
        } else {
            return -1;
        }
    }

    // pure digits check + exponent check
    boolean check(String s) {
        int n = s.length();
        int i = 0;
        boolean eflag = false;
        while(i < n) {
            char x = s.charAt(i);
            if(x >= '0' && x <= '9') {
                // valid digits
                i++;
                continue;
            } else if(x == 'e' || x == 'E') {
                if(eflag == true) {
                    // we have seen eflag once
                    return false;
                }
                eflag = true;
                i = checkExponent(s, i + 1); // check for exponent
                if(i == -1) {
                    // not a valid exponent
                    return false;
                }
            } else {
                // not a valid character
                return false;
            }
        }

        return true;
    }

    public boolean isNumber(String s) {
        if(s.isEmpty()) {
            return false; // not a valid anumber
        }

        char first = s.charAt(0);
        if(first == '-' || first == '+') {
            // valid
            s = s.substring(1);
        }

        // split
        String split[] = s.split("\\.", -1); // split by dot
        if(split.length > 2) {
            return false; // not a valid number, it contains more than one dot
        }

        if(split.length == 1) {
            String x = split[0];
            if(x.isEmpty()) {
                // no digits
                return false;
            }
            char y = x.charAt(0);
            if(y == 'e' || y == 'E') {
                // it should not start with e|E
                return false;
            }
            // no dot
            return check(split[0]);
        } else {
            // check integer and decimal part both are valid or not
            String left = split[0];
            String right = split[1];
            int l1 = left.length(), l2 = right.length();

            if(l1 == 0 && l2 == 0) {
                // both are empty
                return false;
            }

            boolean flag = true;
            if(l1 > 0) {
                // if it has decimal part then first half should not contain 'e' or 'E'
                if(left.contains("e") || right.contains("E")) {
                    return false;
                }
                flag = check(left);
            }

            if(flag == true && l2 > 0) {
                // if first section does not contain and digits then second section can't start with e/E
                char x = right.charAt(0);
                if(l1 == 0 && (x == 'e' || x == 'E')) {
                    return false;
                }
                flag = check(right);
            }
            
            return flag;
        }
    }
}