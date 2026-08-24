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

// decorator of tree node with ID
class TreeNode {
    int id;
    Node node;

    TreeNode(int id, Node node) {
        this.id = id;
        this.node = node;
    }
}

class Codec {
    // Encodes a tree to a single string.
    public String serialize(Node root) {
        if(root == null) {
            return ""; // edge case
        }

        int id = 0;
        Queue<TreeNode> Q = new LinkedList<>();
        Q.add(new TreeNode(id, root));
        id++;

        StringBuilder ans = new StringBuilder();
        List<Integer> idlist = new ArrayList<>();

        while(!Q.isEmpty()) {
            int s = Q.size();
            for(int i = 0; i < s; i++) {
                TreeNode node = Q.poll();
                
                idlist.add(node.node.val);
                ans.append(node.id).append("-[");
                
                List<Node> children = node.node.children;
                int N = children == null ? 0 : children.size();

                for(int j = 0; j < N; j++) {
                    Node child = children.get(j);

                    // psuh to Queue for next level traversal
                    Q.add(new TreeNode(id, child));
                    ans.append(id);

                    if(j < N - 1) {
                        ans.append(",");
                    }

                    id++;
                }

                ans.append("]").append("|");
            }
        }

        StringBuilder idtxt = new StringBuilder();
        idtxt.append("[");
        int N = idlist.size();
        for(int i = 0; i < N - 1; i++) {
            idtxt.append(idlist.get(i)).append(',');
        }
        idtxt.append(idlist.get(N - 1)).append("]#");

        return idtxt.append(ans).toString();
    }

    Node convert(String data, Node[] nodeMapping) {
        int idx = data.indexOf('-');
        int id = Integer.parseInt(data.substring(0, idx));
        
        // children
        String nodes[] = data.substring(idx + 2, data.length() - 1).split(",");
        Node node = nodeMapping[id];
        
        for(String child : nodes) {

            if(child.isEmpty()) {
                // TODO: need to check how empty string comes
                continue;
            }

            id = Integer.parseInt(child);
            Node childNode = nodeMapping[id];
            node.children.add(childNode);
        }

        return node;
    }
	
    // Decodes your encoded data to tree.
    public Node deserialize(String data) {
        if(data.isEmpty()) {
            return null; // edge case
        }

        // System.out.println(data);

        // create all nodes using idlist
        String token[] = data.split("#");
        String idtxt = token[0];
        String idlist[] = idtxt.substring(1, idtxt.length() - 1).split(",");
        
        int N = idlist.length;
        Node[] nodeMapping = new Node[N];
        for(int i = 0; i < N; i++) {
            // create nodes with empty children
            nodeMapping[i] = new Node(Integer.parseInt(idlist[i]), new ArrayList<>(2));
        }
        

        data = token[1];
        String treeNodes[] = data.split("\\|");
        int n = treeNodes.length;

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