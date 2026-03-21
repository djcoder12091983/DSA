class Solution {

    boolean isPossible(int[] stones, int position, int lastMoves, HashMap<String, Boolean> DP) {

        //System.out.println(position + " " + lastMoves);

        int N = stones.length;
        if(position == N - 1) {
            return true;
        }

        String key = lastMoves + "/" + position;

        if(DP.containsKey(key)) {
            //System.out.println("OVERLAPPING");
            return DP.get(key);
        }

        int options[] = new int[]{lastMoves - 1, lastMoves, lastMoves + 1};
        boolean possible = false;
        for(int option : options) {
            
            // find whether option is available or not
            int foundIndex = -1;
            for(int j = position + 1; j < N; j++) {
                if(stones[j] == option + stones[position]) {
                    foundIndex = j;
                    break;
                }
            }

            if(foundIndex != -1) {
                // option is found
                possible = possible | isPossible(stones, foundIndex, option, DP);

                if(possible == true) {
                    break;
                }
            }
        }

        DP.put(key, possible);

        return possible;
    }

    public boolean canCross(int[] stones) {
        int lastMoves = 0;
        int position = 0; // index

        HashMap<String, Boolean> DP = new HashMap<>();

        return isPossible(stones, position, lastMoves, DP);
    }
}