package com.eng2bsl.app;

import java.util.HashMap;
import org.jsoup.Jsoup;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class helperFunctions {
  public String getUrl(Document doc) {
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
    Element h2 = doc.select(".featurette-heading").first();
    if (h2 != null && h2.text().charAt(0) == 'N') return false;
    else return true;
  }
}
