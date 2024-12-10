package org.example;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
       Scanner scan = new Scanner(System.in);
       UserData user = new UserData();
       if(!user.extensionEnabled(true)){
           System.out.println("extension not enabled.");
           return;
       }
       URL urlChecker = new URL();
       ScanEmails scanEmails = new ScanEmails();
       URLTrie urlTrie = new URLTrie();
       GraphURL graphURL = new GraphURL();

       List<String> maliciousPattern = Arrays.asList("phish", "scan", "fraud");
    for(String pattern : maliciousPattern){
        urlTrie.addPattern(pattern);
        }
       System.out.println("Enter the file path: ");
       String filePath = scan.nextLine();;
       urlChecker.URLSstorage(filePath);

       System.out.println("Enter the URL provided in the email: ");
       String userURL = scan.nextLine();

       List<String> processingEmails = Arrays.asList(userURL.split(","));
       System.out.println("which keywords are in your email content: urgent, password, required, action, " +
               "document, or list other you want to check: ");
       String dummyContent = scan.nextLine();


       scanEmails.addEmail(dummyContent,processingEmails);

       boolean keywords = scanEmails.keywordDetection(dummyContent,processingEmails);
       System.out.println(keywords ? "phishing keywords found in email " : "No phishing keywords found" );


       List<String> phishingURLs = new ArrayList<>();
       List<String> safeURLs = new ArrayList<>();
       for (String url : processingEmails){
           if(urlChecker.phishingFound(url) || urlTrie.patternFound(url)){
              phishingURLs.add(url);
           } else{
               safeURLs.add(url);
           }
           graphURL.addNode(url);
       }
     String emailNode = "email_source";
       graphURL.addNode(emailNode);
       for(String url : processingEmails){
           graphURL.addNode(url);
           graphURL.addEdge(emailNode, url);
       }

        System.out.println("Analyzing URL connections: ");
        graphURL.analyze();

       scanEmails.quickSortEmails(scanEmails.emailProcessing, 0, scanEmails.emailProcessing.size()-1);
        System.out.println("Sort emails: ");

        for (Email mail : scanEmails.emailProcessing){
            System.out.println(scanEmails.divideEmails(mail) + ": " + mail);
        }

    }

    }


