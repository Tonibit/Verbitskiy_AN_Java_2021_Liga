package com.verbitskiy.springboot_mvc.controller;

import com.verbitskiy.springboot_mvc.dto.FriendDTO;
import com.verbitskiy.springboot_mvc.dto.UserDTO;
import com.verbitskiy.springboot_mvc.entity.User;
import com.verbitskiy.springboot_mvc.services.interfaces.UserService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers().stream().map(UserDTO::new).collect(Collectors.toList());

    }

    @GetMapping("/{id}")
    public UserDTO getUser(@PathVariable("id") int id) throws NotFoundException {
        return new UserDTO(userService.getUserById(id));
    }

    @PostMapping
    public UserDTO saveUser(@RequestBody User user) {
        return new UserDTO(userService.saveUser(user));
    }

    @PutMapping
    public UserDTO updateUser(@RequestBody User user) {
        return new UserDTO(userService.saveUser(user));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable("id") int id) throws NotFoundException {
        userService.deleteUserById(id);
    }
    @PostMapping("/add_school")
    public void addSchool(@RequestParam("userId") int userId, @RequestParam("schoolId") int schoolId) throws NotFoundException {
        userService.addSchool(userId, schoolId);
    }

    @GetMapping("/friends/{id}")
    public List<FriendDTO> getFriends(@PathVariable("id") int id) throws NotFoundException {
        return userService.getFriends(id).stream()
                .map(FriendDTO::new).collect(Collectors.toList());
    }

    @PostMapping("/friends")
    public void addFriend(@RequestParam("userId") int userId, @RequestParam("friendId") int friendId) throws NotFoundException {
        userService.addFriend(userId, friendId);
    }

    @DeleteMapping("/friends")
    public void deleteFriend(@RequestParam("userId") int userId, @RequestParam("friendId") int friendId) throws NotFoundException {
        userService.deleteFriend(userId, friendId);
    }
}
