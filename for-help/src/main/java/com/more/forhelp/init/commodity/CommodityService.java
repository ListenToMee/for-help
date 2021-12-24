package com.more.forhelp.init.commodity;

import com.more.forhelp.until.Result;
import com.more.forhelp.vo.commodity.CommodityVo;

import java.util.List;

public interface CommodityService {

    Result<List<CommodityVo>> getCommodityInfo(String param);
}
