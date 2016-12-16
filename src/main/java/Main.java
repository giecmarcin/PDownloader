import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class Main {

    public static void main(String[] args) throws IOException, Exception {
        List<Phone> allPhones = new ArrayList<Phone>();
        String url1 = "http://www.x-kom.pl/g-4/c/1590-smartfony-i-telefony.html?per_page=90";
        String url2 = "http://www.x-kom.pl/g-4/c/1590-smartfony-i-telefony.html?page=2&per_page=90";
        String url3 = "http://www.x-kom.pl/g-4/c/1590-smartfony-i-telefony.html?page=3&per_page=90";
        String url4 = "http://www.x-kom.pl/g-4/c/1590-smartfony-i-telefony.html?page=4&per_page=90";
        String url5 = "http://www.x-kom.pl/g-4/c/1590-smartfony-i-telefony.html?page=5&per_page=90";
        System.out.println("Hello World!");
        PhonesDownloader phonesDownloader = new PhonesDownloader();
        allPhones.addAll(phonesDownloader.download(url1));
        allPhones.addAll(phonesDownloader.download(url2));
        allPhones.addAll(phonesDownloader.download(url3));
        allPhones.addAll(phonesDownloader.download(url4));
        allPhones.addAll(phonesDownloader.download(url5));

        ObjectMapper objectMapper = new ObjectMapper();
        //Set pretty printing of json
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        String arrayToJson = "";
        try {
            FileWriter fw = new FileWriter("phones.json");
            arrayToJson = objectMapper.writeValueAsString(allPhones);
            System.out.println("1. Convert List of person objects to JSON :");
            System.out.println(arrayToJson);
            fw.write(arrayToJson);
            fw.close();
            System.out.println("Done " + allPhones.size());
        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
