https://leetcode.com/problems/count-robot-groups/description/

class Solution {
    public int countGroups(int[] position, int[] speed, int k) {
        // we will find next smaller speed that will be merged
        // but here first we will merge robots speed which are having at most k distance
        // then we will find next smaller speed to merge

        int N = position.length;

        if(N == 1) {
            return 1; // only single group
        }
        
        List<Integer> merge = new ArrayList<>();
        int i = 0;
        while(i < N) {

            int j = i;
            int x = speed[j];
            while(j < N - 1) {
                if(position[j + 1] - position[j] <= k) {
                    // merge
                    x = speed[j + 1];
                } else {
                    break; // can't merge
                }

                j++;
            }

            merge.add(x);

            i = j + 1;
        }

        // System.out.println(merge);

        // now find the next smaller of merge speed
        Stack<Integer> monotonic = new Stack<>();
        i = 0;
        N = merge.size();
        int next[] = new int[N]; // tack next smaller index
        Arrays.fill(next, -1); // default fill -1
        while(i < N) {
            if(monotonic.isEmpty()) {
                monotonic.push(i);
            } else {
                int x = merge.get(i);
                if(x >= merge.get(monotonic.peek())) {
                    // inceasing sequence
                    monotonic.push(i);
                } else {
                    // check which index statisfies current index as smaller
                    while(!monotonic.isEmpty()) {
                        int y = monotonic.peek();
                        if(merge.get(y) > x) {
                            // strictly smaller
                            next[y] = i;
                            monotonic.pop();
                        } else {
                            break;
                        }
                    }

                    monotonic.push(i);
                }
            }

            i++;
        }

        // now we will check how many speed has next smaller value -1
        // that means those many groups will be formed
        i = 0;
        int groups = 0;
        while(i < N) {
            if(next[i] == -1) {
                groups++;
            }

            i++;
        }

        return groups;
    }
}