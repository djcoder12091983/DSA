class Solution {

    boolean almostEqual(int X, int Y) {
        if(X == Y) {
            return true;
        } else {
            String N1 = String.valueOf(X);
            String N2 = String.valueOf(Y);
            char bigger[], smaller[];

            if(N1.length() > N2.length()) {
                bigger = N1.toCharArray();
                smaller = N2.toCharArray();
            } else {
                bigger = N2.toCharArray();
                smaller = N1.toCharArray();
            }

            // we will swap digits in bigger and check with smaller
            int n = bigger.length;
            int check = Integer.valueOf(new String(smaller));
            for(int i = 0; i < n; i++) {
                for(int j = i + 1; j < n; j++) {

                    // swap
                    char t = bigger[i];
                    bigger[i] = bigger[j];
                    bigger[j] = t;

                    int afterSwap = Integer.valueOf(new String(bigger));
                    if(afterSwap == check) {
                        return true;
                    }

                    // reswap it back
                    t = bigger[i];
                    bigger[i] = bigger[j];
                    bigger[j] = t;
                }
            }

            return false;
        }
    }

    public int countPairs(int[] A) {

        int N = A.length;
        int c = 0;
        for(int i = 0; i < N; i++) {
            for(int j = i + 1; j < N; j++) {

                if(almostEqual(A[i], A[j])) {
                    c++;
                }

            }
        }
        return c;
    }
}