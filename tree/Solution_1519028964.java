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
    
    public boolean hasPathSum(TreeNode root, int targetSum) {

        //return hasSum(root, targetSum, 0);

        if(root == null) {
            return false;
        }

        
        if(root.left == null && root.right == null) {
            return root.val == targetSum;
        }

        if(hasPathSum(root.left,targetSum - root.val) || hasPathSum(root.right,targetSum - root.val)){
            return true;
        }
        return false;

    }

    boolean hasSum(TreeNode root, int targetSum, int sum) {
        
        if(root == null){
            return false;
        }
        
        if(root.left == null && root.right == null) {
            sum = sum + root.val;
            return sum == targetSum;
        }
        
        if(hasSum(root.left,targetSum, sum + root.val) || hasSum(root.right,targetSum, sum + root.val)){
            return true;
        }
        return false;
    }
}