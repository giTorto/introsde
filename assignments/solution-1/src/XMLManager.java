import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.*;
import java.io.IOException;

/**
 * This class manages the xQueries on the misses.xml file.
 */
public class XMLManager {
    private static Document xmlDB;
    private static XPath xpath;

    /**
     * This method initialize the Document and the XPath
     *
     * @throws IOException
     * @throws SAXException
     * @throws ParserConfigurationException
     */
    protected static void init() throws IOException, SAXException, ParserConfigurationException {
        if (xmlDB == null) {
            DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
            domFactory.setNamespaceAware(true);
            DocumentBuilder builder = domFactory.newDocumentBuilder();
            System.out.println("Loading Misses.xml...");
            xmlDB = builder.parse("misses.xml");
            XPathFactory factory = XPathFactory.newInstance();
            xpath = factory.newXPath();
        }
    }

    /**
     * This method given some parameters returns a Node parameter obtained by an xquery
     *
     * @param FirstName this is the first name of the person we are looking for
     * @param LastName  this is the last name of the person we are looking for
     * @return the node containing the Height of the matching person
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     * @throws XPathExpressionException
     */
    protected static Node getHeight(String FirstName, String LastName) throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {
        init();
        String query = new StringBuilder().append("//person[firstname=\"").append(FirstName).append("\"][lastname=\"").append(LastName).append("\"]/healthprofile/weight/text()").toString();
        XPathExpression expr = xpath.compile(query);

        Object result = expr.evaluate(xmlDB, XPathConstants.NODE);
        Node node = (Node) result;

        return node;
    }

    /**
     * This method given some parameters returns a Node parameter obtained by an xquery
     *
     * @param id this is the ID of the person height we are looking
     * @return the node containing the height of the required missed
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     * @throws XPathExpressionException
     */
    protected static Node getHeight(Integer id) throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {
        init();
        Integer idMiss = id;
        String query = new StringBuilder().append("//person[@id=\"").append(idMiss).append("\"]/healthprofile/height/text()").toString();
        XPathExpression expr = xpath.compile(query);

        Object result = expr.evaluate(xmlDB, XPathConstants.NODE);
        Node node = (Node) result;

        return node;
    }

    /**
     * This method given some parameters returns a Node parameter obtained by an xquery
     *
     * @param id this is the ID of the person weight we are looking
     * @return the node containing the height of the required missed
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     * @throws XPathExpressionException
     */
    protected static Node getWeight(Integer id) throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {
        init();
        Integer idMiss = id;
        String query = new StringBuilder().append("//person[@id=\"").append(idMiss).append("\"]/healthprofile/weight/text()").toString();
        XPathExpression expr = xpath.compile(query);

        Object result = expr.evaluate(xmlDB, XPathConstants.NODE);
        Node node = (Node) result;

        return node;
    }

    /**
     * This method given some parameters returns a Node parameter obtained by an xquery
     *
     * @param FirstName this is the first name of the person we are looking for
     * @param LastName  this is the last name of the person we are looking for
     * @return the node containing the Height of the matching person
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     * @throws XPathExpressionException
     */
    protected static Node getWeight(String FirstName, String LastName) throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {
        init();
        String query = new StringBuilder().append("//person[firstname=\"").append(FirstName).append("\"][lastname=\"").append(LastName).append("\"]/healthprofile/weight/text()").toString();
        XPathExpression expr = xpath.compile(query);

        Object result = expr.evaluate(xmlDB, XPathConstants.NODE);
        Node node = (Node) result;
        return node;
    }

    /**
     * This method given some parameters returns a Node parameter obtained by an xquery
     *
     * @param id this is the ID of the person health profile we are looking
     * @return the nodelist containing the health profile attributes
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     * @throws XPathExpressionException
     */
    protected static NodeList getHealthProfile(Integer id) throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {
        init();
        String query = new StringBuilder().append("//person[@id=\"").append(id).append("\"]/healthprofile").toString();
        XPathExpression expr = xpath.compile(query);

        Object result = expr.evaluate(xmlDB, XPathConstants.NODESET);

        NodeList node = (NodeList) result;
        return node;
    }

    /**
     * This method given a selection string performs an xquery that returns all matching people
     *
     * @param selection is the string which contain the selection parameter on which is based the xquery
     * @return the nodelist of matching people
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     * @throws XPathExpressionException
     */
    protected static NodeList getPersonBasedWeight(String selection) throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {
        init();
        String query = new StringBuilder().append("//person[").append(selection).append("]").toString();
        XPathExpression expr = xpath.compile(query);

        Object result = expr.evaluate(xmlDB, XPathConstants.NODESET);

        NodeList node = (NodeList) result;
        return node;
    }
}
