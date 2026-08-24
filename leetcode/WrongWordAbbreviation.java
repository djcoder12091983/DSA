// https://leetcode.com/problems/word-abbreviation/

class Solution {

    // here all the words length are same
	// TODO may need to think of solve using prefix tree, because a group of words may share different common prefix
	// so longest common prefix may not will help always
    String longestPrefix(List<String> words) {
        int N = words.size();
        if(N == 1) {
            // we can say prefix is empty
            return "";
        }
        int l = words.get(0).length();
        
        int i = 0;
        StringBuilder prefix = new StringBuilder();
        while(i < l) {
            char x = words.get(0).charAt(i);
            boolean match = true;
            for(int j = 1; j < N; j++) {
                char y = words.get(j).charAt(i);
                if(x != y) {
                    match = false;
                    break;
                }
            }

            if(match) {
                prefix.append(x);
            } else {
                break;
            }

            i++;
        }
        return prefix.toString();
    }

    public List<String> wordsAbbreviation(List<String> words) {
        // first we will group based on first + last character and length of the string
        HashMap<String, List<String>> group = new HashMap<>();
        // TODO instead of taking position based map we could think of attaching the index with the word itself
        HashMap<String, Integer> position = new HashMap<>(); // position based map
        int N = words.size();
        for(int i = 0; i < N; i++) {
            
            String word = words.get(i);
            position.put(word, i);

            int l = word.length();
            String key = word.charAt(0) + "-" + word.charAt(l - 1) + "-" + l;

            if(!group.containsKey(key)) {
                group.put(key, new ArrayList<>());
            }

            group.get(key).add(word);
        }

        List<String> ans = new ArrayList<>();
        for(int i = 0; i < N; i++) {
            ans.add(""); // dummy value
        }

        // now for each group
        for(List<String> gwords : group.values()) {
            String prefix = longestPrefix(gwords);
            // System.out.println(gwords + " -> " + prefix);

            for(String word : gwords) {
                // now abbreviate them
                int l1 = prefix.length();
                int l2 = word.length();
                String ab;
                if(l1 + 2 < l2) {
                    // can be abbreviated
                    ab = prefix + word.charAt(l1) + (l2 - 2 - l1) + word.charAt(l2 - 1);
                    if(ab.length() == l2) {
                        // keep it original
                        ab = word;
                    }
                } else {
                    // keep it as it is
                    ab = word;
                }

                // update answer with abbreviation
                ans.set(position.get(word), ab);
            }
        }

        return ans;
    }
}