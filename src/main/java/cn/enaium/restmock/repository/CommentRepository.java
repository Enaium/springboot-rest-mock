package cn.enaium.restmock.repository;

import cn.enaium.restmock.model.entity.Comment;
import org.babyfish.jimmer.spring.repository.JRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author Enaium
 */
public interface CommentRepository extends JRepository<Comment, Integer> {
    Page<Comment> findByPostId(Pageable pageable, int postId);
}
