class Solution:
    def decodeString(self, s: str) -> str:
        stack = []

        for i in range(len(s)):
            if s[i] != ']':
                stack.append(s[i])
            
            else:
                result = ''

                while len(stack) > 0 and stack[-1] != '[':
                    result += stack[-1]
                    stack.pop()

                result = result[::-1]

                stack.pop()

                num = ''

                while len(stack) > 0 and stack[-1].isnumeric() == True:
                    num += stack[-1]
                    stack.pop()

                num = num[::-1]

                num = int(num)

                final_result = ''
                final_result = result * num

                #print(result, num, final_result)

                for x in final_result:
                    stack.append(x)
                #stack.append(final_result)

        output = ''
        for i in stack:
            output+=i

        return output