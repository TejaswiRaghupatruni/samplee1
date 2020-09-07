/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pman;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 *
 * @author DELL
 */
public class Antonym
{
public static String[] getAntonyms(String wordInput) {
    String[] resultArray = {};
    if (wordInput.equals("")) {
        return resultArray;
    }
    try {
        URL url = new URL("http://www.thesaurus.com/browse/"+wordInput+"?s=t");
        URLConnection yc = url.openConnection();
        String foundWords;
        try (BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()))) {
            String inputLine;
            foundWords = "";
            boolean foundListStart = false;
            int divCount = 0;
            while ((inputLine = in.readLine()) != null) {
                String iLine = inputLine.trim();
                if (iLine.equals("<section class=\"container-info antonyms\" >")) {
                    foundListStart = true;
                }
                if (iLine.equals("</div>") && foundListStart) {
                    divCount++;
                }
                if (foundListStart) {
                    if (iLine.equals("</div>") && divCount == 2) {
                        foundListStart = false;
                        break;
                    }
                    if (iLine.startsWith("<li><a href=")) {
                        String[] codeLines = iLine.split(" ");
                        int index = codeLines[1].lastIndexOf('/');
                        String word = codeLines[1].substring(index + 1, codeLines[1].length());
                        word = word.replace("%27", "'").replace("%20", " ").replace("\"", "");
                        if (foundWords.equals("")) {
                            foundWords += word;
                        } else {
                            foundWords += "," + word;
                        }
                    }
                }
            }
        }
        // Convert built comma delimited string to String Array
        if (!foundWords.equals("")) {
            resultArray = foundWords.split(",");
        }
    } catch (MalformedURLException ex) {
        // Do whatever you want with exception.
        ex.printStackTrace();
    } catch (IOException ex) {
        // Do whatever you want with exception.
        ex.printStackTrace();
    }
    return resultArray;
}

public static String[] getSynonyms(String wordInput) {
    String[] resultArray = {};
    if (wordInput.equals("")) {
        return resultArray;
    }
    try {
        URL url = new URL("http://www.thesaurus.com/browse/"+wordInput+"?s=t");
        URLConnection yc = url.openConnection();
        String foundWords;
        try (BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()))) {
            String inputLine;
            foundWords = "";
            boolean foundListStart = false;
            while ((inputLine = in.readLine()) != null) {
                String iLine = inputLine.trim();
                if (iLine.equals("<div class=\"relevancy-list\">")) {
                    foundListStart = true;
                }
                if (foundListStart) {
                    if (iLine.equals("</div>")) {
                        foundListStart = false;
                        break;
                    }
                    if (iLine.startsWith("<li><a href=")) {
                        String[] codeLines = iLine.split(" ");
                        int index = codeLines[1].lastIndexOf('/');
                        String word = codeLines[1].substring(index + 1, codeLines[1].length());
                        word = word.replace("%27", "'").replace("%20", " ").replace("\"", "");
                        if (foundWords.equals("")) {
                            foundWords += word;
                        } else {
                            foundWords += "," + word;
                        }
                    }
                }
            }
        }
        // Convert built comma delimited string to String Array
        if (!foundWords.equals("")) {
            resultArray = foundWords.split(",");
        }
    } catch (MalformedURLException ex) {
        // Do what you want with exception
        ex.printStackTrace();
    } catch (IOException ex) {
        // Do what you want with exception
        ex.printStackTrace();
    }
    return resultArray;
}
}