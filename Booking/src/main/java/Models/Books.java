package Models;

import javax.persistence.*;

public class Books {
//	AUTHORING_ENTITY_NAME VARCHAR(30)
//		constraint BKSTHRNGENTITYNAME
//			references AUTHORING_ENTITIES,
//	PUBLISHER_NAME VARCHAR(80)
//		constraint BOOKSPUBLISHERNAME
//			references PUBLISHERS,
//	unique (TITLE, PUBLISHER_NAME),
//	unique (TITLE, AUTHORING_ENTITY_NAME)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(length = 17, nullable = false)
    private int ISBN;

    /**
     * The Title of the Book
     */
    @Column(length = 80, nullable = false)
    private String title;

    /**
     *The Year the Book was published
     */
    @Column(length = 4, nullable = false)
    private int year_published;

    @ManyToOne
    @JoinColumn(name = "publisher_name", referencedColumnName = "name")
    private Publishers publisher;
}



    @ManyToOne
    @JoinColumn(name = "authoring_entity_name", referencedColumnName = "name")
    private AuthoringEntities authoringEntity;


    public Books(){
    }

    public Books(Long isbn, String title, int yearPublished, Publishers publisher, AuthoringEntities authoringEntity) {
        this.isbn = isbn;
        this.title = title;
        this.yearPublished = yearPublished;
        this.publisher = publisher;
        this.authoringEntity = authoringEntity;
    }

    public String getTitle() {
        return title;
    }

    public int getYearPublished() {
        return yearPublished;
    }

    public Publishers getPublisher() {
        return publisher;
    }

    public AuthoringEntities getAuthoringEntity() {
        return authoringEntity;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setYearPublished(int yearPublished) {
        this.yearPublished = yearPublished;
    }
    public Long getIsbn() {
        return isbn;
    }

    public void setIsbn(Long isbn) {
        this.isbn = isbn;
    }

    public void setPublisher(Publishers publisher) {
        this.publisher = publisher;
    }

    public void setAuthoringEntity(AuthoringEntities authoringEntity) {
        this.authoringEntity = authoringEntity;
    }

    @Override
    public String toString() {
        return "Books{" +
                "isbn=" + isbn +
                ", title='" + title + '\'' +
                ", yearPublished=" + yearPublished +
                ", publisher=" + publisher +
                ", authoringEntity=" + authoringEntity +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Books books = (Books) o;
        return yearPublished == books.yearPublished && isbn.equals(books.isbn) && title.equals(books.title) && publisher.equals(books.publisher) && authoringEntity.equals(books.authoringEntity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isbn, title, yearPublished, publisher, authoringEntity);
    }
} //End of Books Class
