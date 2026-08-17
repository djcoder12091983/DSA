https://leetcode.com/problems/squares-of-a-sorted-array/

class Solution {
    public int[] sortedSquares(int[] A) {
        // negative number square will give positive which is same as positive number square
        // but the if negatives coverted into positives ithen order is ooposite as compared to positive number
        // so we can have a split point, first half negative numbers in opposite direction
        // and second half is in normal direction

        // now we can create merged sorted array then square will help maintain out
        int N = A.length;
        int i = 0;
        while(i < N && A[i] < 0) {
            i++;
        }

        int j = 0;
        while(j < i) {
            A[j] *= -1; // make negatives to positives
            j++;
        }

        // merge two sorted array
        int B[] = new int[N];
        int k = 0;
        int p1 = i - 1, p2 = i;
        while(p1 >= 0 && p2 < N) {
            if(A[p1] < A[p2]) {
                B[k++] = A[p1];
                p1--;
            } else {
                B[k++] = A[p2];
                p2++;
            }
        }

        while(p1 >= 0) {
            B[k++] = A[p1];
            p1--;
        }
        while(p2 < N) {
            B[k++] = A[p2];
            p2++;
        }

        // now we can sort sorted array B
        i = 0;
        while(i < N) {
            B[i] *= B[i];
            i++;
        }

        return B;
    }
}