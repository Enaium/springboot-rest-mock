package cn.enaium.restmock.model.input;

import cn.enaium.restmock.model.entity.Comment;
import lombok.Data;
import org.babyfish.jimmer.Input;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * @author Enaium
 */
@Data
public class CommentInput implements Input<Comment> {

    private static final Converter CONVERTER = Mappers.getMapper(Converter.class);

    private Integer id;

    private String content;

    private Integer memberId;

    private Integer postId;

    @Override
    public Comment toEntity() {
        return CONVERTER.toComment(this);
    }

    @Mapper
    interface Converter {
        @BeanMapping(unmappedTargetPolicy = ReportingPolicy.IGNORE)
        Comment toComment(CommentInput CommentInput);
    }
}
