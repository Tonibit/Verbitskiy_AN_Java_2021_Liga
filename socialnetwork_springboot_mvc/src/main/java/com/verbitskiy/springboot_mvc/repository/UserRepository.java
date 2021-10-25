package com.verbitskiy.springboot_mvc.repository;

import com.verbitskiy.springboot_mvc.entity.Post;
import com.verbitskiy.springboot_mvc.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}
