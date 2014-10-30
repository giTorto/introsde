import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationModule;
import dao.model.People;
import dao.model.Person;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

/**
 * Created by giulian on 09/10/14.
 */
public class HPUnMarshaller {

    /**
     * This method perform the Unmarshalling operation starting from an XML file.
     * In case of the ant target execute.printAll is called passing the misses.xml file.
     * Whereas for the ant target execute.UnMarshallXML is passed the people.xml file, that must be created
     * through execute.MarshallerToXML.
     *
     * @param file determines which xml file in the project folder will be used.
     * @return the list obtained from the unmarshalling of the misses.xml file.
     */
    public static List<Person> fromXML(String file) {
        JAXBContext jc = null;
        try {
            jc = JAXBContext.newInstance(People.class);

            System.out.println();
            System.out.println("Output from our XML File: ");
            Unmarshaller um = jc.createUnmarshaller();
            People people = (People) um.unmarshal(new FileReader(file));
            List<Person> list = people.getData();
            return list;

        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * This method is called to perform the unmarshalling operation starting from a JSON file
     *
     * @return the list obtained from the unmarshalling of the people.json file.
     * @see this method to work accurately requires that the marshalling to JSON is performed before
     */
    public static List<Person> fromJSON() {

        ObjectMapper mapper = new ObjectMapper(); // can reuse, share globally
        try {
            JaxbAnnotationModule module = new JaxbAnnotationModule();
            mapper.registerModule(module);
            People people = mapper.readValue(new File("people.json"), People.class);
            List<Person> list = people.getData();
            return list;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


}