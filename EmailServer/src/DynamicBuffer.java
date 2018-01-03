/*
******cs180 project 4******
Jackson Didat, jdidat@purdue.edu, lab section 12
Jonathon Du, du129@purdue.edu, lab section 1
4-13-2106
*/
public class DynamicBuffer {

    int numEmails = 0;
    Email[] emails;
    int size;


    public DynamicBuffer(int initsize) {
        emails = new Email[initsize];
        size = emails.length;
    }

    public int numElements() {
//        Returns the number of emails stored in the array
        return numEmails;

    }
    public int getBufferSize() {
        return emails.length;
    }
    public void add(Email email) {
        emails[numEmails] = email;
        numEmails++;
        if (numEmails == size) {

            Email[] dubMail = new Email[size * 2];
            for (int i = 0; i < size; i++) {
                dubMail[i] = emails[i];
            }
            size *= 2;
            emails = dubMail;
        }
    }
    public boolean remove(int index) {
        if (index < 0 || index >= numEmails) {
            return false;
        }
        for (int i = index; i < numEmails - 1; i++) {
            emails[i] = emails[i + 1];
        }
        emails[numEmails - 1] = null;
        numEmails--;
        if (numEmails <= emails.length / 4 ) {

            Email[] halfMail = new Email[emails.length / 2];
            for (int i = 0; i < numEmails; i++) {
                halfMail[i] = emails[i];
            }
            emails = halfMail;
        }
        return true;
    }

    public Email[] getNewest(int n) {
        if (numEmails == 0 || n < 1) {
            return null;
        }
        if (n > numEmails) {
            n = numEmails;
        }
        Email[] temp = new Email[n];
        int count = 0;
        for (int i = numEmails - 1; i > numEmails - 1 - n ; i--) {
            temp[count] = emails[i];
            count++;
        }
        return temp;
    }

}
