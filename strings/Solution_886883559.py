class Solution:
    def removeKdigits(self, num: str, k: int) -> str:
        track = []
        # need to push numbers increasing order
        i = 0
        while i < len(num):
            if k == 0 or not track or num[i] >= track[len(track) - 1]:
                # push numbers increasing order
                #print("pushing: ", num[i])
                track.append(num[i])
                i += 1
            else:
                # remove it
                #print("popping: ", track[len(track) - 1])
                track.pop()
                k -= 1
        
        # now check whether any digits to be removed or not
        while k > 0:
            track.pop()
            k -= 1

        # now prepare answer from stack
        ans = ""
        while track:
            ans += track[len(track) - 1]
            track.pop()
        # reverse the answer
        # TODO: you could usein built API
        res = ""
        nonzero = False
        for i in range(len(ans) - 1, -1, -1):
            if ans[i] == "0" and nonzero == False:
                continue
            nonzero = True
            res += ans[i]

        if res == "":
            res = "0"
        return res