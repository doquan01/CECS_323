package csulb.cecs323.model;

import javax.persistence.*;

/**
 * Individual, physical automobiles that someone can drive on land to transport one or more passengers
 * and a limited amount of cargo around.  Cars have four wheels and usually travel on paved roads.
 */
@Entity
public class Cars {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", nullable = false)
    private Owners owners;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "auto_body_style_name", referencedColumnName = "name", nullable = false)
    private auto_body_styles name;

    /**
     * The unique ID of the vehicle.  Limited to 17 characters.
     */
    @Id
    @Column(length = 17, nullable = false)
    private String VIN;

    /**
     * The name of the corporation which manufactured the vehicle.  Limited to 40 characters.
     */
    @Column(length = 40, nullable = false)
    private String manufacturer;

    /**
     * The popular name of the vehicle, like the Prius for Toyota.  Limited to 20 characters.
     */
    @Column(length = 20, nullable = false)
    private String model;

    /**
     * The year that the vehicle was manufactured.  For now, do not worry about validating this #.
     */
    private int years;

    public Cars(Owners owner, auto_body_styles name, String VIN, String manufacturer, String model, int year) {
        this.setOwner(owner);
        this.setName(name);
        this.setVIN(VIN);
        this.setManufacturer(manufacturer);
        this.setModel(model);
        this.setYear(year);
    }

    public Cars() {
    }

    public String getVIN() {
        return VIN;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getModel() {
        return model;
    }

    public int getYear() {
        return years;
    }

    public Owners getOwner() {
        return owners;
    }

    public auto_body_styles getName() {
        return name;
    }

    public void setVIN(String VIN) {
        this.VIN = VIN;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setYear(int year) {
        this.years = year;
    }

    public void setOwner(Owners owner) {
        this.owners = owner;
    }

    public void setName(auto_body_styles name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Cars - VIN: " + this.VIN + " Manufacturer: " + this.manufacturer +
                " Model: " + this.model + " year: " + this.years;
    }
}
