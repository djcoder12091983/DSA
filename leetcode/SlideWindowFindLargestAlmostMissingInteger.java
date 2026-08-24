// // https://leetcode.com/problems/find-the-largest-almost-missing-integer/description/

class Solution {
    public int largestInteger(int[] A, int k) {
        // we will track every element k-size subarray frequency
        int N = A.length;

        if(k > N) {
            return -1; // not possible
        }

        HashMap<Integer, Integer> subarray = new HashMap<>();
        
        // we will use sliding window
        HashMap<Integer, Integer> window = new HashMap<>();
        int i = 0;
        // first window
        while(i < k) {
            window.put(A[i], window.getOrDefault(A[i], 0) + 1);
            i++;
        }

        // now count k-space subarray contribution
        for(int x : window.keySet()) {
            subarray.put(x, subarray.getOrDefault(x, 0) + 1);
        }

        // slide window
        int p1 = 0, p2 = k;
        while(p2 < N) {

            // remove from p1
            window.put(A[p1], window.get(A[p1]) - 1);
            if(window.get(A[p1]) == 0) {
                window.remove(A[p1]);
            }

            // add p2
            window.put(A[p2], window.getOrDefault(A[p2], 0) + 1);

            // now count k-space subarray contribution
            for(int x : window.keySet()) {
                subarray.put(x, subarray.getOrDefault(x, 0) + 1);
            }

            p1++;
            p2++;
        }

        int ans = -1;
        for(int x : subarray.keySet()) {
            int c = subarray.get(x);
            if(c == 1) {
                ans = Math.max(x, ans);
            }
        }

        return ans;
    }
}