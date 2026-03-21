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
    public List<List<Integer>> ans=new ArrayList<>();
    public void check(ArrayList<Integer> arr, TreeNode root,int targetSum){
        if(root==null){
            return;
        }

        targetSum=targetSum-root.val;
        arr.add(root.val);
        
        if(targetSum == 0 && root.left == null && root.right == null) {
            ans.add(new ArrayList<Integer>(arr));
        }
        
        check(arr,root.left,targetSum);
        check(arr,root.right,targetSum);
        arr.remove(arr.size()-1);
    }
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        ArrayList<Integer> arr=new ArrayList<>();
        check(arr,root,targetSum);
        return ans;
    }
}