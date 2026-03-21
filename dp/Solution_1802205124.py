class Solution:

    def longestPalindrome(self, s: str) -> str:
        
        def find_palindrome(s, start, end):
            n = len(s)
            i = start
            j = end
            # expand around the point
            while i >= 0 and j < n:
                if s[i] != s[j]:
                    return s[i + 1 : j]
                
                i -= 1
                j += 1
            
            return s[i + 1 : j]

        # check for every even and odd indices
        i = 0
        n = len(s)
        ans = ""
        while i < n:
            pal = find_palindrome(s, i, i)
            if len(pal) > len(ans):
                ans = pal
            
            pal = find_palindrome(s, i, i + 1)
            if len(pal) > len(ans):
                ans = pal

            i += 1

        return ans