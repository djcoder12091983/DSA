// https://leetcode.com/problems/rotate-array/


// wrong approach
class Solution {
    public void rotate(int[] A, int k) {
        int N = A.length;
        k = k % N;

        int t[] = new int[k];
        for(int i = N - k; i < N; i++) {
            t[i - N + k] = A[i];
        }

        for(int i = 0; i < N - k; i++) {
            A[i + k] = A[i];
        }

        for(int i = 0; i < k; i++) {
            A[i] = t[i];
        }
    }
}

// not sure one TC gets TLE
class Solution {

    // reverse
    void reverse(int A[], int l, int r) {
        while(l < r) {
            // swap two points
            int t = A[l];
            A[l] = A[r];
            A[r] = t;

            l++;
            r--;
        }
    }

    public void rotate(int[] A, int k) {

        int N = A.length;
        
        // reverse entire array
        reverse(A, 0, N - 1);

        k = k % N;
        // first K elements reverse
        reverse(A, 0, k - 1);

        // second half reverse
        reverse(A, k, N - 1);
    }
}

// not sure why again one TC gets TLE
class Solution {
    public void rotate(int[] A, int k) {
        int N = A.length;
        int B[] = new int[N];

        k = k % N;

        int j = 0;
        for(int i = N - k; i < N; i++) {
            B[j++] = A[i];
        }

        for(int i = 0; i < N - k; i++) {
            B[j++] = A[i];
        }

        // now copy back
        for(int i = 0; i < N; i++) {
            A[i] = B[i];
        }
    }
}

// again not sure why even with native call gets TLE
class Solution {
    public void rotate(int[] A, int k) {
        int N = A.length;
        k = k % N;

        if(k == 0) {
            return; // if it's 0 do nothing
        }

        int B[] = new int[N];
        // copy last K elements and move to first
        System.arraycopy(A, N - k, B, 0, k);
        // first N - K elements move to last
        System.arraycopy(A, 0, B, k, N - k);

        // now copy back
        System.arraycopy(B, 0, A, 0, N);
    }
}