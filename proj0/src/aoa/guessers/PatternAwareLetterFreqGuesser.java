package aoa.guessers;

import aoa.utils.FileUtils;
import edu.princeton.cs.algs4.In;

import java.util.*;

public class PatternAwareLetterFreqGuesser implements Guesser {
    private final List<String> words;

    public PatternAwareLetterFreqGuesser(String dictionaryFile) {
        words = FileUtils.readWords(dictionaryFile);
    }

    @Override
    /** Returns the most common letter in the set of valid words based on the current
     *  PATTERN. */

    public char getGuess(String pattern, List<Character> guesses) {
        // TODO: Fill in this method.

        //if there are no previous guesses -
        if(guesses.size() == 0){
            return  getCommonC(keepOnlyWordsThatMatchPattern(pattern, guesses), guesses);
        }


        for(int x = 0; x < pattern.length(); x++){
            for(int l = 0; l < guesses.size(); l++){
                if(pattern.charAt(x) == guesses.get(l)){
                    return getCommonC(keepOnlyWordsThatMatchPattern(pattern, guesses), guesses);
                } else if (guesses.size()  > 0) {
                    return getCommonC(keepOnlyWordsThatMatchPattern(pattern, guesses), guesses);
                }
            }
        }
        return '?';
    }

    public List keepOnlyWordsThatMatchPattern(String p, List<Character> g){
        List<String> match = new ArrayList<>();
        int idx = 0;
        char k = '?';

        if(g.size() == 0){
            for(int y = 0; y < words.size(); y++){
                match.add(words.get(y));
            }
            return match;
        }

        //store the index of the letter in the pattern
        for(int i = 0; i < g.size(); i++){
            for(int  j = 0; j < p.length();j++ ){
                if(g.get(i) == p.charAt(j)){
                    idx = j;
                    k = p.charAt(j);
                }
            }
        }


        for(int l = 0; l < words.size(); l++){
            if(words.get(l).charAt(idx) == k){
                match.add(words.get(l));
            } else if (k == '?' && idx == 0) {
                match.add(words.get(l));
            }
        }
        return match;
    }


    public char getCommonC(List<String> w, List<Character> g) {
        // TODO: Fill in this method.

        char mostCommon = '?';
        int count = 0;
        int temp = 0;
        List<Character> allChar = new ArrayList<>();


        for(int x = 0; x < w.size(); x++){
            for(int i = 0; i < w.get(x).length(); i++){
                allChar.add(w.get(x).charAt(i));
            }
        }

        Collections.sort(allChar);
        for(int t = 0; t < allChar.size() ; t++){
            for(int o = 0; o < allChar.size() ; o++){
                if(allChar.get(t) == allChar.get(o) && !ifGuessed(g, allChar.get(t)) ){
                    temp++;
                    if(temp > count){
                        count = temp;
                        mostCommon = allChar.get(t);
                    }
                }
            }
            temp = 0;
        }
        return mostCommon;
    }


    public boolean ifGuessed(List<Character> g, Character k) {
        boolean guessed = false;
        for (int i = 0; i < g.size(); i++) {
            if (k == g.get(i)) {
                guessed = true;
            }
        }
        return guessed;
    }


    public static void main(String[] args) {
        PatternAwareLetterFreqGuesser palfg = new PatternAwareLetterFreqGuesser("data/example.txt");
        System.out.println(palfg.getGuess("-e--", List.of('e')));
    }
}