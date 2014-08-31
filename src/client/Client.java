package client;

import java.io.File;

/**
 * Created by rob on 8/30/14.
 */
public class Client {

    public static void main(String args[]) throws Exception {
        String server = args[0];
        int port = Integer.parseInt(args[1]);
        String fileName = args[2];
        FileRetriever fileRetriever = new FileRetriever(server, port, fileName);
        fileRetriever.retrieve();
        Transformer transformer = new Transformer();
        transformer.transform(fileName);
    }
}
