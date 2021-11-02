package csulb.cecs323.model;

import javax.persistence.*;
import java.util.Objects;

/**
 * The individual or group that is responsible for creating a book.
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "authoring_entity_type", discriminatorType = DiscriminatorType.STRING)
public class Authoring_Entities {

    /**
     * The email that the authoring entity uses. Limited to 30 characters.
     */
    @Id
    @Column(length = 30, nullable = false)
    private String email;

    /**
     * The name of the authoring enitity. Limited to 80 characters.
     */
    @Column(length = 80, nullable = false)
    private String name;

    /**
     * The constructor for the Autoring_entities class. Creates an Authoring_entities object.
     * @param email     The email of the authoring entity.
     * @param name      The name of the authoring entity.
     */
    public Authoring_Entities(String email, String name) {
        this.setEmail(email);
        this.setName(name);
    }

    /**
     * Default constructor of the Authoring_entities class.
     */
    public Authoring_Entities() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Authoring Email: " + this.getEmail() +
                " Name: " + this.getName();

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Authoring_Entities that = (Authoring_Entities) o;
        return Objects.equals(email, that.email) && Objects.equals(name, that.name);
    }
}

//
//import javax.persistence.*;
//
//
//@Entity
//public class Authoring_Entities {
//    @Id
//    @Column(length = 300, nullable = false)
//    private String email;
//
//    @Column(length = 310, nullable = false)
//    private String authoring_entity_type;
//
//
//    @Column(length = 800, nullable = false, unique = true)
//    private String name;
//
//    @Column(length = 800)
//    private String head_writer;
//
//    @Column
//    private int year_formed;
//
//    public Authoring_Entities(){}
//
//    public Authoring_Entities(String email, String authoring_entity_type, String name, String head_writer, int year_formed) {
//        this.email = email;
//        this.authoring_entity_type = authoring_entity_type;
//        this.name = name;
//        this.head_writer = head_writer;
//        this.year_formed = year_formed;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public String getAuthoring_entity_type() {
//        return authoring_entity_type;
//    }
//
//    public void setAuthoring_entity_type(String authoring_entity_type) {
//        this.authoring_entity_type = authoring_entity_type;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getHead_writer() {
//        return head_writer;
//    }
//
//    public void setHead_writer(String head_writer) {
//        this.head_writer = head_writer;
//    }
//
//    public int getYear_formed() {
//        return year_formed;
//    }
//
//    public void setYear_formed(int year_formed) {
//        this.year_formed = year_formed;
//    }
//
//    @Override
//    public String toString () {
//        return "Authoring Entity - Name: " + this.getName() + " Type: " + this.getAuthoring_entity_type() +
//                " Email: " + this.getEmail() + " Head Writer: " + this.getHead_writer() + " Year Formed: " + this.getYear_formed();
//    }
//}
