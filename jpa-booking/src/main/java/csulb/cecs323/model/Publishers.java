package csulb.cecs323.model;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

/**
 * A person or company that prepares and issues the books for sale.
 */
@Entity
public class Publishers {
    /**
     * The name of the company or individual that prepares the books for sale. Limited to 80 characters.
     */
    @Id
    @Column(name = "name", nullable = false, length = 80)
    private String name;

    /**
     * The email of the publisher. Limited to 80 characters.
     */
    @Column(length = 80, nullable = false, unique = true)
    private String email;

    /**
     * The phone number of the publisher. Limited to 24 characters.
     */
    @Column(length = 24, nullable = false, unique = true)
    private String phone;

    /**
     * The list of books that the publisher has published.
     */
    @OneToMany(mappedBy = "publisher", cascade = CascadeType.PERSIST)
    private List<Books> books;

    /**
     * The constructor of the Publishers class. Creates a Publishers object.
     * @param name      The name of the publisher.
     * @param email     The email of the publisher.
     * @param phone     The phone number of the publisher.
     */
    public Publishers(String name, String email, String phone) {
        this.setName(name);
        this.setEmail(email);
        this.setPhone(phone);
    }

    /**
     * Default constructor of the Publisher class.
     */
    public Publishers() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "name: " + this.getName() +
                ", email: " + this.getEmail() +
                ", phone: " + this.getPhone();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Publishers that = (Publishers) o;
        return Objects.equals(name, that.name) && Objects.equals(email, that.email) && Objects.equals(phone, that.phone) && Objects.equals(books, that.books);
    }
}

//
//import javax.persistence.*;
//import java.util.List;
//
///**
// * CLASS DEFINITION GOES HERE
// *
// */
//@Entity
//public class Publishers {
//
//    /** The name of the publisher.  Limited to 40 characters. */
//    @Id
//    @Column(length = 80, nullable = false)
//    private String name;
//
//    /** The email of the publisher.  Limited to 40 characters. */
//    @Column(length = 80, nullable = false)
//    private String email;
//
//    /** The phone number of the publisher (i.e. 714-555-5555).  Limited to 12 characters. */
//    @Column(length = 24, nullable = false)
//    private String phone;
//
//    @OneToMany(mappedBy = "publisher_name")
//    private List<Books> books;
//
//
//    public Publishers(){}
//
//    public Publishers(String name, String phone, String email){
//        this.name = name;
//        this.phone = phone;
//        this.email = email;
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
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public String getPhone() {
//        return phone;
//    }
//
//    public void setPhone(String phone) {
//        this.phone = phone;
//    }
//
////    public List<Books> getBooks() {
////        return books;
////    }
////
////    public void setBooks(List<Books> books) {
////        this.books = books;
////    }
//
//    @Override
//    public String toString () {
//        return "Publisher - Name: " + this.name + " Phone: " + this.phone +
//                " Email: " + this.email;
//    }
//}
