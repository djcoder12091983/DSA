class Solution {
    public int removeDuplicates(int[] A) {
        // 2P
        int i = 0, j = 0;
        int N = A.length;

        while(j < N) {
            if(A[j] == A[i]) {
                j++;
            } else {
                int odd = j - i - 2;
                if(odd > 0) {
                    // more than 2 elements need to shift left
                    int k = j;
                    i += 2;
                    int idx = i;
                    while(k < N) {
                        A[idx++] = A[k++];
                    }
                    j = i; // reposition j
                    // update N
                    N -= odd;
                } else {
                    i = j; // valid
                }
            }
        }

        int odd = j - i - 2;
        if(odd > 0) {
            N -= odd; //update N is enough
        }

        return N;
    }
}