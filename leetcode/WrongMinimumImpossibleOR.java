// https://leetcode.com/problems/minimum-impossible-or/description/
// the LOGIC is wrong but also logic is not working in the way it's thought of
// TODO may need to correct the wrong logic, even if the output will be WRONG

class Solution {
    public int minImpossibleOR(int[] A) {
        // guessing one pattern, let's try that one first
        // it's like sort the array and trying to see if number x is generated
        // by sequence of consecutive numbers less than that

        Arrays.sort(A);
        int or = 0;
        int x = 1;

        int N = A.length;
        for(int i = 0; i < N; i++) {
            if(A[i] != x && or != x) {
                // number does not exist and prefix OR does not exist as well
                return x; 
            }

            or |= A[i];
            x++;
        }

        return or + 1;
    }
}