class Solution:
    def lengthOfLongestSubstring(self, s: str) -> int:
        i = j = 0
        track = set()
        n = len(s)
        ans = 0
        while j < n:
            x = s[j]
            if x in track:
                # duplicate, need to discard left characters
                track.remove(s[i])
                i += 1
            else:
                ans = max(ans, j - i + 1)
                track.add(x)
                j += 1

        return ans