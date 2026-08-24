// https://leetcode.com/problems/serialize-and-deserialize-n-ary-tree/
// TODO need to work on fixing the BUG and optimization regrarding ID to handle duplicates values in tree node 

/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
};
*/

// NOTE: value is not unique so ID is attached to map
// idea is like bredth first traversal - nodeID#nodeVal-[comma seperated children]
// again children value is combined with ID#VALUE

class Codec {
    // Encodes a tree to a single string.
    // NOTE: node values ate not unique
    public String serialize(Node root) {
        if(root == null) {
            return ""; // edge case
        }

        Queue<Node> Q = new LinkedList<>();
        int id = 0; // NOTE: as node values are nore unique so we will maintain unique ID
        Q.add(root);

        StringBuilder ans = new StringBuilder();

        while(!Q.isEmpty()) {
            int s = Q.size();
            for(int i = 0; i < s; i++) {
                Node node = Q.poll();
                // ID # node-value
                ans.append(id++).append('#').append(node.val).append("-[");
                
                List<Node> children = node.children;
                int N = children == null ? 0 : children.size();

                for(int j = 0; j < N; j++) {
                    Node child = children.get(j);

                    // psuh to Queue for next level traversal
                    Q.add(child);

                    // ID # node-value
                    ans.append(id++).append('#').append(child.val);
                    if(j < N - 1) {
                        ans.append(",");
                    }
                }

                ans.append("]|");
            }
        }

        return ans.toString();
    }

    // extracts ID and value
    int[] extract(String val) {
        String t[] = val.split("#");
        return new int[]{Integer.parseInt(t[0]), Integer.parseInt(t[1])};
    }

    // convert serialized part-string into tree node and it's children
    Node convert(String data, Map<Integer, Node> nodeMapping) {

        // TODO this code needs to be optimized
        if(data.isEmpty()) {
            // TODO need to think whether empty string can occur or what
            return null;
        }

        int idx = data.indexOf('-');

        // extracts ID and value, because values is not unique so ID is attached to map he node
        int t[] = extract(data.substring(0, idx));
        int id = t[0];
        int val = t[1];
        
        // children
        String nodes[] = data.substring(idx + 2, data.length() - 1).split(",");
        
        if(!nodeMapping.containsKey(id)) {
            // NOTE: map by ID, because value is not unique
            nodeMapping.put(id, new Node(val, new ArrayList<>()));
        }
        Node node = nodeMapping.get(id);
        
        for(String child : nodes) {

            if(child.isEmpty()) {
                // TODO: need to check how empty string comes
                continue;
            }

            // this node creation is safe, because this is tree
            // because node only exist once from root path
            
            // extracts ID and value, because values is not unique so ID is attached to map he node
            t = extract(child);
            id = t[0];
            val = t[1];
            Node childNode = new Node(val, new ArrayList<>());
            
            // NOTE: map by ID, because value is not unique
            nodeMapping.put(id, childNode);
            node.children.add(childNode);
        }

        return node;
    }
	
    // Decodes your encoded data to tree.
    public Node deserialize(String data) {
        if(data.isEmpty()) {
            return null; // edge case
        }

        System.out.println("Input: " + data);

        String treeNodes[] = data.split("\\|");
        int n = treeNodes.length;
        Map<Integer, Node> nodeMapping = new HashMap<>();

        // root node
        Node root = convert(treeNodes[0], nodeMapping);

        for(int i = 1; i < n; i++) {
            // each node and it's children creation
            convert(treeNodes[i], nodeMapping);
        }

        return root;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));