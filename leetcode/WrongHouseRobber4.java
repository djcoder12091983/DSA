// https://leetcode.com/problems/house-robber-iv/


// TODO need to check countPairs
class Solution {

    // count valid non-adjacency pairs
    int countPairs(int adjacent, int N) {
        int pairs = 0;
        if(adjacent != N) {
            if(N - adjacent == 1) {
                pairs = 1;
            } else {
                pairs = N - adjacent - 1;
            }
        }
        // edge cases for connect elements
        if(adjacent <= 2) {
            pairs += 1;
        } else if(adjacent == 3) {
            pairs += 2;
        } else {
            pairs += adjacent;
        }

        return pairs;
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
        HashSet<Integer> idxtrack = new HashSet<>();
        while(i < k - 1) {
            int idx = A[i][1];
            idxtrack.add(idx);

            int l = idx - 1;
            int r = idx + 1;
            if(idxtrack.contains(l)) {
                adjacent++;
            }
            if(idxtrack.contains(r)) {
                adjacent++;
            }

            i++;
        }

        // System.out.println("Adjacent-1: " + adjacent);

        // find the first smallest value which statisfies non adjacent rob sequence
        while(i < N) {

            int idx = A[i][1];
            idxtrack.add(idx);

            int l = idx - 1;
            int r = idx + 1;
            if(idxtrack.contains(l)) {
                adjacent++;
            }
            if(idxtrack.contains(r)) {
                adjacent++;
            }

            // count pairs from connect and non-connected elements
            int pairs = countPairs(adjacent, i + 1);
            System.out.println("Adjacent-2: [" + A[i][0] + ", " + A[i][1] + "]" + adjacent + " Pairs: " + pairs);

            if(pairs == k - 1) {
                // we found best possible minimum value which statisfies non adjacent rob sequence
                return A[i][0]; // minimum possible value
            }

            i++;
        }

        // though it will not reach here because everytime it will find solution
        return -1;
    }
}