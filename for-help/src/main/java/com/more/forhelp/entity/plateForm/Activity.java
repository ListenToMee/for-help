package com.more.forhelp.entity.plateForm;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

@Data
public class Activity {

    @TableField(value = "platformCode")
    private String platformCode;

    @TableField(value = "activityCode")
    private String activityCode;

    @TableField(value = "activityName")
    private String activityName;

}
