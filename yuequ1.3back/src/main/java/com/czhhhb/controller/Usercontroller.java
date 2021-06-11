package com.czhhhb.controller;

import com.czhhhb.entity.User;
import com.czhhhb.mapper.Usermapper;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class Usercontroller {
    @Autowired
    Usermapper usermapper;
    @RequestMapping("/hello")
    public List<Map<String, Object>> test()
    {
        return usermapper.test();
    }
    @RequestMapping("/login")
    public JSONObject login(@RequestBody JSONObject js)
    {
        User user=new User();
        user.setUSER_ID(js.getString("openid"));
        user.setUSER_NICKNAME(js.getString("UserName"));
        user.setUSER_headimage(js.getString("USER_headimage"));
        System.out.println(user.toString());
        JSONObject returnjs=new JSONObject();
        if(usermapper.Usermapper_selectByid(user.getUSER_ID())<1) {
            usermapper.Usermapper_add(user);
            returnjs.accumulate("success", 1);
        }
        else
            returnjs.accumulate("success",0);
        System.out.println(returnjs.toString());
        return returnjs;
    }
    @RequestMapping("/change")
    public JSONObject change(@RequestBody JSONObject js)
    {
        User user=new User();
        user.setUSER_ID(js.getString("USER_ID"));
        user.setUSER_NICKNAME(js.getString("USER_NICKNAME"));
        user.setUSER_SEX(js.getInt("USER_SEX"));
        user.setUSER_PHONENUM(js.getString("USER_PHONENUM"));
        user.setUSER_CLOCK_NUM(0);
        //user.setUSER_headimage(js.getString("USER_ID"));
        System.out.println(user.toString());
        JSONObject returnjs=new JSONObject();
        if(usermapper.Usermapper_selectByid(user.getUSER_ID())<1) {
            usermapper.Usermapper_updata(user);
            returnjs.accumulate("success", 1);
        }
        else
            returnjs.accumulate("success",0);
        System.out.println(returnjs.toString());
        return returnjs;
    }
    @RequestMapping("/rank")
    public List<Map<String, Object>> rank()
    {
        return usermapper.Usermapper_select_rank();
    }
    @RequestMapping("/myrank")
    public Map<String, Object> rank(@RequestBody JSONObject js)
    {
        return usermapper.Usermapper_select_myrank(js.get("USER_ID").toString());
    }
    @RequestMapping("/history")
    public JSONObject history(@RequestBody JSONObject js)
    {
        List<Map<String, Object>> maps=usermapper.Usermapper_histroy(js.get("USER_ID").toString());
        List<String> categorieslist=new ArrayList<String>();
        List<String> datalist=new ArrayList<String>();
        for (Map<String, Object> map : maps) {
            String categories1=map.get("date").toString();
            String data1=map.get("time").toString();
            categorieslist.add(categories1);
            datalist.add(data1);
        }
        JSONObject categories=new JSONObject();
        categories.accumulate("categories",categorieslist);

        List<JSONObject> data=new ArrayList<JSONObject>();
        JSONObject datajs=new JSONObject();
        datajs.accumulate("data",datalist);
        data.add(datajs);

        JSONObject series=new JSONObject();
        series.accumulate("series",data);

        List<JSONObject> chartDatalist=new ArrayList<JSONObject>();
        chartDatalist.add(series);
        chartDatalist.add(categories);

        JSONObject returnjs=new JSONObject();
        returnjs.accumulate("chartData",chartDatalist);
        System.out.println(returnjs.toString());
        return returnjs;
    }
    @RequestMapping("/library_advice")
    public JSONObject library_advice(@RequestBody JSONObject js)
    {
        JSONObject returnjs=new JSONObject();
        returnjs.accumulate("success",usermapper.Usermapper_library_advice(js.get("USER_ID").toString(),js.get("advice").toString()));
        System.out.println(js.toString());
        return returnjs;
    }

}
