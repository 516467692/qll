package cn.com.fl.service.spread.service;

import cn.com.doone.tocloud.entity.ServerResp;
import cn.com.doone.tocloud.tools.MyLogUtil;
import cn.com.doone.tocloud.tools.MyLogger;
import cn.com.fl.service.PlatApplication;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface MediaMateriaService {

    MyLogger logger = MyLogUtil.getLogger(PlatApplication.class);

    /**
     * 业务描述：web 获取素材列表
     * @auther: Lxy
     * @date: 2021/11/10
     * @param paramMap
     * @return  cn.com.doone.tocloud.entity.ServerResp<java.lang.Object>
     */
     ServerResp<Object> getWebMediamaterialList (Map<String, Object> paramMap);
}
