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
        int least = getFrequencyMap().get(guesses.get(0));
        Character[] freqSet = getFrequencyMap().keySet().toArray(new Character[getFrequencyMap().keySet().size()]);
        int idx = -1;

        for(int x = 0; x < guesses.size(); x++){
            if(getFrequencyMap().get(guesses.get(x)) < least){
                least = getFrequencyMap().get(guesses.get(x));
            }
        }

        for(int i = least - 1; i >= 0; i--){
            if(getFrequencyMap().containsValue(i)){
                for(int p = 0; p < getFrequencyMap().size(); p++){
                    idx++;
                    if(getFrequencyMap().get(freqSet[p]) == i){
                        return freqSet[idx];
                    }
                }
            }
        }

        return '?';
    }

    public static void main(String[] args) {
        NaiveLetterFreqGuesser nlfg = new NaiveLetterFreqGuesser("data/example.txt");
        System.out.println("list of words: " + nlfg.words);
        System.out.println("frequency map: " + nlfg.getFrequencyMap());

        List<Character> guesses = List.of('e', 'l');
        System.out.println("guess: " + nlfg.getGuess(guesses));
    }
}
