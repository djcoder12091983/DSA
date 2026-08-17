// https://leetcode.com/problems/subarrays-with-k-different-integers/

class Solution {

    // the idea behind the valid count is like track the minimum position of all the distinct elements
    // take maximum of it and count subarrays accordingly because maximum index will track the point from where
    // all elements exist
    // TODO do we need this separate method or else we can manage in the manin function efficiently
    int  count(int A[], int start, int end) {
        HashMap<Integer, List<Integer>> positions = new HashMap<>();
        int i = start;
        // position list
        int max = start;
        while(i < end) {
            int x = A[i];
            if(!positions.containsKey(x)) {
                positions.put(x, new ArrayList<>());
                max = Math.max(max, i); // maximum of all minimum index
            }
            positions.get(x).add(i);
            i++;
        }

        HashMap<Integer, Integer> index = new HashMap<>();
        // how many elements remove from window
        for(int x : positions.keySet()) {
            index.put(x, 0);
        }

        // now remove one by one from window and see how it contributes to count
        i = start;
        int c = 0;
        while(i < end) {
            c += end - max;
            int x = A[i];
            
            index.put(x, index.get(x) + 1); // index forward
            List<Integer> position = positions.get(x);
            int idx = index.get(x);
            if(idx == position.size()) {
                break;
                // now more subarray possible
            }

            if(position.get(idx) > max) {
                // update max
                max = position.get(idx);
            }

            i++;
        }

        return c;
    }

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

                    // now here is the trick how to compute the subarray count
                    ans += count(A, i, j);

                    // already k then adding new elements will increase K + 1 different elements
                    // so slide window, remove ith element from window
                    while(distinct.size() == k) {
                        distinct.put(A[i], distinct.get(A[i]) - 1);
                        if(distinct.get(A[i]) == 0) {
                            // remove 0 frequency
                            distinct.remove(A[i]);
                        }

                        i++;
                    }
                } else {
                    // safe to add
                    distinct.put(A[j], 1);

                    j++;
                }
            }
        }

        if(distinct.size() == k) {
            // last sequence
            ans += count(A, i, j);
        }

        return ans;
    }
}