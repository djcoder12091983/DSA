// https://leetcode.com/problems/largest-multiple-of-three/
// TODO -- Can we do better!

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

    int plan(int count[], int idx1, int idx2) {
        int len = 3 * (count[idx1] / 3);
        int x = count[idx1] % 3;
        len += 2 * Math.min(x, count[idx2]);
        if(count[idx2] > x) {
            len += 3 * ((count[idx2] - x) / 3);
        }

        return len;
    }

    int generate(int remainders[][], int count[], int select[], int idx1, int idx2) {
        int c = 0;
        // free to choose zero remainder
        for(int i = 0; i < count[0]; i++) {
            select[c++] = remainders[0][i];
        }

        int p1 = 0;
        while(count[idx1] - p1 >= 3) {
            for(int i = 0; i < 3; i++) {
                select[c++] = remainders[idx1][p1++];
            }
        }

        int p2 = 0;
        while(p1 < count[idx1] && p2 < count[idx2]) {
            select[c++] = remainders[idx1][p1];
            select[c++] = remainders[idx2][p2];

            p1++;
            p2++;
        }

        while(count[idx2] - p2 >= 3) {
            for(int i = 0; i < 3; i++) {
                select[c++] = remainders[idx2][p2++];
            }
        }

        return c;
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

        // TRICK! is whether we will use all 1's or all 2's remainders
        int len1 = plan(count, 1, 2);
        int len2 = plan(count, 2, 1);

        // System.out.println(len1 + " " + len2);

        int select[] = new int[N];
        int c;
        if(len1 > len2) {
            c = generate(remainders, count, select, 1, 2);
        } else {
            c = generate(remainders, count, select, 2, 1);
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