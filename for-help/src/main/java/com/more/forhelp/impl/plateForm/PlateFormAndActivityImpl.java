package com.more.forhelp.impl.plateForm;

import com.alibaba.druid.util.StringUtils;
import com.more.forhelp.entity.plateForm.Activity;
import com.more.forhelp.entity.plateForm.Platform;
import com.more.forhelp.init.plateForm.PlateFormAndActivity;
import com.more.forhelp.mapper.plateForm.ActivityMapper;
import com.more.forhelp.mapper.plateForm.PlateFormMapper;
import com.more.forhelp.until.Result;
import com.more.forhelp.vo.plateForm.ActivityVo;
import com.more.forhelp.vo.plateForm.PlateFormVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class PlateFormAndActivityImpl implements PlateFormAndActivity {

    @Autowired
    PlateFormMapper plateFormMapper;

    @Autowired
    ActivityMapper activityMapper;
    @Override
    public Result<List<PlateFormVo>> getAllPlateFormAndActivityInfo() {
        Result<List<PlateFormVo>> result = new Result();
        List<PlateFormVo> list = new ArrayList<>();
        try {
            List<Platform> platforms = plateFormMapper.selectList(null);
            List<Activity> activities = activityMapper.getPAInfo();
            PlateFormVo nullPlatform = new PlateFormVo();
            nullPlatform.setText("不限");
            nullPlatform.setValue("null");
            list.add(nullPlatform);
            platforms.forEach(platform -> {
                PlateFormVo plateFormVo = new PlateFormVo();
                plateFormVo.setValue(platform.getPlatformCode());
                plateFormVo.setText(platform.getPlatformName());
                List<ActivityVo> activityList = new ArrayList<>();
                ActivityVo nullActivity = new ActivityVo();
                nullActivity.setText("不限");
                nullActivity.setValue("null");
                activityList.add(nullActivity);
                activities.forEach(activity -> {
                    ActivityVo activityVo = new ActivityVo();
                    if (StringUtils.equals(activity.getPlatformCode(),platform.getPlatformCode())){
                        activityVo.setText(activity.getActivityName());
                        activityVo.setValue(activity.getActivityCode());
                        activityList.add(activityVo);
                    }
                });
                plateFormVo.setChildren(activityList);
                list.add(plateFormVo);
            });
        } catch (Exception e) {
            result.setCode(001);
            result.setMsg("服务端调用异常");
        }

        result.setData(list);
        return result;
    }

    @Override
    public Result<List<PlateFormVo>> getPlatformInfo() {
        Result<List<PlateFormVo>> result = new Result();
        try {
            List<Platform> platforms = plateFormMapper.selectList(null);
            List<PlateFormVo> list = new ArrayList<>();
            platforms.forEach(platform -> {
                PlateFormVo plateFormVo = new PlateFormVo();
                plateFormVo.setText(platform.getPlatformName());
                plateFormVo.setValue(platform.getPlatformCode());
                list.add(plateFormVo);
            });
            result.setData(list);
        } catch (Exception e) {
            System.out.println(e);
            result.setCode(000);
            result.setMsg("服务端异常");
            return result;
        }
        return result;
    }

    @Override
    public Result<List<ActivityVo>> getActivityInfo(String platformCode) {
        Result<List<ActivityVo>> result = new Result();
        try {
            Map<String,Object> param = new HashMap<>();
            param.put("platformCode",platformCode);
            List<Activity> activities = activityMapper.selectByMap(param);
            List<ActivityVo> list = new ArrayList<>();
            activities.forEach(activity -> {
                ActivityVo activityVo = new ActivityVo();
                activityVo.setText(activity.getActivityName());
                activityVo.setValue(activity.getActivityCode());
                list.add(activityVo);
            });
            result.setData(list);
        } catch (Exception e) {
            System.out.println(e);
            result.setCode(000);
            result.setMsg("服务端异常");
            return result;
        }
        return result;
    }
}
