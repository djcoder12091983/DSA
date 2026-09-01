// https://leetcode.com/problems/minimum-deletions-to-make-array-beautiful/
// Wrong-Approach -- TODO FIX BUG

class Solution {
    public int minDeletion(int[] A) {
        int N = A.length;
        Map<Integer, Integer> f = new HashMap<>();
        int min = -1;
        int minFrequency = N + 1;
        for(int i = 0; i < N; i++) {
            int x = A[i];
            f.put(x, f.getOrDefault(x, 0) + 1);
            
            if(minFrequency > f.get(x)) {
                minFrequency = f.get(x);
                min = x;
            }
        }

        int s = f.size();
        int ans = 0;
        if(s % 2 == 1) {
            // odd number of elements then we will remove minimum frequency elements
            // to make even length elements
            ans += f.remove(min);
        }

        for(int x : f.values()) {
            // make all frequencies exactly 1
            if(x > 1) {
                ans += x - 1;
            }
        }

        return ans;
    }
}