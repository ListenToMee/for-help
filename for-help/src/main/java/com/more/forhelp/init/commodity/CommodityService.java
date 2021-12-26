package com.more.forhelp.init.commodity;

import com.more.forhelp.until.Result;
import com.more.forhelp.vo.commodity.CommodityVo;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

public interface CommodityService {

    Result<List<CommodityVo>> getCommodityInfo(String param);

    Result<String> uploadImg(MultipartFile param );

    Result<String> removeImg(String param );

    Result<String> addCommodity(Map param);

    void readImg(HttpServletRequest request, HttpServletResponse response);
}
