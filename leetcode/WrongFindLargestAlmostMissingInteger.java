// https://leetcode.com/problems/find-the-largest-almost-missing-integer/description/
// TODO : approach is WRONG also even it was correct then it considred as messsy we can think of brute force
// considering each element contributions in subarray and hashmap track of frequency of subarray occurence
// TODO this problem misses one edge case when k = 1 and you have find one maximum which occurs only one
// so instead of handling lot of edge cases rather we can track every elemeny subarray frequency

class Solution {
    public int largestInteger(int[] A, int k) {
        int N = A.length;
        
        if(k > N) {
            // not possible
            return -1;
        }

        if(k == N) {
            // all will have only subarray contribution
            int max = -1;
            for(int i = 0; i < N; i++) {
                max = Math.max(max, A[i]);
            }

            return max;
        }

        // first and last is the only element which can have only one subarray contribution
        // other than first and last all others can occur in multiple subarrays
        int first = A[0], last = A[N - 1];
        if(first == last) {
            //not possible because fast and last occur in multiple subarrays
            return -1;
        }

        // now check whether first or last occur how many times
        int c1 = 0, c2 = 0;
        for(int i = 0; i < N; i++) {
            if(A[i] == first) {
                c1++;
            } else if(A[i] == last) {
                c2++;
            }
        }

        if(c1 == 1 && c2 == 1) {
            // both are occuring one time then we can take the maximum one
            return Math.max(first, last);
        } else if(c1 == 1) {
            // only first is occuring 1 time
            return first;
        } else if(c2 == 1) {
            // second one
            return last;
        } else {
            // both are occuring more than once
            return -1; // not possible
        }

    }
}