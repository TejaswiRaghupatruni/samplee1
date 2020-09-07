/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pman;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
 
public class Longest_line {
 
    private int currentMaxCount = 0;
    private List<String> lines = new ArrayList<String>();
     
    public void readMaxLineCount(String fileName){
 
        FileInputStream fis = null;
        DataInputStream dis = null;
        BufferedReader br = null;
         
        try {
            fis = new FileInputStream(fileName);
            dis = new DataInputStream(fis);
            br = new BufferedReader(new InputStreamReader(dis));
            String line = null;
            while((line = br.readLine()) != null){
                 
                int count = (line.split("\\s+")).length;
                if(count > currentMaxCount){
                    lines.clear();
                    lines.add(line);
                    currentMaxCount = count;
                } else if(count == currentMaxCount){
                    lines.add(line);
                } 
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            try{
                if(br != null) br.close();
            }catch(Exception ex){}
        }
    }
 
    public int getCurrentMaxCount() {
        return currentMaxCount;
    }
 
    public void setCurrentMaxCount(int currentMaxCount) {
        this.currentMaxCount = currentMaxCount;
    }
 
    public List<String> getLines() {
        return lines;
    }
 
    public void setLines(List<String> lines) {
        this.lines = lines;
    }
    public boolean isFiller(String s){
        String [] filler={"a","an","the","in","on","by","of","with","into","to"};
        boolean test = false; 
        for (String element : filler) { 
            if (element.equals(s)) { 
                test = true; 
                break; 
            } 
        } 
        return test;
    }
     
    public static void main(String a[]){
         
        Longest_line mdc = new Longest_line();
        mdc.readMaxLineCount("C:\\Users\\ramesh\\Downloads\\sample.txt");
        //System.out.println("Max number of words in a line is: "+mdc.getCurrentMaxCount());
        System.out.println("Line with max word count: i.e.,longest sentence is:");
        List<String> lines = mdc.getLines();
        String [] words = null;
        String [] syn = null;
        String [] ant = null;
        for(String l:lines){
            System.out.println(l);
            words=l.split(" ");
            
            
        }
         
        for(String l:words){
            if(!mdc.isFiller(l))
            {
                //System.out.println("hiiiii");
               ant = Antonym.getAntonyms(l);
               syn = Antonym.getSynonyms(l);
            }
            if(!ant.equals("null"))
                break;
            
        }
        System.out.println("Antonyms");
        for(String l1:ant)
        {
            System.out.println(l1);
        }
        System.out.println("Synonyms");
        for(String l1:syn)
        {
            System.out.println(l1);
        }
    }
}
