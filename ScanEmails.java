package org.example;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class ScanEmails {
    URL domainCheck = new URL();
    URLTrie urlTrie =  new URLTrie();
    List<String> keywords = Arrays.asList("Update", "Invoice", "Document", "Documents", "Verification",
            "Action", "required", "File", "urgent", "password");

    Stack<Email> emailProcessing = new Stack<>();

    public void addEmail(String content, List<String> urls ){
        Email email = new Email(content, urls);
        emailProcessing.push(email);
    }


    public String divideEmails(Email emails){
        boolean phishingDetected = false;
        phishingDetected = keywordDetection(emails.content, emails.urls);

        if(phishingDetected){
            return "phishing found";
        } else{
            return "safe";
        }
    }


    public boolean keywordDetection(String emailContent, List<String> URLprovided){
        boolean phishingDetected = false;
        for(String content : keywords){
            if(emailContent.toLowerCase().contains(content.toLowerCase()))  {
                phishingDetected = true;
                break;
            }
        }
        for (String url : URLprovided){
            if(urlTrie.patternFound(url)){
                phishingDetected = true;
                break;
            }
        }
        return phishingDetected;

    }

    //sort the emails, categorizes the emails - safe and phishing
    public int quickSortPartition(List<Email> email, int low, int high){
        Email pivot = email.get(high);
        String pivotDivide = divideEmails(pivot);

        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (divideEmails(email.get(j)).equals("safe")){
                i++;
                Email temp = email.get(i);
                email.set(j, temp);
            }
        }
        Email temp = email.get(i);
        email.set(i + 1, email.get(high));
        email.set(high, temp);
        return i + 1;
    }

    public void quickSortEmails(List <Email> email, int low, int high ) {
        if (low < high) {
            int partIdx = quickSortPartition(email, low, high);

            quickSortPartition(email, low, partIdx - 1);
            quickSortPartition(email, partIdx + 1, high);
        }
    }
}

