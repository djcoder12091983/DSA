// https://leetcode.com/problems/valid-word-abbreviation/
// TODO i thought of something complex, trying to come up with simpler implementation

class Solution {
    public boolean validWordAbbreviation(String A, String B) {
        // now we can split the abbreviation B into characters (ascii-code) and number
        int N = A.length();
        int x[] = new int[N];
        boolean flags[] = new boolean[N]; // this array will track which is character and which one is numeric
        int i = 0;
        int j = 0, k = 0;
        int M = B.length();

        while(k < M) {
            char ch = B.charAt(k);
            if(x >= 'a' && x <= 'z') {
                // accumulate previous digits if there is any
                if(j < k) {
                    String d = B.substring(j, k);
                    x[i] = Integer.parseInt(d);
                    flags[i] = false;
                    i++;
                }
                // character
                x[i] = ch; // stores the ascii value
                flags[i] = true;
                i++;

                j++;
            }

            k++;
        }

        if(j < k) {
            // has some numeric digit
            String d = B.substring(j, k);
            x[i++] = Integer.parseInt(d);
        }

        // char x[] = A.toCharArray(); // character sequence
        M = i; // modified abbreviation array
        int p1 = 0, p2 = 0;
        while(p1 < M) {
            int v = x[p1];
            // TODO complete the code
        }
    }
}