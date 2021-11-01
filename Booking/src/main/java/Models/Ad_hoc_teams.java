package Models;

import javax.persistence.*;
import java.util.*;

/**
 *
 */
@Entity
@DiscriminatorValue("Ad Hoc Teams")
public class Ad_hoc_teams extends Authoring_entities{
    /**
     *
     */
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "Ad_hoc_teams_member",
            joinColumns = @JoinColumn(name = "ad_hoc_teams_email"),
            inverseJoinColumns = @JoinColumn(name = "individual_authors_email")
    )
    private List<Individual_author> individualAuthors;

    /**
     *
     * @param email
     * @param name
     * @param individual_authors
     */
    public Ad_hoc_teams(String email, String name, List<Individual_author> individual_authors) {
        super(email, name);
        this.setIndividualAuthors(individual_authors);
    }

    public Ad_hoc_teams() {
    }
    
    public List<Individual_author> getIndividual_authors() {
        return individualAuthors;
    }

    public void setIndividualAuthors(List<Individual_author> individualAuthors) {
        this.individualAuthors = individualAuthors;
    }

    /**
     *
     * @param individualAuthor
     */
    public void add_individual_authors(Individual_author individualAuthor){
        if(! this.individualAuthors.contains(individualAuthor)){
            this.individualAuthors.add(individualAuthor);
            individualAuthor.add_ad_hoc_teams(this);
        }
    }

    /**
     *
     * @param individual_author
     */
    public void remove_individual_authors(Individual_author individual_author){
        if(this.individualAuthors.contains(individual_author)){
            this.individualAuthors.remove(individual_author);
            individual_author.remove_ad_hoc_teams(this);
        }
    }

    @Override
    public String toString() {
        return "individual_authors: " + this.getIndividual_authors();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Ad_hoc_teams that = (Ad_hoc_teams) o;
        return Objects.equals(individualAuthors, that.individualAuthors);
    }
}