package aoa.choosers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import edu.princeton.cs.algs4.StdRandom;
import aoa.utils.FileUtils;
import org.checkerframework.checker.units.qual.C;

public class EvilChooser implements Chooser {
    private String pattern;
    private List<String> wordPool;

    public EvilChooser(int wordLength, String dictionaryFile) {
        if(wordLength < 1){
            throw new IllegalArgumentException("Length of the word should be more than 1");
        }

        List<String> wordsList = FileUtils.readWordsOfLength(dictionaryFile, wordLength);

        if(wordsList.size() == 0){
            throw new IllegalStateException("There should be at least one word");
        }

        Collections.sort(wordsList);
        int randomlyChosenWordNumber = StdRandom.uniform(0, wordsList.size());

        String randomW = wordsList.get(randomlyChosenWordNumber);

        wordPool = wordsList;

        System.out.println(wordPool);
        System.out.println(wordPool);
        System.out.println();
        setPattern("----");
//      pattern = "-".repeat(chosenWord.length());
    }

    @Override
    public int makeGuess(char letter) {
        List<String> listOfPatterns = new ArrayList<>();
        String newPattern = "";

        for(int x = 0 ; x < wordPool.size(); x++){
            for(int j = 0; j < wordPool.get(x).length(); j++){
                if(wordPool.get(x).charAt(j) == letter){
                    newPattern+=letter;
                }else{
                    newPattern+="-";
                }
            }

            if (!listOfPatterns.contains(newPattern) && patternsMatch(newPattern, letter)){
                    listOfPatterns.add(newPattern);
            } else if (!listOfPatterns.contains(newPattern) && ifCompatible(newPattern)) {
                listOfPatterns.add(newPattern);
            }
            newPattern = "";
        }

    if(listOfPatterns.size() > 0){
        newPattern = mostWordsPattern(listOfPatterns, letter);
        setPattern(newPattern);
        wordPool = matchingWords(newPattern,letter);
        listOfPatterns.clear();
        listOfPatterns.add(newPattern);
    }

        System.out.println(letter);
        System.out.println(listOfPatterns);
        System.out.println("Most Common pattern: ");
        System.out.print(mostWordsPattern(listOfPatterns, letter));
        System.out.println(wordPool);
        System.out.println(matchingWords(newPattern,letter));
        System.out.println();






        // TODO: Fill in this method.
        return countWords(newPattern, letter);
    }

    @Override
    public String getPattern() {
        // TODO: Fill in this method.
        return this.pattern;
    }

    public void setPattern(String P){
        pattern = P;
    }

    @Override
    public String getWord() {
        // TODO: Fill in this method.
        return wordPool.get(0);
    }

    public String mostWordsPattern(List<String> l, Character c){
        String P = "";
        int count = 0;
        int temp = 0;
        Collections.sort(l);

        for(int i = 0; i < l.size(); i++){
            for(int y = 0; y < wordPool.size();y++){
                if(samePattern(l.get(i),wordPool.get(y), c)){
                    temp++;
                    if(temp > count){
                        count = temp;
                        P = l.get(i);
                    }
                }
            }
            temp = 0;
        }

        return P;
    }

    public boolean samePattern (String p, String w, char l){
        boolean hasLetter = !containsLetters(p);
        for(int j = 0; j < w.length(); j++){
            for(int i = 0; i < p.length(); i++){
                if (i == j && '-' != p.charAt(i) && l != w.charAt(j) && hasLetter) {return false;}
                else if (!hasLetter && l == w.charAt(j)) {return false;}
            }
        }

        return true;
    }

    public List<String> matchingWords (String p, Character l){
        List<String> match = new ArrayList<>();
        int count = 0;

        for(int j = 0; j < wordPool.size(); j++){
                if(samePattern(p,wordPool.get(j),l)){
                    if(!match.contains(wordPool.get(j))){
                        match.add(wordPool.get(j));
                    }
                }

        }
        return match;

    }

    public int countWords (String p, Character l){
        int count=0;
        for(int x =0; x < p.length(); x++){
            if(p.charAt(x) == l){
                count++;
            }
        }
        return count;
    }

    public boolean containsLetters (String p){
        for(int x =0; x < p.length(); x++){
            if(p.charAt(x) != '-'){
                return false;
            }
        }

        return true;
    }

    public boolean patternsMatch (String newP, char c){
        for(int x = 0; x < pattern.length(); x++){
            for(int y = 0; y < newP.length(); y++){
                if(x == y && (pattern.charAt(x) != newP.charAt(y) && pattern.charAt(x) != '-')){
                    return false;
                }
            }
        }

        return true;
    }

    public boolean ifCompatible (String newP){
        for (int x =0; x < pattern.length(); x++){
                if(newP.length() != pattern.length() || (newP.charAt(x) == pattern.charAt(x) && (pattern.charAt(x) != '-' && newP.charAt(x) == '-'))){
                    return false;
                }

        }
        if(!containsLetters(pattern) && containsLetters(newP)){
            return false;
        }
        return true;
    }



}



