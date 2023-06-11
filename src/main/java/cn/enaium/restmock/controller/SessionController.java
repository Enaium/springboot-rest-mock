package cn.enaium.restmock.controller;

import cn.dev33.satoken.annotation.SaIgnore;
import cn.dev33.satoken.stp.StpUtil;
import cn.enaium.restmock.exception.ServiceException;
import cn.enaium.restmock.model.input.MemberInput;
import cn.enaium.restmock.model.response.LoginResponse;
import cn.enaium.restmock.repository.MemberRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;
import java.util.Optional;

/**
 * @author Enaium
 */
@RestController
@AllArgsConstructor
public class SessionController {

    private final MemberRepository memberRepository;

    @SaIgnore
    @PutMapping("/sessions/")
    public LoginResponse login(@RequestBody MemberInput memberInput) {
        return memberRepository.findByUsername(memberInput.getUsername())
                .map(member -> Optional.ofNullable(memberInput.getPassword())
                        .map(password -> Optional.ofNullable(Objects.equals(member.password(), password) ? new LoginResponse(member.id(), member.username()) : null)
                                .orElseThrow(() -> new ServiceException("Password error", HttpStatus.UNAUTHORIZED)))
                        .orElseThrow(() -> new ServiceException("Password is blank", HttpStatus.UNAUTHORIZED)))
                .orElseThrow(() -> new ServiceException("User not found", HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/sessions/")
    public void logout() {
        StpUtil.logout();
    }
}
