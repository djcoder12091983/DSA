int Solution::solve(int A) {
    // reference <leet_code>/articles/number-of-digit-one/
    int C = 0;
    for (long long i = 1; i <= A; i *= 10) {
        long long divider = i * 10;
        C += (A / divider) * i + min(max(A % divider - i + 1, 0LL), i);
    }
    return C;
}