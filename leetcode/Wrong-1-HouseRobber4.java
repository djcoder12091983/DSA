class Solution {

    // add index and decide whether it can be chosen for rob
    boolean select(TreeMap<Integer, Boolean> idxtrack, int idx) {
        // if left index does not exist or if exists then if it's marked as false then
        // potentially we can choose current index 
        boolean left = !idxtrack.containsKey(idx - 1) || !idxtrack.get(idx - 1);
        // same for right
        boolean right = !idxtrack.containsKey(idx + 1) || !idxtrack.get(idx + 1);
        if(left && right) {
            // both are free so we can choose
            idxtrack.put(idx, true);
        } else {
            // can't be chosen
            idxtrack.put(idx, false);
        }

        return idxtrack.get(idx);
    }

    public int minCapability(int[] R, int k) {
        // the idea is to start scanning the result from kth smallest value
        // whenever we will see x >= kth smallest value statisfies a rob sequence like no adjacent values exist
        // TODO either we can can sort then check or else we can use priority queue in case k is quite smalller

        int N = R.length;
        int A[][] = new int[N][2];
        for(int i = 0; i < N; i++) {
            A[i][0] = R[i];
            A[i][1] = i; // index
        }

        // sort based on value and need to track index which helps to find non-adjacency rob sequence
        Arrays.sort(A, (x, y) -> x[0] - y[0]);
        int adjacent = 1;
        int i = 0;
        // trick is to find whether rob sequence is non adjacent
        // treemap will help to find if any new index previous and next index is marked as free
        // then current index we can choose
        TreeMap<Integer, Boolean> idxtrack = new TreeMap<>();
        int robs = 0;
        while(i < k - 1) {
            int idx = A[i][1];
            
            if(select(idxtrack, idx)) {
                robs++;
            }

            i++;
        }

        // find the first smallest value which statisfies non adjacent rob sequence
        while(i < N) {

            int idx = A[i][1];
            
            if(select(idxtrack, idx)) {
                robs++;
            }

            if(robs == k) {
                // we found best possible minimum value which statisfies non adjacent rob sequence
                return A[i][0]; // minimum possible value
            }

            i++;
        }

        // though it will not reach here because everytime it will find solution
        return -1;
    }
}