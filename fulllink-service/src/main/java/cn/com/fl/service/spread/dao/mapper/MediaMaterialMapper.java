package cn.com.fl.service.spread.dao.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import java.util.List;
import java.util.Map;

@Mapper
public interface MediaMaterialMapper {
    /**
     * 业务描述：web 获取素材列表
     * @auther: Lxy
     * @date: 2021/11/10
     * @param paramMap
     * @return  java.util.List<java.lang.Object>
     */
    List<Object> getWebMediamaterialList(Map<String, Object> paramMap, RowBounds rowBounds);


    Map<String, Object> mediaMaterialByid(Map<String, Object> paramMap);
}
