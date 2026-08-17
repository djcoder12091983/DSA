class Solution {

    class Node {
        char digit;
        boolean leaf = false;
        Node children[] = new Node[10]; // 10 digits

        Node(char x) {
            this.digit = x;
        }

        boolean containsKey(char x) {
            return this.children[x - '0'] != null;
        }

        void put(char x, Node node) {
            this.children[x - '0'] = node;
        }

        Node get(char x) {
            return this.children[x - '0'];
        }
    }

    // we will find prefix in tree using some limit
    boolean search(Node root, char[] prefix, int limit) {
        int N = prefix.length;
        Node node = root;
        for(int i = 0; i < N; i++) {
            if(limit == 0) {
                // partial match
                return true;
            }

            char x = prefix[i];
            if(node.containsKey(x)) {
                node = node.get(x);
            } else {
                return false;
            }

            limit--;
        }

        // full match
        return node.leaf; // if last node is leaf
    }

    // add 2 power to prefix tree
    void add(Node root, char digit[]) {
        int N = digit.length;
        Node node = root;
        for(int i = 0; i < N; i++) {
            char x = digit[i];
            if(!node.containsKey(x)) {
                // not exists then create it
                node.put(x, new Node(x));
            }

            node = node.get(x);
        }
        node.leaf = true; // last node
    }

    // find digits
    char[] digit(long n) {
        List<Character> digits = new ArrayList<>();
        while(n > 0) {
            int rem = (int)(n % 10);
            digits.add((char)(rem + '0'));
            n = n / 10;
        }

        int N = digits.size();
        char x[] = new char[N];
        for(int i = 0; i < N; i++) {
            x[i] = digits.get(i);
        }

        return x;
    }

    public boolean reorderedPowerOf2(int n) {
        // will go with prefix tree to match the path i am taking that will lead to solution or what
        // we will build prefix tree from left significant digit

        char d[] = digit(n);
        
        Node root = new Node('0'); // dummy root node

        // storing all 2 powers into prefix tree
        long limit = (long)(Math.pow(10, d.length));
        long p = 1;
        while(p < limit) {
           char x[] = digit(p);
           add(root, x);

           p = p * 2;
        }

        return generateAndCheck(d, 0, root);
    }

    boolean generateAndCheck(char digit[], int pos, Node root) {
        int N = digit.length;
        if(pos == N) {
            // permutation, do full check
            return search(root, digit, pos);
        }

        // generate permutation
        boolean visited[] = new boolean[10]; // visited digit to handle duplicates
        boolean flag = false;
        for(int i = pos; i < N; i++) {

            char x = digit[i];

            if(visited[x - '0']) {
                // already visited
                continue;
            }

            // TODO i think prefix tree will discard leading 0
            // keeping it for reference as well as recursion calls pruning to make it faster
            if(pos == N - 1 && x == '0') {
                continue; // avoid leading zero
            }

            // recursive calls pruning fixed prefix does not match anyways it will not lead to solution
            if(!search(root, digit, pos)) {
                // prefix not found
                continue;
            }

            // swap ith character with pos
            char t = digit[pos];
            digit[pos] = digit[i];
            digit[i] = t;

            flag = generateAndCheck(digit, pos + 1, root);
            if(flag == true) {
                // found 2 power
                break;
            }

            // reswap
            t = digit[pos];
            digit[pos] = digit[i];
            digit[i] = t;
        }

        return flag;
    }
}