class Solution:
    def generate(self, open, close, exp):
        if close > open or open > self.n:
            # not possible
            return
        if open == close and open == self.n:
            self.ans.append(exp)
            return
        
        # explore options
        self.generate(open + 1, close, exp + '(')
        self.generate(open, close + 1, exp + ')')

    def generateParenthesis(self, n: int) -> List[str]:
        self.n = n
        self.ans = []
        self.generate(0, 0, '')
        return self.ans