class Solution:

    def originalDigits(self, s: str) -> str:
        N = len(s)
        F = {}
        for i in range(N):
            x = s[i]
            if x in F:
                F[x] += 1
            else:
                F[x] = 1

        M = ['z1e1r1o1', 'o1n1e1', 't1w1o1', 't1h1r1e2', 'f1o1u1r1',
                'f1i1v1e1','s1i1x1', 's1e2v1n1', 'e1i1g1h1t1', 'n2i1e1']

        def check_and_update(D):
            num = M[D]
            N = len(num)
            i = 0
            while i < N:
                x = num[i]
                c = int(num[i + 1])
                if x not in F or F[x] < c:
                    return False
                
                i += 2
            
            i = 0
            while i < N:
                x = num[i]
                c = int(num[i + 1])

                F[x] -= c

                i += 2
            
            return True

        
        DIGIT = [0, 2, 4, 6, 8, 1, 5, 9, 3, 7]
        ans = []
        i = 0
        while i < 10:
            D = DIGIT[i]    
            if check_and_update(D):
                ans.append(D)
            else:
                i += 1

        ans.sort()
        res = ""
        for i in range(len(ans)):
            res += str(ans[i])
        return res