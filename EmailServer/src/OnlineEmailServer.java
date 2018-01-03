/*
******cs180 project 4******
Jackson Didat, jdidat@purdue.edu, lab section 12
Jonathon Du, du129@purdue.edu, lab section 1
4-13-2106
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import cs180.net.Socket;
import cs180.net.ServerSocket;

public class OnlineEmailServer extends EmailServer {
    ServerSocket s;
    int portNum;
    private boolean stopRun = false;

    public OnlineEmailServer(String filename, int port) throws IOException {
        super(filename);
        ServerSocket serve = new ServerSocket(port);
        //if (port == serve.getLocalPort()) {
        //throw new IOException();
        //}
        serve.setReuseAddress(true);
        this.s = serve;
        portNum = port;

    }

    @Override
    public void run() {
        ServerSocket listener = null;
        try {
            listener = new ServerSocket(portNum);
            while (!stopRun) {
                Socket socket = listener.accept();
                try {
                    processClient(socket);
                    new PrintWriter(socket.getOutputStream(), true);
                } finally {
                    socket.close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
    }

    public void processClient(Socket client) throws IOException {
        String inputLine = null;
        String outputLine = null;
        PrintWriter out = new PrintWriter(client.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        while ((inputLine = in.readLine()) != null) {
            if (inputLine.equals("/exit"))
                break;
            if (inputLine.equals("/help")) {
                outputLine = "ADD-USER:	/add-user username password\n" +
                        "GET-ALL-USERS:	/get-all-users username password\n" +
                        "DELETE-USER:	/delete-user username password\n" +
                        "SEND-EMAIL:	/send-email username password receiver message\n" +
                        "GET-EMAILS:	/get-emails username password numberOfEmails\n" +
                        "DELETE-EMAIL:	/delete-email username password emailId\n" +
                        "\n" +
                        "/raw command, will send the given command\n" +
                        "\t\t-write down the t, r and n\n" +
                        "\t\t- if you want to add the cookie id, use XXX" +
                        "- ex: /raw ADD-USER\\tXXX\\tcs240\\tcs140\\r\\n\n" +
                        "/exit closes this client" +
                        "/help will display this menu";
            }
            if (inputLine.substring(0, 3).equals("/raw")) {
                String temp[] = inputLine.split(" ");
                String response = null;
                try {
                    response = parseRequest(temp[1]);
                } catch (Exception ex) {
                    response = ErrorFactory.makeErrorMessage(ErrorFactory.UNKNOWN_ERROR,
                            String.format("An exception of %s occurred.", ex.getClass().toString()));
                }
                outputLine = response;
            }
            out.println(outputLine);
        }
    }

    public void stop() {
        stopRun = true;
    }

    public static void main(String[] args) {
        try {
            (new OnlineEmailServer("test.txt", 9090)).run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}