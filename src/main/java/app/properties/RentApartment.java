package app.properties;

import javax.persistence.*;

/**
 * Entity of a RentApartment, properties are according to the ones given by kv.ee
 */
@Entity
public class RentApartment implements Property {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String address;
    private float size;
    private String propertyCondition;
    private Integer bedrooms;
    private Integer rooms;
    private float price;
    private Integer renovationYear;
    private String energyLabel;
    private int floor;
    private String URL;

    public RentApartment() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getRooms() {
        return rooms;
    }

    public void setRooms(Integer rooms) {
        this.rooms = rooms;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Integer getRenovationYear() {
        return renovationYear;
    }

    public void setRenovationYear(Integer renovationYear) {
        this.renovationYear = renovationYear;
    }

    public float getSize() {
        return size;
    }

    public void setSize(float size) {
        this.size = size;
    }

    public String getPropertyCondition() {
        return propertyCondition;
    }

    public void setPropertyCondition(String condition) {
        this.propertyCondition = condition;
    }

    public Integer getBedrooms() {
        return bedrooms;
    }

    public void setBedrooms(Integer bedrooms) {
        this.bedrooms = bedrooms;
    }

    public String getEnergyLabel() {
        return energyLabel;
    }

    public void setEnergyLabel(String energyLabel) {
        this.energyLabel = energyLabel;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public RentApartment(String address, float size, String propertyCondition, Integer bedrooms, Integer rooms, float price, Integer renovationYear, String energyLabel, int floor, String url) {
        this.address = address;
        this.size = size;
        this.propertyCondition = propertyCondition;
        this.bedrooms = bedrooms;
        this.rooms = rooms;
        this.price = price;
        this.renovationYear = renovationYear;
        this.energyLabel = energyLabel;
        this.floor = floor;
        URL = url;
    }

    public String toString() {
        return address + "," + "," + size + "," + propertyCondition + "," + bedrooms + "," + rooms + "," + price + "," + renovationYear + "," + energyLabel + "," + floor;
    }

}
