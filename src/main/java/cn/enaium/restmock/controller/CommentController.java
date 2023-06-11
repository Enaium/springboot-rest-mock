package cn.enaium.restmock.controller;

import cn.enaium.restmock.exception.ServiceException;
import cn.enaium.restmock.model.entity.Comment;
import cn.enaium.restmock.model.input.CommentInput;
import cn.enaium.restmock.repository.CommentRepository;
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
public class CommentController {

    private final CommentRepository commentRepository;

    /**
     * Get all comments by post id
     *
     * @param page   page
     * @param size   size
     * @param postId post id
     * @return comments
     */
    @GetMapping("/posts/{postId}/comments/")
    public Page<Comment> getComments(@RequestParam(value = "page", defaultValue = "0") int page,
                                     @RequestParam(value = "size", defaultValue = "10") int size,
                                     @PathVariable int postId) {
        return commentRepository.findByPostId(PageRequest.of(page, size), postId);
    }

    /**
     * Get comment by id
     *
     * @param id comment id
     * @return comment
     */
    @GetMapping("/posts/comments/{id}/")
    public Comment getComment(@PathVariable int id) {
        return commentRepository.findById(id)
                .orElseThrow(() -> new ServiceException("Comment not found", HttpStatus.NOT_FOUND));
    }


    /**
     * Publish comment
     *
     * @param commentInput comment
     * @return comment id
     */
    @PutMapping("/posts/{postId}/comments/")
    public int publish(@RequestBody CommentInput commentInput) {
        return commentRepository.save(commentInput).id();
    }

    @DeleteMapping("/posts/comments/{id}/")
    public void delete(@PathVariable int id) {
        commentRepository.deleteById(id);
    }
}
