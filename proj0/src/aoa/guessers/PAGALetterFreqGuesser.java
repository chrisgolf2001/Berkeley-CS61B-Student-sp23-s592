package aoa.guessers;

import aoa.utils.FileUtils;

import java.util.*;

public class PAGALetterFreqGuesser implements Guesser {
    private final List<String> words;

    public PAGALetterFreqGuesser(String dictionaryFile) {
        words = FileUtils.readWords(dictionaryFile);
    }

    @Override
    /** Returns the most common letter in the set of valid words based on the current
     *  PATTERN and the GUESSES that have been made. */
    public char getGuess(String pattern, List<Character> guesses) {

       //if there are no previous guesses -
        if(guesses.size() == 0){
            return  getCommonCh(keepOnlyWordsThatMatchPatterns(pattern, guesses), guesses);
        }

        System.out.println(words);
        System.out.println(pattern);
        System.out.println(guesses);
        System.out.println(getCommonCh(keepOnlyWordsThatMatchPatterns(pattern, guesses), guesses));
        System.out.println(keepOnlyWordsThatMatchPatterns(pattern, guesses));

        for(int x = 0; x < pattern.length(); x++){
            for(int l = 0; l < guesses.size(); l++){
                if(pattern.charAt(x) == guesses.get(l)){
                    return getCommonCh(keepOnlyWordsThatMatchPatterns(pattern, guesses), guesses);
                } else if (guesses.size()  > 0) {
                    return getCommonCh(keepOnlyWordsThatMatchPatterns(pattern, guesses), guesses);
                }
            }
        }

        return '?';
    }

    public List keepOnlyWordsThatMatchPatterns(String p, List<Character> g){

        ArrayList<String> match = new ArrayList<>();
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

            for(int  j = 0; j < p.length(); j++ ){
                if(g.get(i) == p.charAt(j)){
                    idx = j;
                    k = p.charAt(j);
                }
            }
        }


        for(int l = 0; l < words.size(); l++){

            if(words.get(l).length() >= idx + 1 && words.get(l).charAt(idx) == k && duplicates(words.get(l), k, p)){
                match.add(words.get(l));
            } else if (k == '?' && idx == 0 && notThere(p,g,words.get(l))) {
                match.add(words.get(l));
            }
        }
        Collections.sort(match);
        return match;
    }


    public char getCommonCh(List<String> w, List<Character> g) {
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

    public boolean duplicates(String w, Character c, String p){

        int count = 0;
        String duplicate = "";
        Map<Character, Integer> duplicMap = new HashMap<>();


        for(int x = 0; x < w.length(); x++){
            if(c == w.charAt(x)){
                count++;
            }
        }

        for(int x = 0; x < p.length(); x++) {
            if ((p.charAt(x) >= 'a' && p.charAt(x) <= 'z') || (p.charAt(x) >= 'A' && p.charAt(x) <= 'Z')) {
                duplicate += p.charAt(x);
            }
        }

        for(int x = 0; x < duplicate.length(); x++){
            if(duplicMap.containsKey(duplicate.charAt(x))){
                duplicMap.put(duplicate.charAt(x),duplicMap.get(duplicate.charAt(x))+1);
            }else{
                duplicMap.put(duplicate.charAt(x),1);
            }
        }

        for(int x = 0; x < duplicMap.size();x++){
            if(duplicMap.containsKey(c) && duplicMap.get(c) > 1){
                return true;
            }
        }

        if(count > 1 ){
            return false;
        }

        return true;
    }

    public boolean notThere(String p, List<Character> g, String w){



        //pass all the guessed letters into the List
        for(int x = 0; x < p.length(); x++){
            if((p.charAt(x) >= 'a' && p.charAt(x) <= 'z') || (p.charAt(x) >= 'A' && p.charAt(x) <= 'Z')){
                for(int k = 0; k < g.size(); k++){
                    if(p.charAt(x) == g.get(k)){

                        return true;
                    }
                }

            }
        }

        for(int x= 0; x < w.length();x++){
            for(int y = 0; y < g.size(); y++){
                if(w.charAt(x) == g.get(y)){
                    return false;
                }
            }
            if(x == w.length() - 1){
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        PAGALetterFreqGuesser pagalfg = new PAGALetterFreqGuesser("data/example.txt");
        System.out.println(pagalfg.getGuess("----", List.of('e')));
    }
}
