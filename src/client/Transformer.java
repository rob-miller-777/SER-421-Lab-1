package client;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;

/**
 * Created by rob on 8/30/14.
 */
public class Transformer{

    public void transform(String fileName) throws Exception{
        File xmlFile = new File(fileName);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document document = dBuilder.parse(xmlFile);
        document.getDocumentElement().normalize();

        File file = new File("test.html");
        FileOutputStream fos = new FileOutputStream(file);
        PrintStream ps = new PrintStream(fos);
        System.setOut(ps);
        System.out.println("<!DOCTYPE html>");
        System.out.println("<html>");
        System.out.println("<body>");

        Element root = document.getDocumentElement();
        System.out.println("<h1>" + root.getNodeName() + "</h1>");
        NodeList nodes = root.getChildNodes();

        for(int i = 0; i < nodes.getLength(); i++){
            Node node = nodes.item(i);

            if(node instanceof Element){
                if(node.getNodeName().equals("KnowledgeAsset")){
                    parseKA(node);
                }
                else if(node.getNodeName().equals("KnowledgeSubject")){
                    parseKS(node);
                }
            }
        }
        System.out.println("</body>");
        System.out.println("</html>");

}

    public static void parseKA(Node node){
        Element child = (Element) node;

        System.out.println("<h2>" + node.getNodeName() + "</h2>");

        System.out.println("<h3> Title: " + child.getElementsByTagName("title").item(0).getTextContent() + "</h3>");
        System.out.println("<p> Description: " + child.getElementsByTagName("description").item(0).getTextContent() + "</p>");

        System.out.println("<h3><u> Attributes</u></h3>");
        System.out.println("<p>  Asset ID: " + child.getAttribute("assetID") + "</p>");
        System.out.println("<p>  Part of ID: " + child.getAttribute("partOfID") + "</p>");
        System.out.println("<p>  Created By: " + child.getAttribute("createdBy") + "</p>");
        System.out.println("<p>  Created On: " + child.getAttribute("createdOn") + "</p>");
        System.out.println("<p>  Primary Subject ID: " + child.getAttribute("primarySubjectID") + "</p>");

        NodeList nodes = node.getChildNodes();
        for(int i = 0; i < nodes.getLength(); i++){
            Node newNode = nodes.item(i);
            if(newNode instanceof Element){
                if(newNode.getNodeName().equals("KnowledgeSubject")){
                    parseKS(newNode);
                }
                else if(newNode.getNodeName().equals("KnowledgeRating")){
                    parseKR(newNode);
                }
            }
        }



    }

    public static void parseKS(Node node){
        Element child = (Element) node;

        System.out.println("<h2>" + node.getNodeName() + "</h2>");
        System.out.println("<p> Title: " + child.getElementsByTagName("title").item(0).getTextContent() + "</p>");
        System.out.println("<p> Description: " + child.getElementsByTagName("description").item(0).getTextContent() + "</p>");

        System.out.println("<h3><u> Attributes</u></h3>");
        System.out.println("<p>  Primary Subject ID: " + child.getAttribute("primarySubjectID") + "</p>");
        System.out.println("<p>  Parent ID: " + child.getAttribute("parentID") + "</p>");
        System.out.println("<p>  Related to: " + child.getAttribute("relatedTo") + "</p>");
    }

    public static void parseKR(Node node){
        Element child = (Element) node;

        System.out.println("<h2>" + node.getNodeName() + "</h2>");
        System.out.println("<p> Description: " + child.getElementsByTagName("description").item(0).getTextContent() + "</p>");

        System.out.println("<h3><u> Attributes</u></h3>");
        System.out.println("<p>  Rater: " + child.getAttribute("Rater") + "</p>");
        System.out.println("<p>  Rating: " + child.getAttribute("Rating") + "</p>");
        System.out.println("<p>  Entry Date: " + child.getAttribute("EntryDate") + "</p>");
    }
}
