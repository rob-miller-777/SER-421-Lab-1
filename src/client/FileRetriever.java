package client;

import java.io.*;
import java.net.*;

/**
 * Created by rob on 8/30/14.
 */
public class FileRetriever {
    private String server;
    private int port;
    private String fileName;

    public FileRetriever(String server, int port, String fileName){
        this.server = server;
        this.port = port;
        this.fileName = fileName;
    }

    public void retrieve() throws Exception{

        URL url = new URL("http", server, port, "/" + fileName);
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        System.out.println(connection.getResponseMessage());
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;

        File file = new File(fileName);
        FileOutputStream fos = new FileOutputStream(file);
        PrintStream ps = new PrintStream(fos);
        System.setOut(ps);

        while((inputLine = in.readLine()) != null)
            System.out.println(inputLine);
        in.close();


    }

}
