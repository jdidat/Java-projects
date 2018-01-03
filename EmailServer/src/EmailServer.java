/*

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

******cs180 project 4******
Jackson Didat, jdidat@purdue.edu, lab section 12
Jonathon Du, du129@purdue.edu, lab section 1
4-13-2106
*/

import java.io.*;
import java.util.ArrayList;

/*import java.io.*;
import java.util.ArrayList;
//import java.io.*;
import java.util.ArrayList;

/*import java.io.*;
import java.util.ArrayList;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.io.*;
import java.util.*;
import java.nio.file.Files;

/**
 * <b> CS 180 - Project 4 - Email Server Skeleton </b>
 * <p>
 * <p>
 * This is the skeleton code for the EmailServer Class. This is a private email
 * server for you and your friends to communicate.
 *
 * @author (Your Name) <(YourEmail@purdue.edu)>
 * @version (Today's Date)
 * @lab (Your Lab Section)
 */
public class EmailServer {
    public static final String FAILURE = "FAILURE";
    public static final String DELIMITER = "\t";
    public static final String SUCCESS = "SUCCESS";
    public static final String CRLF = "\r\n";
    private boolean verbose = false;
    private int maxMessages;
    ArrayList<User> list = new ArrayList<User>(10);
    String fileName;

    public EmailServer(User[] users, int maxMessages) {
        list.add(new User("root", "cs180"));
        for (int i = 0; i < users.length; i++) {
            if (users[i] != null) {
                list.add(users[i]);
            }
        }
        this.maxMessages = maxMessages;
    }

    public EmailServer() {
        list.add(new User("root", "cs180"));
    }

    /* here is the new constructor added to deal with files, it assigns
        the file parameter to the field if it is called. Then it checks to see if
        the file actually exists, if it doesn't a new txt file is created and an
        exception is thrown. isCalled is set to true, which this variable is used
        in the other methods to see if this specific constructor has been called.
     */
    /*public EmailServer(String fileName) throws IOException {
        this.fileName = fileName;
        String[] newUser = new String[]{"ADD-USER", "root", "cs180"};
        addUser(newUser);
        try {
            File f = new File(fileName);
            Scanner s = new Scanner(f);
            String line;
            String[] parsedLine;
            while (s.hasNext()) {
                line = s.nextLine();
                parsedLine = line.split(",");
                if (parsedLine.length != 2) {
                    continue;
                }
                String[] addable = new String[3];
                addable[0] = "ADD-USER";
                addable[1] = parsedLine[0];
                addable[2] = parsedLine[1];
                addUser(addable);
                System.out.println("adding to file: " + addable[1] + "," + addable[2]);
            }
        } catch (IOException e) {
            Path path = Paths.get(fileName);
            Files.createFile(path);
        }
    }*/
    public EmailServer(String fileName) throws IOException {
        this.fileName = fileName;
        list.add(new User("root", "cs180"));


        try {
            // if (!file.exists()) file.createNewFile();
            printWriter = new PrintWriter(new FileOutputStream(fileName));
            printWriter.write("root" +"," + "cs180" +"\n");
        } catch (IOException ioex) {
            ioex.printStackTrace();
        } finally {
            if (printWriter != null) {
                printWriter.flush();
                printWriter.close();
            }
        }



    }

