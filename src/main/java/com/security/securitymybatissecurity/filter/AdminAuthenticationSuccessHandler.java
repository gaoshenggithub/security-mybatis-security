package com.security.securitymybatissecurity.filter;

import com.security.securitymybatissecurity.ApiResult;
import com.security.securitymybatissecurity.ResponseUtils;
import com.security.securitymybatissecurity.SecurityUser;
import com.security.securitymybatissecurity.UserVO;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@Component
public class AdminAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse response, Authentication auth) throws IOException, ServletException {
        UserVO user = new UserVO();
        SecurityUser securityUser = ((SecurityUser) auth.getPrincipal());
        user.setToken(securityUser.getUser().getToken());
        ResponseUtils.out(response, ApiResult.ok("登录成功!", user));
    }
}