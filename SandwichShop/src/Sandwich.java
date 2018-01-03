/**
 * Created by JDidat on 3/6/2016.
 */
public class Sandwich implements PurchasedItem {
    String name;
    double matCost;
    double sellPrice;
    int delTime;
    Spicyness level;
    int condiments = 0;
    public static double costOfCondiment = .05;
    public static double pricePerCondiment = .75;
    public Sandwich (String name, double matCost, double sellPrice, int delTime, Spicyness level, int condiments) {
        this.name = name;
        this.matCost = matCost;
        this.sellPrice = sellPrice;
        this.delTime = delTime;
        this.level = level;
        this.condiments = condiments;
    }
    public Sandwich (String name, double matCost, double sellPrice) {
        this(name, matCost, sellPrice, 0, Spicyness.MILD, 0);
    }
    public Sandwich (String name, double matCost) {
        this(name, matCost, matCost * 3.5, 0, Spicyness.MILD, 0);
    }
    public boolean isDelivery() {
        if (delTime <= 0 || delTime > 60) {
            return false;
        }

        return true;
    }
    public String getCustomerName() {
        return name;
    }
    public int getDeliveryTime() {
        return delTime;
    }
    public void setDeliveryTime(int time) {
        if (time < 0) {
            time = 0;
        }
        this.delTime = time;
    }
    public double getMaterialCost() {
        if (condiments == 0) {
            return matCost;
        }
        else {
            return matCost + (condiments * costOfCondiment);
        }
    }
    public double getSalePrice() {
        return sellPrice + (condiments * pricePerCondiment);
    }
    public Spicyness getSpicyness() {
        return level;
    }
    public void setSpicyness(Spicyness level) {
        this.level = level;
    }
    public void addCondiments(int num) {
        condiments = condiments + num;

    }
    public void removeCondiments(int num) {
        if (condiments - num < 0) {
            condiments = 0;
        }
        else {
            condiments = condiments - num;
        }
    }
    public int getNumCondiments() {
        return condiments;
    }
    public boolean equals(Object obj) {
        if (obj instanceof Sandwich) {
            if (this.getCustomerName().equals(((Sandwich)obj).getCustomerName()) &&
                    this.getMaterialCost() == ((Sandwich)obj).getMaterialCost()
                    && this.getSalePrice() == ((Sandwich)obj).getSalePrice() && this.getDeliveryTime() == (
                    (Sandwich)obj).getDeliveryTime() && this.getSpicyness() == ((Sandwich)obj).getSpicyness()) {
                return true;
            }
        }
        return false;
    }
}
