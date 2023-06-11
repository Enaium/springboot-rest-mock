package cn.enaium.restmock.repository;

import cn.enaium.restmock.model.entity.Member;
import org.babyfish.jimmer.spring.repository.JRepository;

import java.util.Optional;

/**
 * @author Enaium
 */
public interface MemberRepository extends JRepository<Member, Integer> {
    Optional<Member> findByUsername(String username);
}
