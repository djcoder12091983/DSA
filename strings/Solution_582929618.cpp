class Solution {
public:
    
    map<int, char> romans; 
    Solution() {
        romans.insert({1, 'I'});
        romans.insert({5, 'V'});
        romans.insert({10, 'X'});
        romans.insert({50, 'L'});
        romans.insert({100, 'C'});
        romans.insert({500, 'D'});
        romans.insert({1000, 'M'});
    }
    
    string romanize(int x) {
        string roman;
        if(romans.find(x) != romans.end()) {
            roman.push_back(romans[x]);
        } else {
            int lower = 1, upper = 5000;
            for(auto rmap : romans) {
                if(rmap.first < x) {
                    lower = rmap.first;
                } else {
                    upper = rmap.first;
                    break;
                }
            }
            if(romans.find(upper - x) != romans.end()) {
                // pair found
                roman.push_back(romans[upper - x]);
                roman.push_back(romans[upper]);
            } else {
                roman.push_back(romans[lower]);
                // we will recursively solve it
                roman.append(romanize(x - lower));
            }
        }
        
        return roman;
    }
    
    string intToRoman(int N) {
        vector<string> romantokens;
        
        int power = 1; // 10^0
        while(N > 0) {
            int d = N%10;
            if(d > 0) {
                // skipping 0 remainder
                int x = power * d;

                // now get roman for x
                romantokens.push_back(romanize(x));
            }
            N = N / 10;
            power = power * 10;
        }
        
        reverse(romantokens.begin(), romantokens.end());
        string roman;
        for(string token : romantokens) {
            roman.append(token);
        }
        //reverse(roman.begin(), roman.end());
        return roman;
    }
};