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
    // note: level order traversal
    vector<TreeNode *> track;
    track.push_back(root);
    vector<int> right_view;
    while(!track.empty()) {
        vector<TreeNode *> next_levels;
        int l = track.size();
        for(int i = 0; i < l; i++) {
            if(track[i]->left) {
                next_levels.push_back(track[i]->left);
            }
            if(track[i]->right) {
                next_levels.push_back(track[i]->right);
            }
        }
        // update right view
        right_view.push_back(track[l - 1]->val);
        
        // next levels
        track = next_levels;
    }
    
    return right_view;
}
