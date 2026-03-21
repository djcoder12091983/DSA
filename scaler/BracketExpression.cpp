char get_sign(char sign1, char sign2) {
    if(sign1 == sign2) {
        return '+';
    } else {
        return '-';
    }
}

void simplify(string A, int positives[], int negatives[]) {
    if(A[0] != '(') {
        // append brackets @ first and end by force
        A = '(' + A + ')';
    }
    // simplify A using stack
    int N = A.length();
    stack<char> expr;
    for(int i = 0; i < N; i++) {
        if(A[i] == ')') {
            // need to work on
            stack<pair<char, char>> t; // sub expression
            while(1) {
                char symbol = expr.top();
                expr.pop();
                char next = expr.top();
                if(next == '(') {
                    t.push({symbol, '+'});
                    expr.pop();
                    break;
                }
                t.push({symbol, next});
                expr.pop();
                if(expr.top() == '(') {
                    expr.pop();
                    break;
                }
            }
            // evaluate and push back to original stack
            char sign = '+';
            if(!expr.empty()) {
                sign = expr.top();
                expr.pop();
            }
            while(!t.empty()) {
                pair<char , char> p = t.top();
                expr.push(get_sign(sign, p.second));
                expr.push(p.first);
                
                t.pop();
            }
        } else {
            expr.push(A[i]);
        }
    }
    // map positives/negatives
    while(!expr.empty()) {
        char symbol = expr.top();
        expr.pop();
        char sign = expr.top();
        expr.pop();
        if(sign == '+') {
            positives[symbol - 97]++;
        } else {
            negatives[symbol - 97]++;
        }
    }
}

int Solution::solve(string A, string B) {
    // simplify
    int positives_a[26], positives_b[26], negatives_a[26], negatives_b[26];
    memset(positives_a, 0, sizeof(positives_a));
    memset(positives_b, 0, sizeof(positives_b));
    memset(negatives_a, 0, sizeof(negatives_a));
    memset(negatives_b, 0, sizeof(negatives_b));
    simplify(A, positives_a, negatives_a);
    simplify(B, positives_b, negatives_b);
    
    // map match
    bool ok = true;
    for(int i = 0; i < 26; i++) {
        if(positives_a[i] != positives_b[i]) {
            // not equal
            ok = false;
            break;
        }
        if(negatives_a[i] != negatives_b[i]) {
            // not equal
            ok = false;
            break;
        }
    }
    
    return ok;
}
