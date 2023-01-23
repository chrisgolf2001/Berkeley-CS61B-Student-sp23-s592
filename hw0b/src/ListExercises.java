import java.util.ArrayList;
import java.util.List;

public class ListExercises {

    /** Returns the total sum in a list of integers */
	public static int sum(List<Integer> L) {

        int sum = 0;
        for(int x = 0; x < L.size(); x++){
            sum+=L.get(x);
        }
        return sum;
    }

    /** Returns a list containing the even numbers of the given list */
    public static List<Integer> evens(List<Integer> L) {
        List <Integer> evenList = new ArrayList<>();

        for(int x = 0; x < L.size();x++){
            if(L.get(x) % 2 == 0){
                evenList.add(L.get(x));
            }
        }
        return evenList;
    }

    /** Returns a list containing the common item of the two given lists */
    public static List<Integer> common(List<Integer> L1, List<Integer> L2) {
        List<Integer> commonList = new ArrayList<>();
        for(int x = 0; x < L1.size(); x++){
            for(int i = 0; i <L2.size();i++){
                if(L1.get(x) == L2.get(i)){
                    commonList.add(L1.get(x));
                }
            }
        }

        return commonList;
    }


    /** Returns the number of occurrences of the given character in a list of strings. */
    public static int countOccurrencesOfC(List<String> words, char c) {
        int occured = 0;
        for(int x = 0; x < words.size(); x++){
            for(int i = 0; i < words.get(x).length(); i++){
                if(c == words.get(x).charAt(i)){
                    occured++;
                }
            }
        }
        return occured;
    }
}


