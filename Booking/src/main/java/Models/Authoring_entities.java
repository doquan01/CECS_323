package Models;

import javax.persistence.*;

@Entity
public class Authoring_entities {

    @Id
    @Column(length = 30, nullable = false)
    private String email;

    @Column(length = 31, nullable = false)
    private String authoring_entity_type;

    @Column(length = 80, nullable = false)
    private String name;

    @Column(length = 80, nullable = false)
    private String head_writer;

    @Column(length = 4, nullable = false)
    private int year_formed;

    public Authoring_entities(String email, String authoring_entity_type, String name, String head_writer, int year_formed) {
        this.setEmail(email);
        this.setAuthoring_entity_type(authoring_entity_type);
        this.setName(name);
        this.setHeadWriter(head_writer);
        this.setYearFormed(year_formed);
    }

    public Authoring_entities() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAuthoring_entity_type() {
        return authoring_entity_type;
    }

    public void setAuthoring_entity_type(String authoring_entity_type) {
        this.authoring_entity_type = authoring_entity_type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHeadWriter() {
        return head_writer;
    }

    public void setHeadWriter(String headWriter) {
        this.head_writer = headWriter;
    }

    public int getYearFormed() {
        return year_formed;
    }

    public void setYearFormed(int yearFormed) {
        this.year_formed = yearFormed;
    }

    @Override
    public String toString() {
        return "Authoring Email: " + this.getEmail() + " Entity Type: " + this.getAuthoring_entity_type() +
                " Name: " + this.getName() + " Head Writer Name: " + this.getHeadWriter() +
                " Year Formed: " + this.getYearFormed();
    }
}

