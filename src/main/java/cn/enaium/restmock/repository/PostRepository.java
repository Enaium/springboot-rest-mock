package cn.enaium.restmock.repository;

import cn.enaium.restmock.model.entity.Post;
import org.babyfish.jimmer.spring.repository.JRepository;

/**
 * @author Enaium
 */
public interface PostRepository extends JRepository<Post, Integer> {
}
