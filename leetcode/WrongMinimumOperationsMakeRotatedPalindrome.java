// https://leetcode.com/problems/minimum-operations-to-make-a-rotated-palindrome-i/

class Solution {

    // left rorate by x times
    // TODO may need to think whether actual rotation will be required or what
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
        for(int i = 1; i <= N/2; i++) {
            // 1 for each rotation and then convert characters accordingly and then work on substring
            int op = i;
            for(int j = 0; j < i; j++) {
                char x = s.charAt(j);
                char y = s.charAt(j + i);
                int diff = Math.min((x - y + 26) % 26, (y - x + 26) % 26);
                op += diff;
            }
            // if x number of characters left rotated then 2*x characters will be balaced and remaining will be worked on
            min = Math.min(min, op + operate(s, 2*i, N - 1));
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