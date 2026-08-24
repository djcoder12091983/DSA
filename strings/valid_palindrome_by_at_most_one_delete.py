# https://neetcode.io/problems/valid-palindrome-ii/history
class Solution:
    def validPalindrome(self, s: str) -> bool:
        return self.check(s, 0, len(s) - 1, 0)

    def check(self, s, start, end, c):
        if start > end:
            return True
        if s[start] != s[end]:
            # character mismatch
            if c == 1:
                # already we have removed one ans still more delete required
                # so not possible to be palindrome
                return False
            else:
                # still have one chance to remove and see how it goes
                # either we can remove s[start] so new start is start + 1
                # or we can remove s[end] so new end is end - 1
                # we have updated c to c + 1 because we have done one delete
                return self.check(s, start + 1, end, c + 1) or self.check(s, start, end - 1, c + 1)
        else:
            # if both are same then move ahead
            return self.check(s, start + 1, end - 1, c)