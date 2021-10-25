package com.verbitskiy.springboot_mvc.dto;

import com.verbitskiy.springboot_mvc.entity.User;

public class FriendDTO {

    private String firstName;
    private String lastName;

    public FriendDTO(User user) {
        firstName = user.getFirstName();
        lastName = user.getLastName();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
