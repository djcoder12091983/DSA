public class Codec {
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if(root == null){
            return "";
        }
        Queue<TreeNode> q = new LinkedList();
        StringBuilder builder = new StringBuilder();
        q.add(root);
        while(!q.isEmpty()){
            TreeNode front = q.poll();
            if(front == null){
                builder.append("$");
                builder.append(",");
                continue;
            }
            builder.append(front.val);
            builder.append(",");
            q.add(front.left);
            q.add(front.right);
        }
        return builder.toString();
    }
    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if(data.isEmpty()){
            return null;
        }
        //System.out.println(data);
        String[] nodes = data.substring(0,data.length()-1).split(",");
        TreeNode resNode = new TreeNode(Integer.parseInt(nodes[0]));
        Queue<TreeNode> q = new LinkedList();
        q.add(resNode);
        int i = 1;
        while(i < nodes.length){
            TreeNode front = q.poll();
            if(!nodes[i].equals("$")){
                front.left = new TreeNode(Integer.parseInt(nodes[i]));
                q.add(front.left);
            }
            i++;
            if(!nodes[i].equals("$")){
                front.right = new TreeNode(Integer.parseInt(nodes[i]));
                q.add(front.right);
            }
            i++;
        }
        return resNode;
    }
}
// Your Codec object will be instantiated and called as such:
// Codec ser = new Codec();
// Codec deser = new Codec();
// TreeNode ans = deser.deserialize(ser.serialize(root));