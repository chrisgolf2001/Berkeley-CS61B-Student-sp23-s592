import java.util.*;

public class MapExercises {
    /** Returns a map from every lower case letter to the number corresponding to that letter, where 'a' is
     * 1, 'b' is 2, 'c' is 3, ..., 'z' is 26.
     */
    public static Map<Character, Integer> letterToNum() {
       char[] lowAlpha = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
        Map<Character,Integer> newMap = new HashMap<>();

        for(int x = 0; x < 26; x++){
            newMap.put(lowAlpha[x], x+1);
        }

        return newMap;
    }

    /** Returns a map from the integers in the list to their squares. For example, if the input list
     *  is [1, 3, 6, 7], the returned map goes from 1 to 1, 3 to 9, 6 to 36, and 7 to 49.
     */
    public static Map<Integer, Integer> squares(List<Integer> nums) {
        TreeMap<Integer, Integer> squaredMap = new TreeMap<>();

        if(nums.size() == 0){
            return squaredMap;
        }else {
            for (int x = 0; x < nums.size(); x++) {
                squaredMap.put(nums.get(x), nums.get(x) * nums.get(x));
            }
        }
        return squaredMap;
    }

    /** Returns a map of the counts of all words that appear in a list of words. */
    public static Map<String, Integer> countWords(List<String> words) {

        int occured = 0;
        TreeMap<String, Integer> countMap = new TreeMap<>();

        for(int x = 0; x < words.size(); x++){
            for(int i = 0; i < words.size(); i++){
                if(words.get(x) == words.get(i)){
                    occured++;
                }
            }
            if(!countMap.containsKey(words.get(x))){
                countMap.put(words.get(x),occured);
            }
            occured = 0;


        }

        return countMap;
    }
}
