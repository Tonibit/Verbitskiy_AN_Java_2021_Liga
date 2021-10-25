package com.verbitskiy.springboot_mvc.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.beans.Mergeable;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.CascadeType.*;

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

    @OneToMany(cascade = {DETACH, PERSIST, MERGE, REFRESH}, mappedBy = "school")
    private List<User> users;

    public School() {
    }

    public School(String title, String address) {
        this.title = title;
        this.address = address;
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
