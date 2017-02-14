package SocketProgramming;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import javax.swing.JOptionPane;

/**
 * Trivial client for the date server.
 */
public class client {

    /**
     * Runs the client as an application.  First it displays a dialog
     * box asking for the IP address or hostname of a host running
     * the date server, then connects to it and displays the date that
     * it serves.
     */
    public static void main(String[] args) throws IOException {
        //String serverAddress = JOptionPane.showInputDialog("Enter the command in format\n" + "cp path_to_local file path_to_remote_directory:");
        Socket s = new Socket("172.16.27.46", 9091);
        String httpResponse = "cp /Users/puevigreven/Desktop/main.c /Users/puevigreven/Desktop/Midsem/ ";
		s.getOutputStream().write(httpResponse.getBytes("UTF-8"));
       // JOptionPane.showMessageDialog(null, answer);
       // System.exit(0);
    }
}