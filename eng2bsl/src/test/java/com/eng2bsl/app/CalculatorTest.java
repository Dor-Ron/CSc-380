package com.eng2bsl.app;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;

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

  public static String egNotFound = "Cat";
  public static HashMap<Character, String> egHashMap = helper.makeLettersMap();

  // Positive Test 1
  @Test
  public void getUrlPositiveTest() {
    String url = helper.getUrl(sampleHTML);
    String correct = "https://media.signbsl.com/videos/bsl/signstation/reindeer.mp4";
    assertEquals(url, correct);
  }

  // Negative Test 1
  @Test(expected=NullPointerException.class)
  public void getUrlNegativeTest() {
    String url = helper.getUrl("thisIsNotHTML");
    String correct = "https://media.signbsl.com/videos/bsl/signstation/reindeer.mp4";
    assertNotEquals(url, correct);
  }

  // Positive Test 2
  @Test
  public void makeLettersMapPositiveTest() {
    HashMap<Character, String> correctMap = new HashMap<Character, String>();
    correctMap.put('a', "/img/a.gif");
    correctMap.put('b', "/img/b.gif");
    correctMap.put('c', "/img/c.gif");
    correctMap.put('d', "/img/d.gif");
    correctMap.put('e', "/img/e.gif");
    correctMap.put('f', "/img/f.gif");
    correctMap.put('g', "/img/g.gif");
    correctMap.put('h', "/img/h.gif");
    correctMap.put('i', "/img/i.gif");
    correctMap.put('j', "/img/j.gif");
    correctMap.put('k', "/img/k.gif");
    correctMap.put('l', "/img/l.gif");
    correctMap.put('m', "/img/m.gif");
    correctMap.put('n', "/img/n.gif");
    correctMap.put('o', "/img/o.gif");
    correctMap.put('p', "/img/p.gif");
    correctMap.put('q', "/img/q.gif");
    correctMap.put('r', "/img/r.gif");
    correctMap.put('s', "/img/s.gif");
    correctMap.put('t', "/img/t.gif");
    correctMap.put('u', "/img/u.gif");
    correctMap.put('v', "/img/v.gif");
    correctMap.put('w', "/img/w.gif");
    correctMap.put('x', "/img/x.gif");
    correctMap.put('y', "/img/y.gif");
    correctMap.put('z', "/img/z.gif");
    assertEquals(egHashMap, correctMap);
  }

  // Positive Test 3
  @Test
  public void lettersForWordPositiveTest() {
    String[] filePaths = helper.lettersForWord(egNotFound, egHashMap);
    String[] correct = {"/img/c.gif", "/img/a.gif", "/img/t.gif"};
    for (int i = 0; i < 3; i ++) System.out.println(filePaths[i]);
    assertEquals(filePaths, correct);
  }

  // Negative Test 3
  @Test()
  public void lettersForWordNegativeTest() {
    String badWord = "hello!+-~"; // BSL words dont have numerics or symbols
    String[] goodArr = helper.lettersForWord(badWord, egHashMap);
    String[] badArr = {"/img/h.gif", "/img/e.gif", "/img/l.gif", "/img/l.gif", "/img/o.gif", "/img/!.gif", "/img/+.gif", "/img/-.gif", "/img/~.gif"};
    assertNotEquals(goodArr, badArr);
  }
}
