// https://leetcode.com/problems/sum-game/description/
// TODO incomplete, seems to be MESSY, can we think of by seeing the t1 and t2 and c1 and c2 what strategy alice can take to win
// as it may get one more extra turn

class Solution {
    public boolean sumGame(String A) {
        
        int N = A.length();
        
        // calculate first half
        int t1 = 0;
        int c1 = 0;
        for(int i = 0; i < N/2; i++) {
            char x = S.charAt(i);
            if(x == '?') {
                c1++; // slots
            } else {
                t1 += x - '0';
            }
        }

        // second half
        int t2 = 0;
        int c2 = 0;
        for(int i = N/2; i < N; i++) {
            char x = S.charAt(i);
            if(x == '?') {
                c2++; // slots
            } else {
                t2 += x - '0';
            }
        }

        // now aline will try two options like one increasing left or right
        // and to make it unbalance will do do opposites on other side
        
        // left side increase and right side decrease
        int left = (c1 + 1) / 2;
        for(int i = 0; i < left; i++) {
            t1 += 9;
        }
        int alice = (c1 + c2 + 1) / 2; // total alice steps
        
        if(t1 > t2) {
            // now bob will try to fill on left side to balance
            int bob = (c1 + c2 - alice) - (c1 - left); // bob on right side
            int max = 9 * bob;
            if(t2 + max < t1) {
                // no way possible so this strategy can be alice to win
                return true;
            }
        } else {
            int bob = c1 - left; // bob on left
            int max = 9 * bob;
            if(t1 + max < t2) {
                // no way possible so this strategy can be alice to win
                return true;
            }
        }

        // now another strategy right side increase and left side decrease
        // TODO incomplete, seems to be MESSY, can we think of by seeing the t1 and t2 and c1 and c2 what strategy alice can take to win
        // as it may get one more extra turn
    }
}