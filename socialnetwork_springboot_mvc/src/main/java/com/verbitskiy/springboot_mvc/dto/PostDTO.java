package com.verbitskiy.springboot_mvc.dto;

import com.verbitskiy.springboot_mvc.entity.Post;

public class PostDTO {

    private String text;
    private String userFirstName;
    private String userLastName;


    public PostDTO(Post post) {
        text = post.getText();
        userFirstName = post.getUser().getFirstName();
        userLastName = post.getUser().getLastName();
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    @Override
    public String toString() {
        return "PostDTO{" +
                "text='" + text + '\'' +
                ", userFirstName='" + userFirstName + '\'' +
                ", userLastName='" + userLastName + '\'' +
                '}';
    }
}
