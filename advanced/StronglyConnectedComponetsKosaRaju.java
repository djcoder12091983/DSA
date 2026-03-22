public class StronglyConnectedComponetsKosaRaju {

    // dfs Function to reach destination
    boolean dfs(int curr, int des, int[][] adj, int[] vis, int n) {
        if (curr == des) return true;
        vis[curr] = 1;

        for (int i = 1; i <= n; i++) {
            if (adj[curr][i] == 1 && vis[i] == 0) {
                if (dfs(i, des, adj, vis, n)) return true;
            }
        }
        return false;
    }

    // whether there is path from source to destination
    boolean isPath(int src, int des, int[][] adj, int n) {
        int[] vis = new int[n + 1];
        return dfs(src, des, adj, vis, n);
    }

    // strongly connected component of a graph.
    int[][] findSCC(int n, int[][] a, int m) {
        // Stores whether a vertex is part of any SCC
        int[] is_scc = new int[n + 1];

        int[][] adj = new int[n + 1][n + 1];
        for (int i = 0; i < m; i++) {
            int u = a[i][0];
            int v = a[i][1];
            adj[u][v] = 1;
        }

        int[][] result = new int[n][n + 1];
        int sccCount = 0;

        // Traversing all the vertices
        for (int i = 1; i <= n; i++) {
            if (is_scc[i] == 0) {
                int[] scc = new int[n + 1];
                int size = 0;
                scc[++size] = i;

                for (int j = i + 1; j <= n; j++) {
                    if (is_scc[j] == 0 && isPath(i, j, adj, n) && isPath(j, i, adj, n)) {
                        is_scc[j] = 1;
                        scc[++size] = j;
                    }
                }

                // Mark current node as visited in SCC
                is_scc[i] = 1;

                // Store size at index 0 and then elements
                scc[0] = size;
                result[sccCount++] = scc;
            }
        }

        // valid SCCs only
        int[][] finalResult = new int[sccCount][];
        for (int i = 0; i < sccCount; i++) {
            int size = result[i][0];
            finalResult[i] = new int[size];
            for (int j = 0; j < size; j++) {
                finalResult[i][j] = result[i][j + 1];
            }
        }

        return finalResult;
    }
}

public class Main {

    public static void main(String[] args) {
        GfG obj = new GfG();
        int V = 5;
        int[][] edges = {
            {1, 3},
            {1, 4},
            {2, 1},
            {3, 2},
            {4, 5}
        };
        int M = edges.length;

        int[][] ans = obj.findSCC(V, edges, M);
        System.out.println("Strongly Connected Components are:");
        for (int[] scc : ans) {
            for (int x : scc) {
                System.out.print(x + " ");
            }
            System.out.println();
        }
    }
}