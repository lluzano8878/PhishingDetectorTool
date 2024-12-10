package org.example;

import java.util.List;

public class Email {
    String content;
    List<String> urls;

    public Email(String content2, List<String> urls2){
        this.content = content2;
        this.urls = urls2;
    }
    public String toString(){
        return "Email{" + "Content= " + content + '\'' + ", urls = " + urls + '}';

    }
}
