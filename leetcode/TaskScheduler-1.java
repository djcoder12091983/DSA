https://leetcode.com/problems/task-scheduler/description/

class Solution {

    // TODO we can use entry-set rather 
    class Task {
        char task;
        int freq;

        Task(char task, int freq) {
            this.task = task;
            this.freq = freq;
        }
    }

    public int leastInterval(char[] T, int gap) {
        int N = T.length;

        // frequency map
        Map<Character, Integer> fmap = new HashMap<>();
        for(int i = 0; i < N; i++) {
            fmap.put(T[i], fmap.getOrDefault(T[i], 0) + 1);
        }

        // now sort entry set based on frequency
        // maxheap to choose task with higher frequency to balance with lower frequyencyfrequency
        PriorityQueue<Task> Q = new PriorityQueue<>((x, y) -> y.freq - x.freq);
        for(Map.Entry<Character, Integer> task : fmap.entrySet()) {
            Q.add(new Task(task.getKey(), task.getValue()));
        }

        // now arrange task
        int slots = 0;
        while(!Q.isEmpty()) {

            List<Task> temp = new ArrayList<>(gap);
            int i = 0;
            // maintain at least give gaps between similar task
            while(!Q.isEmpty() && i < gap + 1) {
                // place task in each gap window
                Task t = Q.poll();
                slots++; // task slots
                // when frequency will become 0 then we are done with that job
                if(t.freq > 1) {
                    t.freq--; // update frequency 
                    temp.add(t);
                }
                
                i++;
            }

            if(!temp.isEmpty()) {
                slots += gap + 1 - i; // idle slots
                for(Task t : temp) {
                    Q.add(t);
                }
            }
        }

        return slots;
    }
}