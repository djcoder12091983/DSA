/**
 * Definition for binary tree
 * struct TreeNode {
 *     int val;
 *     TreeNode *left;
 *     TreeNode *right;
 *     TreeNode(int x) : val(x), left(NULL), right(NULL) {}
 * };
 */
vector<vector<int> > Solution::zigzagLevelOrder(TreeNode* root) {
    vector<TreeNode *> track; // simulate as queue
    track.push_back(root);
    bool left = true;
    vector<vector<int>> zigzag;
    while(!track.empty()) {
        int l = track.size();
        vector<int> level;
        // takes care of zigzag printing
        if(left) {
            for(int i = 0; i < l; i++) {
                level.push_back(track[i]->val);
            }
        } else {
            for(int i = l - 1; i >= 0; i--) {
                level.push_back(track[i]->val);
            }
        }
        zigzag.push_back(level);
        
        // next levels
        vector<TreeNode *> next;
        for(int i = 0; i < l; i++) {
            TreeNode *node = track[i];
            if(node->left) {
                next.push_back(node->left);
            }
            if(node->right) {
                next.push_back(node->right);
            }
        }
        
        track = next;
        
        left = !left; // zigzag orientation flag
    }
    
    return zigzag;
}
