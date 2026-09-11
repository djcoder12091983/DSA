// https://leetcode.com/problems/stone-game-vi/
// TODO greedy won't work -- need to FIX
// what is the optimal game winning strategy!

class Solution {
    public int stoneGameVI(int[] aliceValues, int[] bobValues) {
        // greedy both will choose next possible greater value based on available index
        int N = aliceValues.length;

        int A[][] = new int[N][2];
        int B[][] = new int[N][2];

        for(int i = 0; i < N; i++) {
            A[i][0] = aliceValues[i];
            A[i][1] = i;

            B[i][0] = bobValues[i];
            B[i][1] = i;
        }

        // sort based on highest value
        Arrays.sort(A, (x, y) -> y[0] - x[0]);
        Arrays.sort(B, (x, y) -> y[0] - x[0]);

        boolean V[] = new boolean[N]; // visited index
        Arrays.fill(V, false);

        int i = 0, j = 0;
        int alice = 0, bob = 0;
        int k = 0;
        while(k < N) {
            // next available index for alice
            while(V[A[i][1]] == true) {
                i++;
            }

            k++;
            if(k == N) {
                // done -- odd case
                break;
            }

            //System.out.println("Alice index: " + A[i][1]);

            alice += A[i][0];
            V[A[i][1]] = true; // block the index
            i++;

            // next available index for bob
            while(V[B[j][1]] == true) {
                j++;
            }

            // System.out.println("Bob index: " + B[j][1]);

            bob += B[j][0];
            V[B[j][1]] = true; // block the index
            j++;

            k++; // done both turns
        }

        if(alice == bob) {
            // both scored same
            return 0;
        }

        if(alice > bob) {
            return 1;
        } else {
            return -1;
        }
    }
}