package org.example;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonMappingException;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;


public class URL {
    Hashtable<String, Boolean> phishingURLStorage = new Hashtable<>();

    public static class domainInfo{
        @JsonProperty("dateadded")
        public String dataadded;
        @JsonProperty("url")
        public String url;
        @JsonProperty("url_status")
        public String url_status;
        @JsonProperty("last_online")
        public String last_online;
        @JsonProperty("threat")
        public String threat;
        @JsonProperty("tags")
        public List<String> tags;
        @JsonProperty("urlhaus_link")
        public String urlhaus_link;
        @JsonProperty("reporter")
        public String reporter;
    }

    public void PhishingURLStable(String JsonFilePath) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            File file = new File(JsonFilePath);
            if (!file.exists() || !JsonFilePath.toLowerCase().endsWith(".json")) {
                System.err.println("File not found or not a JSON file");
                return;
            }

            JsonNode rootNode = objectMapper.readTree(file);
            JsonNode urlsNode = rootNode.get("urls");
            if(urlsNode == null){
                System.err.println("'urls' key is not found in file");
                return;
            }
            Iterator<String> keys = urlsNode.fieldNames();
            while (keys.hasNext()) {
                String key = keys.next();
                JsonNode entryNode = urlsNode.get(key);
                domainInfo[] domain = objectMapper.treeToValue(entryNode,domainInfo[].class);

                for(domainInfo info : domain){
                    String normalURL = info.url.toLowerCase().trim();
                    phishingURLStorage.put(normalURL,true);
                }
            }
            System.out.println("Successfully loaded phishing URLS from " + JsonFilePath);
        } catch (Exception e) {
            System.err.println("Error loading phishingURLS: " + e.getMessage());
        }
    }

    public boolean phishingFound(String url){
        String normalURL = url.trim().toLowerCase();
        if(phishingURLStorage.get(normalURL)){
            System.out.println("Phishing URL found: " + url);
            System.out.println("marked as phishing");
            return true;
        }
        else{
            System.out.println("URL is safe: " + url);
            return false;
        }
    }
    public void URLSstorage(String absolutePath){
        PhishingURLStable(absolutePath);
    }
}
