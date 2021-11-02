package csulb.cecs323.model;

import javax.persistence.*;
import java.util.Objects;

/**
 * A physical object that contains written or printed literary work.
 */
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames =
        {"title", "publisher_name"}),
        @UniqueConstraint(columnNames =
                {"title", "authoring_entity_name"})})

@NamedNativeQuery(
        name = "ReturnAllBooks",
        query = "SELECT * FROM BOOKS",
        resultClass = Books.class
)

public class Books {
    /**
     * A unique ID that helps identify a book.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(length = 17, nullable = false)
    private int ISBN;

    /**
     * The title of the book.
     */
    @Column(length = 80, nullable = false)
    private String title;

    /**
     * The year that the book was published.
     */
    @Column(length = 4, nullable = false)
    private int year_published;

    /**
     * The name of the publisher that published the book.
     */
    @ManyToOne
    @JoinColumn(name = "publisher_name", referencedColumnName = "name")
    private Publishers publisher;

    /**
     * The name of the authoring entity that wrote the book.
     */
    @OneToOne
    @JoinColumn(name = "authoring_entity_name", referencedColumnName = "name")
    private Authoring_Entities authoringEntity;

    /**
     * The constructor of the Books class. Creates a book object.
     * @param isbn              The ID of the book;
     * @param title             The title of the book
     * @param yearPublished     The year that the book was published.
     * @param publisher         The publisher of the book.
     * @param authoringEntity   The authoring entity that wrote the book.
     */
    public Books(int isbn, String title, int yearPublished, Publishers publisher, Authoring_Entities authoringEntity) {
        this.setISBN(isbn);
        this.setTitle(title);
        this.setYear_published(yearPublished);
        this.setPublisher(publisher);
        this.setAuthoringEntity(authoringEntity);
    }

    /**
     * The default constructor of the Books class.
     */
    public Books(){
    }

    public int getISBN() {
        return ISBN;
    }

    public void setISBN(int ISBN) {
        this.ISBN = ISBN;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear_published() {
        return year_published;
    }

    public void setYear_published(int year_published) {
        this.year_published = year_published;
    }

    public Publishers getPublisher() {
        return publisher;
    }

    public void setPublisher(Publishers publisher) {
        this.publisher = publisher;
    }

    public Authoring_Entities getAuthoringEntity() {
        return authoringEntity;
    }

    public void setAuthoringEntity(Authoring_Entities authoringEntity) {
        this.authoringEntity = authoringEntity;
    }

    @Override
    public String toString() {
        return "ISBN: " + this.getISBN() +
                ", title: " + this.getTitle() +
                ", year_published: " + this.getYear_published() +
                ", publisher: " + this.getPublisher() +
                ", authoringEntity: " + this.getAuthoringEntity();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Books books = (Books) o;
        return ISBN == books.ISBN && year_published == books.year_published && Objects.equals(title, books.title) && Objects.equals(publisher, books.publisher) && Objects.equals(authoringEntity, books.authoringEntity);
    }
}


//
//import javax.persistence.*;
//
//@Entity
//
//@Table(uniqueConstraints = {
//        @UniqueConstraint(columnNames = {"title", "publisherName"}),
//        @UniqueConstraint(columnNames = {"title", "authoringEntity"})})
//
//public class Books {
//
//    /** The ISBN of the book, uniquely identifies each book. */
//    @Id
//    @Column(length=17, nullable = false, updatable = false) //Be sure to set max length & NOT NULL where appropriate
//    private String ISBN;
//
//    /** The given name of the book. */
//    @Column (length=80, nullable = false) //Be sure to set max length & NOT NULL where appropriate
//    private String title;
//
//    /** The year the book was published. */
//    @Column (length=4, nullable = false, unique = true)
//    private int year_published;
//
//    /** The name of the publisher who published the book. Referenced from Publishers*/
//    @ManyToOne(fetch = FetchType.LAZY)
//    @PrimaryKeyJoinColumn
//    private Publishers publisher_name;
//
//    /** The name of the authoring entity who authors the book. Referenced from AuthoringEntity */
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "name", insertable = false, updatable = false)
//    private Authoring_Entities authoring_entity_name;
//
//    public Books(Publishers publisherName, Authoring_Entities authoringEntity, String ISBN, String title, int yearPublished) {
//        this.publisher_name = publisherName;
//        this.authoring_entity_name = authoringEntity;
//        this.ISBN = ISBN;
//        this.title = title;
//        this.year_published = yearPublished;
//    }
//
//    public Books() {}
//
//    public Publishers getPublisherName() {
//        return publisher_name;
//    }
//
//    public void setPublisherName(Publishers publisherName) {
//        this.publisher_name = publisherName;
//    }
//
//    public Authoring_Entities getAuthoringEntity() {
//        return authoring_entity_name;
//    }
//
//    public void setAuthoringEntity(Authoring_Entities authoringEntity) {
//        this.authoring_entity_name= authoringEntity;
//    }
//
//    public String getISBN() {
//        return ISBN;
//    }
//
//    public void setISBN(String ISBN) {
//        this.ISBN = ISBN;
//    }
//
//    public String getTitle() {
//        return title;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    public int getYearPublished() {
//        return year_published;
//    }
//
//    public void setYearPublished(int yearPublished) {
//        this.year_published = yearPublished;
//    }
//
//    @Override
//    public String toString () {
//        return "Book - ISBN: " + this.getISBN() + " Title: " + this.getTitle() +
//                " Year Published: " + this.getYearPublished() +
//                " Authoring Entity: " + this.getAuthoringEntity() +
//                " Publisher: " + this.getPublisherName();
//    }
//}
