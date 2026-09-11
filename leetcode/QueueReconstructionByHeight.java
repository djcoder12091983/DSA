// https://leetcode.com/problems/queue-reconstruction-by-height/
// TODO -- may need to reduce execution time

class Solution {
    public int[][] reconstructQueue(int[][] people) {
        // this sort we are doing it will help to construct queue
        // for each distance in sorted order
        Arrays.sort(people, (x, y) -> x[0] - y[0]); // sort based on height

        Map<Integer, Queue<Integer>> map = new HashMap<>();
        int N = people.length;
        for(int i = 0; i < N; i++) {
            int d = people[i][1];
            Queue<Integer> data = map.get(d);
            if(data == null) {
                data = new LinkedList<>();
                map.put(d, data);
            }

            data.add(people[i][0]); // put shorter height first
        }

        // System.out.println(map);

        // now put shorter height with 0 distance first
        int[][] ans = new int[N][2];
        ans[0][0] = map.get(0).poll();
        ans[0][1] = 0;
        
        int i = 1;
        while(i < N) {

            // BRUTE force to find which one will fit optimally
            //  TODO can we think something better here

            for(int j = i; j >= 0; j--) {
                Queue<Integer> Q = map.get(j);
                if(Q != null && !Q.isEmpty()) {
                    int h = Q.peek();
                    int c = 0;
                    for(int k = i - 1; k >= 0; k--) {
                        if(ans[k][0] >= h) {
                            c++;
                        }
                    }

                    if(c == j) {
                        // found a suitable fit
                        h = Q.poll();
                        ans[i][0] = h;
                        ans[i][1] = j;

                        break;
                    }
                }
            }

            i++;
        }

        return ans;
    }
}