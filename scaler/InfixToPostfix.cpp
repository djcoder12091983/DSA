string Solution::solve(string A) {
    // operators rank
    unordered_map<char, int> operators_rank;
    operators_rank['+'] = 1;
    operators_rank['-'] = 1;
    operators_rank['*'] = 2;
    operators_rank['/'] = 2;
    operators_rank['^'] = 3;
    
    // maintain two stacks
    stack<char> operands_expr;
    stack<char> operators;
    
    int N = A.size();
    for(int i = 0; i < N; i++) {
        if(A[i] == ')') {
            // need to evaluate sub expression
            while(!operators.empty()) {
                char op = operators.top();
                if(op == '(') {
                    // sub expr evaluation done
                    operators.pop();
                    break;
                }
                if(operators_rank[A[i]] > operators_rank[op]) {
                    // sequence break
                    break;
                }
                operands_expr.push(op); // postfix expr
                operators.pop();
            }
        } else if(operators_rank.find(A[i]) != operators_rank.end()) {
            // operator
            if(operators.empty()) {
                operators.push(A[i]);
            } else {
                // need to check operator precedence
                char op = operators.top();
                if(op != '(' && operators_rank[A[i]] <= operators_rank[op]) {
                    // need to apply
                    while(!operators.empty()) {
                        op = operators.top();
                        if(op == '(' || operators_rank[A[i]] > operators_rank[op]) {
                            // sequence break
                            break;
                        }
                        operands_expr.push(op); // postfix expr
                        operators.pop();
                    }
                }
                operators.push(A[i]);
            }
        } else if(A[i] == '(') {
            // subexpr starts
            operators.push(A[i]);
        } else {
            // operand
            operands_expr.push(A[i]);
        }
    }
    
    // remaining operators to be added
    while(!operators.empty()) {
        char op = operators.top();
        if(op == '(') {
            break;
        }
        operands_expr.push(op);
        operators.pop();
    }
    
    // final result (postfix expr)
    string postfix_expr;
    while(!operands_expr.empty()) {
        postfix_expr.push_back(operands_expr.top());
        operands_expr.pop();
    }
    // need to reverse to get the original expr
    reverse(postfix_expr.begin(), postfix_expr.end());
    
    return postfix_expr;
}
