typedef long long int LL;

string Solution::solve(int A) {
    LL X = A;
    int d = 8;
    LL N = 0L;
    // try to place MSB recursively till d becomes 0
    while(d >= 1) {
        // base case
        if(X == 1L) {
            N += (1L << d) - 1;
            break;
        }
        LL s = 1L;
        LL f = 1L;
        int c = 1;
        // check how many combinations can be possible till A
        while(1) {
            f = (f * (d + c - 1)) / c;
            if(s + f > X) {
                break;
            }
            s += f;
            c++;
        }
        X -= s;
        if(X == 0L) {
            // perfect match
            N += ((1L << d) - 1) << (c - 1);
            break;
        } else {
            // process remaining digits recursively
            N += 1L << (d + c - 1);
            d--;
        }
    }
    return to_string(N);
}
