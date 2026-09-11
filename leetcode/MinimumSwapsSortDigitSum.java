// https://leetcode.com/problems/minimum-swaps-to-sort-by-digit-sum/
// TOODO -- may need to reduce execution time

class Solution {

    // digit sum
    int sum(int x) {
        int s = 0;
        while(x > 0) {
            s += x % 10;
            x = x / 10;
        }

        return s;
    }

    int compare(int x, int y) {
        int c = sum(x) - sum(y);
        if(c != 0) {
            return c;
        }

        return x - y;
    }

    public int minSwaps(int[] A) {
        int N = A.length;
        Integer data[] = new Integer[N];
        for(int i = 0; i < N; i++) {
            data[i] = A[i];
        }

        Arrays.sort(data, (x, y) -> compare(x, y));

        // index based tracking
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < N; i++) {
            map.put(data[i], i);
        }

        // put index [0, N - 1]
        int idx[] = new int[N];
        for(int i = 0; i < N; i++) {
            idx[i] = map.get(A[i]);
        }

        // now put index in it's original position
        int i = 0;
        int swap = 0;
        while(i < N) {
            if(idx[i] != i) {
                // need to swap
                swap++;
                
                int pos = idx[i];
                int t = idx[pos];
                idx[pos] = idx[i];
                idx[i] = t;
                // wait for next swap
            } else {
                i++; // safe to move, it's already in position
            }
        }
        return swap;
    }
}