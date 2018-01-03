/*
******cs180 project 4******
Jackson Didat, jdidat@purdue.edu, lab section 12
Jonathon Du, du129@purdue.edu, lab section 1
4-13-2106
*/

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Email {
    private String recipient;
    private String sender;
    private long id;
    private String message;

    public Email(String recipient, String sender, long id, String message) {
        this.recipient = recipient;
        this.sender = sender;
        this.id = id;
        this.message = message;
    }

    public String getOwner() {
        return recipient;
    }

    public String getSender() {
        return sender;
    }

    public long getID() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public String toString() {
        Date today = Calendar.getInstance().getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("EEE MMM dd hh:mm:ss zzz yyyy ");
        String time = formatter.format(today);

        return id + ";" + time + "; " + "From: " + sender + " \"" + message + "\"";
    }

}
