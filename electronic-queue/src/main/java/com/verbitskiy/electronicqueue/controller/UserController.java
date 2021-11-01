package com.verbitskiy.electronicqueue.controller;

import com.verbitskiy.electronicqueue.form.RegistrationUserForm;
import com.verbitskiy.electronicqueue.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/registration")
    public void registerUser(@RequestBody RegistrationUserForm userForm) {
        userService.registerUser(userForm);

    }

    @DeleteMapping("/admins/users/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable("id") Long id) {
        userService.deleteUserById(id);
    }
}
