package com.ari.stream;

import java.util.Arrays;

public class LargestWordInDictionary {

    private String[] dictionary;

    public LargestWordInDictionary(String[] dictionary) {
        this.dictionary = dictionary;
    }

    public String searchForMax()
    {
        return  Arrays.stream(dictionary).max((word1, word2) -> Integer.compare(word1.length(), word2.length())).orElse("");
    }


    public static void main(String[] args)
    {
        LargestWordInDictionary dictionary = new LargestWordInDictionary(new String[]{"pintu", "geeksfor", "geeksgeeks", "forgeek", "foo", "foo12foo12"});

        String word = dictionary.searchForMax();
        System.out.println( word+" of len:"+word.length());
    }

}
