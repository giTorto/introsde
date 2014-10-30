import dao.model.People;
import dao.model.Person;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;
import java.util.List;

import static java.lang.System.exit;

public class HPMain {
    public static People people = new People();
    private static int page = 1;

    /**
     * This method is always called at the beginning of the program. Depending on the arguments calls different methods.
     *
     * @param args
     */
    public static void main(String[] args) {
        int argCount = args.length;
        if (argCount == 0) {
            System.out.println("I cannot do anithing without a function name.");
        } else if (argCount < 1) {
            System.out.println("I need a function name and its parameters");
        } else {
            String funName = args[0];

            switch (funName) {
                case "selectWeight": {
                    if (args.length > 2)
                        page = new Integer(args[2]).intValue();

                    getPersonBasedOnWeight(args);
                    break;
                }
                case "getHealthProﬁle": {
                    getHealthProfile(args);
                    break;
                }
                case "getWeight": {
                    getWeight(args);
                    break;
                }
                case "getHeight": {
                    getHeight(args);
                    break;
                }
                case "unmarshallerXML": {
                    printUnMarshalling(HPUnMarshaller.fromXML("people.xml"));
                    break;
                }
                case "printAll": {
                    if (args.length > 1)
                        page = new Integer(args[1]).intValue();

                    printPeople(HPUnMarshaller.fromXML("misses.xml"));
                    break;
                }
                case "marshallerXML": {
                    HPMarshaller.toXML();
                    break;
                }
                case "unmarshallerJSON": {
                    printUnMarshalling(HPUnMarshaller.fromJSON());
                    break;
                }
                case "marshallerJSON": {
                    HPMarshaller.toJson();
                    break;
                }
                default:
                    System.out.println("This function doesn't exist");
            }

        }

    }

    /**
     * This method is called when we want to select person basing on weight. After the required argument are passed to
     * the XMLManager method that returns the matching results. Obtained results are printed on the console
     *
     * @param args the arguments passed at the beginning of the program. The selection is based on a giving argument
     */
    private static void getPersonBasedOnWeight(String[] args) {
        String input = args[1];
        String operator = input.replaceAll("\\w+", "");

        if (!operator.equals(">") && !operator.equals("<") && !operator.equals("=")) {
            System.out.println("Wrong operator. The accepted operators are '>','<','='. ");
            exit(0);
        }

        String number = input.replaceAll("[^\\d]", "");
        if (operator.equals("") || number.equals("")) {
            System.out.println("Missing operator or missing number. The first parameter must be the selection(like \">80kg\"). " +
                    "In case of too much results a second parameter for the page can be added through -DPage=X." +
                    "Where X is the page number.  ");
            exit(0);
        }

        String selection = "healthprofile/weight" + operator + number;
        NodeList results = null;

        try {
            results = XMLManager.getPersonBasedWeight(selection);

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }

        printResults(results);

    }

