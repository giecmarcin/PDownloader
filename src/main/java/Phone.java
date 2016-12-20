/**
 * Created by Marcin on 16.12.2016.
 */
public class Phone {
    private int id;
    private String brand;
    private String fullName;
    private double price;
    private String processor;
    private String graphics;
    private double ram;
    private double builtInMemory;
    private String typeOfDisplay;
    private double sizeOfDisplay;
    private String resolutionOfDisplay;
    private String[] communication;
    private String navigation;
    private String connectors;
    private double capacityOfBattery;
    private String operatingSystem;
    private double frontCameraMPX;
    private double cameraMPX;
    private String flashLamp;
    private double thickness;
    private double width;
    private double height;
    private double weight;
    private String colour="";
    private String[] imagesUrl;
    private String imageUrlXKom;
    private String extraInfo;
    private String guarantee;
    private String resolutionRecordingVideo;
    private String includedAccessories;
    private byte[] imageTab;

    public Phone() {
    }

    public String[] getImagesUrl() {
        return imagesUrl;
    }

    public void setImagesUrl(String[] imagesUrl) {
        this.imagesUrl = imagesUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getProcessor() {
        return processor;
    }

    public void setProcessor(String processor) {
        this.processor = processor;
    }

    public String getGraphics() {
        return graphics;
    }

    public void setGraphics(String graphics) {
        this.graphics = graphics;
    }

    public double getRam() {
        return ram;
    }

    public void setRam(double ram) {
        this.ram = ram;
    }

    public double getBuiltInMemory() {
        return builtInMemory;
    }

    public void setBuiltInMemory(double builtInMemory) {
        this.builtInMemory = builtInMemory;
    }

    public String getTypeOfDisplay() {
        return typeOfDisplay;
    }

    public void setTypeOfDisplay(String typeOfDisplay) {
        this.typeOfDisplay = typeOfDisplay;
    }

    public double getSizeOfDisplay() {
        return sizeOfDisplay;
    }

    public void setSizeOfDisplay(double sizeOfDisplay) {
        this.sizeOfDisplay = sizeOfDisplay;
    }

    public String getResolutionOfDisplay() {
        return resolutionOfDisplay;
    }

    public void setResolutionOfDisplay(String resolutionOfDisplay) {
        this.resolutionOfDisplay = resolutionOfDisplay;
    }

    public String[] getCommunication() {
        return communication;
    }

    public void setCommunication(String[] communication) {
        this.communication = communication;
    }

    public String getNavigation() {
        return navigation;
    }

    public void setNavigation(String navigation) {
        this.navigation = navigation;
    }

    public String getConnectors() {
        return connectors;
    }

    public void setConnectors(String connectors) {
        this.connectors = connectors;
    }

    public double getCapacityOfBattery() {
        return capacityOfBattery;
    }

    public void setCapacityOfBattery(double capacityOfBattery) {
        this.capacityOfBattery = capacityOfBattery;
    }

    public String getOperatingSystem() {
        return operatingSystem;
    }

    public void setOperatingSystem(String operatingSystem) {
        this.operatingSystem = operatingSystem;
    }

    public double getFrontCameraMPX() {
        return frontCameraMPX;
    }

    public void setFrontCameraMPX(double frontCameraMPX) {
        this.frontCameraMPX = frontCameraMPX;
    }

    public double getCameraMPX() {
        return cameraMPX;
    }

    public void setCameraMPX(double cameraMPX) {
        this.cameraMPX = cameraMPX;
    }

    public String getFlashLamp() {
        return flashLamp;
    }

    public void setFlashLamp(String flashLamp) {
        this.flashLamp = flashLamp;
    }

    public double getThickness() {
        return thickness;
    }

    public void setThickness(double thickness) {
        this.thickness = thickness;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public String getExtraInfo() {
        return extraInfo;
    }

    public void setExtraInfo(String extraInfo) {
        this.extraInfo = extraInfo;
    }

    public String getGuarantee() {
        return guarantee;
    }

    public void setGuarantee(String guarantee) {
        this.guarantee = guarantee;
    }

    public String getResolutionRecordingVideo() {
        return resolutionRecordingVideo;
    }

    public void setResolutionRecordingVideo(String resolutionRecordingVideo) {
        this.resolutionRecordingVideo = resolutionRecordingVideo;
    }

    public String getIncludedAccessories() {
        return includedAccessories;
    }

    public void setIncludedAccessories(String includedAccessories) {
        this.includedAccessories = includedAccessories;
    }

    public String getImageUrlXKom() {
        return imageUrlXKom;
    }

    public void setImageUrlXKom(String imageUrlXKom) {
        this.imageUrlXKom = imageUrlXKom;
    }

    public byte[] getImageTab() {
        return imageTab;
    }

    public void setImageTab(byte[] imageTab) {
        this.imageTab = imageTab;
    }
}
