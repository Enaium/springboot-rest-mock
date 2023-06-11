package cn.enaium.restmock.model.entity;

import org.babyfish.jimmer.sql.*;

import java.util.List;

/**
 * @author Enaium
 */
@Entity
public interface Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id();

    String title();

    String content();

    @ManyToOne
    Member member();

    @IdView
    int memberId();

    @OneToMany(mappedBy = "post")
    List<Comment> comments();
}
