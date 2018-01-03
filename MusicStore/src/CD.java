/**
 * Created by JDidat on 2/29/2016.
 */
public class CD implements Sellable {
    public String getProductName() {
        return productName;
    }
    public double getPrice() {
        return price;
    }
    String productName;
    int totalSongs;
    double price;
    public CD(String productName, int totalSongs, double price) {
        this.productName = productName;
        this.totalSongs = totalSongs;
        this.price = price;
    }
    public int getTotalSongs() {
        return totalSongs;
    }
}
