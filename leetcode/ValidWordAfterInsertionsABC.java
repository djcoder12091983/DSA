// https://leetcode.com/problems/check-if-word-is-valid-after-substitutions/description/

class Solution {
    public boolean isValid(String s) {
        // stack based approach whenever we will see sequence 'abc' then remove from stack
        // and then push characters into stack
        // if after doing all these if stack becomes empty then only we can say it's valid otherwise it's not

        int N = s.length();
        char[] track = new char[N]; // mimic stack using array it will be better for remove check and all
        int top = 0;
        for(int i = 0; i < N; i++) {
            track[top++] = s.charAt(i);
            if(top >= 3) {
                // check abc can be removed or not
                if(track[top - 1] == 'c' && track[top - 2] == 'b' && track[top - 3] == 'a') {
                    // update top 3 back moves
                    top -= 3;
                }
            }
        }

        return top == 0;
    }
}