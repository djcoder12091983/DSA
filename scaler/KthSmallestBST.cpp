/**
 * Definition for binary tree
 * struct TreeNode {
 *     int val;
 *     TreeNode *left;
 *     TreeNode *right;
 *     TreeNode(int x) : val(x), left(NULL), right(NULL) {}
 * };
 */
void inorder_traversal(TreeNode *node, vector<int> &inorder) {
    if(node->left) {
        inorder_traversal(node->left, inorder);
    }
    inorder.push_back(node->val);
    if(node->right) {
        inorder_traversal(node->right, inorder);
    }
}

int Solution::kthsmallest(TreeNode* root, int B) {
    // inorder traversal
    // note: augmented tree could help to search in log(height)
    vector<int> inorder;
    inorder_traversal(root, inorder);
    
    return inorder[B - 1];
}
