class Solution {

    // add index and decide whether it can be chosen for rob and update robs count accordingly
    int count(TreeSet<Range> idxtrack, int idx, int robs) {
        Range newrange = new Range(idx, idx); // new range
        Range left = idxtrack.floor(newrange);
        Range right = idxtrack.ceiling(newrange);

        if(left != null) {
            if(left.end + 1 == idx) {
                // merged with left, remove left range and update robs count
                robs -= (left.end - left.start + 2) / 2;
                idxtrack.remove(left);

                newrange.start = left.start;
            }
        }

        if(right != null) {
            if(newrange.end + 1 == right.start) {
                // merged with right, remove right range and update robs count
                robs -= (right.end - right.start + 2) / 2;
                idxtrack.remove(right);

                newrange.end = right.end;
            }
        }

        // now add modified range robs count
        robs += (newrange.end - newrange.start + 2) / 2;
        // add newrange to sorted tree
        idxtrack.add(newrange);

        return robs;
    }

    // range will track start and end point
    class Range {
        int start, end;

        Range(int start, int end) {
            this.start = start;
            this.end = end;
        }
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
        // treemap will store range, and merge range and update robs count
        TreeSet<Range> idxtrack = new TreeSet<>((x, y) -> x.start - y.start);
        int robs = 0;
        int t[] = new int[k - 1];
        while(i < k - 1) {
            // add index
            t[i] = A[i][1];

            i++;
        }

        // TODO instead of computing robs count again and again
        // we will prepare robs count
        if(k > 1) {
            Arrays.sort(t); // sort the index
            Range range = new Range(t[0], t[0]);
            for(int j = 0; j < k - 2; j++) {
                if(t[j] + 1 < t[j + 1]) {
                    // sequence break
                    range.end = t[j];
                    // update robs count and add to tree
                    robs += (range.end - range.start + 2) / 2;
                    idxtrack.add(range);

                    // range reset
                    range = new Range(t[j + 1], t[j + 1]);
                }
            }

            // last sequence
            range.end = t[k - 2];
            // update robs count and add to tree
            robs += (range.end - range.start + 2) / 2;
            idxtrack.add(range);
        }

        // find the first smallest value which statisfies non adjacent rob sequence
        while(i < N) {

            int idx = A[i][1];
            
            // add index and update robs count
            robs = count(idxtrack, idx, robs);

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