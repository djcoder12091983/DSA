class Solution {

    int digits(long n, int d[]) {
        int c = 0;
        while(n > 0) {
            int rem = (int)(n % 10);
            d[c++] = rem;
            n = n / 10;
        }

        return c;
    }

    public boolean reorderedPowerOf2(int n) {
        // one easier way to solve without recursion that's generating all possible 2 power which will O(log(N))
        // then for each 2 power match the digit frequency if same then permutation can be formed

        int[] d1 = new int[10]; // maximum 10 digits can be there
        int c1 = digits(n, d1);

        int f1[] = new int[10]; // 10 digits frequency
        for(int i = 0; i < c1; i++) {
            f1[d1[i]]++;
        }
        
        long limit = (long)(Math.pow(10, c1)); // MAX LIMIT as per input
		//long N = n;
        long p = 1;
        while(p < limit) {
            int[] d2 = new int[10]; // maximum 10 digits can be there
            int c2 = digits(p, d2);

            int f2[] = new int[10]; // 10 digits frequency
            for(int i = 0; i < c2; i++) {
                f2[d2[i]]++;
            }

            boolean flag = true;
            for(int i = 0; i < 10; i++) {
                if(f1[i] != f2[i]) {
                    // frequencyt mismatch
                    flag = false;
                    break;
                }
            }
            if(flag) {
                return true; // match found
            }

            p = p * 2; // power of 2
        }

        return false; // no match found
    }
}