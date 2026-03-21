class Solution {
    public int[] topKFrequent(int[] nums, int k) {
       HashMap<Integer, Integer> F = new HashMap<>();
       for(int x : nums) {
           F.put(x, F.getOrDefault(x, 0) + 1);
       }

        PriorityQueue<Map.Entry<Integer,Integer>> PQ = new PriorityQueue<>(new Comparator<Map.Entry<Integer,Integer>>(){
            @Override
            public int compare(Map.Entry<Integer,Integer> e1, Map.Entry<Integer,Integer> e2) {
                return e2.getValue() - e1.getValue();
            }
        });
       for (Map.Entry<Integer,Integer> e : F.entrySet()) {
           PQ.add(e);
       }

        int ans[] = new int[k];
        int i = 0;
       while(i < k) {
           Map.Entry<Integer,Integer> e = PQ.poll();
           ans[i] = e.getKey();
           i++;
       }

       return ans;
    }
}