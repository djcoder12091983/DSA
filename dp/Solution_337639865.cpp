typedef struct symbol_data {
    char symbol;
    int idx;
    
    //data(){}
    symbol_data(char symbol, int idx) {
        this->symbol = symbol;
        this->idx = idx;
    }
} symbol_data;

class Solution {
public:
    int longestValidParentheses(string S) {
        // note: add add symbols into stack along with their positions
        stack<symbol_data> track;
        int l = S.length();
        for(int i = 0; i < l; i++) {
            char ch = S[i];
            if(ch == ')') {
                if(!track.empty() && track.top().symbol == '(') {
                    track.pop(); // nutralize
                } else {
                    // sequence break
                    track.push(symbol_data(ch, i)); // odd one
                }
            } else if(ch == '(') {
                track.push(symbol_data(ch, i));
            }
        }
        
        if(track.empty()) {
            // full length
            return l;
        }
        // find out maximum from stired odd symbols and their positions
        int max_l = 0;
        int last = l;
        while(!track.empty()) {
            int t_idx = track.top().idx;
            int c = last - t_idx - 1;
            max_l = max(c, max_l);
            last = t_idx;
            track.pop();
        }
        // last computation
        max_l = max(last, max_l);
        
        return max_l;
    }
};