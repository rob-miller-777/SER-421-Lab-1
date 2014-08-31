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

    public void retrieve() {
        try{
            URL url = new URL("http", server, port, "/" + fileName);
            System.out.println("Establishing connection to " + server + ":" + port + "/" + fileName);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            if(connection.getResponseCode() != 200){
                System.exit(1);
            }else System.out.println("Connection Established");
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            String directory = new File(".").getCanonicalPath();
            File file = new File(directory + "/resources/xmlfiles/" + fileName);
            FileOutputStream fos = new FileOutputStream(file);
            PrintStream stdout = System.out;
            PrintStream ps = new PrintStream(fos);
            System.out.println("Transfering Data...");
            System.setOut(ps);
            while((inputLine = in.readLine()) != null)
                System.out.println(inputLine);
            in.close();
            System.setOut(stdout);
            System.out.println("Connection Closed");

        } catch(MalformedURLException e){
            System.err.println("MalformedURLException: " + e.getMessage());
        } catch(IOException e){
            System.err.println("IOException: " + e.getMessage());
        }
    }

}
