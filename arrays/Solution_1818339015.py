class Solution:
    def maxArrayValue(self, A: List[int]) -> int:
        #B = [] # optimally merged array
        ans = 0
        n = len(A)
        # to get optimally merged result we need to merge it from right to left
        s = A[n - 1]

        i = n - 2
        # right to left merging
        while i >= 0:
            if A[i + 1] >= A[i]:
                # increasing sequence from left to right
                s += A[i] # keep on merging
            else:
                # check whether merged result can be merged with current sequence
                if s >= A[i]:
                    s += A[i]
                else:
                    # add merge result and look for next increasing sequence from left to right
                    #B.append(s)
                    ans = max(ans, s)
                    s = A[i]
            i -= 1
        
        # add last merged result
        #B.append(s)
        ans = max(ans, s)
        #return max(B) # max of optimally merged result
        return ans