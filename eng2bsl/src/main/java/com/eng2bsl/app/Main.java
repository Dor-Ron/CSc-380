package com.eng2bsl.app;

import java.util.*;
import static spark.Spark.*;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;


public class Main {
  public static helperFunctions helper = new helperFunctions();
  public static HashMap<Character, String> charMap = helper.makeLettersMap();
  public static Map<String, HashMap<String, ArrayList<String>>> passToResult = new HashMap<String, HashMap<String, ArrayList<String>>>();

  public static void main(String[] args) {
    staticFiles.location("/public");
    get("/", (req, res) -> {
      Map<String, Object> model = new HashMap<>();
      model.put("title", "English-to-BSL Translator");
      return new VelocityTemplateEngine().render(
          new ModelAndView(model, "templates/index.vtl")
      );
    });

    post("/", (req, res) -> {
      String specialUrlRegex = "[\\?&%=;\\/:\\\\@=\"<>#{}\\|\\^~\\[\\]\\`]";  // characters from https://perishablepress.com/stop-using-unsafe-characters-in-urls/
      String sentence = req.queryParams("userPhrase").replaceAll(specialUrlRegex, " ").trim();  // problem if reserved URL characters in URL string
      passToResult.put(sentence, new HashMap<String, ArrayList<String>>());
      HashMap<String, ArrayList<String>> passMap = passToResult.get(sentence);
      passMap.put("phrase", new ArrayList<String>());
      passMap.put("components", new ArrayList<String>());
      ArrayList<String> wordsForTemplate = passMap.get("phrase");
      ArrayList<String> wordsAndChars = passMap.get("components");
      ArrayList<String> wordsFromSent = helper.sentToWordArr(sentence);
      for(String word: wordsFromSent) {
        if(helper.wordExists(word)) {
          wordsForTemplate.add(helper.getUrl(helper.getHTML(word)));
          wordsAndChars.add(word);
        } else {
          for (int i = 0; i < word.length(); i++) {
            if (helper.isValidCharacter(word.toLowerCase().charAt(i))) {
               wordsForTemplate.add(charMap.get(word.toLowerCase().charAt(i)));
               wordsAndChars.add(String.valueOf(word.toLowerCase().charAt(i)));
            }
          }
        }
      }
      res.redirect(String.format("/results/%s", sentence));
      return "submitted";
    });

    get("/results/*", (req, res) -> {
      return new VelocityTemplateEngine().render(
          new ModelAndView(passToResult.get(req.splat()[0]), "templates/result.vtl")
      );
    });
  }
}
