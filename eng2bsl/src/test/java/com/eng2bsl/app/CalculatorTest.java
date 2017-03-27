package com.eng2bsl.app;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;
import org.jsoup.Jsoup;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class CalculatorTest {
  public static helperFunctions helper = new helperFunctions();
  public static String sampleHTML= "<!doctype html>" +
                                   "<html xmlns=\"http://www.w3.org/1999/xhtml\">" +
                                   "<meta name=\"msapplication-TileImage\" content=\"/mstile-144x144.png\">" +
                                   "<meta name=\"theme-color\" content=\"#ffffff\">" +
                                   "<!-- Bootstrap core CSS -->" +
                                   "<source src=\"https://media.signbsl.com/videos/bsl/signstation/reindeer.mp4\"  type=\"video/mp4\" />" +
                                   "<!--<link href=\"/css/bootstrap.css\" rel=\"stylesheet\">-->" +
                                   "<link href=\"//maxcdn.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css\" rel=\"stylesheet\">" +
                                   "<!-- Custom styles for this template -->" +
                                   "<link href=\"/jumbotron.css\" rel=\"stylesheet\">" +
					                         "<meta property=\"og:type\" content=\"article\" />" +
                                   "<meta name=\"description\" content=\"Watch how to sign 'home' in British Sign Language.\" />" +
		                               "<meta property=\"og:description\" content=\"Watch how to sign 'home' in British Sign Language.\" />" +
		                               "<meta itemprop=\"description\" content=\"Watch how to sign 'home' in British Sign Language.\">" +
		                               "<meta property=\"og:video\" content=\"http://media.signbsl.com/videos/wolver/mp4/eng_home_8906.mp4\" />" +
								                   "<meta name=\"twitter:card\" content=\"player\">" +
	                                 "<meta name=\"twitter:title\" content=\"Watch how to sign 'home' in British Sign Language.\" />" +
			                             "<meta name=\"twitter:player:stream\" content=\"https://media.signbsl.com/videos/wolver/mp4/eng_home_8906.mp4\">" +
                                   "<link href=\"//vjs.zencdn.net/4.2.2/video-js.css\" rel=\"stylesheet\">" +
                                   "<script src=\"//vjs.zencdn.net/4.2.2/video.js\"></script>" +
                                   "<script type=\"text/javascript\" src=\"//cdnjs.cloudflare.com/ajax/libs/js-cookie/2.0.3/js.cookie.js\"></script>" +
                                   "</script>" +
                                   "</head>" +
                                   "<body>" +
                                   "</body>" +
                                   "</html>";

  public String egNotFound = "Cat";
  public HashMap<Character, String> egHashMap = helper.makeLettersMap();

  // Positive Test for helperFunctions.getUrl
  @Test
  public void getUrlPositiveTest() {
    Document doc = Jsoup.parse(sampleHTML);
    String url = helper.getUrl(doc);
    String correct = "https://media.signbsl.com/videos/bsl/signstation/reindeer.mp4";
    assertEquals(url, correct);
  }

  // Negative Test for helperFunctions.getUrl
  // Makes sure program crashes if String input isn't valid HTML
  @Test(expected=java.lang.Exception.class)
  public void getUrlNegativeTest() {
    Document doc = Jsoup.parse("hello");
    String url = helper.getUrl(doc);
    String correct = "https://media.signbsl.com/videos/bsl/signstation/reindeer.mp4";
    assertNotEquals(url, correct);
  }

  // Positive Test for helperFunctions.makeLettersMap
  // Asserts that alphanumeric characters mapped to correct file paths
  @Test
  public void makeLettersMapPositiveTest() {
    assertEquals(egHashMap.get('a'), "/img/a.gif");
    assertEquals(egHashMap.get('g'), "/img/g.gif");
    assertEquals(egHashMap.get('z'), "/img/z.gif");
    assertEquals(egHashMap.get('f'), "/img/f.gif");
    assertEquals(egHashMap.get('8'), "/img/8.gif");
    assertEquals(egHashMap.get('2'), "/img/2.gif");
  }

  /* Might not need these any longer
  // Positive Test 3
  @Test
  public void lettersForWordPositiveTest() {
    String[] filePaths = helper.lettersForWord(egNotFound, egHashMap);
    String[] correct = {"/img/c.gif", "/img/a.gif", "/img/t.gif"};
    assertEquals(filePaths, correct);
  }

  // Negative Test 3
  @Test
  public void lettersForWordNegativeTest() {
    String badWord = "hello!+-~"; // BSL words dont have numerics or symbols
    String[] goodArr = helper.lettersForWord(badWord, egHashMap);
    String[] badArr = {"/img/h.gif", "/img/e.gif", "/img/l.gif", "/img/l.gif", "/img/o.gif", "/img/!.gif", "/img/+.gif", "/img/-.gif", "/img/~.gif"};
    assertNotEquals(goodArr, badArr);
  } */

  // ---------------------------------------------

  // Positive Test for helperFunctions.getHTML
  public void getHTMLPositiveTest() {
    Document doc = helper.getHTML("cat");
    assertEquals(doc.title(), "British Sign Language (BSL) Video Dictionary - cat");
    assertEquals(helper.getUrl(doc), "https://media.signbsl.com/videos/bsl/signmonkey/mp4/cat.mp4");
  }

  // Negative Test for helperFunctions.getHTML
  // Makes sure program crashes if you attempt to get HTML for word that doesn't exist
  @Test(expected=NullPointerException.class)
  public void getHTMLNegativeTest() {
    Document doc = helper.getHTML("giberrishhhhh");
    assertNotEquals(doc.title(), "British Sign Language (BSL) Video Dictionary - giberrishhhhh");
    assertNotEquals(helper.getUrl(doc), "https://media.signbsl.com/videos/bsl/signmonkey/mp4/giberrishhhhh.mp4");
  }

  // Positive Test for helperFunctions.wordExists
  @Test
  public void wordExistsPositiveTest() {
    boolean exists = helper.wordExists("cat");
    assertTrue(exists);
  }

  @Test
  public void wordExistsPositiveTest2() {
    boolean DoesNotExist = helper.wordExists("giberrishhhh");
    assertFalse(DoesNotExist);
  }

  // Negative Test helperFunctions.wordExists
  // Shows that program crashes for none UTF-8 characters
  @Test(expected=NullPointerException.class)
  public void wordExistsNegativeTest() {
    boolean notWordBool = helper.wordExists("ßßåßœ∑");
    assertFalse(notWordBool);
  }

  // Positive Test helperFunctions.sentToWordArr
  @Test
  public void sentToWordArrPositiveTest() {
    ArrayList<String> sentence = helper.sentToWordArr("Sample sentence for the test");
    assertEquals(sentence.get(0), "Sample");
    assertEquals(sentence.get(1), "sentence");
  }

  /*
  //Negative Test 6
  @Test
  public void sentToWordArrNegativeTest() {

  }*/

  // Positive Test for helperFunctions.isValidCharacter
  @Test
  public void isValidCharacterPositiveTest() {
    boolean valid = helper.isValidCharacter('a');
    boolean valid2 = helper.isValidCharacter('1');
    boolean notValid = helper.isValidCharacter('ç');
    assertTrue(valid);
    assertTrue(valid2);
    assertFalse(notValid);
  }

  /*
  // Negative Test 7
  @Test
  public void isValidCharacterPositiveTest() {

  }*/
}
