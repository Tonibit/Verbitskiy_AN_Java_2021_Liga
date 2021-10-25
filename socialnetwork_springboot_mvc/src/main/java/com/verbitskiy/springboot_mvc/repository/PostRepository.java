package com.verbitskiy.springboot_mvc.repository;

import com.verbitskiy.springboot_mvc.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {


}
