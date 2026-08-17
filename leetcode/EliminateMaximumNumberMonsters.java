// https://leetcode.com/problems/eliminate-maximum-number-of-monsters/

class Solution {
    public int eliminateMaximum(int[] dist, int[] speed) {
        // we will check which monster will take how much time to reach city
        // then sort them based on arrival time and then see how many maximum monsters we can kill

        int N = dist.length;
        // either we can use PQ or sort based on arrival time
        PriorityQueue<Integer> time = new PriorityQueue<>();
        //int time[] = new int[N];
        for(int i = 0; i < N; i++) {
            // TODO could ne simplify
            int t = Double.valueOf(Math.ceil(1.0 * dist[i] / speed[i])).intValue();
            time.add(t);
        }

        int t = 0;
        int ans = 0;
        // now see if current time t < required arrival time, if so then it's possible to kill
        while(!time.isEmpty()) {
            int arrival = time.poll();
            if(arrival > t) {
                ans++;
            } else {
                // one monster rech the city
                break;
            }

            t++;
        }

        return ans;
    }
}