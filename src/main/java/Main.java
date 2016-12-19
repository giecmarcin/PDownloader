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
        //MotoDownloader motoDownloader = new MotoDownloader();
        //motoDownloader.download("");
        List<Phone> allPhones = new ArrayList<Phone>();
////        String url1 = "http://www.x-kom.pl/g-4/c/1590-smartfony-i-telefony.html?per_page=90";
////        String url2 = "http://www.x-kom.pl/g-4/c/1590-smartfony-i-telefony.html?page=2&per_page=90";
////        String url3 = "http://www.x-kom.pl/g-4/c/1590-smartfony-i-telefony.html?page=3&per_page=90";
////        String url4 = "http://www.x-kom.pl/g-4/c/1590-smartfony-i-telefony.html?page=4&per_page=90";
////        String url5 = "http://www.x-kom.pl/g-4/c/1590-smartfony-i-telefony.html?page=5&per_page=90";
//
//
       String url1 = "http://www.x-kom.pl/g-4/c/1590-smartfony-i-telefony.html?per_page=90&f[201][61322]=1&f[201][61325]=1&f[201][61324]=1&f[201][61323]=1";
//        String url2 = "http://www.x-kom.pl/g-4/c/1590-smartfony-i-telefony.html?page=2&per_page=90&f[201][61322]=1&f[201][61325]=1&f[201][61324]=1&f[201][61323]=1";
//        String url3 = "http://www.x-kom.pl/g-4/c/1590-smartfony-i-telefony.html?page=3&per_page=90&f[201][61322]=1&f[201][61325]=1&f[201][61324]=1&f[201][61323]=1";
//        String url4 = "http://www.x-kom.pl/g-4/c/1590-smartfony-i-telefony.html?page=4&per_page=90&f[201][61322]=1&f[201][61325]=1&f[201][61324]=1&f[201][61323]=1";
//        String url5 = "http://www.x-kom.pl/g-4/c/1590-smartfony-i-telefony.html?page=5&per_page=90&f[201][61322]=1&f[201][61325]=1&f[201][61324]=1&f[201][61323]=1";
//
//        System.out.println("Hello World!");
       PhonesDownloader phonesDownloader = new PhonesDownloader();
       phonesDownloader.download(url1);
       phonesDownloader.convertToList();
      //allPhones.addAll(phonesDownloader.download(url1));
//        allPhones.addAll(phonesDownloader.download(url2));
//        allPhones.addAll(phonesDownloader.download(url3));
//        allPhones.addAll(phonesDownloader.download(url4));
//        allPhones.addAll(phonesDownloader.download(url5));
//
//        int id = 1;
//        for (Phone p : allPhones) {
//            p.setId(id);
//            id += 1;
//
//            if(p.getColour().isEmpty()){
//                p.setColour(randomColour());
//            }
//            if (checkTextHaveNumbers(p.getColour())) {
//                p.setColour(randomColour());
//            }
//
//            if (p.getColour().toLowerCase().contains("czujnik")) {
//                p.setColour(randomColour());
//            }
//
//            String temp = p.getFullName().replaceAll("\\s+", "");
//            String jpg1 = temp + "Img1";
//            String jpg2 = temp + "Img2";
//            String jpg3 = temp + "Img3";
//            p.setImagesUrl(new String[]{jpg1, jpg2, jpg3});
//        }
//
//        ObjectMapper objectMapper = new ObjectMapper();
//        //Set pretty printing of json
//        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
//        String arrayToJson = "";
//        try {
//            FileWriter fw = new FileWriter("phones-data.json");
//            arrayToJson = objectMapper.writeValueAsString(allPhones);
//            System.out.println("1. Convert List of person objects to JSON :");
//            System.out.println(arrayToJson);
//            fw.write(arrayToJson);
//            fw.close();
//            System.out.println("Done " + allPhones.size());
//            System.out.println("Błędy: " + phonesDownloader.numberOfErrors);
//        } catch (JsonGenerationException e) {
//            e.printStackTrace();
//        } catch (JsonMappingException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
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
