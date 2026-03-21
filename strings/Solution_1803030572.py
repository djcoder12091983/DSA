class Solution:
    def convert(self, s: str, numRows: int) -> str:
        if numRows == 1:
            # edge case
            return s
        zigzag = [""] * numRows
        i = 0
        row = 0
        n = len(s)
        direction = 1
        while i < n:
            zigzag[row] += s[i]
            # move pointers as per direction
            if direction == -1 and row == 0:
                direction = 1
            elif direction == 1 and row == numRows - 1:
                direction = -1

            row += direction
            
            i += 1

        return ''.join(zigzag)