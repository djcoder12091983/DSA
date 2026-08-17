// https://leetcode.com/problems/alien-dictionary/
// TODO lot of edge cases are there
class Solution {

    // graph node
    class GraphNode {
        char x;
        Map<Character, GraphNode> next = new HashMap<>();

        GraphNode(char x) {
            this.x = x;
        }
    }

    // graph nodes
    Map<Character, GraphNode> graphNodes = new HashMap<>(); // reuse node
    // inbound edge count
    Map<Character, Integer> inbound = new HashMap<>();

    // single node add
    void addToGraph(char u) {
        if(!graphNodes.containsKey(u)) {
            graphNodes.put(u, new GraphNode(u));
        }
        if(!inbound.containsKey(u)) {
            inbound.put(u, 0);
        }
    }

    // add u->v
    void addToGraph(char u, char v) {
        if(!graphNodes.containsKey(u)) {
            graphNodes.put(u, new GraphNode(u));
        }
        if(!graphNodes.containsKey(v)) {
            graphNodes.put(v, new GraphNode(v));
        }
        GraphNode x = graphNodes.get(u);
        GraphNode y = graphNodes.get(v);

        if(!x.next.containsKey(v)) {
            // dependency does not exist
            x.next.put(v, y);
        } else {
            // dependency already exist then return no need to update inbound
            // to avoid duplicate inbound count
            return;
        }

        // inbound update is valid when a new dependency is there
        if(!inbound.containsKey(u)) {
            inbound.put(u, 0);
        }
        if(!inbound.containsKey(v)) {
            inbound.put(v, 0);
        }
        inbound.put(v, inbound.get(v) + 1);
    }

    // compare two word then add to graph
    // edge case when characters are matching then add them a single node also 
    boolean compareAndUpdateGraph(String word1, String word2) {
        int N1 = word1.length(), N2 = word2.length();
        int N = Math.min(N1, N2);
        int i = 0;
        while(i < N) {
            char u = word1.charAt(i);
            char v = word2.charAt(i);
            if(u != v) {
                addToGraph(u, v);
                break;
            } else {
                // single node add
                addToGraph(u);
            }

            i++;
        }

        if(i == N) {
            // valid scenario and extra characters in word2 will be added to graph
            if(N1 < N2) {
                for(int j = N; j< N2; j++) {
                    addToGraph(word2.charAt(j));
                }
            } else if(N1 > N2) {
                // imvalid case
                return false;
            }
        } else {
            // extra characters will be added as single node
            for(int j = i + 1; j < N1; j++) {
                addToGraph(word1.charAt(j));
            }
            for(int k = i + 1; k < N2; k++) {
                addToGraph(word2.charAt(k));
            }
        }

        return true;
        
    }

    public String alienOrder(String[] words) {

        int N = words.length;
        String prev = "";
        for(int i = 0; i < N; i++) {
            if(!compareAndUpdateGraph(prev, words[i])) {
                // not a valid case
                return "";
            }
            prev = words[i];
        }
        
        // now find is there any topological sorting exists or not
        Queue<GraphNode> topoq = new LinkedList<>();
        for(char x : inbound.keySet()) {
            if(inbound.get(x) == 0) {
                // start with inbound 0
                topoq.add(graphNodes.get(x));
            }
        }

        // iterate over graph
        StringBuilder ans = new StringBuilder();
        while(!topoq.isEmpty()) {
            GraphNode u = topoq.poll();
            ans.append(u.x);

            for(GraphNode v : u.next.values()) {
                inbound.put(v.x, inbound.get(v.x) - 1);
                if(inbound.get(v.x) == 0) {
                    // if it becomes 0 then add to topoq
                    topoq.add(v);
                }
            }
        }

        // now check for cycle
        for(char x : inbound.keySet()) {
            if(inbound.get(x) > 0) {
                // cycle
                return ""; // not possible order
            }
        }

        return ans.toString();
    }
}