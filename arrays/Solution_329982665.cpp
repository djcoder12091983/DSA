typedef struct str_data {
    string s;
    int idx;
    str_data(string s, int idx) {
        this->s = s;
        this->idx = idx;
    }
} str_data;

// sort comparison
bool data_compare(str_data d1, str_data d2) {
    int c = d1.s.compare(d2.s);
    return c <= 0;
}

class Solution {
public:
    vector<vector<string>> groupAnagrams(vector<string>& A) {
        vector<str_data> sorted_A;
        int N = A.size();
        for(int i = 0; i < N; i++) {
            string s = A[i];
            sort(s.begin(), s.end());
            sorted_A.push_back(str_data(s, i));
        }
        // now sort
        sort(sorted_A.begin(), sorted_A.end(), data_compare);
        vector<vector<string>> groups;
        for(int i = 0; i < N;) {
            vector<string> group;
            group.push_back(A[sorted_A[i].idx]);
            int j = i + 1;
            while(j < N) {
                if(sorted_A[i].s != sorted_A[j].s) {
                    break;
                }
                group.push_back(A[sorted_A[j].idx]);
                j++;
            }
            groups.push_back(group);
            i = j;
        }
        return groups;
    }
};