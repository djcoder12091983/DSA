class Solution {
    public boolean isAdditiveNumber(String A) {
        return sequence(A, -1, -1, 0, 0);
    }

    boolean sequence(String A, long a, long b, int idx, int c) {
        int N = A.length();
        if(idx == N) {
            return c >= 3; // valid sequence
        }

        for(int i = idx + 1; i <= Math.min(idx + 17, N); i++) {
            String s = A.substring(idx, i);
            if(s.length() >= 2 && s.charAt(0) == '0') {
                // no leading 0's supported
                continue;
            }
            long t = Long.parseLong(s);
            boolean ans = false;
            if(a == -1) {
                ans = sequence(A, t, b, i, c + 1); // set a
            } else if(b == -1) {
                ans = sequence(A, a, t, i, c + 1); // set b
            } else {
                long sum = a + b;
                //System.out.println(a + " " + b + " " + t);
                if(sum == t) {
                    // now a and b set
                    ans = sequence(A, b, t, i, c + 1);
                }
            }
            

            if(ans == true) {
                return true;
            }
        }

        return false;
    }
}