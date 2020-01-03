package com.security.securitymybatissecurity.service;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.security.securitymybatissecurity.SecurityUser;
import com.security.securitymybatissecurity.UserVO;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        // 从数据库中取出用户信息
        //List<User> userList = userMapper.selectList(new EntityWrapper<User>().eq("username", username));
        UserVO user;
        // 判断用户是否存在
        List<UserVO> userList= new ArrayList<UserVO>(){{
            UserVO userVO = new UserVO();
            userVO.setPassword("123456");
            userVO.setUsername("root");
            add(userVO);
        }};
        if (!CollectionUtils.isEmpty(userList)) {
            user = userList.get(0);
        } else {
            throw new UsernameNotFoundException("用户名不存在！");
        }
        // 返回UserDetails实现类
        return new SecurityUser(user);
    }
}
