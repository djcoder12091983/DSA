// https://leetcode.com/problems/alien-dictionary/description/
// wrong approach
class Solution {

    // trie node
    class TrieNode {
        char x;
        Map<Character, TrieNode> order = new LinkedHashMap<>(); // figure out order

        TrieNode(char x) {
            this.x = x;
        }

        TrieNode add(char x) {
            if(!this.order.containsKey(x)) {
                // not exixts then add
                this.order.put(x, new TrieNode(x));
            }
            return this.order.get(x);
        }
    }

    // add to TRIE
    void addToTrie(String word, TrieNode root) {
        TrieNode t = root;
        int s = word.length();
        for(int i = 0; i < s; i++) {
            char x = word.charAt(i);
            t = t.add(x); // add to move to next node
        }
    }

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
            x.next.put(v, y);
        }

        if(!inbound.containsKey(v)) {
            inbound.put(v, 0);
        }
        if(!inbound.containsKey(u)) {
            inbound.put(u, 0);
        }
        inbound.put(v, inbound.get(v) + 1);
    }

    public String alienOrder(String[] words) {
        // create prefix TREE to create order tree then we will try to figure out topological order
        // if cycle detected then return empty string otherwise topological order in any order

        TrieNode root = new TrieNode('X'); // dummy node

        // prepare prefix tree
        for(String word : words) {
            addToTrie(word, root);
        }

        // do BFS to build order and create graph based on that
        Queue<TrieNode> Q = new LinkedList<>();
        Q.add(root);
        while(!Q.isEmpty()) {
            // build order if node has multiple nodes
            TrieNode node = Q.poll();
            Iterator<TrieNode> order = node.order.values().iterator();
            if(order.hasNext()) {
                TrieNode prev = order.next();
                Q.add(prev);
                while(order.hasNext()) {
                    TrieNode cur = order.next();
                    Q.add(cur);
                    //System.out.println("Link: " + prev.x + " " + cur.x);
                    addToGraph(prev.x, cur.x); // add to graph
                    prev = cur;
                }
            }
        }

        //System.out.println("1. " + inbound);

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

        // System.out.println("2. " + inbound);

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