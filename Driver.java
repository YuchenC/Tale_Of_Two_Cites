
package com.mycompany.taleoftwocities;

import edu.princeton.cs.introcs.In;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;

public class Driver 
{

   public static void main(String[] args) 
   {
      URL url = ClassLoader.getSystemResource("kap1.txt");
      Trie trie = new Trie();
      
      if (url != null) 
      {
        System.out.println("Reading from: " + url);
      } 
      else 
      {
        System.out.println("Couldn't find file: kap1.txt");
      }

      In input = new In(url);

      while (!input.isEmpty()) 
      {
        String line = input.readLine().trim();
        String[] words = line.split("(\\. )|:|,|;|!|\\?|( - )|--|(\' )| ");
        String lastOfLine = words[words.length - 1];

        if (lastOfLine.endsWith(".")) 
        {
            words[words.length - 1] = lastOfLine.substring(0, lastOfLine.length() - 1);
        }

        for (String word : words) 
        {
            String word2 = word.replaceAll("‘|’|“|”|\"|\\(|\\)|\\'|\\-|\\.|\\_|\\*", "");
            String word3 = word2.replaceAll("\"|\\é", "e");
            String word4 = word3.replaceAll("\"0|1|2|3|4|5|6|7|8|9", "");
            //String word5 = word4.replaceAll(" ", "");

            if (word4.isEmpty()) 
                continue;

            System.out.println(word4);
            // Add the word to the trie
            word4 = word4.toLowerCase();
            trie.put(word4);   
         }
      }
      
      System.out.println();
      
      int ifcount = trie.count("if");
      int ancount = trie.count("an");
      int itcount = trie.count("it");
      int thcount = trie.count("th");
            
      System.out.println("if = " + ifcount);
      System.out.println("an = " + ancount);
      System.out.println("it = " + itcount);
      System.out.println("th = " + thcount);
            
      System.out.println();
      
      int sdistinct = trie.distinct("s");
      int idistinct = trie.distinct("i");
      int tdistinct = trie.distinct("t");
      int adistinct = trie.distinct("a");
            
      System.out.println("s = " + sdistinct);
      System.out.println("i = " + idistinct);
      System.out.println("t = " + tdistinct);
      System.out.println("a = " + adistinct);  
      
      Iterator iter = trie.iterator("");
      String[] keyHighest = new String[10];
      int[] valueHighest = new int[10];
      String[] keyLowest = new String[10];
      int[] valueLowest = new int[10];
      int min = 0;
      int indexMin = 0;
      int max = 0;
      int indexMax = 0;
      int i = 0;
      int m = 0;
      int j = 0;
      int n = 0;
      while (iter.hasNext())
      {
          Map.Entry<String, Integer> entry = (Map.Entry) iter.next();
          String key = entry.getKey();
          Integer value = entry.getValue();
          
          // highest frequency
          if (i == keyHighest.length)
          {
            min = valueHighest[0];
            for (j = 0; j < keyHighest.length; j++)
            {
                if (valueHighest[j] < min)
                {
                    min = valueHighest[j];
                    indexMin = j;   
                }   
            }
            if (value > min)
            {
                keyHighest[indexMin] = key;
                valueHighest[indexMin] = value;
            }
          }
          else
          {
              keyHighest[i] = key;
              valueHighest[i] = value;
              i++;  
          }
          
          // lowest frequency
          if (m == keyLowest.length)
          {
            max = valueLowest[0];
            for (n = 0; n < keyLowest.length; n++)
            {
                if (valueLowest[n] > max)
                {
                    max = valueLowest[n];
                    indexMax = n;   
                }   
            }
            if (value < max)
            {
                keyLowest[indexMax] = key;
                valueLowest[indexMax] = value;
            }
          }
          else
          {
              keyLowest[m] = key;
              valueLowest[m] = value;
              m++;  
          }   
      }
      
      int length = 10;
      
      System.out.println();
      
      System.out.println("Highest frequency");
      for (int k = 0; k < keyHighest.length; k++)
          System.out.println(keyHighest[k] + " " + valueHighest[k]);
      
      System.out.println();
      
      System.out.println("Lowest frequency");
      for (int k = 0; k < keyLowest.length; k++)
          System.out.println(keyLowest[k] + " " + valueLowest[k]);      
   }
}


