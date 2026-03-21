public class Solution {
    
    class Node implements Comparable<Node> {
        int x;
        int idx; // current index of data used
        
        Node(int x, int idx) {
            this.x = x;
            this.idx = idx;
        }
        
        @Override
        public int compareTo(Node o) {
            return x - o.x;
        }
    }
    
    public ArrayList<Integer> solve(int A, int B, int C, int D) {
        // priority queue based BFS on combinatorial tree with repetitions
        Queue<Node> bfs = new PriorityQueue<>();
        List<Integer> X = new ArrayList<>(3);
        // avoid adding duplicates
        X.add(A);
        if(!X.contains(B)) {
            X.add(B);
        }
        if(!X.contains(C)) {
            X.add(C);
        }
        Collections.sort(X); // required to sort to make consistent
        
        int N = X.size();
        for(int i = 0; i < N; i++) {
            bfs.add(new Node(X.get(i), i));
        }
        ArrayList<Integer> sorted = new ArrayList<>(D);
        while(sorted.size() < D) {
            Node node = bfs.poll();
            int x = node.x;
            sorted.add(x);
            int idx = node.idx;
            // explore it's children
            for(int i = idx; i < N; i++) {
                bfs.add(new Node(x * X.get(i), i));
            }
        }
        
        return sorted;
    }
}
