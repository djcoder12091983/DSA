class Solution:
    def subsetsWithDup(self, nums: List[int]) -> List[List[int]]:
        answer = []
        
        def recursion(index,nums:List[int],temp:List[int]):
            if index == len(nums):
                container = []
                for i in temp:
                    container.append(i)
                answer.append(container)
                return

            # Not take
            recursion(index+1,nums,temp)

            # Take
            temp.append(nums[index])
            recursion(index+1,nums,temp)
            temp.pop()

        temp = []
        recursion(0,nums,temp)

        unique_subsets = set()
        final_output = []
        for subset in answer:
            subset.sort()
            if len(subset) == 0:
                final_output.append(subset)
            else:
                key = str(subset[0])
                for i in range(1, len(subset)):
                    key += "/" + str(subset[i])
                
                if key in unique_subsets:
                    continue
                else:
                    final_output.append(subset)
                    unique_subsets.add(key)
        
        return final_output