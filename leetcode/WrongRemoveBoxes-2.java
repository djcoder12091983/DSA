// failing for this test case
// [1,2,2,1,1,1,2,2,1,2,1,1,2,2,1,1,2,2,1,2,2,2,2,1]

class Solution {
    public int removeBoxes(int[] B) {
        
        return solve(B, 0, B.length - 1);
    }

    int solve(int B[], int start, int end) {
        if(start > end) {
            return 0;
        }

        HashSet<Integer> unique = new HashSet<>();
        // we will try to fix one elements and try to solve other partitions
        for(int i = start; i <= end; i++) {
            unique.add(B[i]);
        }

        int maxscore = 0;
        for(int x : unique) {

            int i = start, j = start;
            int score = 0, c = 0;
            while(j <= end) {
                if(B[j] == x) {
                    // take a partition and recursively solve
                    score += solve(B, i, j - 1);

                    // put i and j to next element other than x
                    int k = j;
                    while(k <= end && B[k] == x) {
                        k++;
                        c++;
                    }
                    i = k;
                    j = k;
                } else {
                    j++;
                }
            }

            // last sequence if any
            // TODO need to check
            score += solve(B, i, j - 1);

            // compute final score after remove all elements other than x
            score += c * c;
            maxscore = Math.max(maxscore, score);
        }

        return maxscore;
    }
}