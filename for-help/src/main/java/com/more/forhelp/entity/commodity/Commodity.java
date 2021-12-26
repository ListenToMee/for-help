package com.more.forhelp.entity.commodity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class Commodity {

    @TableId(value = "id", type = IdType.AUTO)
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
