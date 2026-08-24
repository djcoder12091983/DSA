// https://leetcode.com/problems/word-abbreviation/
// TODO may need to think whether group of words abbreviation can done by sorting
// it seems the implementation almost same tricky as TRIE but bit confusing in correctness
// so decided to go with TRIE

class Solution {

    // longest commmon prefix for two words
    String prefix(String w1, String w2) {
        int N = w1.length();
        StringBuilder p = new StringBuilder();
        for(int i = 0; i < N; i++) {
            char x = w1.charAt(i);
            char y = w2.charAt(i);

            if(x != y) {
                break;
            }

            p.append(x);
        }

        return p.toString();
    }

    // abbreviate based on prefix
    String abbreviate(String word, String prefix) {
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

        return ab;
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

            N = gwords.size();
            if(N == 1) {
                // we can easily abbbreviate it
                String w = gwords.get(0);
                int l = w.length();
                if(l == 2) {
                    ans.set(position.get(w), w);
                } else {
                    ans.set(position.get(w), w.charAt(0) + (l - 2) + w.charAt(l - 1));
                }
            } esle {
                // we will sort the words so that consecutive words are more likely to share common prefix
                Collections.sort(gwords);

                // consider pairs
                String w1 = gwords.get(0);
                String w2 = gwords.get(1);
                String prev = prefix(w1, w2);
                ans.set(position.get(w1), abbreviate(w1));
                ans.set(position.get(w2), abbreviate(w2));

                int i = 2;
                while() {
                    w1 = gwords.get(i - 1);
                    w2 = gwords.get(i);

                    STring p = prefix(w1, w2);
                    if(p.equals(prev)) {
                        // save same prefix
                        ans.set(position.get(w2), abbreviate(w2));
                        i++;
                    } else {
                        // different prefix then start with pairs again
                        if(i + 1 < N) {

                        } else {
                            // only one word remaining
                        }
                    }
                }
            }
        }

        return ans;
    }
}