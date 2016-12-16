import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Marcin on 16.12.2016.
 */
public class PhonesDownloader {

    boolean addToList = true;

    public List<Phone> download(String url) throws IOException {
        Connection connect = Jsoup.connect(url);
        Document document = connect.get();
        Elements all = document.getElementsByClass("product-item");//.tagName("data-product-name");//data-products-list-name=

        //System.out.println(all.toString());
        List<Phone> phones = new ArrayList<Phone>();
        //System.out.println("Ilosć:  " + all.size());
        Phone phone = null;
        for (int i = 0; i < all.size(); i++) {
            try {
                phone = new Phone();
                String brand = all.get(i).attr("data-product-brand");//ok
                phone.setBrand(brand);
                String fullName = all.get(i).attr("data-product-name");//ok
                phone.setFullName(fullName);
                double price = Double.parseDouble(all.get(i).attr("data-product-price")); //ok
                phone.setPrice(price);
                String urlDetails = all.get(i).getElementsByClass("description-wrapper").select("a").first().attr("abs:href");

                //For read details
                Connection connect2 = Jsoup.connect(urlDetails);
                Document document2 = connect2.get();
                Elements allDetailsForPhone = document2.getElementsByClass("js-table-preview");
                phone.setProcessor(allDetailsForPhone.get(0).select("td").get(1).text());//processor
                phone.setGraphics(allDetailsForPhone.get(0).select("td").get(3).text()); //graphics
                double ram = findNumberInString(allDetailsForPhone.get(0).select("td").get(5).text());//ram
                phone.setRam(ram);
                //double builtInMemory =  findNumberInString(allDetailsForPhone.get(0).select("td").get(7).text());//builtInMemory
                String tempBuiltInMemory = allDetailsForPhone.get(0).select("td").get(7).text().trim();
                //System.out.println("Problem here: " + tempBuiltInMemory);
                if(tempBuiltInMemory.contains("+")){
                    int index = tempBuiltInMemory.indexOf("+");
                    tempBuiltInMemory = tempBuiltInMemory.substring(0, index).trim();
                }
                tempBuiltInMemory = tempBuiltInMemory.substring(0, tempBuiltInMemory.length() - 2).replaceAll(",", ".");
                phone.setBuiltInMemory(Double.parseDouble(tempBuiltInMemory));



                phone.setTypeOfDisplay(allDetailsForPhone.get(0).select("td").get(9).text()); //type of display
                String tempSize = allDetailsForPhone.get(0).select("td").get(11).text();
                tempSize = tempSize.substring(0, tempSize.length() - 1);

                phone.setSizeOfDisplay(Double.parseDouble(tempSize));


                phone.setResolutionOfDisplay(allDetailsForPhone.get(0).select("td").get(13).text());
                phone.setCommunication(allDetailsForPhone.get(0).select("td").get(15).text());
                phone.setNavigation(allDetailsForPhone.get(0).select("td").get(17).text());
                phone.setConnectors(allDetailsForPhone.get(0).select("td").get(19).text());
                phone.setCapacityOfBattery(findNumberInString(allDetailsForPhone.get(0).select("td").get(21).text()));
                phone.setOperatingSystem(allDetailsForPhone.get(0).select("td").get(23).text());

                String[] splitCamera = allDetailsForPhone.get(0).select("td").get(25).text().split("\\s");
                //System.out.println(splitCamera[0]);
                phone.setCameraMPX(Double.parseDouble(splitCamera[0]));
                phone.setFrontCameraMPX(Double.parseDouble(splitCamera[4]));
                phone.setFlashLamp(allDetailsForPhone.get(0).select("td").get(27).text());

                String tempThickness = allDetailsForPhone.get(0).select("td").get(31).text().replaceAll(",", ".");
                phone.setThickness(findNumberInString(tempThickness)); //thickness in mm

                String tempWidth = allDetailsForPhone.get(0).select("td").get(33).text().replaceAll(",", ".");
                phone.setWidth(findNumberInString(tempWidth)); //mm

                phone.setHeight(findNumberInString(allDetailsForPhone.get(0).select("td").get(35).text().replaceAll(",", "."))); //mm

                phone.setWeight(findNumberInString(allDetailsForPhone.get(0).select("td").get(37).text().replaceAll(",", "."))); //g

                phone.setColour(allDetailsForPhone.get(0).select("td").get(39).text());

            } catch (Exception e) {
                phone = null;
            }
            if (phone != null) {
                phones.add(phone);
            }
        }
        //System.out.println("Dodano: " + phones.size());
        return phones;
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
