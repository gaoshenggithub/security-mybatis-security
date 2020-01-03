package com.security.securitymybatissecurity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
public class UserVO {

    private Long id;

    private String username;

    private String password;

    private String salt;

    private String token;
}
