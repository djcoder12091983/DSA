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
    TreeNode* bstFromPreorder(vector<int>& preorder) {
        int N = preorder.size();
        if(N == 0) {
            return NULL;
        }
        
        TreeNode *root = new TreeNode(preorder[0]);
        int prev_node = preorder[0];
        stack<TreeNode *> track;
        track.push(root);
        // all nodes are distinct
        int h = 0;
        map<int, int> heights;
        heights.insert({INT_MAX, -1}); // dummy node
        heights.insert({preorder[0], h});
        
        for(int i = 1; i < N; i++) {
            int node = preorder[i];
            if(node < prev_node) {
                // left side
                TreeNode *left = track.top()->left = new TreeNode(node);
                track.push(left);
                h++;
                heights[node] = h;
            } else {
                // right side
                int req_h = (--heights.upper_bound(node))->second;
                while(--h >= req_h) {
                    // reach @ target node
                    track.pop();
                }
                TreeNode *right = track.top()->right = new TreeNode(node);
                track.push(right);
                h = req_h;
                h++;
                heights[node] = h;
            }
            
            prev_node = node;
        }
        return root;
    }
};