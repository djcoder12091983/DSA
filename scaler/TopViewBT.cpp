/**
 * Definition for binary tree
 * struct TreeNode {
 *     int val;
 *     TreeNode *left;
 *     TreeNode *right;
 *     TreeNode(int x) : val(x), left(NULL), right(NULL) {}
 * };
 */
vector<int> Solution::solve(TreeNode* root) {
    vector<int> top_view;
    // left side recursive
    TreeNode *node = root;
    while(node) {
        top_view.push_back(node->val);
        node = node->left;
    }
    // right side recursive
    node = root->right;
    while(node) {
        top_view.push_back(node->val);
        node = node->right;
    }
    
    return top_view;
}
