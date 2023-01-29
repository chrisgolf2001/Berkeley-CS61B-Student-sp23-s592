package aoa.choosers;

import edu.princeton.cs.algs4.StdRandom;
import aoa.utils.FileUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class RandomChooser implements Chooser {
    private final String chosenWord;
    private String pattern;

    public RandomChooser(int wordLength, String dictionaryFile) {
        // TODO: Fill in/change this constructor.

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


        chosenWord = randomW;
        pattern = "-".repeat(chosenWord.length());


    }

    @Override
    public int makeGuess(char letter) {
        // TODO: Fill in this method.
        int count = 0;
        String updatedPattern = "";

        for(int x = 0; x < chosenWord.length();x++){
            if(chosenWord.charAt(x) == letter){
                count++;
                updatedPattern+=chosenWord.charAt(x);
            }else {
                updatedPattern += getPattern().charAt(x);
            }
        }
        pattern = updatedPattern;
        return count;
    }

    @Override
    public String getPattern() {
        // TODO: Fill in this method.
        return pattern;
    }

    @Override
    public String getWord() {
        // TODO: Fill in this method.
        return chosenWord;
    }
}
