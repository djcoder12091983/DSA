// 	https://leetcode.com/problems/remove-boxes/

class Solution {
    public int removeBoxes(int[] B) {
        // we can select  sequence then compute for left ad right 
        // not the trick is that if after removing a sequence and left and right sequence share the same element
        // then we need to take into consideration
        // for example ...1112211...after removing 2 sequence we are left with 1 sequence but they belong to different call
        // so we need to merge the result like if X count exists on left and Y count exists on right count
        // then X square and Y sqaure we already computed then to merge (X+Y) square we just need to add 2XY

        int N = B.length;
        return solve(B, 0, N - 1);
    }

    // compute score
    int compute(int B[], int left, int right, int start, int i) {
        int c = i - start;
        int score = solve(B, left, start - 1) + c * c + solve(B, i, right);
        // tricky part merging if left ends with and right starts with same element
        int j = start - 1, k = i;
        if(j >= left && k <= right && B[j] == B[k]) {
            // need to merge if they share the same element at end of left and start of right part
            int x = B[j];
            j--;
            k++;

            // now count on left side
            while(j >= left && B[j] == x) {
                j--;
            }

            while(k <= right && B[k] == x) {
                k++;
            }

            score += 2 * (start - 1 - j) * (k - i);
        }

        return score;  // merging part it has been explained above
    }

    int solve(int B[], int left, int right) {
        if(left > right) {
            // they cross each other
            return 0;
        }

        int prev = -1;
        int start = -1;
        int i = left;
        int maxscore = 0;
        while(i <= right) {

            if(prev != B[i]) {
                if(start != -1) {
                    // valid sequence so make recursive call to compute
                    int score = compute(B, left, right, start, i);
                    maxscore = Math.max(maxscore, score);
                }

                // reset values for next sequence
                start = i;
                prev = B[i];
            }

            i++;
        }

        // last sequence
         if(start != -1) {
            // valid sequence so make recursive call to compute
            int score = compute(B, left, right, start, i);
            maxscore = Math.max(maxscore, score);
        }

         return maxscore;
    }
}