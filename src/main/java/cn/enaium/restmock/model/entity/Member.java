package cn.enaium.restmock.model.entity;

import org.babyfish.jimmer.sql.Entity;
import org.babyfish.jimmer.sql.GeneratedValue;
import org.babyfish.jimmer.sql.GenerationType;
import org.babyfish.jimmer.sql.Id;
import org.jetbrains.annotations.NotNull;

/**
 * @author Enaium
 */
@Entity
public interface Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id();

    @NotNull
    String username();

    @NotNull
    String password();
}
