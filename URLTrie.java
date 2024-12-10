package org.example;
import java.util.HashMap;
import java.util.Map;

public class URLTrie {
    Map<Character,URLTrie> children;
    boolean maliciousPattern;

    public URLTrie(){
        children = new HashMap<>();
        maliciousPattern = false;
    }
    public void addPattern(String pattern) {
        URLTrie current = this;
        for (char ch : pattern.toCharArray()) {
            if (!current.children.containsKey(ch)) {
                current.children.put(ch, new URLTrie());
            }
            current = current.children.get(ch);
        }
        current.maliciousPattern = true;
    }
    public boolean patternFound(String url){
        URLTrie current = this;
        for(char ch : url.toCharArray()) {
            current.children.get(ch);
            if (current == null) {
                return false;
            }
        }
        return current.maliciousPattern;

    }
}



