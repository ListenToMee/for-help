package com.more.forhelp.entity.login;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class User {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField(value = "userName")
    private String userName;

    @TableField(value = "passWord")
    private String passWord;

    @TableField(value = "phoneNum")
    private String phoneNum;

    @TableField(value = "userStatus")
    private Integer userStatus;

    @TableField(value = "registerDate")
    private String registerDate;
}
