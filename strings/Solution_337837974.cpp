class Solution {
public:
    string minRemoveToMakeValid(string S) {
        stack<char> track;
        int l = S.length();
        stack<char> T;
        for(int i = 0; i < l; i++) {
            bool ok = true;
            if(S[i] == '(') {
                track.push(S[i]);
            } else if(S[i] == ')') {
                if(!track.empty() && track.top() == '(') {
                    // nutralize
                    track.pop();
                } else {
                    // odd one, remove it
                    ok = false;
                }
            }
            
            if(ok) {
                T.push(S[i]);
            }
        }
        
        // remove extra paranthesis if left
        string modified;
        while(!T.empty()) {
            char ch = T.top();
            if(ch == '(') {
                if(!track.empty()) {
                    // need to remove it
                    track.pop();
                } else {
                    modified.push_back(ch);
                }
            } else {
                modified.push_back(ch);
            }
            
            T.pop();
        }
        
        // reverse the string
        reverse(modified.begin(), modified.end());
        return modified;
    }
};