package cn.enaium.restmock.controller;

import cn.enaium.restmock.exception.ServiceException;
import cn.enaium.restmock.model.entity.Post;
import cn.enaium.restmock.model.input.PostInput;
import cn.enaium.restmock.repository.PostRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * @author Enaium
 */
@RestController
@AllArgsConstructor
public class PostController {

    private final PostRepository postRepository;

    /**
     * Get all posts
     *
     * @param page page
     * @param size size
     * @return posts
     */
    @GetMapping("/posts/")
    public Page<Post> getPosts(@RequestParam(value = "page", defaultValue = "0") int page, @RequestParam(value = "size", defaultValue = "10") int size) {
        return postRepository.findAll(PageRequest.of(page, size));
    }

    /**
     * Get post by id
     *
     * @param id post id
     * @return post
     */
    @GetMapping("/posts/{id}")
    public Post getPost(@PathVariable int id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new ServiceException("Post not found", HttpStatus.NOT_FOUND));
    }

    /**
     * Publish post
     *
     * @param postInput post
     * @return post id
     */
    @PutMapping("/posts/")
    public int publish(@RequestBody PostInput postInput) {
        return postRepository.save(postInput).id();
    }

    /**
     * Delete post by id
     *
     * @param id post id
     */
    @DeleteMapping("/posts/{id}")
    public void delete(@PathVariable int id) {
        postRepository.deleteById(id);
    }
}
