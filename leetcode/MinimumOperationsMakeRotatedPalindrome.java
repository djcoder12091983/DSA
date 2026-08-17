class Solution {

    // left rorate by x times
    String rotate(String s, int x) {
        int N = s.length();
        char r[] = new char[N];
        for(int i = 0; i < N; i++) {
            r[(i - x + N) % N] = s.charAt(i);
        }

        return String.valueOf(r);
    }

    public int minOperations(String s) {
        int N = s.length();

        if(N <= 1) {
            return 0;
        }

        int min = operate(s, 0, N - 1); // without rotation
        for(int i = 1; i < N; i++) {
            // rotate and check for minimization
            min = Math.min(min, i + operate(rotate(s, i), 0, N - 1));
        }

        return min;
    }

    int operate(String s, int l, int r) {

        if(l > r) {
            // already a palindrome
            return 0;
        }

        // we will explore change option
        char x = s.charAt(l);
        char y = s.charAt(r);
        int diff = Math.min((x - y + 26) % 26, (y - x + 26) % 26);
        return diff + operate(s, l + 1, r - 1);
    }
}