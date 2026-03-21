/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     TreeNode *left;
 *     TreeNode *right;
 *     TreeNode(int x) : val(x), left(NULL), right(NULL) {}
 * };
 */
class Solution {
public:
    // ref: <geeksforgeeks>/diameter-of-a-binary-tree/
    int recursive_diameter(TreeNode *root, int &H) {
        int lh = 0, rh = 0;
        int ldiameter = 0, rdiameter = 0;
        if(root == NULL) {
            H = 0;
            return 0;
        }
        
        ldiameter = recursive_diameter(root->left, lh);
        rdiameter = recursive_diameter(root->right, rh);
        
        H = max(lh, rh) + 1;
        
        return max(lh + rh, max(ldiameter, rdiameter));
    }
    
    int diameterOfBinaryTree(TreeNode* root) {
        int H;
        return recursive_diameter(root, H);
    }
};