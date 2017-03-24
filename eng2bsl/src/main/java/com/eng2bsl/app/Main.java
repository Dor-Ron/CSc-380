package com.eng2bsl.app;

import java.util.*;
import static spark.Spark.*;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;


public class Main {
  public static helperFunctions helper = new helperFunctions();
  public static HashMap<Character, String> charMap = helper.makeLettersMap();
  public static ArrayList<String> wordsFromSent = new ArrayList<String>();
  public static ArrayList<String> wordsForTemplate = new ArrayList<String>();
  public static Map<String, Object> passToResult = new HashMap<>();

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
      wordsForTemplate.clear(); // clear list before each POST
      wordsFromSent = helper.sentToWordArr(req.queryParams("userPhrase"));
      for(String word: wordsFromSent) {
        if(helper.wordExists(word)) wordsForTemplate.add(helper.getUrl(helper.getHTML(word)));
        else {
          for (int i = 0; i < word.length(); i++) {
            if (helper.isValidCharacter(word.charAt(i))) wordsForTemplate.add(charMap.get(word.charAt(i)));
          }
        }
      }
      passToResult.put("phrase", wordsForTemplate);
      res.redirect("/hey");
      return "submitted";
    });

    get("/hey", (req, res) -> {
      return new VelocityTemplateEngine().render(
          new ModelAndView(passToResult, "templates/result.vtl")
      );
    });
  }
}