    /**
     * This function prints on the console the height related to the given ID or FirstName and LastName.
     * This is done by calling the method of XMLManager class.
     *
     * @param args the arguments passed at the beginning of the program. They can contain the ID or the First and Last name
     *             of the person
     */
    private static void getHeight(String args[]) {
        Node node = null;
        if (args.length == 2) {
            Integer id = null;
            if (args[1].matches("[1234567890]+")) {
                id = new Integer(args[1]);
                try {
                    node = XMLManager.getHeight(id);
                } catch (ParserConfigurationException e) {
                    e.printStackTrace();
                } catch (SAXException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (XPathExpressionException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("It should be an id or you need both first name and second name");
            }
        } else
            try {
                node = XMLManager.getHeight(args[1], args[2]);
            } catch (ParserConfigurationException e) {
                e.printStackTrace();
            } catch (SAXException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (XPathExpressionException e) {
                e.printStackTrace();
            }
        if (node != null) {
            System.out.println("It's height is " + node.getNodeValue());
        } else {
            System.out.println("Either ID or first name or last name not found");
        }

    }

    /**
     * This function prints on the console the weight related to the given ID or FirstName and LastName.
     * This is done by calling the method of XMLManager class.
     *
     * @param args the arguments passed at the beginning of the program. They can contain the ID or the First and Last name
     *             of the person
     */
    private static void getWeight(String[] args) {
        Node node = null;
        if (args.length == 2) {
            Integer id = null;
            if (args[1].matches("[1234567890]+")) {
                id = new Integer(args[1]);

                try {
                    node = XMLManager.getWeight(id);
                } catch (ParserConfigurationException e) {
                    e.printStackTrace();
                } catch (SAXException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (XPathExpressionException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("It should be an id or you need both first name and second name");
            }
        } else
            try {
                node = XMLManager.getWeight(args[1], args[2]);
            } catch (ParserConfigurationException e) {
                e.printStackTrace();
            } catch (SAXException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (XPathExpressionException e) {
                e.printStackTrace();
            }

        if (node != null) {
            System.out.println("It's weight is " + node.getNodeValue());
        } else {
            System.out.println("ID not found");
        }

    }

    /**
     * This method is called after the UnMarshalling from XML. Then start printing the information about people
     *
     * @param list the list of people to print
     */
    public static void printPeople(List<Person> list) {
        int numRecord = page * 20;
        int maxpages = list.size() / 20;
        if (maxpages % 20 != 0)
            maxpages++;
        if (page > maxpages) {
            System.out.println("There are only " + maxpages + " pages. So choose one from the range");
            exit(0);
        }
        System.out.println("Page: " + page + " on " + maxpages + ". Each page can contain at most 20 entry");
        for (int i = (page - 1) * 20; i < Math.min(list.size(), numRecord); i++) {
            System.out.println("Person: ");
            System.out.println("   " + "First name: " + list.get(i).getFirstname());
            System.out.println("   " + "Last name: " + list.get(i).getLastname());
            System.out.println("   " + "Birthdate: " + list.get(i).getBirthdate());
            System.out.println("   " + "HealthProfile:");
            dao.model.HealthProfile hp = list.get(i).getHProfile();
            System.out.println("       " + "Height: " + hp.getLastupdate());
            System.out.println("       " + "Height: " + hp.getHeight());
            System.out.println("       " + "Weight: " + hp.getWeight());
            System.out.println("       " + "BMI: " + hp.getBmi());
        }

        System.out.println("Page: " + page + " on " + maxpages + ". Each page can contain at most 20 entry");
        System.out.println("To see another page write -DPage=X. Where X is the page number.");
    }

    /**
     * This method when it's called checks the arguments and the calls the getHealthProfile method of the XMLManager class.
     * After that prints the results on the console.
     *
     * @param args the parameter passed at the beginning to the main
     */
    public static void getHealthProfile(String[] args) {
        NodeList nodes = null;
        if (args.length == 2) {
            Integer id = null;
            if (args[1].matches("[1234567890]+")) {
                id = new Integer(args[1]);

                try {
                    nodes = XMLManager.getHealthProfile(id);
                } catch (ParserConfigurationException e) {
                    e.printStackTrace();
                } catch (SAXException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (XPathExpressionException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("It should be an id or you need both first name and second name");
            }
        }

        if (nodes.getLength() == 0) {
            System.out.println("ID not found");
        } else {

            for (int i = 0; i < nodes.getLength(); i++) {
                System.out.println(nodes.item(i).getTextContent());
            }
        }
    }

    /**
     * This method prints the Person list with some details
     *
     * @param list obtained by unmarshalling files
     */
    public static void printUnMarshalling(List<Person> list) {
        for (Person person : list) {
            System.out.println("Person: " + person.getFirstname() + " " + person.getLastname() + " born "
                    + person.getBirthdate());
            System.out.println("   Health profile: Height is " + person.getHProfile().getHeight() +
                    ", Weight is " + person.getHProfile().getWeight());
            System.out.println("      LastUpdate is "+ person.getHProfile().getLastupdate() +
                    " and the BMI is " + person.getHProfile().getBmi() );

        }
    }

    /**
     * This method prints results obtained from the selection based on weight
     *
     * @param results is the nodelist obtained from the xquery
     */
    public static void printResults(NodeList results) {
        if (results != null) {
            if (results.getLength() > 20) {
                int numRecord = page * 20;
                int maxpages = results.getLength() / 20;
                if (maxpages % 20 != 0)
                    maxpages++;

                if (page > maxpages) {
                    System.out.println("There are only " + maxpages + " pages. So choose one from the range");
                    exit(0);
                }

                if (results.getLength() == 0) {
                    System.out.println("No matching result found");
                    exit(0);
                }

                for (int i = (page - 1) * 20; i < Math.min(results.getLength(), numRecord); i++) {
                    System.out.println(results.item(i).getTextContent());
                    System.out.println("-----------------");
                }
                System.out.println("Page: " + page + " on " + maxpages + ". Each page can contain at most 20 entry");
                System.out.println("To see another page write -DPage=X. Where X is the page number.");
                System.out.println("To change the default selection -DSelection=selection. " +
                        "Where selection can be something like >80Kg");

            } else {
                if (results.getLength() == 0) {
                    System.out.println("No matching result found");
                    exit(0);
                }

                for (int i = 0; i < results.getLength(); i++) {
                    System.out.println(results.item(i).getTextContent());
                    System.out.println("-----------------");
                }
            }

        } else {
            System.out.println("No matching result found");
        }

    }
}
