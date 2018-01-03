/*
******cs180 project 4******
Jackson Didat, jdidat@purdue.edu, lab section 12
Jonathon Du, du129@purdue.edu, lab section 1
4-13-2106
*/

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by winsh on 3/26/2016.
 */
public class User {
    private String username;
    private String password;
    int usermail = 0;
    private long emailID;
    DynamicBuffer buffer = new DynamicBuffer(10);
    Random r = new Random(999999999);

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        emailID = 0;

    }

    public String getName() {
        return username;
    }

    public boolean checkPassword(String password) {
        if (password.equals(this.password)) {
            return true;
        }
        return false;
    }

    public int numEmail() {
        return usermail;
    }

    public void receiveEmail(String sender, String message) {
//        Add the message to the user's inbox.
//        You should call some operations provided by DynamicBuffer
//        Email email = new Email(username,sender,emailID,message);
//        //make new email id
//        emailID = r.nextLong();
//        buffer.add(email);

        emailID = r.nextLong();
        Email email = new Email(username, sender, emailID, message);
        //make new email id

        buffer.add(email);
        usermail++;
    }

    public Email[] retrieveEmail(int n) {
//        Retrieve the n most recent emails in the user's inbox.
//        You should call some operations provided by DynamicBuffer
        return buffer.getNewest(n);
    }

    public boolean removeEmail(long emailID) {
//        Remove an email with the specified emailID
//        You should call some operations provided by DynamicBuffer
        for (int i = 0; i < buffer.numElements(); i++) {
            if (emailID == buffer.emails[i].getID()) {
                buffer.remove(i);
                usermail--;
                return true;
            }
        }
        return false;
    }

    public boolean checker(String username, String password) {
        if (username.length() > 20 || username.length() < 1) {
            return false;
        }
        if (password.length() > 40 || password.length() < 4) {
            return false;
        }
//        if(username.matches("[A-Za-z0-9]") && password.matches("[A-Za-z0-9]")){
//            return true;
//        }
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9]*$");
        Matcher matcher1 = pattern.matcher(username);
        Matcher matcher2 = pattern.matcher(password);
        if (matcher1.matches() && matcher2.matches()) {

            //  if(username.matches("[a-zA-Z0-9]") && password.matches("[a-zA-Z0-9]")){
            return true;
        }
        return false;
    }


}
