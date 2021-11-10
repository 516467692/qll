package cn.com.fl.service.oneid.controller;

import cn.com.doone.tocloud.entity.Result;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @auther: Lxy
 * @description: oneId接口
 * @create: 2021-11-09 17:30
 */
@Controller
@RequestMapping("/oneid")
public class oneidController {

    @GetMapping("/testGet")
    public String testOneid(HttpServletRequest request, HttpServletResponse response){
        Result result = new Result();
        String a = request.getParameter("userid");
        return a;
    }
    @ResponseBody
    @PostMapping("/testPost")
    public String testPost (@RequestBody Object a){
        Object a0 = a;
        return "111";

    }
}
