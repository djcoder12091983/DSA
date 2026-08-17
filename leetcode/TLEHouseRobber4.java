// https://leetcode.com/problems/house-robber-iv/

class Solution {

    // count valid robs count
    int countRobs(List<Integer> idxlist) {
        Collections.sort(idxlist); // sort the index

        int robs = 1;
        int N = idxlist.size();
        int prev = idxlist.get(0);
        int i = 1;
        while(i < N) {
            int next = idxlist.get(i);
            if(prev + 1 < next) {
                robs++;
                prev = next;
            }

            i += 1;
        }

        return robs;
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
        int i = 0;

        List<Integer> idxlist = new ArrayList<>();

        while(i < k - 1) {
            int idx = A[i][1];
            idxlist.add(idx);
            i++;
        }

        // find the first smallest value which statisfies non adjacent rob sequence
        while(i < N) {

            int idx = A[i][1];
            idxlist.add(idx);

            // count rob count
            int robs = countRobs(idxlist);
            
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