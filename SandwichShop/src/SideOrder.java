/**
 * Created by JDidat on 3/6/2016.
 */
public class SideOrder implements PurchasedItem {
    String name;
    double matCost;
    double sellPrice;
    int delTime;
    OrderSize size = OrderSize.SMALL;
    public SideOrder(String name, double matCost, double sellPrice, int delTime, OrderSize size) {
        this.name = name;
        this.matCost = matCost;
        this.sellPrice = sellPrice;
        this.delTime = delTime;
        this.size = size;
    }
    public SideOrder(String name, double matCost, double sellPrice, int delTime) {
        this(name, matCost, sellPrice, delTime, OrderSize.SMALL);
    }
    public SideOrder(String name, double matCost, double sellPrice) {
        this(name, matCost, sellPrice, 0, OrderSize.SMALL);
    }
    public OrderSize getOrderSize() {
        return size;
    }
    public void setOrderSize(OrderSize size) {
        this.size = size;
    }
    public boolean isDelivery() {
        if (delTime <= 0 || delTime > 30)
            return false;

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
        if (size == OrderSize.SMALL) {
            return matCost + 0.00;
        }
        else if (size == OrderSize.MEDIUM) {
            return matCost + 0.40;
        }
        else if (size == OrderSize.LARGE) {
            return matCost + 0.80;
        }
        else if (size == OrderSize.ABSURD) {
            return matCost + 1.50;
        }
        return matCost;
    }
    public double getSalePrice() {
        if (size == OrderSize.SMALL) {
            return sellPrice + 0.00;
        }
        else if (size == OrderSize.MEDIUM) {
            return sellPrice + 2.00;
        }
        else if (size == OrderSize.LARGE) {
            return sellPrice + 3.00;
        }
        else if (size == OrderSize.ABSURD) {
            return sellPrice + 4.50;
        }
        return sellPrice;
    }
    public boolean equals(Object obj) {
        if (obj instanceof SideOrder) {
            if (this.getCustomerName().equals(((SideOrder)obj).getCustomerName()) &&
                    this.getDeliveryTime() == ((SideOrder)obj).getDeliveryTime()
                    && this.getMaterialCost() == ((SideOrder)obj).getMaterialCost() && this.getSalePrice() == (
                    (SideOrder)obj).getSalePrice() && this.getOrderSize() == ((SideOrder)obj).getOrderSize()) {
                return true;
            }
        }
        return false;
    }
}
