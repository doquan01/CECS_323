package Models;

import javax.persistence.*;
import java.util.*;

/**
 * A group of people that work together in order to write a book.
 */
@Entity
@DiscriminatorValue("Writing Groups")
public class Writing_group extends Authoring_entities {

    /**
     * A person who leads the group members. Limited to 80 characters.
     */
    @Column(length = 80)
    private String head_writer;

    /**
     * The year that the group was formed. Limited to 4 characters.
     */
    @Column(length = 4)
    private int year_formed;

    /**
     * The constructor of the Writing_groups class. Creates the Writing_groups object.
     * @param name          The name of the writing group.
     * @param email         The email of the writing group.
     * @param headWriter    The name of the person that is the leader of the group.
     * @param year_formed   The year that the group was formed.
     */
    public Writing_group(String name, String email, String headWriter, int year_formed) {
        super(name, email);
        this.setHead_writer(headWriter);
        this.setYear_formed(year_formed);
    }

    /**
     * The default constructor of the Writing_group class.
     */
    public Writing_group(){
    }

    public String getHead_writer() {
        return head_writer;
    }

    public void setHead_writer(String head_writer) {
        this.head_writer = head_writer;
    }

    public int getYear_formed() {
        return year_formed;
    }

    public void setYear_formed(int year_formed) {
        this.year_formed = year_formed;
    }

    @Override
    public String toString() {
        return "head_writer: " + head_writer +
                ", year_formed: " + year_formed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Writing_group that = (Writing_group) o;
        return year_formed == that.year_formed && Objects.equals(head_writer, that.head_writer);
    }
}