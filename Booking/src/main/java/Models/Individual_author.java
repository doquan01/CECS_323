package Models;

import javax.persistence.*;
import java.util.*;

@Entity
@DiscriminatorValue("Individual Authors")
public class Individual_author extends Authoring_entities{

    @ManyToMany(mappedBy = "individual_authors",
            cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Ad_hoc_teams> ad_hoc_teams;

    public Individual_author(String email, String name, List<Ad_hoc_teams> ad_hoc_teams) {
        super(email, name);
        this.ad_hoc_teams = new ArrayList<Ad_hoc_teams>();
    }

    public Individual_author() {
    }

    public List<Ad_hoc_teams> getAd_hoc_teams() {
        return ad_hoc_teams;
    }

    public void setAd_hoc_teams(List<Ad_hoc_teams> ad_hoc_teams) {
        this.ad_hoc_teams = ad_hoc_teams;
    }

    public void add_ad_hoc_teams (Ad_hoc_teams team){
        if(! this.ad_hoc_teams.contains(team)){
            this.ad_hoc_teams.add(team);
            team.add_individual_authors(this);
        }
    }

    public void remove_ad_hoc_teams (Ad_hoc_teams team){
        if(this.ad_hoc_teams.contains(team)){
            this.ad_hoc_teams.remove(team);
            team.remove_individual_authors(this);
        }
    }

    @Override
    public String toString() {
        return "ad_hoc_teams=" + this.getAd_hoc_teams();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Individual_author that = (Individual_author) o;
        return Objects.equals(ad_hoc_teams, that.ad_hoc_teams);
    }
}
