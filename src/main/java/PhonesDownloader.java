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
    List<TreeMap<String, String>> dataForPhones = new ArrayList<>();

    public TreeMap<String, String> download(String url) throws IOException {
        Connection connect = Jsoup.connect(url).timeout(10 * 1000);
        Document document = connect.get();
        Elements all = document.getElementsByClass("product-item");//.tagName("data-product-name");//data-products-list-name=
        List<String> urls = new ArrayList<>();


        for (int i = 0; i < 2; i++) {
            String urlDetails = all.get(i).getElementsByClass("description-wrapper").select("a").first().attr("abs:href");
            urls.add(urlDetails);
        }
        System.out.println(urls.size());
        for (String u : urls) {
            values = new TreeMap<>();
            //System.out.println(u);
            Connection connect2 = Jsoup.connect(u).timeout(10 * 2000);
            Document document2 = connect2.get();
            String fullName = document2.getElementsByClass("product-info").select("h1").text();
            values.put("Nazwa", fullName);
            String brand = document2.getElementsByClass("subheader").select("span").first().text();
            values.put("Marka", brand);
            String price = document2.getElementsByClass("clearfix").first().text().replaceAll(",", ".");
            //System.out.println(price);
            values.put("Cena", price);
            Elements allDetailsForPhone = document2.getElementsByClass("js-table-preview");

            int numberOfRows = allDetailsForPhone.select("table").get(0).select("th").size();
            List<String> keys = new ArrayList<>();
            for (int m = 0; m < numberOfRows; m++) {
                String key = allDetailsForPhone.select("table").get(0).select("th").get(m).text();
                String val = allDetailsForPhone.select("table").get(0).getElementsByClass("col-xs-9").get(m).text();
                values.put(key, val);
                //System.out.println(key + ": " + val);
            }
            dataForPhones.add(values);
        }


        return values;
    }

    public void convertToList() {
        List<Phone> phones = new ArrayList<>();
        Phone phone = null;
        for (TreeMap<String, String> dataForOnePhone : dataForPhones) {
            phone = new Phone();
            //System.out.println(dataForOnePhone.get("Cena").replaceAll(",","."));
            phone.setBrand(dataForOnePhone.get("Marka"));
            phone.setFullName(dataForOnePhone.get("Nazwa"));
            phone.setPrice(findNumberInString(dataForOnePhone.get("Cena")));
            phone.setProcessor(dataForOnePhone.get("Procesor"));
            phone.setGraphics(dataForOnePhone.get("Układ graficzny"));

            double ram = findNumberInString(dataForOnePhone.get("Pamięć RAM").replaceAll(",","."));
            phone.setRam(ram);
            String tempBuiltMemory = dataForOnePhone.get("Pamięć wbudowana").replaceAll(",",".");
            if(tempBuiltMemory.contains("+")){
                int index = tempBuiltMemory.indexOf("+");
                if(index!=-1){
                    tempBuiltMemory = tempBuiltMemory.substring(0, index);
                }
            }
            phone.setBuiltInMemory(findNumberInString(tempBuiltMemory));
            phone.setTypeOfDisplay(dataForOnePhone.get("Typ ekranu"));
            phone.setSizeOfDisplay(findNumberInString(dataForOnePhone.get("Przekątna ekranu").substring(0, dataForOnePhone.get("Przekątna ekranu").length()-1)));
            phone.setResolutionOfDisplay(dataForOnePhone.get("Rozdzielczość ekranu"));
            String tempCommunity[] = dataForOnePhone.get("Łączność").split("\\s+");
            phone.setCommunication(tempCommunity);
            phones.add(phone);
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
