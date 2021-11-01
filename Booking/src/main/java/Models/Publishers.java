package Models;

import javax.persistence.Entity;


import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Publishers {
    @Id
    @Column(name = "name", nullable = false, length = 80)
    private String name;

    @Column(length = 80, nullable = false, unique = true)
    private String email;

    @Column(length = 24, nullable = false, unique = true)
    private String phone;

    @OneToMany(mappedBy = "publisher_name", cascade = CascadeType.PERSIST)
    private List<Books> books;

    public Publishers(String name, String email, String phone) {
        this.setName(name);
        this.setEmail(email);
        this.setPhone(phone);
    }

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