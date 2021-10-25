package com.verbitskiy.springboot_mvc.dto;

import com.verbitskiy.springboot_mvc.entity.Post;
import com.verbitskiy.springboot_mvc.entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class UserDTO {

    private String firstName;

    private String lastName;

    private int age;

    private String gender;

    private String school;

    private List<String> posts;

    public UserDTO(User user) {
        firstName = user.getFirstName();
        lastName = user.getLastName();
        age = user.getAge();
        gender = user.getGender();

        if (user.getSchool() != null) {
            school = user.getSchool().toString();
        } else {
            school = "empty";
        }

        posts = getPostDtoList(user.getPosts());

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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }


    public List<String> getPosts() {
        return posts;
    }

    public void setPosts(List<String> posts) {
        this.posts = posts;
    }

    private List<String> getPostDtoList(List<Post> posts) {
        AtomicInteger i = new AtomicInteger(1);
        List<String> postList = new ArrayList<>();

        if(posts == null) {
            return new ArrayList<>();
        }
        posts.forEach(post -> {postList.add(i.getAndIncrement() + ". " + post.getText());});
        return postList;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", schoolName='" + school + '\'' +
                '}';
    }
}
