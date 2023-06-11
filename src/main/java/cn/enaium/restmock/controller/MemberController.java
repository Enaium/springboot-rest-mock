package cn.enaium.restmock.controller;

import cn.dev33.satoken.annotation.SaIgnore;
import cn.enaium.restmock.model.input.MemberInput;
import cn.enaium.restmock.repository.MemberRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Enaium
 */
@RestController
@AllArgsConstructor
public class MemberController {

    private final MemberRepository memberRepository;

    @SaIgnore
    @PutMapping("/members/")
    public void register(@RequestBody MemberInput memberInput) {
        memberRepository.insert(memberInput);
    }
}
