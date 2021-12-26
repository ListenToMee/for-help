package com.more.forhelp.init.plateForm;

import com.more.forhelp.until.Result;
import com.more.forhelp.vo.plateForm.ActivityVo;
import com.more.forhelp.vo.plateForm.PlateFormVo;

import java.util.List;

public interface PlateFormAndActivity {

    Result<List<PlateFormVo>> getAllPlateFormAndActivityInfo();

    Result<List<PlateFormVo>> getPlatformInfo();

    Result<List<ActivityVo>> getActivityInfo(String platformCode);
}
