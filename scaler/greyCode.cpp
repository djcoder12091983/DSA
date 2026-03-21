vector<int> Solution::grayCode(int N) {
    vector<string> gray_codes;
    
    gray_codes.push_back("0");
    gray_codes.push_back("1");
    
    int i, j;
    int l = 1 << N;
    for(i = 2; i < l; i = i << 1) {
        for(j = i - 1; j >= 0; j--) {
            gray_codes.push_back(gray_codes[j]);
        }
        for(j = 0; j < i; j++) {
            gray_codes[j] = "0" + gray_codes[j];
        }
        for(j = i; j < 2 * i; j++) {
            gray_codes[j] = "1" + gray_codes[j];
        }
    }
    
    vector<int> numeric_codes;
    for(i = 0; i < l; i++) {
        int g_code = 0;
        for(j = 0;  j < N; j++) {
            g_code += (gray_codes[i][j] - 48) * (1 << j);
        }
        numeric_codes.push_back(g_code);
    }
    
    return numeric_codes;
}
