package com.verbitskiy.springboot_mvc.services;

import com.verbitskiy.springboot_mvc.entity.School;
import com.verbitskiy.springboot_mvc.entity.User;
import com.verbitskiy.springboot_mvc.repository.UserRepository;
import com.verbitskiy.springboot_mvc.services.interfaces.SchoolService;
import com.verbitskiy.springboot_mvc.services.interfaces.UserService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final SchoolService schoolService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, SchoolService schoolService) {
        this.userRepository = userRepository;
        this.schoolService = schoolService;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(int id) throws NotFoundException{
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Could not find user with this ID. Try another one"));
    }

    @Override
    @Transactional
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public void deleteUserById(int id) throws NotFoundException {
        userRepository.deleteById(userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Could not find user with this ID. Try another one"))
                .getId());
    }

    @Override
    @Transactional
    public void addSchool(int userId, int schoolId) throws NotFoundException {
        User user = getUserById(userId);
        School school = schoolService.getSchoolById(schoolId);
        user.setSchool(school);
        userRepository.save(user);
    }
    @Override
    public List<User> getFriends(int id) throws NotFoundException{
        User user = getUserById(id);
        if (user.getFriendList() == null) {
            user.setFriendList(new ArrayList<>());
        }
        return user.getFriendList();
    }

    @Override
    @Transactional
    public void addFriend(int userId, int friendId) throws NotFoundException {
        User user = getUserById(userId);
        User friend = getUserById(friendId);
        user.addFriend(friend);
        friend.addFriend(user);
        userRepository.save(user);
    }



    @Override
    @Transactional
    public void deleteFriend(int userId, int friendId) throws NotFoundException {
        User user = getUserById(userId);
        User friend = getUserById(friendId);
        user.deleteFriend(friend);
        friend.deleteFriend(user);
        userRepository.save(user);
    }



}
