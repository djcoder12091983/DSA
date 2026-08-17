https://leetcode.com/problems/subarrays-with-k-different-integers/description/

class Solution {
    public int subarraysWithKDistinct(int[] A, int k) {
        int N = A.length;

        // the idea is we keep moving a window <= having diffrent K elements
        // as long as window fulfills the K distinct elements we can save the positions so that
        // when it reaches the window then we can keep on moving the window from left side and
        // update the answer checking the positions of all the elements using contribution technique

        int i = 0, j = 0;
        int ans = 0, max = 0;
        // position map will help accurately track elements in one window
        HashMap<Integer, LinkedList<Integer>> distinct = new HashMap<>();
        // linked list will help to remove the index from head easily and the positions are sorted
        // so when we remove the elements we can identify the minimum window for contribution technique
        while(j < N) {
            if(distinct.containsKey(A[j])) {
                // safe to move it's not changing the elements
                distinct.get(A[j]).add(j);
                j++;
            } else {
                if(distinct.size() == k) {
                    // already size k so we need to remove from window and update answer
                    ans += j - max; // contribution array for i start index

                    // slide window and remove first index form position list for current element
                    LinkedList<Integer> positions = distinct.get(A[i]);
                    positions.removeFirst();
                    if(positions.size() == 0) {
                        distinct.remove(A[i]);
                    } else {
                        // after removing head from position list we will update the max index for next window
                        // either window size will remain same or window will sift to right
                        max = Math.max(max, positions.get(0));
                    }

                    i++;
                    
                } else {
                    // safe to add
                    LinkedList<Integer> positions = new LinkedList<>();
                    positions.add(j);

                    distinct.put(A[j], positions);

                    // here we will update the max of all minimum index in the window
                    // that point is the minimum window size for contribution
                    max = Math.max(max, j);

                    j++;
                }
            }
        }

        // last sequence
        while(distinct.size() == k) {
            ans += j - max; // contribution array for i start index

            LinkedList<Integer> positions = distinct.get(A[i]);
            positions.removeFirst();
            if(positions.size() == 0) {
                distinct.remove(A[i]);
            } else {
                // after removing head from position list we will update the max index for next window
                // either window size will remain same or window will sift to right
                max = Math.max(max, positions.get(0));
            }

            i++;
        }

        return ans;
    }
}