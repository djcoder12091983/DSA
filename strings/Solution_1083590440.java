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
    String ans = "";
    public String smallestFromLeaf(TreeNode root) {
        getAllPaths(root, new StringBuilder());
        return ans;
    }
    public void getAllPaths(TreeNode root, StringBuilder str) {
        if (root == null) {
            return;
        }

        str.insert(0, (char)(root.val + 97));

        if (root.left == null && root.right == null) {
            String s = str.toString();
            if (ans.equals("") || ans.compareTo(s) > 0) {
                ans = s;
            }
        }
        

        getAllPaths(root.left, str);
        getAllPaths(root.right, str);
        
        str.deleteCharAt(0);
    }
}