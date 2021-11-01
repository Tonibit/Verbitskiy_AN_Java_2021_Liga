package com.verbitskiy.electronicqueue.service.interfaces;

import com.verbitskiy.electronicqueue.form.RegistrationUserForm;

public interface UserService {

    void registerUser(RegistrationUserForm userFrom);

    void deleteUserById(Long id);
}
