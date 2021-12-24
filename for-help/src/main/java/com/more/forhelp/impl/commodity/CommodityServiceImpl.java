package com.more.forhelp.impl.commodity;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSONObject;
import com.more.forhelp.entity.commodity.Commodity;
import com.more.forhelp.init.commodity.CommodityService;
import com.more.forhelp.mapper.commodity.CommodityMapper;
import com.more.forhelp.until.Result;
import com.more.forhelp.vo.commodity.CommodityVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CommodityServiceImpl implements CommodityService {

    @Autowired
    CommodityMapper commodityMapper;

    @Override
    public Result<List<CommodityVo>> getCommodityInfo(String param) {
        Map<String,String> jsonObject = (Map<String,String>) JSONObject.parse(param);
        String activityCode = jsonObject.get("activityCode");
        String platformCode = jsonObject.get("platformCode");
        Result<List<CommodityVo>> result = new Result();
        try {
            List<Commodity> list = new ArrayList<>();
            if (StringUtils.equals(platformCode,"null")){
                list = commodityMapper.selectList(null);
            } else if (!StringUtils.equals(platformCode,"null")){
                Map paramMap = new HashMap();
                if (!StringUtils.equals(activityCode,"null")){
                    paramMap.put("activityCode",activityCode);
                }
                paramMap.put("platformCode",platformCode);
                list = commodityMapper.selectByMap(paramMap);
            }
            List<CommodityVo> commodityVoList = new ArrayList<>();
            list.forEach(commodity -> {
                CommodityVo commodityVo = new CommodityVo();
                commodityVo.setPrice(String.valueOf(commodity.getPrice()));
                commodityVo.setDesc(commodity.getCommodityDesc());
                commodityVo.setTitle(commodity.getCommodityName());
                commodityVo.setThumb(commodity.getUrl());
                commodityVoList.add(commodityVo);
            });
            result.setData(commodityVoList);
        } catch (Exception e) {
            result.setCode(000);
            result.setMsg("服务端异常");
        }
        return result;
    }
}
