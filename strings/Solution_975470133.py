class Solution:
    def myAtoi(self, s: str) -> int:

        # ignore the space from left side
        i = 0
        N = len(s)
        while i < N:
            if s[i] != ' ':
                break
            i = i + 1

        sign = 1
        j = i
        # sign
        if j < N:
            if s[j] == '+':
                sign = 1
                j = j + 1
            elif s[j] == '-':
                sign = -1
                j = j + 1

        # extract number
        num = 0
        p10 = 10 # 10 power
        zero = ord('0')
        nine = ord('9')
        #print(sign, zero, nine)
        while j < N:
            code = ord(s[j])
            #print("code: ", code)
            if code >= zero and code <= nine:
                num = num * p10 + code - zero
            else:
                break # break when you see non digit character
            
            j = j + 1
        
        num = num * sign
        neglimit = -(2**31)
        poslimit = 2**31 - 1
        if num < neglimit:
            return neglimit
        
        if num > poslimit:
            return poslimit

        return num