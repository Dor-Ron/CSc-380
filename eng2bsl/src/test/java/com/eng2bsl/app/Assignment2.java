package com.eng2bsl.app;

import static org.junit.Assert.*;
import org.junit.Test;

public class Assignment2 {
  public static String sampleHTML= "<!doctype html>" +
                                   "<html xmlns=\"http://www.w3.org/1999/xhtml\">" +
                                   "<meta name=\"msapplication-TileImage\" content=\"/mstile-144x144.png\">" +
                                   "<meta name=\"theme-color\" content=\"#ffffff\">" +
                                   "<!-- Bootstrap core CSS -->" +
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

  // Positive Test 1
  @Test
  public void getUrlPositiveTest() {
    String url = getUrl(sampleHTML);
    String correct = "http://media.signbsl.com/videos/wolver/mp4/eng_home_8906.mp4";
    assertEquals(url, correct);
  }

/*
  // Positive Test 2
  @Test
  public void () {
  }

  // Negative Test 2
  @Test
  public void () {

  }
*/
  // Positive Test 3
  @Test
  public void lettersForWordPositiveTest() {
    String[] filePaths = lettersForWord(egNotFound);
    String[] correct = {"/img/c.gif", "/img/a.gif", "/img/t.gif"};
    assertEquals(filePaths, correct);
  }
}
