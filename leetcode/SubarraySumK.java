// https://leetcode.com/problems/subarray-sum-equals-k/

class Solution {
    public int subarraySum(int[] A, int k) {
        // frequency based approach to count subarrays
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);

        int N = A.length;
        int s = 0, c = 0;
        for(int i = 0; i < N; i++) {
            s = s + A[i];

            // count
            int req = s - k;
            if(map.containsKey(req)) {
                c += map.get(req);
            }

            map.put(s, map.getOrDefault(s, 0) + 1);
        }

        return c;
    }
}