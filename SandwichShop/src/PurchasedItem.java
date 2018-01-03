public interface PurchasedItem {
    boolean isDelivery();
    String getCustomerName();
    int getDeliveryTime();
    void setDeliveryTime(int time);
    double getMaterialCost();
    double getSalePrice();
}

