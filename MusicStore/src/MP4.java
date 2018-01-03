/**
 * Created by JDidat on 2/29/2016.
 */
import java.util.UUID;
public class MP4 implements Sellable, Downloadable {
    public String getProductName() {
        return productName;
    }
    public double getPrice() {
        double price = 0;
        if(type == VideoType.MOVIE) {
            price = 4.99;
        }
        if(type == VideoType.TVSERIES) {
            price = 19.99;
        }
        return price;
    }
    public String generateDownloadCode() {
        return UUID.randomUUID().toString();
    }
    String productName;
    VideoType type;
    public MP4(String productName, VideoType type) {
        this.productName = productName;
        this.type = type;
    }
    public VideoType getType() {
        return type;
    }
}
