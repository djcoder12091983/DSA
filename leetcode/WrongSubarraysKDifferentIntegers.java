// https://leetcode.com/problems/subarrays-with-k-different-integers/

class Solution {
    public int subarraysWithKDistinct(int[] A, int k) {
        int N = A.length;

        // the idea is we keep moving a window <= having diffrent K elements
        // as long as window fulfills the K distinct elements we can save out answer when it increases we will slide the window

        int i = 0, j = 0;
        int ans = 0;
        // frequency map will help accurately track elements in one window
        HashMap<Integer, Integer> distinct = new HashMap<>();
        while(j < N) {
            if(distinct.containsKey(A[j])) {
                // safe to move it's not changing the elements
                distinct.put(A[j], distinct.get(A[j]) + 1);
                j++;
            } else {
                if(distinct.size() == k) {
                    // already k then adding new elements will increase K + 1 different elements
                    // so slide window, remove ith element from window
                    distinct.put(A[i], distinct.get(A[i]) - 1);
                    if(distinct.get(A[i]) == 0) {
                        // remove 0 frequency
                        distinct.remove(A[i]);
                    }

                    i++;
                } else {
                    // safe to add
                    distinct.put(A[j], 1);

                    j++;
                }

                // check whether it's K distinct or what
                // if so then update answer
                if(distinct.size() == k) {
                    ans++;
                }
            }
        }

        return ans;
    }
}