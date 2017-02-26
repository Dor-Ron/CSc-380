package com.eng2bsl.app;

import java.util.HashMap;
import org.jsoup.Jsoup;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class helperFunctions {
  public String getUrl(String html) {
    Document doc = Jsoup.parse(html);
    Element link = doc.select("source").first();
    String url = link.attr("src");
    return url;
  }

  public static String[] lettersForWord(String word, HashMap<Character, String> letterMap) {
    String[] retArr = new String[word.length()];
    for (int i = 0; i < word.length(); i++) {
      if("abcdefghijklmnopqrstuvwxyz".indexOf(word.toLowerCase().charAt(i)) != -1)
        retArr[i] = letterMap.get(word.toLowerCase().charAt(i));
    }
    return retArr;
  }

  public static HashMap<Character, String> makeLettersMap() {
    HashMap<Character, String> letters = new HashMap<Character, String>();
    String alphabet = "abcdefghijklmnopqrstuvwxyz";
    for (int i = 0; i < alphabet.length(); i++)
      letters.put(alphabet.charAt(i), String.format("/img/%s.gif", alphabet.charAt(i)));
    return letters;
  }
}
