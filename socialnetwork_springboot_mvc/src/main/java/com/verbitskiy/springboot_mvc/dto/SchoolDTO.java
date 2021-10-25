package com.verbitskiy.springboot_mvc.dto;

import com.verbitskiy.springboot_mvc.entity.School;
import com.verbitskiy.springboot_mvc.entity.User;

import java.util.List;

public class SchoolDTO {

    private String title;

    private String address;


    public SchoolDTO(School school) {
        title = school.getTitle();
        address = school.getAddress();
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

    @Override
    public String toString() {
        return "SchoolDTO{" +
                "title='" + title + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
