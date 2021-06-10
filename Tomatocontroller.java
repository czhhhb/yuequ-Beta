package com.czhhhb.controller;

import com.czhhhb.entity.Tomato_clock;
import com.czhhhb.entity.count_down;
import com.czhhhb.mapper.Clockmapper;
import com.czhhhb.mapper.Countmapper;
import com.czhhhb.mapper.Tomatomapper;
import com.czhhhb.service.Common_methods;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/tomato")
public class Tomatocontroller {
    @Autowired
    Tomatomapper tomatomapper;
    @Autowired
    Common_methods common_methods;
    @RequestMapping("/add")
    public JSONObject tomato_clock_add(@RequestBody JSONObject js)
    {
        Tomato_clock tomato=new Tomato_clock();
        tomato.setUSER_ID(js.get("USER_ID").toString());
        tomato.setStart_time(common_methods.nowdate());
        tomato.setTime(js.get("time").toString());
        tomatomapper.tomato_clock_add(tomato);
        JSONObject returnjs=new JSONObject();
        returnjs.accumulate("tomatoadd_success",1);
        return returnjs;
    }
    @RequestMapping("/state")
    public JSONObject tomato_clock_state(@RequestBody JSONObject js)
    {
        JSONObject returnjs=new JSONObject();
        List<Map<String, Object>> maps=tomatomapper.Tomato_clock_selectByidandstate(js.get("USER_ID").toString(),1);
        if(maps.size()==0)
        {
            returnjs.accumulate("state",0);
        }
        else
        {
            Map<String, Object> map=maps.get(0);
            long time=Integer.parseInt(map.get("time").toString())-common_methods.date_subtract_miao(map.get("Start_time").toString().replace('T',' '),common_methods.nowdate());
            System.out.println(time);
            System.out.println(Integer.parseInt(map.get("time").toString()));
            if(time<=0)
            {
                tomatomapper.Tomato_clock_updatastate(map.get("USER_ID").toString());
                returnjs.accumulate("state",0);
            }
            else {
                returnjs.accumulate("state", 1);
                returnjs.accumulate("time_hour",time/3600);
                returnjs.accumulate("time_minute",(time/60)%60);
                returnjs.accumulate("time_miao",time%60);
            }
        }
        return returnjs;
    }
    @RequestMapping("/over")
    public JSONObject tomato_clock_earlyover(@RequestBody JSONObject js) {
        JSONObject returnjs=new JSONObject();
        List<Map<String, Object>> maps=tomatomapper.Tomato_clock_selectByidandstate(js.get("USER_ID").toString(),1);
        Map<String, Object> map=maps.get(0);
        tomatomapper.Tomato_clock_updatastate(map.get("USER_ID").toString());
        returnjs.accumulate("successover",1);
        return returnjs;
    }
}

