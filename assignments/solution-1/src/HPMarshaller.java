import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationModule;
import dao.model.HealthProfile;
import dao.model.People;
import dao.model.Person;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Random;

public class HPMarshaller {
    public static People people = new People();

    /**
     * This class perform the marshalling operation to an XML file starting from default values.
     * Everytime this method is called a different JSON file is created, because contains random entry of the
     * file misses.xml and three fixed person. The same person can be added more than one time.
     */
    public static void toXML() {
        Person pallino = new Person();
        HealthProfile hp = new HealthProfile(79.0, 1.72, "1922-12-12");
        Person pallo = new Person(new Long(1), "Pallo", "Pinco", "1984-06-21", hp);
        HealthProfile hps = new HealthProfile(68.0, 1.72, "1922-12-12");
        Person john = new Person(new Long(2), "John", "Doe", "1985-03-20", hps);

        people.getData().add(pallino);
        people.getData().add(pallo);
        people.getData().add(john);

        List<Person> fromMisses = HPUnMarshaller.fromXML("misses.xml");

        // variable so that it is not re-seeded every call.
        Random rand = new Random();

        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive

        for (int i = 0; i < 3; i++)
            people.getData().add(fromMisses.get(rand.nextInt((fromMisses.size()) + 1)));


        JAXBContext jc = null;
        try {
            jc = JAXBContext.newInstance(People.class);

            Marshaller m = jc.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            m.marshal(people, new File("people.xml")); // marshalling into a file
            m.marshal(people, System.out);              // marshalling into the system default output

        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    /**
     * This class perform the marshalling operation to a JSON file starting from default values.
     * Everytime this method is called a different JSON file is created, because contains different entry of the
     * file misses.xml and three fixed person. The same person can be added more times.
     */
    public static void toJson() {
        Person pallino = new Person();
        HealthProfile hp = new HealthProfile(79.0, 1.72, "1922-12-12");
        Person pallo = new Person(new Long(1), "Pallo", "Pinco", "1984-06-21", hp);
        HealthProfile hps = new HealthProfile(68.0, 1.72, "1922-12-12");
        Person john = new Person(new Long(2), "John", "Doe", "1985-03-20", hps);

        people.getData().add(pallino);
        people.getData().add(pallo);
        people.getData().add(john);

        Random rand = new Random();
        List<Person> fromMisses = HPUnMarshaller.fromXML("misses.xml");
        for (int i = 0; i < 3; i++)
            people.getData().add(fromMisses.get(rand.nextInt((fromMisses.size()) + 1)));

        // Jackson Object Mapper
        ObjectMapper mapper = new ObjectMapper();

        // Adding the Jackson Module to process JAXB annotations
        JaxbAnnotationModule module = new JaxbAnnotationModule();

        // configure as necessary
        mapper.registerModule(module);
        mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        mapper.configure(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS, true);

        String result = null;
        try {
            result = mapper.writeValueAsString(people);
            System.out.println(result);
            mapper.writeValue(new File("people.json"), people);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}