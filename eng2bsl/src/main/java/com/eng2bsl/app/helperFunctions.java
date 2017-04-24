package com.eng2bsl.app;

import java.util.*;
import org.jsoup.Jsoup;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class helperFunctions {
  // Returns String of URL from the Jsoup.Document parameter
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

  // Returns java.util.HashMap with alphanumeric key pointing to corresponding string filepath for that input
  public static HashMap<Character, String> makeLettersMap() {
    HashMap<Character, String> letters = new HashMap<Character, String>();
    String alphabet = "abcdefghijklmnopqrstuvwxyz0123456789";
    for (int i = 0; i < alphabet.length(); i++)
      letters.put(alphabet.charAt(i), String.format("/img/%s.gif", alphabet.charAt(i)));
    return letters;
  }

  //----------------------------------

  // Returns Jsoup.Document object of String param: http://www.signbsl.com/sign/<param> API endpoint
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

  // Returns true if URL for video exists, false otherwise.
  public static boolean wordExists(String word) {
    word = word.replaceAll("[\\?&%=;\\/:\\\\@=\"<>#{}\\|\\^~\\[\\]\\`]", " ");
    Document doc = getHTML(word);
    Element link = doc.select("source").first(); // Should be null if word exists..
    if (link != null) return true;
    return false;
  }

  // Returns true if input is alphanumeric, false otherwise.
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
