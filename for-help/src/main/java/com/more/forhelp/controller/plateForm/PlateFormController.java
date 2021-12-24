package com.more.forhelp.controller.plateForm;

import com.more.forhelp.init.plateForm.PlateFormAndActivity;
import com.more.forhelp.until.Result;
import com.more.forhelp.vo.plateForm.PlateFormVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/plateForm")
public class PlateFormController {

    @Autowired
    PlateFormAndActivity plateFormAndActivity;

    /**
     * 获取所有平台及活动信息
     * @return
     */
    @RequestMapping("/getPAInfo")
    public Result<List<PlateFormVo>> getAllPlateFormAndActivityInfo(){

        Result<List<PlateFormVo>> result = plateFormAndActivity.getAllPlateFormAndActivityInfo();

        return result;
    }
}
