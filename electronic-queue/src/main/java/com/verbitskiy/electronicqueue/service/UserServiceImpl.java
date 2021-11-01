package com.verbitskiy.electronicqueue.service;

import com.verbitskiy.electronicqueue.entity.AppUser;
import com.verbitskiy.electronicqueue.exception.UserFoundException;
import com.verbitskiy.electronicqueue.form.RegistrationUserForm;
import com.verbitskiy.electronicqueue.repository.RoleRepository;
import com.verbitskiy.electronicqueue.repository.UserRepository;
import com.verbitskiy.electronicqueue.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User with name" + username + "not found"));

        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.getRole().getName()));

        return new User(user.getUsername(), user.getPassword(), authorities);
    }

    @Transactional
    @Override
    public void registerUser(RegistrationUserForm userFrom) {

        if (userRepository.findByUsername(userFrom.getUsername()).isPresent()) {
            throw new UserFoundException("User with login " + userFrom.getUsername() + " exist. Create another one.");
        }
        AppUser newUser = new AppUser();
        newUser.setName(userFrom.getName());
        newUser.setUsername(userFrom.getUsername());
        newUser.setPassword(passwordEncoder.encode(userFrom.getPassword()));
        newUser.setBookingList(new ArrayList<>());
        newUser.setRole(roleRepository.findByName("ROLE_USER"));
        userRepository.save(newUser);
    }

    @Override
    @Transactional
    public void deleteUserById(Long id) {
        userRepository.deleteById(userRepository.findById(id)
                .orElseThrow(() -> new UserFoundException("Could not find user with this ID. Try another one"))
                .getId());
    }

}
