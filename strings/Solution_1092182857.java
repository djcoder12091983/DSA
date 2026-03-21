class Solution {
    public int minMutation(String startGene, String endGene, String[] bank) {
        HashMap<String,Boolean> hm = new HashMap<>();
        hm.put(startGene, false);
        for(String s : bank)
        {
            hm.put(s,false);
        }
        if(hm.containsKey(endGene) == false) return -1;
        
        Queue<pair> que = new LinkedList<>();
        que.add(new pair(startGene,0));

        char changes[] = {'A', 'C', 'G', 'T'};

        while(que.size() > 0)
        {
            pair curr = que.remove();
            String currS = curr.s;
            int currChange = curr.cnt;
            hm.put(currS,true);
            
            if(currS.equals(endGene) == true)
                return currChange;
            
            for(int i=0; i<8; i++)
            {
                // here I am simplifying the logic
                // for every character i will change possible characters A C G T
                for(int j = 0; j < 4; j++) {
                    char currSS[] = curr.s.toCharArray();
                    if(currS.charAt(i) != changes[j]) {
                        currSS[i] = changes[j];
                    }

                    String next = new String(currSS);
                    if(hm.containsKey(next) && hm.get(next) == false) {
                        // we can choose path
                        que.add(new pair(next, currChange + 1));
                    }
                }
            }
        }
        return -1;
    }
}
class pair
{
    String s;
    int cnt;
    public pair(String s, int cnt)
    {
        this.s = s;
        this.cnt = cnt;
    }
}