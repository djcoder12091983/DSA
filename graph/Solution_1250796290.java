class Solution {

    public boolean isBipartite(int[][] graph) {

        int m = graph.length;

        int visited[] = new int[m];

        Arrays.fill(visited, -1);

        Queue < Integer > qu = new LinkedList < > ();

        for(int node = 0; node < m; node++) {

            if(visited[node] != -1) {
                continue;
            }

        qu.add(node);

        visited[node] = 0;

        while (!qu.isEmpty()) {

            int p = qu.poll(); //0

            int n = graph[p].length;

            int currentState = visited[p]; //0
            //System.out.prinltn(p + " => " + currentState);

            for (int i = 0; i < n; i++) {

                int temp = graph[p][i]; //1

                if (visited[temp] == currentState) {
                    //System.out.println(temp + " " + visited[temp]);

                    return false;

                } else if (visited[temp] == -1) {

                    visited[temp] = currentState ^ 1;

                    qu.add(temp);

                }

            }

        }
        }

        return true;

    }

}