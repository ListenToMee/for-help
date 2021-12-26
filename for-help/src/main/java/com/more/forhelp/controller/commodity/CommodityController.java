package com.more.forhelp.controller.commodity;

import com.alibaba.fastjson.JSONObject;
import com.more.forhelp.init.commodity.CommodityService;
import com.more.forhelp.until.Result;
import com.more.forhelp.vo.commodity.CommodityVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

    @RequestMapping("/addCommodity")
    public Result<String> addCommodity(@RequestBody Map param ){

        Result<String> result = commodityService.addCommodity(param);

        return result;
    }

    @RequestMapping("/uploadImg")
    public Result<String> uploadImg(@RequestParam("file") MultipartFile param ){

        Result<String> result = commodityService.uploadImg(param);

        return result;
    }

    @RequestMapping("/removeImg")
    public Result<String> removeImg(@RequestBody String param ){
        Map<String,String> jsonObject = (Map<String,String>) JSONObject.parse(param);
        String url = jsonObject.get("url");
        Result<String> result = commodityService.removeImg(url);

        return result;
    }

    @RequestMapping("/readImg")
    @ResponseBody
    public void readImg(HttpServletRequest request, HttpServletResponse response)throws Exception{
        commodityService.readImg(request,response);
    }

}
