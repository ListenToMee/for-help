package com.more.forhelp.entity.plateForm;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

@Data
public class Platform {

    @TableField(value = "platformCode")
    private String platformCode;

    @TableField(value = "platformName")
    private String platformName;

}
