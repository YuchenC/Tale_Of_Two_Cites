
package com.mycompany.taleoftwocities;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class Trie 
{
    private TrieNode root;

    public Trie()
    {
        root = new TrieNode();
    }
    
    public int index (char key)
    {
        int index = 0;
        if ((int)key > 91)
            index = (int)key - 32;
        index = index - 64;
        return index;
    }
    
    public void put (String word)
    {
        //int value = 0;
        TrieNode x;
        if (root.linksTo[index(word.charAt(0))] == null)
        {
            root.linksTo[index(word.charAt(0))] = new TrieNode();
            x = root.linksTo[index(word.charAt(0))];
        }    
        else
            x = root.linksTo[index(word.charAt(0))];
            
        for (int i = 1; i < word.length(); i++)
        {
            if (x.linksTo[index(word.charAt(i))] == null)
            {
                x.linksTo[index(word.charAt(i))] = new TrieNode();
                x = x.linksTo[index(word.charAt(i))];
            }
            else
                x = x.linksTo[index(word.charAt(i))];
        }
        
        x.value++;
        x.word = word;
   }
    
    public int get (String word)
    {
        int value = 0;
        TrieNode x = root;
        int i = 0;
        while (i < word.length() && x.linksTo[index(word.charAt(i))] !=null)
        {
            x = x.linksTo[index(word.charAt(i))];
            if (i == word.length() - 1)
                value = x.value;
            i++;
        }
        return value;
    }
    
    public int count(String word)
    {
        int sum = 0;
        TrieNode x = root;
        int i = 0;
        while (i < word.length() && x.linksTo[index(word.charAt(i))] !=null)
        {
            x = x.linksTo[index(word.charAt(i))];
            sum = x.value;
            if (i == word.length() - 1)
                sum = helpCount(x, sum);
            i++;
        }
        return sum;
    }
    
    public int helpCount (TrieNode x, int sum)
    {
        for (int i = 1; i < x.linksTo.length; i++)
        {
            if (x.linksTo[i] != null)
            {
                if (x.linksTo[i].value >= 1)
                    sum += x.linksTo[i].value;
                sum = helpCount(x.linksTo[i], sum);
            }   
        }
        return sum;
    }
    
    public int distinct (String word)
    {
        int sum = 0;
        TrieNode x = root;
        int i = 0;
        while (i < word.length() && x.linksTo[index(word.charAt(i))] !=null)
        {
            x = x.linksTo[index(word.charAt(i))];
            if (i == word.length() - 1)
                sum = helpDistinct(x, sum); 
            i++;
        }
        return sum;
    }
    
    public int helpDistinct (TrieNode x, int sum)
    {
        for (int i = 1; i < x.linksTo.length; i++)
        {
            if (x.linksTo[i] != null)
            {
                if (x.linksTo[i].value >= 1)
                    sum++;
                sum = helpDistinct(x.linksTo[i], sum);
            }   
        }
        return sum;
    }
    
    public Iterator<Map.Entry<String, Integer>> iterator (String word)
    {
        
        TreeMap<String, Integer> map = new TreeMap<String, Integer>();
        if (word == "")
            convertTrieNodeToMap(root, map);
        else
        {
            TrieNode  x = root;
            for (int k = 0; k < word.length(); k++)
            {
                int m = index(word.charAt(k));
                x = x.linksTo[m];
            }
            if (x.word != null)
                convertTrieNodeToMap(x, map);
            else
                System.out.println("No such word");
        }
        
        Iterator<Map. Entry<String, Integer>> iter = map.entrySet().iterator();
        return iter;
    }
    
    public void convertTrieNodeToMap (TrieNode current, Map<String, Integer> map)
    {
        TrieNode x = current;
        for (int i = 1; i < 27; i++)
        {
            
            if (current.linksTo[i] != null)
            {
                if (current.linksTo[i].word != null)
                {
                    x = current.linksTo[i];
                    map.put(x.word, x.value);
                }
                convertTrieNodeToMap(current.linksTo[i], map);
            }   
        }    
    }   
}
