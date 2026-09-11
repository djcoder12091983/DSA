// https://leetcode.com/problems/stone-game-ix/
// FIXED -- need to validate starting by 1 and 2 both
// TODO -- need to validate correctness -- DEEP analysis

class Solution {

    // check whether it will lead alice win or loose
    boolean validate(int stones[], int sum, int N) {

        // copy remainder
        int remainder[] = new int[3];
        for(int k = 0; k < 3; k++) {
            remainder[k] = stones[k];
        }

        remainder[sum]--;

        int i = 1;
        boolean divisible3 = false;
        while(i < N) {

            if(sum == 1) {
                // choose 0 or 1, coz 2 will give sum divisible by 3
                if(remainder[0] > 0) {
                    remainder[0]--;
                } else if(remainder[1] > 0) {
                    sum += 1;
                    remainder[1]--;
                } else {
                    // this is break condition, sum is divisible by 3
                    sum += 2;
                    remainder[2]--;
                }
            } else {
                // sum is 2, choose either 0 or 2
                if(remainder[0] > 0) {
                    remainder[0]--;
                } else if(remainder[2] > 0) {
                    sum += 2;
                    remainder[2]--;
                } else {
                    // this is break condition, sum is divisible by 3
                    sum += 1;
                    remainder[1]--;
                }
            }

            i++;

            sum = sum % 3;
            if(sum == 0) {
                // sum becomes divisible by 3
                divisible3 = true;
                break;
            }
        }

        if(divisible3) {
            // now whose last turn was
            if(i % 2 == 0) {
                // it's BOB
                return true; // alice wins
            } else{
                // it's ALICE
                return false; // BOB wins
            }
        }

        // all stones removed and sum is not divisible by 3, so BOB wins
        return false;
    }

    public boolean stoneGameIX(int[] stones) {
        int N = stones.length;
        // we will find remainder which helps to see whether next element divded by 3
        int remainder[] = new int[3]; // possible remainder [0, 1, 2]
        Arrays.fill(remainder, 0);

        for(int i = 0; i < N; i++) {
            remainder[stones[i] % 3]++;
        }

        if(remainder[1] == 0 && remainder[2] == 0) {
            // all are divisible by 3
            // alice will loose coz whatever the stone is picked up it's divisible by 3
            return false;
        }

        boolean win = false;
        // we will try both 1 or 2
        if(remainder[1] > 0) {
            // start with 1
            win = win | validate(remainder, 1, N);
        }
        
        if(remainder[2] > 0) {
            // start with 2
            win = win | validate(remainder, 2, N);
        }
        
        return win;
    }
}