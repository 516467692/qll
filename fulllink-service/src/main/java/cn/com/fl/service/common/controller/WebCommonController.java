package cn.com.fl.service.common.controller;

import cn.com.ct.service.controller.AppBaseController;
import cn.com.doone.tocloud.entity.CheckReqResultEntity;
import cn.com.doone.tocloud.entity.Result;
import cn.com.doone.tocloud.entity.ServerResp;
import cn.com.doone.tocloud.enums.CommonResultCode;
import cn.com.doone.tocloud.tools.MyLogUtil;
import cn.com.doone.tocloud.tools.MyLogger;
import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Controller
@RequestMapping("/")
public class WebCommonController extends AppBaseController {
    MyLogger logger = MyLogUtil.getLogger(WebCommonController.class);

    /**
     * 标准接口
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping("/doRequest")
    public Result doRequest(HttpServletRequest request, HttpServletResponse response){
        return handleReq(request, response);
    }

    @ResponseBody
    @RequestMapping("/doRequest1")
    public Result doRequest1(@RequestBody Map<String,Object> param, HttpServletRequest request){
        Result result = new Result();
        ServerResp<Object> resp = new ServerResp<>();
        for(String key : param.keySet()){
            String pStr = "";
            if (!"POST".equals(request.getMethod()) && !"PUT".equals(request.getMethod())) {
                pStr = String.valueOf(param.get(key));
            } else {
                pStr = UriUtils.decode(String.valueOf(param.get(key)), "utf-8");
            }
            param.put(key, pStr);
        }
        resp = methodInvoke(request.getRemoteHost(), (Map)JSON.parse(String.valueOf(param.get("params"))));
        return result.conversion(resp);
    }

    /**
     * 请求处理
     * 统一请求入口形式使用反射机制通过请求编码指向业务执行类的对应函数
     * 统一请求入口形式必须带有reqCode参数
     * @param request
     * @return
     */
    private Result handleReq(HttpServletRequest request, HttpServletResponse response){
        Result result = new Result();
        ServerResp<Object> resp = new ServerResp<>();
        try{
            String host = request.getRemoteHost();
            //提取请求参数并解码
            CheckReqResultEntity cqEnt = getParameter(request, response);
            if(cqEnt.getResCode() == CommonResultCode.CHECK_REQ_SUCCESS.getCode()){
                Map<String, Object> params = cqEnt.getResData();
                if(!params.containsKey("reqCode")){
                    logger.log(MyLogUtil.LOG_ERROR, "必要参数【reqCode】缺失");
                    return result.error(CommonResultCode.REQ_ERROR.getCode(), "必要参数【reqCode】缺失");
                }
                resp = methodInvoke(host, params);
            }else{
                resp.error(cqEnt.getResCode(), cqEnt.getResMsg());
            }
            return result.conversion(resp);
        }catch (Exception e){
            logger.log(MyLogUtil.LOG_ERROR, "请求异常", e);
            return result.error(CommonResultCode.REQ_ERROR.getCode(), "请求异常");
        }

    }

}
