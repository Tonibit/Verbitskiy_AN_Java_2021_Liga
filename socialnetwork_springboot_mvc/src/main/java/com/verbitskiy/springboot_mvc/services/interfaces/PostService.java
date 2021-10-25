package com.verbitskiy.springboot_mvc.services.interfaces;

import com.verbitskiy.springboot_mvc.dto.PostDTO;
import com.verbitskiy.springboot_mvc.entity.Post;
import javassist.NotFoundException;

import java.util.List;

public interface PostService {

    List<Post> getAllPosts();

    Post getPostById(int id) throws NotFoundException;

    Post savePost(int userId, Post post) throws NotFoundException;

    Post updatePost(int id, String text) throws NotFoundException;

    void deletePostById(int id) throws NotFoundException;

}
