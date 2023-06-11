package cn.enaium.restmock.model.entity;

import org.babyfish.jimmer.sql.*;

/**
 * @author Enaium
 */
@Entity
public interface Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id();

    String content();

    @ManyToOne
    Member member();

    @IdView
    int memberId();

    @ManyToOne
    @OnDissociate(DissociateAction.DELETE)
    Post post();

    @IdView
    int postId();
}
