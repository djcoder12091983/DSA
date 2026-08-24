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
			// TODO - Note: THINK again why this 2P movements fail
			// THINK in detailed way again
            int p1 = i + 1, p2 = i + 2;
            while(p1 < p2 && p2 < N) {
                int s = A[p1] + A[p2];
                if(s < y) {
                    p2++;
                } else if(s > y) {
                    p1++;
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
                    while(p2 < N && A[p2] == t) {
                        p2++;
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