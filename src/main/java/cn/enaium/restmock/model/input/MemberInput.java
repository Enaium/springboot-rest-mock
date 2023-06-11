package cn.enaium.restmock.model.input;

import cn.enaium.restmock.model.entity.Member;
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
public class MemberInput implements Input<Member> {

    private static final Converter CONVERTER = Mappers.getMapper(Converter.class);

    private Integer id;

    private String username;

    private String password;

    @Override
    public Member toEntity() {
        return CONVERTER.toMember(this);
    }

    @Mapper
    interface Converter {
        @BeanMapping(unmappedTargetPolicy = ReportingPolicy.IGNORE)
        Member toMember(MemberInput memberInput);
    }
}
