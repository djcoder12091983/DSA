class Solution {
    public int findCircleNum(int[][] isConnected) {
        int nodes = isConnected[0].length;
        
        int count = 0;
        int visited[] = new int[nodes];

        for(int i = 0 ; i < nodes ; i++){
            if(visited[i] == 0){
                count++;
                dfs(isConnected,visited,i);
            }
        }

        return count; 
    }

    public void dfs(int[][] isConnected, int visited[], int i){

        visited[i] = 1;

        int neighbours[] = isConnected[i];

        for(int x = 0; x < neighbours.length; x++){
            if(visited[x] == 0 && neighbours[x] == 1){
                dfs(isConnected,visited,x);
            }
        }
    }
}