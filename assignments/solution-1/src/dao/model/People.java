package dao.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * This class contains the annotation and the methods for marshalling and unmarshalling of the people root element
 */
@XmlRootElement(name = "people")
@XmlAccessorType(XmlAccessType.FIELD)
public class People {
    @XmlElement(name = "person")
    private List<Person> data = new ArrayList<Person>();

    public People() {
    }

    public List<Person> getData() {
        return data;
    }

    public void setData(List<Person> data) {
        this.data = data;
    }
}


