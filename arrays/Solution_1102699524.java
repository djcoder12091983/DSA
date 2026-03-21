class Solution {
    public int firstMissingPositive(int[] nums) {
        int n = nums.length ;
        int i = 0;
        while(i<n){
            if(nums[i] >= 1 && nums[i] <= n){
                         
                    if(nums[i] != i+1 && nums[i] != nums[nums[i] - 1]) {
                        int temp = nums[i];
                        nums[i] = nums[temp-1];
                        nums[temp-1] = temp;
                    } else {
                        i++;
                    }
            }else {
                i++;
            }
        }
        i = 0;
        while(i<n){
            if(nums[i] != i+1)
            return i+1;

            i++;
        } 
        return n + 1;
    }
}