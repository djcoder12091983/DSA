/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}
*/

class Solution {
    public Node cloneGraph(Node node) {
        
        if(node == null) {
            return null; // edge case
        }

        Map<Integer, Node> newnodes = new HashMap<>();
        Set<Integer> visited = new HashSet<>();
        clone(node, newnodes, visited);

        return newnodes.get(1);
    }

    void clone(Node node, Map<Integer, Node> newnodes, Set<Integer> visited) {
        // try depth first
        int x = node.val;
        if(visited.contains(x)) {
            return; // already visited
        }

        visited.add(x);

        if(!newnodes.containsKey(x)) {
            // create new
            newnodes.put(x, new Node(x));
        }

        Node newnode = newnodes.get(x);

        // neighbors
        for(Node neighbor : node.neighbors) {
            x = neighbor.val;
            
            if(!newnodes.containsKey(x)) {
                // create new
                newnodes.put(x, new Node(x));
            }

            // add neighbour
            newnode.neighbors.add(newnodes.get(x));

            // recursively call
            clone(neighbor, newnodes, visited);

        }
    }
}