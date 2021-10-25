package com.verbitskiy.springboot_mvc.services.interfaces;


import com.verbitskiy.springboot_mvc.dto.UserDTO;
import com.verbitskiy.springboot_mvc.entity.User;
import javassist.NotFoundException;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    User getUserById(int id) throws NotFoundException;

    User saveUser(User user);

    void deleteUserById(int id) throws NotFoundException;

    List<User> getFriends(int id) throws NotFoundException;

    void deleteFriend(int userId, int friendId) throws NotFoundException;

    void addFriend(int userId, int friendId) throws NotFoundException;

    void addSchool(int userId, int schoolId) throws NotFoundException;
}
