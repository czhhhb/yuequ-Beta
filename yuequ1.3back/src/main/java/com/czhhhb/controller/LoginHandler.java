package com.czhhhb.controller;

import com.czhhhb.entity.OpenIdJson;
import com.czhhhb.service.HttpUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.sf.json.JSONObject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class LoginHandler {

    private String appID = "";
    private String appSecret = "";

    @PostMapping("/user/testlogin")
    public JSONObject userLogin(@RequestBody JSONObject js) throws IOException {
        System.out.println(js.toString());
        String result = "";
        try{//请求微信服务器，用code换取openid。HttpUtil是工具类，后面会给出实现，Configure类是小程序配置信息，后面会给出代码
            result = HttpUtil.doGet(
                    "https://api.weixin.qq.com/sns/jscode2session?appid="
                            + js.get("appID") + "&secret="
                            + js.get("appSecret") + "&js_code="
                            + js.get("code").toString()
                            + "&grant_type=authorization_code", null);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("获取到的信息为+"+result);
        ObjectMapper mapper = new ObjectMapper();
        OpenIdJson openIdJson = mapper.readValue(result,OpenIdJson.class);
        System.out.println(result.toString());
        System.out.println(openIdJson.getOpenid());
        JSONObject returnjs=new JSONObject();
        returnjs.accumulate("openid",openIdJson.getOpenid());
        return returnjs;
    }

}
