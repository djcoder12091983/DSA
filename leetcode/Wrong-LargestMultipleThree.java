// https://leetcode.com/problems/largest-multiple-of-three/
// TODO -- Order of combination of we need to change
// like if have 3 2 remainders and < 3 1 remainders or vice versa
// we should go with longer length instead of larger digits

class Solution {

    void reverse(int A[], int l, int r) {
        while(l < r) {
            int t = A[l];
            A[l] = A[r];
            A[r] = t;

            l++;
            r--;
        }
    }

    public String largestMultipleOfThree(int[] digits) {
        // the idea is like group the digits based remainder %3
        // now we will choose remainders based digit greedily
        // we can choose remainders like 3 times 2 or  3 times 1 or we can freely choose 0
        // or else we will choose 2 + 1

        int N = digits.length;
        
        Arrays.sort(digits);
        reverse(digits, 0, N - 1); // sort based on larger digit

        int remainders[][] = new int[3][N];
        int count[] = new int[3];
        Arrays.fill(count, 0);

        for(int i = 0; i < N; i++) {
            int rem = digits[i] % 3;
            remainders[rem][count[rem]++] = digits[i];
        }

        int select[] = new int[N];
        int c = 0;
        // first we will add 0 remainder digit freely
        for(int i = 0; i < count[0]; i++) {
            select[c++] = remainders[0][i];
        }
		
		// TODO -- Order of combination of we need to change
		// like if have 3 2 remainders and < 3 1 remainders or vice versa
		// we should go with longer length instead of larger digits

        // now we will combine 2 + 1
        int p1 = 0, p2 = 0;
        while(p1 < count[1] && p2 < count[2]) {
            select[c++] = remainders[1][p1];
            select[c++] = remainders[2][p2];

            p1++;
            p2++;
        }

        // extra 1 and 2 self combine
        // 3 times combine 1
        while(count[1] - p1 >= 3) {
            for(int i = 0; i < 3; i++) {
                select[c++] = remainders[1][p1++];
            }
        }

        // 3 times combine 2
        while(count[2] - p2 >= 3) {
            for(int i = 0; i < 3; i++) {
                select[c++] = remainders[2][p2++];
            }
        }

        if(c == 0) {
            // not digits fit into
            return "";
        }

        Arrays.sort(select, 0, c); // now sort selected digits to form largest number
        reverse(select, 0, c - 1); // descending order

        StringBuilder ans = new StringBuilder();
        boolean nonzero = false;
        for(int i = 0; i < c; i++) {
            if(select[i] > 0) {
                nonzero = true;
            }
            ans.append(select[i]);
        }

        if(nonzero == false) {
            // all are zero
            return "0";
        }

        return ans.toString();

    }
}