// https://leetcode.com/problems/valid-word-abbreviation/
// seems to be quite messy or may be lengthy
// TODO may need ot complete this code

class Solution {
    public boolean validWordAbbreviation(String A, String B) {
        int N = A.length();
        int M = B.length();

        int p1 = 0, p2 = 0;
        int i = 0, j = 0;
        while(j < M) {
            char x = A.charAt(j);
            if(x >= '0' && x <= '9') {
                String s1 = B.substring(i, j);
                if(p2 <= N) {
                    String s2 = A.substring(p1, p2);
                    if(!s1.equals(s2)) {
                        // not possible
                        return false;
                    }
                } else {
                    // not possible
                    return false;
                }

                // now extract number once digit is encountered
                int k = j;
                while(k < M) {
                    x = B.charAt(k);
                    if(x < '0' || x > '9') {
                        break;
                    }
                }

                int d = Integer.parseInt(B.substring(j, k)); // number
                // also shift p1 and p2
                if(p2 + d > N) {
                    // not possible, number skip invalid number of characters
                    return false;
                }

                // reset pointers of both string, source and abbreviation
                i = k;
                j = k;

                p2 = p2 + d;
                p1 = p2;

            } else {
                j++;
                p2++;
            }
        }

        // seems to be quite messy or may be lengthy
        // TODO may need ot complete this code
    }
}