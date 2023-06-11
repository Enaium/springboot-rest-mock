package cn.enaium.restmock.model.response;

import lombok.Data;

/**
 * @author Enaium
 */
@Data
public class LoginResponse {
    private final int id;
    private final String token;
}
