package cn.com.fl.service.spread.service.impl;

import cn.com.doone.tocloud.entity.ServerResp;
import cn.com.doone.tocloud.tools.MyLogUtil;
import cn.com.fl.service.spread.dao.mapper.MediaMaterialMapper;
import cn.com.fl.service.spread.service.MediaMateriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @auther: Lxy
 * @description: 素材库实现类
 * @create: 2021-11-10 10:36
 */
@Service
public class MediaMateriaServiceImpl implements MediaMateriaService {

    @Autowired
    MediaMaterialMapper mediaMaterialMapper;

    @Override
    public ServerResp<Object> getWebMediamaterialList(Map<String, Object> paramMap) {
        ServerResp<Object> resp = new ServerResp<Object>();
        try {
//            List<Object> list = mediaMaterialMapper.getWebMediamaterialList(paramMap,new RowBounds(0,5));
            mediaMaterialMapper.mediaMaterialByid(paramMap);
            return resp.success();
        }catch (Exception e){
            logger.log(MyLogUtil.LOG_INFO,"web获取素材列表系统报错："+e);
            return resp.error("web获取素材列表系统报错");
        }
    }
}
