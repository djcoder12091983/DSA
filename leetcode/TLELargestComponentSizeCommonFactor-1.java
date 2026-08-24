// https://leetcode.com/problems/largest-component-size-by-common-factor/
// TODO Another approach -- TLE FIX

class Solution {
    
    static final int LIMIT = 100000; // as per input
    int spf[] = new int[LIMIT + 1]; // SPF array

    // IDEA is like connecting nodes across prime numbers not the actual number
    // then we will find largest group of prime numbers
    Map<Integer, List<Integer>> graph = new HashMap<>();

    // all factors
    List<Integer> factors(int n) {
        List<Integer> pf = new ArrayList<>(2);
        if (n < 2 || n > LIMIT) {
            return pf;
        }

        while (n > 1) {
            int prime = spf[n];
            pf.add(prime);
            
            // Repeatedly divide n by this prime to strip out all its duplicates
            while (n % prime == 0) {
                n /= prime;
            }
        }

        return pf;
    }

    // create SPF
    void createSPF() {
        for (int i = 1; i <= LIMIT; i++) {
            spf[i] = i;
        }

        for (int i = 4; i <= LIMIT; i += 2) {
            spf[i] = 2;
        }

        for (int i = 3; i * i <= LIMIT; i += 2) {
            if (spf[i] == i) {
                // smallest prime factor for all
                for (int j = i * i; j <= LIMIT; j += i) {
                    if (spf[j] == j) {
                        spf[j] = i;
                    }
                }
            }
        }
    }
    
    public int largestComponentSize(int[] A) {
        // SPF
        createSPF();

        Set<Integer> data = new HashSet<>();

        // create graph of grouped prime factors
        for(int x : A) {

            // note: we need to skip 1
            if(x == 1) {
                continue;
            }

            List<Integer> pf = factors(x);

            // linear connection of prime factors
            int s = pf.size();
            int f = pf.get(0);
            if(!graph.containsKey(f)) {
                graph.put(f, new ArrayList<>(2));
            }
            for(int i = 1; i < s; i++) {
                int u = pf.get(i - 1);
                int v = pf.get(i);

                if(!graph.containsKey(u)) {
                    graph.put(u, new ArrayList<>(2));
                }
                if(!graph.containsKey(v)) {
                    graph.put(v, new ArrayList<>(2));
                }

                // bi-directional
                graph.get(u).add(v);
                graph.get(v).add(u);
            }

            // this set will help, once a data is added under a group
            // then next time we will not add this into another group
            // anyways one number will be part of one group
            data.add(x);
        }

        // check for largest component of prime groups using BFS
        int ans = 1;
        Set<Integer> V = new HashSet<>();
        for(int prime : graph.keySet()) {
            if(!V.contains(prime)) {
                Set<Integer> group = bfs(prime, V);

                // now check what are elements are part of this group
                Iterator<Integer> i = data.iterator();
                int c = 0;
                while(i.hasNext()) {
                    int x = i.next();
                    List<Integer> facts = factors(x);

                    boolean found = false;
                    for(int fact : facts) {
                        if(group.contains(fact)) {
                            found = true;
                            break;
                        }
                    }

                    if(found) {
                        i.remove();
                        c++;
                    }
                }

                // maximize answer
                ans = Math.max(ans, c);
            }
        }
        
        return ans;
    }

    // bfs and returns component-group of primes
    Set<Integer> bfs(int node, Set<Integer> V) {
        Queue<Integer> Q = new LinkedList<>();
        Q.add(node);
        V.add(node); // mark it as visited
        
        Set<Integer> nodes = new HashSet<>(2);
        while(!Q.isEmpty()) {
            node = Q.poll();
            nodes.add(node); // add to group
            
            List<Integer> connections = graph.get(node);
            for(int x : connections) {
                if(!V.contains(x)) {
                     // not visited yet
                     Q.add(x);
                     V.add(x); // mark it as visited right here to add duplicate nodes into Q
                }
            }
        }

        return nodes;
    }
}