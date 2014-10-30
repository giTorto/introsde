package dao.model;

import javax.xml.bind.annotation.*;
import java.util.Calendar;

/**
 * This class contains the annotation and the methods for marshalling and unmarshalling of the person element
 */
// XmlRootElement defines the root element of the XML tree to which this class will be serialized
// --> <person> ... </person> 
@XmlRootElement(name = "person")
// XmlType can optionally define the order in which the fields of person are written
@XmlType(propOrder = {"firstname", "lastname", "birthdate", "healthprofile"})
// XmlAccessorType indicates what to use to create the mapping: either FIELDS, PROPERTIES (i.e., getters/setters), PUBLIC_MEMBER or NONE (which means, you should indicate manually)
@XmlAccessorType(XmlAccessType.FIELD)
public class Person {
    @XmlElement
    private String firstname;
    @XmlElement
    private String lastname;
    // XmlElement indicates the element to use for this field. Only used if the name in XML will be different than that in the class
    @XmlElement
    private HealthProfile healthprofile;
    @XmlElement
    private String birthdate;
    // XmlAttribute indicates that this field will be serialized as an attribute
    @XmlAttribute
    private Long id;

    public Person(Long id, String fname, String lname, String birthdate, HealthProfile hp) {
        this.setId(id);
        this.setFirstname(fname);
        this.setLastname(lname);
        this.setBirthdate(birthdate);
        this.healthprofile = hp;
    }


    public Person() {
        this.firstname = "Pinco";
        this.lastname = "Pallino";
        this.healthprofile = new HealthProfile();

        // setting id to a random number between 1 and 9999
        this.id = Math.round(Math.floor(Math.random() * 9998) + 1); // Solution to Exercise #01-1d
        this.birthdate = this.getRandomDate();
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public HealthProfile getHProfile() {
        return healthprofile;
    }

    public void setHProfile(HealthProfile hProfile) {
        this.healthprofile = hProfile;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /*
     * Solution to Exercise #01-1e
     * creating a random date between now and 1950
     */
    private String getRandomDate() {
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);        // 1. get the current year
        int year = (int) Math.round(Math.random() * (currentYear - 1950) + 1950); // 2. generate a random year
        //    between 1950 and currentYear
        int month = (int) Math.round(Math.floor(Math.random() * 11) + 1);        // 3. select a random month of the year
        // 4. select a random day in the selected month
        // 4.1 prepare a months array to store how many days in each month
        int[] months = new int[]{31, 28, 30, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        // 4.2 if it is a leap year, feb (months[1]) should be 29
        if ((currentYear % 4 == 0) && ((currentYear % 100 != 0) || (currentYear % 400 == 0))) {
            months[1] = 29;
        }
        long day = Math.round(Math.floor(Math.random() * (months[month - 1] - 1) + 1));
        return "" + year + "-" + month + "-" + day;
    }
}
