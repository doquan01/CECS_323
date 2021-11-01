package Models;

import javax.persistence.*;
import java.util.List;

public class Publishers {
    @Id
    @Column(name = "name", length = 80, nullable = false)
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
        return "Publishers{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}