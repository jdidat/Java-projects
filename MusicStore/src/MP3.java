/**
 * Created by JDidat on 2/29/2016.
 */
import java.util.UUID;
public class MP3 implements Sellable, Downloadable {
    public String getProductName() {
        return productName;
    }
    public double getPrice() {
        return 0.99;
    }
    public String generateDownloadCode() {
        return UUID.randomUUID().toString();
    }
    String productName;
    public MP3(String productName) {
        this.productName = productName;
    }
}
