// https://leetcode.com/problems/largest-component-size-by-common-factor/
class Solution {
    
    static final int LIMIT = 100000; // as per input
    int spf[] = new int[LIMIT + 1]; // SPF array

    // we will use UNION-FIND to group all primes and their corresponding multiples
    class Node {
        int x; // prime node
        Node parent;
        // track unique multiples to get actual goup data size
        Set<Integer> data = new HashSet<>();
        int h = 1; // height for balancing

        Node(int x) {
            this.x = x;
            this.parent = this; // self point
        }

        int addData(int d) {
            this.data.add(d);
            return this.data.size();
        }
    }

    // union two nodes
    int union(Node node1, Node node2) {
        Node p1 = find(node1);
        Node p2 = find(node2);

        if(p1 == p2) {
            return p1.data.size(); // nothing to do and return data size
        }

        // otehrwise merge - always choose larger height for balance
        int h1 = p1.h;
        int h2 = p2.h;
        if(h1 >= h2) {
            p2.parent = p1;
            p1.h = Math.max(p1.h, 1 + p2.h);
            p1.data.addAll(p2.data);
            // TODO here we can clean P2 data
            return p1.data.size();
        } else {
            p1.parent = p2;
            p2.h = Math.max(p2.h, 1 + p1.h);
            p2.data.addAll(p1.data);
            // TODO here we can clean P1 data
            return p2.data.size();
        }
    }

    Node find(Node node) {
        Node x = node;
        while(x.parent != x) {
            x = x.parent;
        }

        return x;
    }

    // IDEA is like connecting nodes across prime numbers not the actual number
    // then we will find largest group of prime numbers
    Map<Integer, Node> graph = new HashMap<>();

    // all factors
    List<Integer> factors(int n) {
        List<Integer> pf = new ArrayList<>(2);

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

        int ans = 1;
        for(int x : A) {

            if(x == 1) {
                // we will skip 1, because 1 is prime and can't be grouped with other values
                continue;
            }

            List<Integer> facts = factors(x);
            // System.out.println(x + " -> " + facts);
            // now find and union all prime factors

            int first = facts.get(0);
            Node node = graph.get(first);
            if(node == null) {
                node = new Node(first);
                graph.put(first, node);
            }
            int c = find(node).addData(x); // add data to parent node

            // System.out.println("Fact: " + first + " Data: " + c);

            // even if the factors count = 1 then also we need to update out answer
            ans = Math.max(ans, c);

            int s = facts.size();
            for(int i = 1; i < s; i++) {
                int u = facts.get(i - 1);
                int v = facts.get(i);
                
                // always u is created already, so we will create v only
                Node nodev = graph.get(v);
                if(nodev == null) {
                    nodev = new Node(v);
                    graph.put(v, nodev);
                }
                find(nodev).addData(x); // add data to parent-node
                
                // merge u and v and update answer with resultant number of grouped data under same prime component
                c = union(graph.get(u), nodev);
                // System.out.println("Merging U: " + u + " V: " + v + " Result-C: " + c);
                ans = Math.max(c, ans);
            }
        }

        return ans;
    }
}