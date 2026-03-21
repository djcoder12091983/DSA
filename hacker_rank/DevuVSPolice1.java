import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class DevuVSPolice1 {
    
    static long gcd(long a, long b) {
        if (a < b) {
            // exchange
            long t = a;
            a = b;
            b = t;
        }
        long r;
        while((r = a % b) != 0) {
            r = a % b;
            a = b;
            b = r;
        }
        return b;
    }
    
    static List<Long> primefactors(long n) {
        long limit = Double.valueOf(Math.ceil(Math.sqrt(n))).intValue();
        List<Long> factors = new ArrayList<Long>(2);
        long c = 1;
        boolean found = false;
        // all prime factors are below 50
        while(++c <= limit) {
            if(n % c == 0) {
                long q = n / c;
                factors.add(c);
                if (q != 1) {
                    // exclude 1
                    // next recursive call
                    factors.addAll(primefactors(q));
                }
                found = true;
                break;
            }
        }
        if(!found) {
            // no factors found
            factors.add(n);
        }
        return factors;
    }
    
    static long exponentiation(long base, long exp, long N) {
        if(base == 0) {
            if(exp == 0) {
                // 0^0 = 1
                return 1 % N;
            } else {
                return 0;
            }
        }
        // base^0 = 1
        if(exp == 0) {
            return 1;
        }
        long t = 1L;
        while (exp > 0) {
            // for cases where exponent
            // is not an even value
            if (exp % 2 != 0) {
                t = (t * base) % N;
            }

            base = (base * base) % N;
            exp /= 2;
        }
        return t % N;
    }
    
    static class Factor {
        long mul;
        long c;
        
        Factor(long c, long mul) {
            this.c = c;
            this.mul = mul;
        }
    }
    
    // http://mathworld.wolfram.com/CarmichaelFunction.html
    // https://en.wikipedia.org/wiki/Carmichael_function
    // https://math.stackexchange.com/questions/2865076/power-of-power-modulo-with-non-square-free-numbers
    static long lamda(Map<Long, Factor> fmap) {
        List<Long> lamdas = new ArrayList<Long>();
        for(long f : fmap.keySet()) {
            long l;
            Factor detail = fmap.get(f);
            long c = detail.c;
            long mul = detail.mul;
            //System.out.println(f + " " + c + " " + mul);
            if(f == 2 && c >= 3) {
                l = mul / (2 * f);
            } else {
                if(c == 1) {
                    l = f - 1;
                } else {
                    l = (mul / f) * (f - 1);
                }
            }
            lamdas.add(l);
        }
        
        // LCM of lamdas
        long s = lamdas.size();
        //System.out.println(lamdas);
        if(s == 1) {
            return lamdas.get(0);
        } else {
            long first = lamdas.get(0);
            long p = first;
            long lcm = first;
            for(int i = 1; i < s; i++) {
                long l = lamdas.get(i);
                p *= l;
                lcm = p / gcd(lcm, l);
                p = lcm; // reset
            }
            // lcm
            return lcm;
        }
    }
    
    static long exponentiation(long n1, long k1, long n2, long k2, long n) {
        if(n == 1) {
            // best cases
            return 0;
        }
        long base = exponentiation(n1, k1, n);
        if(base == 0) {
            // best cases
            if(n2 == 0 && k2 != 0) {
                return 1 % n;
            } else {
                return 0;
            }
        }
        long pbase;
        if(n2 == 0) {
            // best cases
            if(k2 == 0) {
                pbase = 1;
            } else {
                pbase = 0;
            }
        } else {
            List<Long> factors = primefactors(n);
            //System.out.println(factors);
            Map<Long, Factor> fmap = new HashMap<Long, Factor>();
            long expmax = 0;
            for(long f : factors) {
                Factor detail = fmap.get(f);
                Factor newf;
                if(detail == null) {
                    newf = new Factor(1L, f);
                } else {
                    newf = new Factor(detail.c + 1, f * detail.mul);
                }
                fmap.put(f, newf);
                if(newf.c > expmax) {
                    expmax = newf.c;
                }
            }
            long lamda = lamda(fmap);
            pbase = exponentiation(n2, k2, lamda);
            if(pbase < expmax && n2 > 1 && k2 > 0) {
                // Exponential cycle length
                pbase += lamda;
            }
        }
        long m = exponentiation(base, pbase, n);
        return m;
    }
    
    public static void main(String[] args) throws Exception {
        //System.out.println(exponentiation(2, 1, 1, 2, 80968));
        //System.setIn(new FileInputStream("/home/dspace/debasis/NDL/HackerRank/data/dvp_input01.txt"));
        //BufferedReader oreader = new BufferedReader(new FileReader("/home/dspace/debasis/NDL/HackerRank/data/dvp_output01.txt"));
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        for(int i = 0; i < n; i++) {
            StringTokenizer tokens = new StringTokenizer(reader.readLine());
            long n1 = Long.parseLong(tokens.nextToken());
            long k1 = Long.parseLong(tokens.nextToken());
            long n2 = Long.parseLong(tokens.nextToken());
            long k2 = Long.parseLong(tokens.nextToken());
            long N = Long.parseLong(tokens.nextToken());
            
            //long eanswer = Long.parseLong(oreader.readLine());
            long answer = exponentiation(n1, k1, n2, k2, N);
            /*if(eanswer != answer) {
                // mismatch
                System.out.println(n1 +" " + k1 + " " + n2 + " " + k2 + " " + N);
                System.out.println("Expected: " + eanswer + " Actual: " + answer);
            }*/
            System.out.println(answer);
        }
        
        reader.close();
        //oreader.close();
    }
}