    public void run() {
        Scanner in = new Scanner(System.in);

        while (true) {
            System.out.printf("Input Server Request: ");
            String command = in.nextLine();

            command = replaceEscapeChars(command);

            if (command.equalsIgnoreCase("kill") || command.equalsIgnoreCase("kill\r\n"))
                break;

            if (command.equalsIgnoreCase("verbose") || command.equalsIgnoreCase("verbose\r\n")) {
                verbose = !verbose;
                System.out.printf("VERBOSE has been turned %s.\n\n", verbose ? "on" : "off");
                continue;
            }

            String response = null;
            try {
                response = parseRequest(command);
            } catch (Exception ex) {
                response = ErrorFactory.makeErrorMessage(ErrorFactory.UNKNOWN_ERROR,
                        String.format("An exception of %s occurred.", ex.getClass().toString()));
            }
            if (response.startsWith(FAILURE) && !DELIMITER.equals("\t"))
                response = response.replace(DELIMITER, "\t");
            System.out.printf("\"%s\"\n\n", response);
        }

        in.close();
    }
    public String addUser(String[] args) {
        if (args.length != 3) {
            return ErrorFactory.makeErrorMessage(ErrorFactory.FORMAT_COMMAND_ERROR);
        }

        String username = args[1];
        String temp[] = args[2].split("\r\n");
        String password = temp[0];
        User user = new User(username, password);
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getName().equals(username)) {
                return ErrorFactory.makeErrorMessage(ErrorFactory.USER_EXIST_ERROR);
            }
        }
        if (!user.checker(username, password)) {
            return ErrorFactory.makeErrorMessage(ErrorFactory.INVALID_VALUE_ERROR);
        }
        list.add(user);
        if (fileName != null) {
            try {
                File f = new File(fileName);
                Scanner scanner = new Scanner(f);
                String l;
                boolean addable = true;
                while (scanner.hasNext()) {
                    l = scanner.nextLine();
                    if (l.equals(args[1] + "," + args[2])) {
                        addable = false;
                    }
                }
                scanner.close();
                if (addable) {
                    FileOutputStream stream = new FileOutputStream(f, true);
                    PrintWriter writer = new PrintWriter(stream);
                    writer.print(args[0] + "," + args[0]);
                    writer.flush();
                    writer.close();
                    stream.close();
                }
                Scanner scan2 = new Scanner(f);
                String[] lineArray;
                String replace = "";
                while (scan2.hasNext()) {
                    l = scan2.nextLine();
                    lineArray = l.split(",");
                    if (lineArray.length == 2) {
                        replace += lineArray[0] + "," + lineArray[1];
                    }
                }
                scan2.close();
                FileOutputStream stream2 = new FileOutputStream(f);
                PrintWriter writer2 = new PrintWriter(stream2);
                writer2.print(replace);
                writer2.close();
                stream2.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return SUCCESS+CRLF;
    }
    public String getAllUsers(String[] args) {
        if (args.length != 3) {
            return ErrorFactory.makeErrorMessage(ErrorFactory.FORMAT_COMMAND_ERROR);
        }
        String username = args[1];
        String temp[];
        temp = args[2].split(CRLF);
        String password = temp[0];
        String totalUsers = null;
        boolean matchFlag = false;
        StringBuilder ret = new StringBuilder("SUCCESS\t");
        for (int i = 0; i < list.size(); i++) {
            if (i == list.size() - 1)
                ret.append(list.get(i).getName());
            else
                ret.append(list.get(i).getName()).append("\t");
            if (list.get(i).getName().equals(username)) {
                if (list.get(i).checkPassword(password)) {
                    matchFlag = true;
                } else {
                    return ErrorFactory.makeErrorMessage(ErrorFactory.AUTHENTICATION_ERROR);
                }
            }
        }
        if (matchFlag) {
            ret.append("\r\n");
            return ret.toString();
        } else
            return ErrorFactory.makeErrorMessage(ErrorFactory.USERNAME_LOOKUP_ERROR);
    }
    /*public String deleteUser(String[] args) {
        if (args.length != 3) {
            return ErrorFactory.makeErrorMessage(ErrorFactory.FORMAT_COMMAND_ERROR);
        }
        String username = args[1];
        if (username.equals("root"))
            return ErrorFactory.makeErrorMessage(ErrorFactory.INVALID_VALUE_ERROR);
        String temp[] = args[2].split(CRLF);
        String password = temp[0];
        User user = null;
        boolean matches = false;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getName().equals(username)) {
                if (list.get(i).checkPassword(password)) {
                    matches = true;
                    user = list.get(i);
                } else {
                    return ErrorFactory.makeErrorMessage(ErrorFactory.AUTHENTICATION_ERROR);
                }
            }
        }
        if (!matches) {
            return ErrorFactory.makeErrorMessage(ErrorFactory.USERNAME_LOOKUP_ERROR);
        }
        if (list.remove(user)) {
            File inputFile = new File(filename);
            File tempFile = new File("myTempFile.txt");

            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            String lineToRemove = "bbb";
            String currentLine;

            while((currentLine = reader.readLine()) != null) {
                String lineStr[] = currentLine.split(",");
                if (user.getName().equals(lineStr[0]))
                    continue;
                writer.write(currentLine );
            }
            writer.close();
            reader.close();
            boolean successful = tempFile.renameTo(inputFile);

        }
        return SUCCESS + CRLF;
    }
    else {
        return ErrorFactory.makeErrorMessage(ErrorFactory.INVALID_VALUE_ERROR);
    }
    }*/
    public String deleteUser(String[] args) {
        if (args.length != 3) {
            return ErrorFactory.makeErrorMessage(ErrorFactory.FORMAT_COMMAND_ERROR);
        }
        String username = args[1];
        if (username.equals("root"))
            return ErrorFactory.makeErrorMessage(ErrorFactory.INVALID_VALUE_ERROR);
        String temp[] = args[2].split(CRLF);
        String password = temp[0];
        User user = null;
        boolean matches = false;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getName().equals(username)) {
                if (list.get(i).checkPassword(password)) {
                    matches = true;
                    user = list.get(i);
                } else {
                    return ErrorFactory.makeErrorMessage(ErrorFactory.AUTHENTICATION_ERROR);
                }
            }
        }
        if (!matches) {
            return ErrorFactory.makeErrorMessage(ErrorFactory.USERNAME_LOOKUP_ERROR);
        }
        if (list.remove(user)) {
            File inputFile = new File(filename);
            File tempFile = new File("myTempFile.txt");

            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            String lineToRemove = "bbb";
            String currentLine;

            while((currentLine = reader.readLine()) != null) {
                String lineStr[] = currentLine.split(",");
                if (user.getName().equals(lineStr[0]))
                    continue;
                writer.write(currentLine );
            }
            writer.close();
            reader.close();
            boolean successful = tempFile.renameTo(inputFile);

        }
        return SUCCESS + CRLF;
    }
    else {
        return ErrorFactory.makeErrorMessage(ErrorFactory.INVALID_VALUE_ERROR);
    }
}

    public String sendEmail(String[] args) {
        if (args.length != 5) {
            return ErrorFactory.makeErrorMessage(ErrorFactory.FORMAT_COMMAND_ERROR);
        }
        String username = args[1];
        String password = args[2];
        String recipient = args[3];
        String m = args[4];
        String message = m.trim();
        if (message.length() < 1) {
            return ErrorFactory.makeErrorMessage(ErrorFactory.INVALID_VALUE_ERROR);
        }
        boolean matchFlag = false;
        User user = null;
        for (int i = 0; i < list.size(); i++) {

            if (list.get(i).getName().equals(username)) {
                //Password check
                if (list.get(i).checkPassword(password)) {
                    matchFlag = true;
                    user = list.get(i);
                } else {
                    return ErrorFactory.makeErrorMessage(ErrorFactory.AUTHENTICATION_ERROR);
                }
            }
        }
        if (!matchFlag)
            return ErrorFactory.makeErrorMessage(ErrorFactory.USERNAME_LOOKUP_ERROR);
        int count = 0;
        User receiver = null;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getName().equals(recipient)) {
                count++;
                receiver = list.get(i);
            }
        }
        if (count != 1) {
            return ErrorFactory.makeErrorMessage(ErrorFactory.USERNAME_LOOKUP_ERROR);
        }
        // user.receiveEmail(recipient, message);
        receiver.receiveEmail(username, message);
        return SUCCESS + CRLF;
    }

    public String getEmails(String[] args) {
        if (args.length != 4) {
            return ErrorFactory.makeErrorMessage(ErrorFactory.FORMAT_COMMAND_ERROR);
        }
        String username = args[1];
        String password = args[2];
        String temp[] = args[3].split(CRLF);
        String numMessagesString = temp[0];
        int numMessages = Integer.parseInt(numMessagesString);
        boolean matchFlag = false;
        User user = null;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getName().equals(username)) {
                //Password check
                if (list.get(i).checkPassword(password)) {
                    matchFlag = true;
                    user = list.get(i);
                } else {
                    return ErrorFactory.makeErrorMessage(ErrorFactory.AUTHENTICATION_ERROR);
                }
            }
        }
        if (!matchFlag)
            return ErrorFactory.makeErrorMessage(ErrorFactory.USERNAME_LOOKUP_ERROR);
        if (user.numEmail() < 0 || numMessages < 0) {
            return ErrorFactory.makeErrorMessage(ErrorFactory.INVALID_VALUE_ERROR);
        }
        if (user.numEmail() < numMessages) {
            numMessages = user.numEmail();
        }
        Email[] a = user.retrieveEmail(numMessages);
        String totalUsers = null;
        if (a == null)
            return SUCCESS + CRLF;
        for (int i = 0; i < a.length; i++) {

            if (totalUsers == null)
                totalUsers = a[i].toString();
            else
                totalUsers = totalUsers + "\t" + a[i].toString();
        }
        return SUCCESS + "\t" + totalUsers + CRLF;
    }

    public String deleteEmail(String[] args) {
        if (args.length != 4) {
            return ErrorFactory.makeErrorMessage(ErrorFactory.FORMAT_COMMAND_ERROR);
        }
        String username = args[1];
        String password = args[2];
        String temp[] = args[3].split(CRLF);
        String numMessagesString = temp[0];
        long emailID;
        try {
            emailID = Long.parseLong(numMessagesString);
        } catch (NumberFormatException e) {
            return ErrorFactory.makeErrorMessage(ErrorFactory.AUTHENTICATION_ERROR);
        }
        boolean matchFlag = false;
        User user = null;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getName().equals(username)) {
                if (list.get(i).checkPassword(password)) {
                    matchFlag = true;
                    user = list.get(i);
                } else {
                    return ErrorFactory.makeErrorMessage(ErrorFactory.AUTHENTICATION_ERROR);
                }
            }
        }
        if (!matchFlag)
            return ErrorFactory.makeErrorMessage(ErrorFactory.USERNAME_LOOKUP_ERROR);


        if (user.removeEmail(emailID)) {
            return SUCCESS + CRLF;
        } else {
            return ErrorFactory.makeErrorMessage(ErrorFactory.INVALID_VALUE_ERROR);
        }

    }

    /**
     * Determines which client command the request is using and calls
     * the function associated with that command.
     *
     * @param request - the full line of the client request (CRLF included)
     * @return the server response
     */
    public String parseRequest(String request) {
        String[] commandArray = request.split(DELIMITER);
        if (!commandArray[commandArray.length - 1].contains(CRLF)) {
            return ErrorFactory.makeErrorMessage(ErrorFactory.FORMAT_COMMAND_ERROR);
        }
        String commandStr = "";
        for (int i = 0; i < commandArray.length - 1; i++) {
            commandStr = commandStr + commandArray[i] + "   ";
        }
        String temp[] = commandArray[commandArray.length - 1].split(CRLF);
        commandStr = commandStr + temp[0];

        if (verbose)
            System.out.println("Received request:  " + "\"" + commandStr + "\"");
        switch (commandArray[0]) {
            case "ADD-USER":
                if (commandArray.length != 3) {
                    return ErrorFactory.makeErrorMessage(ErrorFactory.FORMAT_COMMAND_ERROR);
                } else {
                    return addUser(commandArray);
                }
            case "GET-ALL-USERS":
                if (commandArray.length != 3) {
                    return ErrorFactory.makeErrorMessage(ErrorFactory.FORMAT_COMMAND_ERROR);
                } else {
                    return getAllUsers(commandArray);
                }
            case "DELETE-USER":
                if (commandArray.length != 3) {
                    return ErrorFactory.makeErrorMessage(ErrorFactory.FORMAT_COMMAND_ERROR);
                } else {
                    return deleteUser(commandArray);
                }
            case "SEND-EMAIL":
                if (commandArray.length != 5) {
                    return ErrorFactory.makeErrorMessage(ErrorFactory.FORMAT_COMMAND_ERROR);
                } else {
                    return sendEmail(commandArray);
                }

                //INCOMPLETE return command for all subseq
            case "GET-EMAILS":
                if (commandArray.length != 4) {
                    return ErrorFactory.makeErrorMessage(ErrorFactory.FORMAT_COMMAND_ERROR);
                } else {
                    return getEmails(commandArray);
                }
                //INCOMPLETE
            case "DELETE-EMAIL":
                if (commandArray.length != 4) {
                    return ErrorFactory.makeErrorMessage(ErrorFactory.FORMAT_COMMAND_ERROR);
                } else {
                    return deleteEmail(commandArray);
                }
            case "ADD-USER\r\n":
                if (commandArray.length != 3) {
                    return ErrorFactory.makeErrorMessage(ErrorFactory.FORMAT_COMMAND_ERROR);
                } else {
                    return addUser(commandArray);
                }
            case "GET-ALL-USERS\r\n":
                if (commandArray.length != 3) {
                    return ErrorFactory.makeErrorMessage(ErrorFactory.FORMAT_COMMAND_ERROR);
                } else {
                    return getAllUsers(commandArray);
                }
            case "DELETE-USER\r\n":
                if (commandArray.length != 3) {
                    return ErrorFactory.makeErrorMessage(ErrorFactory.FORMAT_COMMAND_ERROR);
                } else {
                    return deleteUser(commandArray);
                }
            case "SEND-EMAIL\r\n":
                if (commandArray.length != 5) {
                    return ErrorFactory.makeErrorMessage(ErrorFactory.FORMAT_COMMAND_ERROR);
                } else {
                    return sendEmail(commandArray);
                }
            case "GET-EMAILS\r\n":
                if (commandArray.length != 4) {
                    return ErrorFactory.makeErrorMessage(ErrorFactory.FORMAT_COMMAND_ERROR);
                } else {
                    return getEmails(commandArray);
                }
                //INCOMPLETE
            case "DELETE-EMAIL\r\n":
                if (commandArray.length != 4) {
                    return ErrorFactory.makeErrorMessage(ErrorFactory.FORMAT_COMMAND_ERROR);
                } else {
                    return deleteEmail(commandArray);
                }
                //INCOMPLETE
            default:
                return ErrorFactory.makeErrorMessage(ErrorFactory.UNKNOWN_COMMAND_ERROR);
        }
    }

    /**
     * Replaces "poorly formatted" escape characters with their proper
     * values. For some terminals, when escaped characters are
     * entered, the terminal includes the "\" as a character instead
     * of entering the escape character. This function replaces the
     * incorrectly inputed characters with their proper escaped
     * characters.
     *
     * @param str - the string to be edited
     * @return the properly escaped string
     */
    private static String replaceEscapeChars(String str) {
        str = str.replace("\\r\\n", "\r\n"); // may not be necessary, but just in case
        str = str.replace("\\r", "\r");
        str = str.replace("\\n", "\n");
        str = str.replace("\\t", "\t");
        str = str.replace("\\f", "\f");

        return str;
    }

    /**
     * This main method is for testing purposes only.
     *
     * @param args - the command line arguments
     */
    public static void main(String[] args) {
        (new EmailServer()).run();
    }
}