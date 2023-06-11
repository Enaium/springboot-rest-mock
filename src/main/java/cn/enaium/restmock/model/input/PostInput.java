package cn.enaium.restmock.model.input;

import cn.enaium.restmock.model.entity.Post;
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
public class PostInput implements Input<Post> {

    private static final Converter CONVERTER = Mappers.getMapper(Converter.class);

    private Integer id;

    private String title;

    private String content;

    private Integer memberId;

    @Override
    public Post toEntity() {
        return CONVERTER.toPost(this);
    }

    @Mapper
    interface Converter {
        @BeanMapping(unmappedTargetPolicy = ReportingPolicy.IGNORE)
        Post toPost(PostInput PostInput);
    }
}
