class Solution {
    public List<Integer> diffWaysToCompute(String exp) {
        List<Integer> ans = new ArrayList<>(); // list of answers

        if(!isExpression(exp)) {
            // single value
            ans.add(Integer.parseInt(exp));

            return ans;
        }

        int N = exp.length();
        for(int i = 0; i < N; i++) {
            char x = exp.charAt(i);
            if(isOperator(x)) {
                // split and recursively call
                List<Integer> left = diffWaysToCompute(exp.substring(0, i));
                List<Integer> right = diffWaysToCompute(exp.substring(i + 1, N));
                for(int j = 0; j < left.size(); j++) {
                    for(int k = 0; k < right.size(); k++) {
                        int val = eval(left.get(j), right.get(k), x);
                        ans.add(val);
                    }
                }
            }
        }

        return ans;
    }

    boolean isExpression(String exp) {
        // contains @ least one operator
        return exp.contains("+") || exp.contains("-") || exp.contains("*");
    }

    boolean isOperator(char x) {
        return x == '+' || x == '-' || x == '*';
    }

    int eval(int x, int y, char op) {
        switch(op) {
            case '+':
                return x + y;
            case '-':
                return x - y;
            case '*':
                return x * y;
            default:
                return 0;
        }
    }
}