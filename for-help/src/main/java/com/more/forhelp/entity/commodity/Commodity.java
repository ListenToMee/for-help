package com.more.forhelp.entity.commodity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class Commodity {

    @TableField(value = "id")
    private String id;

    @TableField(value = "commodityName")
    private String commodityName;

    @TableField(value = "price")
    private BigDecimal price;

    @TableField(value = "url")
    private String url;

    @TableField(value = "commodityDesc")
    private String commodityDesc;

    @TableField(value = "platformCode")
    private String platformCode;

    @TableField(value = "activityCode")
    private String activityCode;
}
