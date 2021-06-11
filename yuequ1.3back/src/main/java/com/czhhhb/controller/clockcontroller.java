package com.czhhhb.controller;

import com.czhhhb.entity.Clock_record;
import com.czhhhb.mapper.Clockmapper;
import com.czhhhb.service.Common_methods;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.crypto.Data;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/clock")
public class clockcontroller {
    @Autowired
    Clockmapper clockmapper;
    @Autowired
    Common_methods common_methods;
    @RequestMapping("/begin")
    public JSONObject clockbegin(@RequestBody JSONObject js)
    {
        Clock_record clock=new Clock_record();
        clock.setUSER_ID(js.getString("USER_ID"));
        clock.setCLOCK_BEGIN_TIME(common_methods.nowdate());
        clock.setCLOCK_STATE(1);
        System.out.println(clock.toString());
        JSONObject returnjs=new JSONObject();
        if(clockmapper.Clock_record_selectByidandstate(clock.getUSER_ID(),1).size()==0)
        {
            clockmapper.Clock_record_add(clock);
            returnjs.accumulate("clockbegin_success",1);
        }
        else
            returnjs.accumulate("clockbegin_success",0);
        System.out.println(returnjs.toString());
        return returnjs;
    }
    @RequestMapping("/over")
    public JSONObject clockover(@RequestBody JSONObject js)
    {
        JSONObject returnjs=new JSONObject();
        List<Map<String, Object>> maps=clockmapper.Clock_record_selectByidandstate(js.getString("USER_ID"),1);
        if(maps.size()==0)
            returnjs.accumulate("success",0);
        else
        {
            Clock_record clock=new Clock_record();
            Map<String, Object> map=maps.get(0);
            map.replace("CLOCK_OVER_TIME",common_methods.nowdate());
            map.replace("CLOCK_BEGIN_TIME",map.get("CLOCK_BEGIN_TIME").toString().replace('T',' '));
            map.replace("CLOCK_STATE",0);
            map.replace("CLOCK_TIME",common_methods.date_subtract(map.get("CLOCK_BEGIN_TIME").toString(),map.get("CLOCK_OVER_TIME").toString()));
            System.out.println("clockcontroller.clockover.map is "+map.toString());
            clock=clockmapper.Clock_record_maps(map);
            clockmapper.Clock_record_updata(clock);
            returnjs.accumulate("clockover_success",1);
        }
        return returnjs;
    }
    @RequestMapping("/history")
    public List<Map<String, Object>> clock_histroy(@RequestBody JSONObject js)
    {
        return clockmapper.Clock_record_histroy(js.getString("USER_ID"));
    }
    @RequestMapping("/state")
    public JSONObject clockhistroy_state(@RequestBody JSONObject js)
    {
        JSONObject returnjs=new JSONObject();
        returnjs.accumulate("state",clockmapper.Clock_record_selectByidandstate(js.get("USER_ID").toString(),1).size());
        return returnjs;
    }
}
