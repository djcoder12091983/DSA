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

    public List<TreeNode> generateTrees(int A[]) {
        int n = A.length;
        //System.out.println(Arrays.toString(A) + " " + n);
        if(n == 0) {
            ArrayList<TreeNode> trees = new ArrayList<>(1);
            trees.add(null);
            
            return trees;
        } else {
            ArrayList<TreeNode> trees = new ArrayList<>();
            // i will try out every A[i] as root of the BST
            for(int i = 0; i < n; i++) {
                // try to get how many ways i can get BSTs using left values
                // that i will add as left subtree
                int s = i;
                int L[] = new int[s];
                for(int j = 0; j < s; j++) {
                    L[j] = A[j];
                }
                List<TreeNode> leftBSTs = generateTrees(L);

                // try to get how many ways i can get BSTs using right values
                // that i will add as right subtree
                s = n - i - 1;
                int R[] = new int[s];
                for(int j = 0; j < s; j++) {
                    R[j] = A[j + i + 1];
                }
                List<TreeNode> rightBSTs = generateTrees(R);
                
                // add to left and right
                for(TreeNode left : leftBSTs) {
                    for(TreeNode right : rightBSTs) {
                        // root node
                        TreeNode root = new TreeNode(A[i]);
                        root.left = left;
                        root.right = right;

                        trees.add(root);
                    }
                }
            }
            return trees;
        }
    }
    
    public List<TreeNode> generateTrees(int n) {
        int A[] = new int[n];
        for(int i = 0; i < n; i++) {
            A[i] = i + 1;
        }
        return generateTrees(A);
    }
}