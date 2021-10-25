package com.verbitskiy.springboot_mvc.controller;

import com.verbitskiy.springboot_mvc.dto.PostDTO;
import com.verbitskiy.springboot_mvc.entity.Post;
import com.verbitskiy.springboot_mvc.services.interfaces.PostService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public List<PostDTO> getAllPosts() {
        return postService.getAllPosts().stream()
                .map(PostDTO::new).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public PostDTO getPost(@PathVariable("id") int id) throws NotFoundException {
        return new PostDTO(postService.getPostById(id));
    }

    @PostMapping
    public PostDTO save(@RequestParam("id") int userId, @RequestBody Post post) throws NotFoundException {
        return new PostDTO(postService.savePost(userId, post));
    }

    @PutMapping("/{id}")
    public PostDTO updatePost(@PathVariable("id")int id, @RequestBody String text) throws NotFoundException {
        return new PostDTO(postService.updatePost(id, text));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePost(@PathVariable("id") int id) throws NotFoundException {
        postService.deletePostById(id);
    }
}
