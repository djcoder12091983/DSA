// https://leetcode.com/problems/serialize-and-deserialize-n-ary-tree/

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

// idea is like bredth first traversal - node-[comma seperated children]

class Codec {
    // Encodes a tree to a single string.
    public String serialize(Node root) {
        if(root == null) {
            return ""; // edge case
        }

        Queue<Node> Q = new LinkedList<>();
        Q.add(root);

        StringBuilder ans = new StringBuilder();

        while(!Q.isEmpty()) {
            int s = Q.size();
            for(int i = 0; i < s; i++) {
                Node node = Q.poll();
                ans.append(node.val).append("-[");
                
                List<Node> children = node.children;
                int N = children == null ? 0 : children.size();

                for(int j = 0; j < N; j++) {
                    Node child = children.get(j);

                    // psuh to Queue for next level traversal
                    Q.add(child);

                    ans.append(child.val);
                    if(j < N - 1) {
                        ans.append(",");
                    }
                }

                ans.append("]").append("|");
            }
        }

        return ans.toString();
    }

    Node convert(String data, Map<Integer, Node> nodeMapping) {
        int idx = data.indexOf('-');
        int val = Integer.parseInt(data.substring(0, idx));
        
        // children
        String nodes[] = data.substring(idx + 2, data.length() - 1).split(",");
        
        if(!nodeMapping.containsKey(val)) {
            nodeMapping.put(val, new Node(val, new ArrayList<>()));
        }
        Node node = nodeMapping.get(val);
        
        for(String child : nodes) {

            if(child.isEmpty()) {
                // TODO: need to check how empty string comes
                continue;
            }

            // this node creation is safe, because this is tree
            // because node only exist once from root path
            val = Integer.parseInt(child);
            Node childNode = new Node(val, new ArrayList<>());
            nodeMapping.put(val, childNode);
            node.children.add(childNode);
        }

        return node;
    }
	
    // Decodes your encoded data to tree.
    public Node deserialize(String data) {
        if(data.isEmpty()) {
            return null; // edge case
        }

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