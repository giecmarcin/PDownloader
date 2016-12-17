import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhonesDownloader {

    boolean addToList = true;
    public int numberOfErrors=0;

    public List<Phone> download(String url) throws IOException {

        Connection connect = Jsoup.connect(url).timeout(10 * 1000);
        Document document = connect.get();
        Elements all = document.getElementsByClass("product-item");//.tagName("data-product-name");//data-products-list-name=

        //System.out.println(all.toString());
        List<Phone> phones = new ArrayList<Phone>();
        //System.out.println("Ilosć:  " + all.size());
        Phone phone = null;
        for (int i = 0; i <all.size(); i++) {
            //try {
            System.out.println(i);
            phone = new Phone();
            String brand = all.get(i).attr("data-product-brand");//ok
            phone.setBrand(brand);
            String fullName = all.get(i).attr("data-product-name");//ok
            phone.setFullName(fullName);
            double price = Double.parseDouble(all.get(i).attr("data-product-price")); //ok
            phone.setPrice(price);
            String urlDetails = all.get(i).getElementsByClass("description-wrapper").select("a").first().attr("abs:href");

            String tempDisplayOfSize = all.get(i).getElementsByClass("features").select("ul").select("li").get(0).text();
            tempDisplayOfSize = tempDisplayOfSize.replaceAll(",",".");
            //System.out.println(findNumberInString(tempDisplayOfSize));
            phone.setSizeOfDisplay(findNumberInString(tempDisplayOfSize));

            String processor = all.get(i).getElementsByClass("features").select("ul").select("li").get(1).text();
            phone.setProcessor(processor);
            String tempRam = all.get(i).getElementsByClass("features").select("ul").select("li").get(2).text();
            tempRam = tempRam.replaceAll(",",".");
            if(tempRam.contains("+")){
                int index = tempRam.indexOf("+");
                if(index!=-1){
                    tempRam = tempRam.substring(0, index);
                }
            }
            phone.setRam(findNumberInString(tempRam));
            String operatingSystem = all.get(i).getElementsByClass("features").select("ul").select("li").get(3).text();
            phone.setOperatingSystem(operatingSystem);
            //System.out.println(all.get(i).getElementsByClass("features").select("ul").select("li").get(3).text());





            //For read details
            Connection connect2 = Jsoup.connect(urlDetails).timeout(10 * 1000);
            Document document2 = connect2.get();
            Elements allDetailsForPhone = document2.getElementsByClass("js-table-preview");
            ///phone.setProcessor(allDetailsForPhone.get(0).select("td").get(1).text());//processor
            phone.setGraphics(allDetailsForPhone.get(0).select("td").get(3).text()); //graphics


//            try {
//                double ram = findNumberInString(allDetailsForPhone.get(0).select("td").get(5).text());//ram
//                phone.setRam(ram);
//            } catch (Exception ex) {
//                double[] tab = {2.0, 3.0, 3.5, 4.0, 6.0, 12.0};
//                Random r = new Random();
//                phone.setBuiltInMemory(tab[r.nextInt(6)]);
//            }


            //double builtInMemory =  findNumberInString(allDetailsForPhone.get(0).select("td").get(7).text());//builtInMemory
            String tempBuiltInMemory = allDetailsForPhone.get(0).select("td").get(7).text().trim();
            //System.out.println("Problem here: " + tempBuiltInMemory);
            if (tempBuiltInMemory.contains("+")) {
                int index = tempBuiltInMemory.indexOf("+");
                tempBuiltInMemory = tempBuiltInMemory.substring(0, index).trim();
            }
            tempBuiltInMemory = tempBuiltInMemory.substring(0, tempBuiltInMemory.length() - 2).replaceAll(",", ".");
            try {
                phone.setBuiltInMemory(Double.parseDouble(tempBuiltInMemory));
            } catch (Exception e) {
                double[] tab = {5.0, 6.0, 8.0, 16.0, 32.0};
                Random r = new Random();
                phone.setBuiltInMemory(tab[r.nextInt(5)]);
                System.out.println("Był ex" + e.getMessage() + " "+ tempBuiltInMemory);
                numberOfErrors+=1;
            }


            phone.setTypeOfDisplay(allDetailsForPhone.get(0).select("td").get(9).text()); //type of display
//            String tempSize = allDetailsForPhone.get(0).select("td").get(11).text();
//            tempSize = tempSize.substring(0, tempSize.length() - 1);
//            try {
//                phone.setSizeOfDisplay(Double.parseDouble(tempSize));
//            } catch (Exception ex) {
//                double[] tab = {4.0, 4.5, 5.0, 5.0, 5.5, 6.0, 6.5, 7.0};
//                Random r = new Random();
//                phone.setSizeOfDisplay(tab[r.nextInt(8)]);
//            }


            phone.setResolutionOfDisplay(allDetailsForPhone.get(0).select("td").get(13).text());
            phone.setCommunication(allDetailsForPhone.get(0).select("td").get(15).text());
            phone.setNavigation(allDetailsForPhone.get(0).select("td").get(17).text());
            phone.setConnectors(allDetailsForPhone.get(0).select("td").get(19).text());
            try{
                phone.setCapacityOfBattery(findNumberInString(allDetailsForPhone.get(0).select("td").get(21).text()));
            }catch (Exception e){
                phone.setCapacityOfBattery(2500);
                System.out.println("Był ex");
                numberOfErrors+=1;
            }

            //phone.setOperatingSystem(allDetailsForPhone.get(0).select("td").get(23).text());

            String[] splitCamera = allDetailsForPhone.get(0).select("td").get(25).text().split("\\s");
            //System.out.println(splitCamera[0]);
            try {
                phone.setCameraMPX(Double.parseDouble(splitCamera[0]));
            } catch (Exception e) {
                double[] tab = {5.0, 6.0, 8.0, 12.0};
                Random r = new Random();
                phone.setCameraMPX(tab[r.nextInt(4)]);
                System.out.println("Był ex");
                numberOfErrors+=1;
            }


            try {
                phone.setFrontCameraMPX(Double.parseDouble(splitCamera[4]));
            } catch (Exception e) {
                double[] tab = {2.0, 3.0, 4.5, 5.0, 8.0};
                Random r = new Random();
                phone.setFrontCameraMPX(tab[r.nextInt(5)]);
                System.out.println("Był ex");
                numberOfErrors+=1;
            }

            phone.setFlashLamp(allDetailsForPhone.get(0).select("td").get(27).text());

            String tempThickness = allDetailsForPhone.get(0).select("td").get(31).text().replaceAll(",", ".");
            if (tempThickness.contains("m")) {
                int endIndex = tempThickness.indexOf("m");
                tempThickness = tempThickness.substring(0, endIndex);
            }
            try {
                phone.setThickness(findNumberInString(tempThickness)); //thickness in mm
            } catch (Exception e) {
                //	8,95
                double[] tab = {8.95, 8.5, 9.5, 9.0, 8.0, 9.5, 10.0, 7.5, 7.0};
                Random r = new Random();
                phone.setThickness(tab[r.nextInt(9)]);
                System.out.println("Był ex");
                numberOfErrors+=1;
            }

            try {
                String tempWidth = allDetailsForPhone.get(0).select("td").get(33).text().replaceAll(",", ".");
                phone.setWidth(findNumberInString(tempWidth)); //mm
            } catch (Exception e) {
                double[] tab = {70.0, 71.0, 71.5, 72.5, 73.0, 76.5, 78.0, 78, 5, 80.0, 80.5, 81.5, 82.5, 84.5, 85.0};
                Random r = new Random();
                phone.setWidth(tab[r.nextInt(15)]);
                System.out.println("Był ex");
                numberOfErrors+=1;
            }


            try {
                phone.setHeight(findNumberInString(allDetailsForPhone.get(0).select("td").get(35).text().replaceAll(",", "."))); //mm
            } catch (Exception e) {
                double[] tab = {140.0, 144.0, 145.0, 145.5, 148.0, 148.5, 150.5, 160.5, 165.5, 170.6};
                Random r = new Random();
                phone.setHeight(tab[r.nextInt(10)]);
                System.out.println("Był ex");
                numberOfErrors+=1;
            }

            try {
                phone.setWeight(findNumberInString(allDetailsForPhone.get(0).select("td").get(37).text().replaceAll(",", "."))); //g
            } catch (Exception e) {
                double[] tab = {140.0, 144.0, 145.0, 145.5, 148.0, 148.5, 150.5, 160.5, 165.5, 170.6};
                Random r = new Random();
                phone.setWeight(tab[r.nextInt(10)]);
                System.out.println("Był ex");
                numberOfErrors+=1;
            }


            if(39<=allDetailsForPhone.get(0).select("td").size()){
                phone.setColour(allDetailsForPhone.get(0).select("td").get(39).text());
            }


            //} catch (Exception e) {
            // phone = null;
            //}
            //if (phone != null) {
            phones.add(phone);
            //}
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
