https://leetcode.com/problems/minimum-number-of-operations-to-make-array-continuous/

class Solution {

    // find the floor value position for given maximum-X
    int find(int B[], int start, int x) {
        int N = B.length;
        int l = start, r = N - 1;

        int pos = -1;
        while(l <= r) {
            int mid = (l + r) / 2;
            if(B[mid] > x) {
                // left move
                r = mid - 1;
            } else {
                // right move
                pos = mid;
                l = mid + 1;
            }
        }

        return pos;
    }

    public int minOperations(int[] A) {
        // first we will find unique values so that we can work with distinct values make continuous array
        HashSet<Integer> unique = new HashSet<>();
        for(int x : A) {
            unique.add(x);
        }

        // now sort those values then we will fix the minimum value then see how many values proesent and how many not
        // then find the minimized operations
        int limit = unique.size();
        int B[] = new int[limit];
        int i = 0;
        for(int x : unique) {
            B[i++] = x;
        }
        Arrays.sort(B);

        // now fix minimum value then minimize operations based on present and missing values
        int N = A.length;
        int ops = N;
        i = 0;
        while(i < limit) {
            int min = B[i];
            // for every given min target max to make array continuous
            int max = min + N - 1;

            int pos = find(B, i, max);
            int present = pos - i + 1; // present values
            int missing = N - present; // missing values

            ops = Math.min(ops, missing);

            i++;
        }

        return ops;
    }
}