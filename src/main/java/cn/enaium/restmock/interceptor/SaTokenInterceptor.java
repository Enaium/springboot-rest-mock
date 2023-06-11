package cn.enaium.restmock.interceptor;

import cn.dev33.satoken.interceptor.SaInterceptor;
import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.stp.StpUtil;
import org.springframework.stereotype.Component;

/**
 * @author Enaium
 */
@Component
public class SaTokenInterceptor extends SaInterceptor {
    public SaTokenInterceptor() {
        super(r -> SaRouter.match("/**").notMatch("/swagger-ui/**", "/v3/api-docs/**").notMatchMethod("OPTIONS").check(r1 -> {
            StpUtil.checkLogin();
        }));
    }
}
