package com.more.forhelp.controller.plateForm;

import com.alibaba.fastjson.JSONObject;
import com.more.forhelp.init.plateForm.PlateFormAndActivity;
import com.more.forhelp.until.Result;
import com.more.forhelp.vo.plateForm.ActivityVo;
import com.more.forhelp.vo.plateForm.PlateFormVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/plateForm")
public class PlateFormController {

    @Autowired
    PlateFormAndActivity plateFormAndActivity;

    /**
     * 获取所有平台及活动信息(构造筛框数据)
     * @return
     */
    @RequestMapping("/getPAInfo")
    public Result<List<PlateFormVo>> getAllPlateFormAndActivityInfo(){

        Result<List<PlateFormVo>> result = plateFormAndActivity.getAllPlateFormAndActivityInfo();

        return result;
    }

    /**
     * 获取所有平台信息
     * @return
     */
    @RequestMapping("/getPlatformInfo")
    public Result<List<PlateFormVo>> getPlatformInfo(){

        Result<List<PlateFormVo>> result = plateFormAndActivity.getPlatformInfo();

        return result;
    }

    /**
     * 获取所有平台信息
     * @return
     */
    @RequestMapping("/getActivityInfo")
    public Result<List<ActivityVo>> getActivityInfo(@RequestBody String param){

        Map<String,String> jsonObject = (Map<String,String>) JSONObject.parse(param);
        String platformCode = jsonObject.get("platformCode");
        Result<List<ActivityVo>> result = plateFormAndActivity.getActivityInfo(platformCode);

        return result;
    }
}
