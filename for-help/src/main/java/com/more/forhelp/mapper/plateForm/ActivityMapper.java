package com.more.forhelp.mapper.plateForm;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.more.forhelp.entity.plateForm.Activity;
import com.more.forhelp.entity.plateForm.Platform;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ActivityMapper extends BaseMapper<Activity> {
    @Select("select \n" +
            "\t   p.platformCode,\n" +
            "\t   a.activityCode,\n" +
            "\t   a.activityName\n" +
            "\t   \n" +
            "from platform p\n" +
            "left join activity a\n" +
            "on p.platformCode = a.platformCode")
    List<Activity> getPAInfo();
}
