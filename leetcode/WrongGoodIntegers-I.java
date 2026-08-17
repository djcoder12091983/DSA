// https://leetcode.com/problems/find-the-count-of-good-integers/

class Solution {

    // track if two numbers share same frequency of digits
    HashSet<String> unique = new HashSet<>();

    // generate palindrome
    long generate(int x, int n) {
        int half = (n % 2 == 0) ? x : x / 10;

        int rev = 0;
        int p = 1;
        while(half > 0) {
            int rem = half % 10;
            rev = rev * 10 + rem;

            half = half / 10;
            p = p * 10;
        }

        return 1L * x * p +  rev;
    }

    // factorial
    long f(int x) {
        long ans = 1;
        for(int i = 1; i <= x; i++) {
            ans *= i;
        }
        return ans;
    }

    // given a palindrome number which is divisble by k
    // now how many ways we can arrange this number so that it can be rearranged to palindrome back
    long count(long x) {
        
        //long org = x;
        // 0 to 9 frequency
        int digits[] = new int[10];
        Arrays.fill(digits, 0); // default 0 fill
        int d = 0;
        while(x > 0) {
            int rem = (int)(x % 10);
            digits[rem]++;
            x = x / 10;
            d++;
        }

        // now rearrange
        long t = 1;
        String key = ""; // key to track if two numbers share same frequency
        for(int i = 0; i < 10; i++) {
            if(digits[i] > 0) {
                t = t * f(digits[i]);
                key += i + "-" + digits[i] + "/";
            }
        }

        if(unique.contains(key)) {
            // already computed so avoid duplicate counts
            return 0;
        }

        long c = f(d) / t;
        //System.out.println("ORG: " + org + " C1: " + c);

        // now exclude prefix 0'x
        int l = digits[0];
        for(int i = 1; i <= l; i++) {
            digits[0]--;
            t = 1;
            for(int j = 0; j < 10; j++) {
                if(digits[j] > 0) {
                    t = t * f(digits[j]);
                }
            }

            c -= f(d - i) / t;
            //System.out.println("ORG: " + org + " C2: " + c);
        }

        //System.out.println("Palindrome: " + org + " " + c);

        unique.add(key);

        return c;
    }

    public long countGoodIntegers(int n, int k) {
        // we will go with half number and from there generate the palindrome
        // from that palindrome number can arrange the digits which can be again formed as palindrome

        int middle = (n + 1) / 2; // handle odd and even length

        int start = Double.valueOf(Math.pow(10, middle - 1)).intValue();
        int end = start * 10;

        long c = 0;
        for(int x = start; x < end; x++) {
            long palindrome = generate(x, n);
            if(palindrome % k == 0) {
                // divisible not contribute to count
                c += count(palindrome);
            }
        }

        return c;

    }
}