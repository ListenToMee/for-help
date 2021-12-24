package com.more.forhelp.vo.plateForm;

import lombok.Data;

import java.util.List;

@Data
public class PlateFormVo {

    private String text;

    private String value;

    private List<ActivityVo> children;
}
