// https://leetcode.com/problems/integers-with-multiple-sum-of-two-cubes/

class Solution {
    public List<Integer> findGoodIntegers(int n) {
        // apply brute-force
        // TODO -- need to check whether +1 is required or not 
        int limit = Double.valueOf(Math.pow(n, 1.0/3)).intValue() + 1;

        Map<Long, Integer> frequency = new HashMap<>();
        for(int a = 1; a <= limit; a++) {
            for(int b = a + 1; b <= limit; b++) {
                long total = 1L* a * a * a + 1L * b * b * b;
                if(total <= n) {
                    // valid number
                    frequency.put(total, frequency.getOrDefault(total, 0) + 1);
                }
            }
        }

        List<Integer> ans = new ArrayList<>();
        for(Long x : frequency.keySet()) {
            if(frequency.get(x) >= 2) {
                // at least 2
                ans.add(x.intValue());
            }
        }

        // sort it
        Collections.sort(ans);

        return ans;

        
    }
}