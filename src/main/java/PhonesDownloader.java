import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhonesDownloader {
    TreeMap<String, String> values = new TreeMap<>();

    public TreeMap<String, String> download(String url) throws IOException {
//        TreeMap<String, String> values = new TreeMap<>();
        Connection connect = Jsoup.connect(url).timeout(10 * 1000);
        Document document = connect.get();
        Elements all = document.getElementsByClass("product-item");//.tagName("data-product-name");//data-products-list-name=

        for (int i = 0; i < 1; i++) {
            System.out.println("Numer: " + i);
            String brand = all.get(i).attr("data-product-brand");
            values.put("Producent", brand);

            String fullName = all.get(i).attr("data-product-name");
            values.put("Nazwa", fullName);
            String price = all.get(i).attr("data-product-price");
            values.put("Cena", price);
            String urlDetails = all.get(i).getElementsByClass("description-wrapper").select("a").first().attr("abs:href");
            values.put("xKomURL", urlDetails);

            //For read details
            Connection connect2 = Jsoup.connect(urlDetails).timeout(10 * 1000);
            Document document2 = connect2.get();
            Elements allDetailsForPhone = document2.getElementsByClass("js-table-preview");

            int numberOfRows = allDetailsForPhone.select("table").get(i).select("th").size();
            List<String> keys = new ArrayList<>();
            for (int m = 0; m < numberOfRows; m++) {
                String key = allDetailsForPhone.select("table").get(i).select("th").get(m).text();
                String val = allDetailsForPhone.select("table").get(i).getElementsByClass("col-xs-9").get(m).text();
                values.put(key, val);
            }


        }
//        for (Map.Entry<String, String> entry : values.entrySet()) {
//            System.out.println(entry.getKey() + ": " + entry.getValue());
//        }
        return values;
    }

    public void convertToList() {
        List<Phone> phones = new ArrayList<>();
        Phone phone = null;
        for (Map.Entry<String, String> entry : values.entrySet()) {
            phone = new Phone();
            System.out.println(entry.getKey() + ": " + entry.getValue());
            String key = entry.getKey();
            String value = entry.getValue();
            if (key.contains("Aparat")) {
                System.out.println("Tutaj");
                String[] temp = value.split("\\s+");
                List<Double> camera = new ArrayList<>();
                for (String s : temp) {
                    try{
                        double tempCamera = Double.parseDouble(s.replaceAll(",","."));
                        camera.add(tempCamera);
                    }catch (Exception e){

                    }
                }
                if(!camera.isEmpty()){
                    phone.setCameraMPX(camera.get(0));
                }
                if(camera.size()==2){
                    phone.setFrontCameraMPX(camera.get(1));
                }
            }
            if(key.contains("Bateria")){
                String temp = value.replaceAll(",",".");
                phone.setCapacityOfBattery(findNumberInString(temp));
            }
            if(key.contains("Cena")){
                String temp = value.replaceAll(",",".");
                phone.setPrice(Double.parseDouble(temp));
            }
            if(key.contains("Dodatkowe informacje")){

            }
        }
    }

    private Double findNumberInString(String text) {
        StringBuffer sBuffer = new StringBuffer();
        Pattern p = Pattern.compile("[0-9]+.[0-9]*|[0-9]*.[0-9]+|[0-9]+");
        Matcher m = p.matcher(text);
        while (m.find()) {
            sBuffer.append(m.group());
        }
        return Double.parseDouble(sBuffer.toString());
    }
}
