/**
 * Created by JDidat on 2/29/2016.
 */
public class DVD implements Sellable {
    public String getProductName() {
        return productName;
    }
    public double getPrice() {
        return price;
    }
    String productName;
    VideoType type;
    double price;
    public DVD(String productName, VideoType type, double price) {
        this.productName = productName;
        this.type = type;
        this.price = price;
    }
    public VideoType getType() {
        return type;
    }
}
