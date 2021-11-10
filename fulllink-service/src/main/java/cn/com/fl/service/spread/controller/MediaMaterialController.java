package cn.com.fl.service.spread.controller;

import cn.com.ct.service.controller.AppBaseController;
import cn.com.doone.tocloud.entity.Result;
import cn.com.doone.tocloud.entity.ServerResp;
import cn.com.doone.tocloud.tools.MyLogUtil;
import cn.com.doone.tocloud.tools.MyLogger;
import cn.com.fl.service.PlatApplication;
import cn.com.fl.service.spread.evt.mediaMaterEvt.MdiaMaterialWebList;
import cn.com.fl.service.spread.service.MediaMateriaService;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

/**
 * @auther: Lxy
 * @description: 素材库
 * @create: 2021-11-10 09:35
 */
@Controller
@RequestMapping("/mediaMaterial")
public class MediaMaterialController extends AppBaseController {

    MyLogger logger = MyLogUtil.getLogger(PlatApplication.class);

    @Autowired
    MediaMateriaService mediaMateriaService;

    /**
     * 业务描述：PC 获取素材库列表
     * @auther: Lxy
     * @date: 2021/11/10
     * @param
     * @return  cn.com.doone.tocloud.entity.Result
     */
    @ResponseBody
    @GetMapping("/getWebMediamaterialList")
    public Result getWebMediamaterialList(@Valid  @ModelAttribute MdiaMaterialWebList evt){
        Result result = new Result();
        try {
            logger.log(MyLogUtil.LOG_INFO,"web获取素材列表入参:"+evt);
            Map<String, Object> paramMap = BeanUtils.describe(evt);
            ServerResp<Object> resp = mediaMateriaService.getWebMediamaterialList(paramMap);
            return result.conversion(resp);
        }catch (Exception e){
            logger.log(MyLogUtil.LOG_ERROR, "系统异常", e);
            return result.error("");

        }
    }
}
