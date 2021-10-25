package com.verbitskiy.springboot_mvc.services;

import com.verbitskiy.springboot_mvc.dto.PostDTO;
import com.verbitskiy.springboot_mvc.entity.Post;
import com.verbitskiy.springboot_mvc.entity.User;
import com.verbitskiy.springboot_mvc.repository.PostRepository;
import com.verbitskiy.springboot_mvc.repository.UserRepository;
import com.verbitskiy.springboot_mvc.services.interfaces.PostService;
import com.verbitskiy.springboot_mvc.services.interfaces.UserService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Autowired
    public PostServiceImpl(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    @Override
    public Post getPostById(int id) throws NotFoundException{
        return postRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Could not find Post by ID. Try another one"));
    }

    @Override
    @Transactional
    public Post savePost(int userId, Post post) throws NotFoundException {
        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException(""));
        post.setUser(user);
        return postRepository.save(post);
    }

    @Override
    @Transactional
    public Post updatePost(int id, String text) throws NotFoundException {
        Post post = getPostById(id);
        post.setText(text);
        return postRepository.save(post);
    }

    @Override
    @Transactional
    public void deletePostById(int id) throws NotFoundException{
        postRepository.deleteById(postRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Could not find element. Try another one"))
                .getId());
    }
}
