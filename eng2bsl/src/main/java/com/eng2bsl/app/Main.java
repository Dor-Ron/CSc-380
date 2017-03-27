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
      passToResult.put(req.queryParams("userPhrase"), new HashMap<String, ArrayList<String>>());
      HashMap<String, ArrayList<String>> passMap = passToResult.get(req.queryParams("userPhrase"));
      passMap.put("phrase", new ArrayList<String>());
      ArrayList<String> wordsForTemplate = passMap.get("phrase");
      ArrayList<String> wordsFromSent = helper.sentToWordArr(req.queryParams("userPhrase"));
      for(String word: wordsFromSent) {
        if(helper.wordExists(word)) wordsForTemplate.add(helper.getUrl(helper.getHTML(word)));
        else {
          for (int i = 0; i < word.length(); i++) {
            //System.out.println(word.toLowerCase().charAt(i));
            if (helper.isValidCharacter(word.toLowerCase().charAt(i))) wordsForTemplate.add(charMap.get(word.toLowerCase().charAt(i)));
          }
        }
      }
      res.redirect(String.format("/results/%s", req.queryParams("userPhrase")));
      return "submitted";
    });

    get("/results/:sent", (req, res) -> {
      return new VelocityTemplateEngine().render(
          new ModelAndView(passToResult.get(req.params("sent")), "templates/result.vtl")
      );
    });
  }
}
