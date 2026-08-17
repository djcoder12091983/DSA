https://leetcode.com/problems/task-scheduler/description/

class Solution {
    public int leastInterval(char[] T, int gap) {
        // take advantage of all tasks are uppercase
        int[] fmap = new int[26]; // by default all values will be 0
        
        for(char task : T) {
            fmap[task - 'A'] += 1; // frequency map
        }

        // put tasks into PQ based on frequency in descending order
        // so that we can choose higher frequency task with lower frequency
        // to minimize idle slots
        PriorityQueue<Integer> Q = new PriorityQueue<>(Collections.reverseOrder());
        for(int i = 0; i < 26; i++) {
            if(fmap[i] > 0) {
                Q.add(fmap[i]);
            }
        }

        int slots = 0;
        // when we pop from PQ then those task will be placed in this temp array so that next time it
        // when we pop from PQ then those task will be placed in this temp array so that next time it
        // it ensures similar tasks will be placed at least given gaps apart
        int temp[] = new int[gap + 1];
        while(!Q.isEmpty()) {

            int i = 0, k = 0;
            // place gap + 1 different tasks
            while(!Q.isEmpty() && i < gap + 1) {
                slots++; // task placed
                int freq = Q.poll();
                if(freq > 1) {
                    // after reducing frequency it will be not be 0
                    temp[k++] = freq - 1; // adjust frequency and added to temp
                }

                i++;
            }

            if(k > 0) {
                // more tasks to be arranged
                slots += gap + 1 - i; // idle slots
                i = 0;
                while(i < k) {
                    Q.add(temp[i++]); // readded to PQ with updated frequency
                }
            }
        }

        return slots;
    }
}