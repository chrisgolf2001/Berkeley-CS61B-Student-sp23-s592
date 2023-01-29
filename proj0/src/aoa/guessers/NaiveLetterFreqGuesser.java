package aoa.guessers;

import aoa.utils.FileUtils;

import java.util.TreeMap;
import java.util.List;
import java.util.Map;

public class NaiveLetterFreqGuesser implements Guesser {
    private final List<String> words;

    public NaiveLetterFreqGuesser(String dictionaryFile) {
        words = FileUtils.readWords(dictionaryFile);
    }

    @Override
    /** Makes a guess which ignores the given pattern. */
    public char getGuess(String pattern, List<Character> guesses) {
        return getGuess(guesses);
    }

    /** Returns a map from a given letter to its frequency across all words.
     *  This task is similar to something you did in hw0b! */
    public Map<Character, Integer> getFrequencyMap() {
        // TODO: Fill in this method.

        Map<Character, Integer> freqMap = new TreeMap<>();
        for(int x = 0; x < words.size(); x++){
            for(int i = 0; i < words.get(x).length(); i++){
                if(freqMap.containsKey(words.get(x).charAt(i))) {
                    freqMap.put(words.get(x).charAt(i), freqMap.get(words.get(x).charAt(i))+1);
                }else{
                    freqMap.put(words.get(x).charAt(i), 1);
                }
            }
        }

        return freqMap;
    }

    /** Returns the most common letter in WORDS that has not yet been guessed
     *  (and therefore isn't present in GUESSES). */
    public char getGuess(List<Character> guesses) {
        // TODO: Fill in this method.

        int least;
        int idx = 0;
        int temp = 0;
        int greatest= 0;
        boolean fOccur = true;

        if(getFrequencyMap().size() > 0 && guesses.size() > 0) {
            for (int x = 0; x < guesses.size(); x++) {
                least = getFrequencyMap().get(guesses.get(0));
                if (getFrequencyMap().get(guesses.get(x)) < least || guesses.size() == 1) {
                    least = getFrequencyMap().get(guesses.get(x));
                    for (int i = least - 1; i >= 0; i--) {
                        for (Character j : getFrequencyMap().keySet()) {
                            if (getFrequencyMap().get(j) == i) {
                                if(guessCheck(guesses, j)){
                                    return j;
                                }

                            }
                        }
                    }

                }
            }
        }else{

           for(Object key: getFrequencyMap().keySet()){
               if(getFrequencyMap().get(key) > temp){
                       greatest = getFrequencyMap().get(key);
                       temp = getFrequencyMap().get(key);
                   }
           }

            temp = 0;
            for(Object key: getFrequencyMap().keySet()){
                if(getFrequencyMap().get(key) == greatest && fOccur == true){
                    idx = temp;
                    fOccur = false;
                }
                temp++;
            }

            temp = 0;

            for(Character key: getFrequencyMap().keySet()){

                if(temp == idx){
                    return key;
                }
                temp++;
            }
        }

        return '?';
        }


        public boolean guessCheck(List<Character> guess, char c){
        for(int x = 0; x < guess.size(); x++){
            if(guess.get(x) == c){
                return false;
            }

        }
        return true;
        }



    public static void main(String[] args) {
        NaiveLetterFreqGuesser nlfg = new NaiveLetterFreqGuesser("data/example.txt");
        System.out.println("list of words: " + nlfg.words);
        System.out.println("frequency map: " + nlfg.getFrequencyMap());

        List<Character> guesses = List.of('e', 'l');
        System.out.println("guess: " + nlfg.getGuess(guesses));

    }
}
