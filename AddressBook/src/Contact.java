package src;

/**
 * Created by JDidat on 2/21/2016.
 */
public class Contact {
    private String name;
    private long number;
    private String address;
    public Contact(String name) {
        this.name = name;
        this.number = 0;
        this.address = null;
    }
    public Contact(String name, long phoneNumber) {
        this(name);
        this.number = phoneNumber;
        this.address = null;
    }
    public Contact(String name, long phoneNumber, String address) {
        this(name, phoneNumber);
        this.address = address;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public long getNumber() {
        return number;
    }
    public void setNumber(long number) {
        this.number = number;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public boolean equals(Contact c) {
        if (c.getName().equals(this.name)) {
            return true;
        }
        return false;
    }
}
