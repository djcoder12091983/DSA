/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public TreeNode replaceValueInTree(TreeNode root) {
        Queue<TreeNode> Q = new LinkedList<>();
        HashMap<Integer, Integer> map = new HashMap<>();
        
        // mapping
        Q.add(root);
        int level = 0;
        while(Q.size() > 0) {

            int s = Q.size();
            int sum = 0;
            for(int i = 0; i < s; i++) {
                TreeNode node = Q.poll();
                sum = sum + node.val;
                
                if(node.left != null) {
                    Q.add(node.left);
                }
                
                if(node.right != null) {
                    Q.add(node.right);
                }
            }
            
            map.put(level, sum);
            level++;
        }
        
        System.out.println(map);
        
        // update the tree with cousins
        root.val = 0;
        Q.add(root);
        level = 0;
        while(Q.size() > 0) {

            int s = Q.size();
            int nextsum = 0;
            if(map.containsKey(level + 1)) {
                nextsum = map.get(level + 1);
            }
            for(int i = 0; i < s; i++) {
                TreeNode node = Q.poll();
                
                int currentsum = 0;
                
                if(node.left != null) {
                    currentsum = currentsum + node.left.val;
                }
                
                if(node.right != null) {
                    currentsum = currentsum + node.right.val;
                }
                
                if(node.left != null) {
                    node.left.val = nextsum - currentsum;
                    Q.add(node.left);
                }
                
                if(node.right != null) {
                    node.right.val = nextsum - currentsum;
                    Q.add(node.right);
                }
            }
            
            level++;
        }
        
        return root;
    }
}