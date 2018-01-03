import java.util.Arrays;

public class DeliveryDriver {
    private String name;
    private double wage;
    private int maxCarriableItems;
    private int numDeliveries = 0;
    private int minutesDelivering;
    private int numItems = 0;
    private PurchasedItem[] items;
    public DeliveryDriver(String name, double wage, int maxCarriableItems) {
        this.name = name;
        this.wage = wage;
        this.maxCarriableItems = maxCarriableItems;
        this.items = new PurchasedItem[maxCarriableItems];
    }
    public DeliveryDriver(String name, double wage) {
        this(name, wage, 5);
    }
    public DeliveryDriver(String name) {
        this(name, 7.25, 5);
    }
    public String getName() {
        return this.name;
    }
    public double getWage() {
        return this.wage;
    }
    public int getTimeSpent() {
        return this.minutesDelivering;
    }
    /**
     * * Consults the number of orders that the driver has delivered
     * @return number of orders delivered
     */
    public int getNumDelivered() {
        return this.numDeliveries;
    }
    public int getMaxCapacity() {
        return maxCarriableItems;
    }
    /**
     * Add the order to the list/array of items to deliver.
     *
     * @param item - order to add
     * @return true if the item is for delivery and if the driver can
     * hold more orders, return false otherwise
     */
    public boolean pickupOrder(PurchasedItem item) {
        if (numItems >= maxCarriableItems || !item.isDelivery()) {
            return false;
        }
        items[numItems] = item;
        numItems = numItems + 1;
        return true;
    }
    /**
     * Returns the number of items in the delivery list
     *
     * @return num items
     */
    public int getNumOrders() {
        return numItems;
    }
    /** Return an array of items to deliver.
     * the array has to be populated within the index 0 to numItems - 1
     * and of size numItems
     *
     * @return array of type PurchasedItem
     * */
    public PurchasedItem[] getOrders() {
        PurchasedItem[] itemCopy = new PurchasedItem[this.getNumOrders()];
        for (int i = 0; i < this.getNumOrders(); i++) {
            itemCopy[i] = items[i];
        }
        return itemCopy;
    }
    /**
     * Update how long the driver has been delivering and empty the
     * list of items to deliver.
     */
    public void deliverOrders() {
        for (int i = 0; i < numItems; i++) {
            minutesDelivering += items[i].getDeliveryTime();
        }
        numDeliveries = numDeliveries + numItems;
        numItems = 0;
        PurchasedItem[] tempItems = new PurchasedItem[maxCarriableItems];
        items = tempItems;
    }
    /**
     * Check if driver is scheduled to deliver an order and remove it
     * and update the driver's counters if the item is found.
     *
     * @param item - order to remove from deliveries
     * @return true if the driver is scheduled to deliver the item,
     * 			false otherwise
     */
    public boolean removeOrder(PurchasedItem item) {
        for (int i = 0; i < numItems; i++) {
            if (items[i].equals(item)) {
              //  items[i] = null;
                for (int j = i; j < numItems - 1; j++) {
                    items[j] = items[j + 1];
                }
                numItems--;
                return true;
            }
        }
        return false;
    }
    /**
     * Calculates the amount of money earned by the driver
     * @return amount of money earned by the driver
     */
    public double getMoneyEarned() {
        double hours = minutesDelivering / 60.0;
        double overtimeHours = hours - 8;
        if (hours >= 8) {
            return ((wage * 1.5) * (overtimeHours)) + wage * 8;
        }
        else {
            return wage * hours;
        }
    }
    /**
     * Compares if the input object is equal to the instance
     * Two objects are equal if they are of the same type and
     * all instance variables are equal.
     * @return true if they are, false if they are not
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof DeliveryDriver) {
            if (this.getName().equals(((DeliveryDriver)obj).getName()) && this.getWage() ==
                    ((DeliveryDriver)obj).getWage() && this.getTimeSpent() ==
                    ((DeliveryDriver)obj).getTimeSpent() && this.getNumDelivered()
                    == ((DeliveryDriver)obj).getNumDelivered()) {
                return true;
            }
        }
        return false;
    }
    @Override
    public String toString() {
        StringBuilder ret = new StringBuilder();

        ret.append("Name: ");
        ret.append(this.name);

        ret.append(" - Wage: $");
        ret.append(String.format("%.2f", this.wage));

        ret.append(" - Can Carry: ");
        ret.append(this.maxCarriableItems);

        ret.append(" items - Num Deliveries: ");
        ret.append(this.numDeliveries);

        ret.append(" - Minutes Worked: ");
        ret.append(this.minutesDelivering);
        ret.append(" min");

        ret.append(" - Currently Carrying: ");
        ret.append(this.numItems);
        ret.append(" items");

        return ret.toString();
    }

}
