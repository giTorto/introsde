package dao.model;

import javax.xml.bind.annotation.*;

/**
 * This class contains the annotation and the methods for marshalling and unmarshalling of the healthProfile element
 */
@XmlRootElement(name = "healthprofile")
@XmlType(propOrder = {"lastupdate", "weight", "height", "bmi"})
@XmlAccessorType(XmlAccessType.FIELD)
public class HealthProfile {
    @XmlElement
    private double weight; // in kg
    @XmlElement
    private double height; // in m
    @XmlElement
    private double bmi;
    @XmlElement
    private String lastupdate;

    public HealthProfile(double weight, double height, String lastupdate) {
        this.weight = weight;
        this.height = height;
        this.lastupdate = lastupdate;
        this.bmi = weight / (height * height);
    }

    public HealthProfile() {
        this.weight = -1;
        this.height = -1;
        this.bmi = -1;
        this.lastupdate = null;
    }


    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public String toString() {
        return "Height=" + height + ", Weight=" + weight;
    }

    public double getComputedBMI() {
        return this.weight / (Math.pow(this.height, 2));
    }

    public double getBmi() {
        return bmi;
    }

    public void setBmi(double bmi) {
        this.bmi = bmi;
    }

    public String getLastupdate() {
        return lastupdate;
    }

    public void setLastupdate(String lastupdate) {
        this.lastupdate = lastupdate;
    }
}
