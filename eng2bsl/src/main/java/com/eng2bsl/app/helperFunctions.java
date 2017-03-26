package com.eng2bsl.app;

import java.util.*;
import org.jsoup.Jsoup;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class helperFunctions {

  public static String getUrl(Document doc) {
    Element link = doc.select("source").first();
    String url = link.attr("src");
    return url;
  }

  /* Might not need this any longer...
  public static String[] lettersForWord(String word, HashMap<Character, String> letterMap) {
    String[] retArr = new String[word.length()];
    for (int i = 0; i < word.length(); i++) {
      if("abcdefghijklmnopqrstuvwxyz0123456789".indexOf(word.toLowerCase().charAt(i)) != -1)
        retArr[i] = letterMap.get(word.toLowerCase().charAt(i));
    }
    return retArr;
  } */
  // HashMap that implements String values that loops through the letters and links it with the associated image.
  public static HashMap<Character, String> makeLettersMap() {
    HashMap<Character, String> letters = new HashMap<Character, String>();
    String alphabet = "abcdefghijklmnopqrstuvwxyz0123456789";
    for (int i = 0; i < alphabet.length(); i++)
      letters.put(alphabet.charAt(i), String.format("/img/%s.gif", alphabet.charAt(i)));
    return letters;
  }

  //----------------------------------

  public static Document getHTML(String word) {
    String url = String.format("http://www.signbsl.com/sign/%s", word);
    Document doc = null;
    try {
      doc = Jsoup.connect(url).get();
    } catch(java.io.IOException e) {
      e.printStackTrace();
    }
    return doc;
  }

  public static boolean wordExists(String word) {
    Document doc = getHTML(word);
    Element link = doc.select("source").first(); // Should be null if word exists..
    if (link != null) return true;
    return false;
  }
  // Returns the character value that can only be valid and if not false.
  public static boolean isValidCharacter(char symbol) {
    return ("abcdefghijklmnopqrstuvwxyz0123456789".indexOf(symbol) > -1) ? true : false;
  }

  // Separates String sentence argument into individual words, adds them to the ArrayList, and returns it.
  public static ArrayList<String> sentToWordArr(String sent) {
    ArrayList<String> wordList = new ArrayList<String>();
    int cnt = 0;
    for (int i = 0; i < sent.length(); i++) {
        if (sent.charAt(i) == ' ') {
          if (cnt == 0) wordList.add(sent.substring(cnt, i));
          else wordList.add(sent.substring(cnt+1, i));
          cnt = i;
        }
        if (i == sent.length()-1 && cnt != 0) wordList.add(sent.substring(cnt+1, i+1));
        if (i == sent.length()-1 && cnt == 0) wordList.add(sent.substring(cnt, i+1));
    }
    return wordList;
  }
}
