// https://leetcode.com/problems/3sum/

class Solution {
    public List<List<Integer>> threeSum(int[] A) {
        // we will sort the array then for every index we will look at right side only where we will apply 2P approach to pair
        // note: we will not look for left side to avoid duplicates

        int N = A.length;
        Arrays.sort(A);
        List<List<Integer>> ans = new ArrayList<>();
        int i = 0;
        while(i < N - 2) {
            int x = A[i];
            int y = -x;

            // now apply 2P to find -x on right side
            int p1 = i + 1, p2 = N - 1;
            while(p1 < p2) {
                int s = A[p1] + A[p2];
                if(s < y) {
                    // towards right it sum will increase as next elements are greater
                    p1++;
                } else if(s > y) {
                    // towards left it sum will decrease as from right direction elements are decreasing
                    p2--;
                } else {
                    // match found
                    List<Integer> row = new ArrayList<>(3);
                    row.add(x);
                    row.add(A[p1]);
                    row.add(A[p2]);
                    
                    ans.add(row);

                    // to avoid duplicates we need move pointers to next unique elements
                    int t = A[p1];
                    while(p1 < N && A[p1] == t) {
                        p1++;
                    }

                    t = A[p2];
                    while(p2 >= 0 && A[p2] == t) {
                        p2--;
                    }
                }
            }

            // to avoid duplicates we need to move next unique along i index
            int t = A[i];
            while(i < N && A[i] == t) {
                i++;
            }
        }

        return ans;
    }
}