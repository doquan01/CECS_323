package Models;

import javax.persistence.*;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames =
        {"title", "publisher_name"}),
        @UniqueConstraint(columnNames =
                {"title", "authoring_entity_name"})})

public class Books {
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

    @OneToOne
    @JoinColumn(name = "authoring_entity_name", referencedColumnName = "name")
    private Authoring_entities authoringEntity;

    public Books(int isbn, String title, int yearPublished, Publishers publisher, Authoring_entities authoringEntity) {
        this.setISBN(isbn);
        this.setTitle(title);
        this.setYear_published(yearPublished);
        this.setPublisher(publisher);
        this.setAuthoringEntity(authoringEntity);
    }

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

    public Authoring_entities getAuthoringEntity() {
        return authoringEntity;
    }

    public void setAuthoringEntity(Authoring_entities authoringEntity) {
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
}
