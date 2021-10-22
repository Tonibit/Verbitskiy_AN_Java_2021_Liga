package hibernate_mapping.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "schools")
public class School {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "title")
    private String title;

    @Column(name = "address")
    private String address;

    @OneToMany(cascade = {CascadeType.DETACH,CascadeType.MERGE,
    CascadeType.PERSIST,CascadeType.REFRESH}, mappedBy = "school")
    private List<User> users;

    public School() {
    }

    public School(String title, String address) {
        this.title = title;
        this.address = address;
    }

    public void addUser(User user) {
        if (users == null) {
            users = new ArrayList<>();
        }
        users.add(user);
        user.setSchool(this);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "School{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
