class Solution:
    def twoSum(self, nums: List[int], target: int) -> List[int]:
        idx = {}
        i = 0
        n = len(nums)
        while i < n:
            x = target - nums[i]
            if x in idx:
                return [idx[x], i]
            
            idx[nums[i]] = i
            i += 1