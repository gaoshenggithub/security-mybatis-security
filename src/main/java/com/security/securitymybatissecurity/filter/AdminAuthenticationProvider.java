package com.security.securitymybatissecurity.filter;

import com.security.securitymybatissecurity.SecurityUser;
import com.security.securitymybatissecurity.UserVO;
import com.security.securitymybatissecurity.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class AdminAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // 获取前端表单中输入后返回的用户名、密码
        String userName = (String) authentication.getPrincipal();
        String password = (String) authentication.getCredentials();

        SecurityUser userInfo = (SecurityUser) userDetailsService.loadUserByUsername(userName);

        //boolean isValid = PasswordUtils.isValidPassword(password, userInfo.getPassword(), userInfo.getCurrentUserInfo().getSalt());
        if (!userInfo.getPassword().equals(password)) {
            throw new BadCredentialsException("密码错误！");
        }
        // 验证密码
        //if (!isValid) {
        //throw new BadCredentialsException("密码错误！");
        //}

        // 前后端分离情况下 处理逻辑...
        // 更新登录令牌 - 之后访问系统其它接口直接通过token认证用户权限...
        //String token = PasswordUtils.encodePassword(System.currentTimeMillis() + userInfo.getCurrentUserInfo().getSalt(), userInfo.getCurrentUserInfo().getSalt());
        //User user = userMapper.selectById(userInfo.getCurrentUserInfo().getId());
        UserVO userVO = new UserVO();
        //userMapper.updateById(user);
        userInfo.getUser().setToken(UUID.randomUUID().toString());
        return new UsernamePasswordAuthenticationToken(userInfo, password, userInfo.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
