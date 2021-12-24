package com.more.forhelp.controller.commodity;

import com.alibaba.fastjson.JSONObject;
import com.more.forhelp.entity.plateForm.Activity;
import com.more.forhelp.init.commodity.CommodityService;
import com.more.forhelp.until.Result;
import com.more.forhelp.vo.commodity.CommodityVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/commodity")
public class CommodityController {

    @Autowired
    CommodityService commodityService;

    @RequestMapping("/getCommodityInfo")
    public Result<List<CommodityVo>> getCommodityInfo(@RequestBody String param ){

        Result<List<CommodityVo>> result = commodityService.getCommodityInfo(param);

        return result;
    }
}
