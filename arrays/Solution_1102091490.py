class Solution:

    def checkSingleNumberPositives(self, nums: List[int]) -> int:
        N=len(nums)
        ans=0
        found = 0
        for b in range(0,32):
            count=0
            for j in range(N):
                if((nums[j]>>b)&1==1):
                    count+=1
            if((count%3)!=0):
                found = 1
                ans=ans|(1<<b)
        return [found, ans]

    def singleNumber(self, nums: List[int]) -> int:

        # handle edge case 0
        c0 = 0
        for x in nums:
            if x == 0:
                c0 += 1
        
        if c0 == 1:
            return 0

        positives = []
        negatives = []

        for x in nums:
            if x >= 0:
                positives.append(x)
            else:
                negatives.append(-1 * x)

        positive_single = self.checkSingleNumberPositives(positives)
        negative_single = self.checkSingleNumberPositives(negatives)

        if positive_single[0] == 1:
            return positive_single[1]
        else:
            return negative_single[1] * -1
        