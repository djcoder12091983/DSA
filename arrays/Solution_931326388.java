class Solution {
    public long[] distance(int[] nums) {
        int n = nums.length;
        long ans[] = new long[n];
        Map<Integer, Long> indexesMap = new HashMap<>();
        Map<Integer, Integer> freqMap = new HashMap<>();

        //traversing left to right
        for(int i = 0; i < n; i++){
            if(!indexesMap.containsKey(nums[i])){
                indexesMap.put(nums[i], i * 1l);
                freqMap.put(nums[i], 1);
            }else{
                ans[i] = (1l * i * freqMap.get(nums[i])) - indexesMap.get(nums[i]);
                indexesMap.put(nums[i], indexesMap.get(nums[i]) + i);
                freqMap.put(nums[i], freqMap.get(nums[i]) + 1);
            }
        }

        indexesMap.clear();
        freqMap.clear();
        
        //traversing right to left
        for(int i = n-1; i >= 0; i--){
            if(!indexesMap.containsKey(nums[i])){
                indexesMap.put(nums[i], i * 1l);
                freqMap.put(nums[i], 1);
            }else{
                ans[i] += indexesMap.get(nums[i]) - (1l* i * freqMap.get(nums[i]));
                indexesMap.put(nums[i], indexesMap.get(nums[i]) + i);
                freqMap.put(nums[i], freqMap.get(nums[i]) + 1);
            }
        }
        return ans;
    }
}