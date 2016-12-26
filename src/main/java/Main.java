import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class Main {

    public static void main(String[] args) throws IOException, Exception {

        List<Phone> allPhones = new ArrayList<Phone>();
        String url1 = "http://www.x-kom.pl/g-4/c/1590-smartfony-i-telefony.html?per_page=90&f[201][61322]=1&f[201][61325]=1&f[201][61324]=1&f[201][61323]=1";
        String url2 = "http://www.x-kom.pl/g-4/c/1590-smartfony-i-telefony.html?page=2&per_page=90&f[201][61322]=1&f[201][61325]=1&f[201][61324]=1&f[201][61323]=1";
        String url3 = "http://www.x-kom.pl/g-4/c/1590-smartfony-i-telefony.html?page=3&per_page=90&f[201][61322]=1&f[201][61325]=1&f[201][61324]=1&f[201][61323]=1";
        String url4 = "http://www.x-kom.pl/g-4/c/1590-smartfony-i-telefony.html?page=4&per_page=90&f[201][61322]=1&f[201][61325]=1&f[201][61324]=1&f[201][61323]=1";
        String url5 = "http://www.x-kom.pl/g-4/c/1590-smartfony-i-telefony.html?page=5&per_page=90&f[201][61322]=1&f[201][61325]=1&f[201][61324]=1&f[201][61323]=1";
//
//        System.out.println("Hello World!");
       PhonesDownloader phonesDownloader1 = new PhonesDownloader();
       phonesDownloader1.download(url1);
       allPhones.addAll(phonesDownloader1.convertToList());

        PhonesDownloader phonesDownloader2 = new PhonesDownloader();
        phonesDownloader2.download(url2);
        allPhones.addAll(phonesDownloader2.convertToList());

        PhonesDownloader phonesDownloader3 = new PhonesDownloader();
        phonesDownloader3.download(url3);
        allPhones.addAll(phonesDownloader3.convertToList());

        PhonesDownloader phonesDownloader4 = new PhonesDownloader();
        phonesDownloader4.download(url4);
        allPhones.addAll(phonesDownloader4.convertToList());

        PhonesDownloader phonesDownloader5 = new PhonesDownloader();
        phonesDownloader5.download(url5);
        allPhones.addAll(phonesDownloader5.convertToList());

        ObjectMapper objectMapper = new ObjectMapper();
        //Set pretty printing of json
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        String arrayToJson = "";
        try {
            FileWriter fw = new FileWriter("D:\\phones-final.json");
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

    private static boolean checkTextHaveNumbers(String text) {
        boolean found = false;
        if (!text.isEmpty()) {
            for (char c : text.toCharArray()) {
                if (Character.isDigit(c)) {
                    found = true;
                    break;
                }
            }
        }
        return found;
    }

    private static String randomColour() {
        String[] colours = {"szary", "czarny", "granatowy", "niebieski", "biały", "złoty"};
        Random r = new Random();
        return colours[r.nextInt(6)];
    }

}
